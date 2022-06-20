package com.jxjy.exam.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.rholder.retry.Retryer;
import com.github.rholder.retry.RetryerBuilder;
import com.github.rholder.retry.StopStrategies;
import com.github.rholder.retry.WaitStrategies;
import com.jxjy.exam.common.CommonConst;
import com.jxjy.exam.common.convert.JxjyMapUtils;
import com.jxjy.exam.common.exception.BizException;
import com.jxjy.exam.common.exception.IllegalParamException;
import com.jxjy.exam.common.httpclient.HttpClientUtils;
import com.jxjy.exam.domain.dao.Category;
import com.jxjy.exam.domain.dao.Question;
import com.jxjy.exam.domain.vo.question.QuestionResultVo;
import com.jxjy.exam.mapper.QuestionMapper;
import com.jxjy.exam.service.AbstractTokenService;
import com.jxjy.exam.service.CategoryService;
import com.jxjy.exam.service.QuestionService;
import com.jxjy.exam.service.convert.QuestionConverter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * @author yyc
 * @version 1.0
 * @date 2022/6/16 11:08
 */
@Service
@Slf4j
public class QuestionServiceImpl extends AbstractTokenService implements QuestionService {

    @Autowired
    private QuestionConverter questionConverter;

    @Resource
    private QuestionMapper questionMapper;

    @Autowired
    private CategoryService categoryService;

    //定义guava重试机制
    private static Retryer<Integer> retryer = RetryerBuilder.<Integer>newBuilder()
            //设置异常重试源
            .retryIfExceptionOfType(IllegalParamException.class)
            //设置等待间隔时间
            .withWaitStrategy(WaitStrategies.incrementingWait(2, TimeUnit.SECONDS, 1, TimeUnit.SECONDS))
            //设置最大重试次数
            .withStopStrategy(StopStrategies.stopAfterAttempt(3))
            .build();

    /**
     * 考题url
     */
    private static final String question_url = "https://www.juhaisoft.net/cloud/exam/practise/getpage3?page=%s&isTakeNew=false&keepr=true&tid=%s&type=1&dir_id=";
    /**
     * referer_url
     */
    private static final String question_referer_url = "https://www.juhaisoft.net/cloud/exam/practise/practise3?tid=%s&keepr=true&type=1";

    @Override
    public void scrapyQuestions(List<Long> catIds) {
        ListUtils.emptyIfNull(catIds)
                .stream()
                .filter(Objects::nonNull)
                .forEach(catId -> {
                    try{
                        scrapyQuestions(catId);
                    }catch (Exception e){
                        log.error("QuestionServiceImpl.scrapyQuestions by category error,catId->{}", catId);
                    }
                });
    }

    /**
     * 根据类目id爬取考题
     * @param catId 类目id
     */
    private void scrapyQuestions(Long catId) throws InterruptedException {
        Integer pageNo = 1;
        Integer totalPage = scrapyCallable(catId, pageNo);
        while (totalPage > pageNo){
            pageNo ++;
            /**先睡一小会儿*/
            Thread.sleep(2000 + (int) (Math.random() * 1000));
            Integer num = scrapyCallable(catId, pageNo);
            if(CommonConst.ZERO.equals(num) || CommonConst.NEGATIVE_ONE.equals(num)){
                log.warn("QuestionServiceImpl.scrapyQuestions cycle is end,catId->{},pageNo->{}", catId, pageNo);
                break;
            }
        }
    }

    /**
     * 通过guava-retry重试爬取考题
     * @param catId 类目id
     * @param pageNo 当前页数
     * @return
     */
    private Integer scrapyCallable(Long catId, Integer pageNo){
        Callable<Integer> callable = () -> {
            return scrapyQuestionsRetry(catId, pageNo);
        };
        try {
            return retryer.call(callable);
        }catch (Exception e){
//            log.warn("scrapy question by guava retry error,catId->{},pageNo->{}", catId, pageNo);
            throw new BizException("scrapy question by guava retry error,catId->" + catId + ",pageNo->" + pageNo);
        }
    }



    /**
     * 根据类目id以及分页数爬取考题
     * @param catId 类目id
     * @param pageNo 分页数
     * @return
     */
    @Override
    @Retryable(value= {IllegalParamException.class},maxAttempts = 3,backoff = @Backoff(delay = 2000L, multiplier = 1.2))
    public Integer scrapyQuestionsRetry(Long catId, Integer pageNo) {
        try {
            String url = String.format(question_url, pageNo, catId);
            String result = HttpClientUtils.doPost(url, getHeaders(String.format(question_referer_url, catId)), null, CommonConst.TIME_OUT);
            if(StringUtils.isEmpty(result)){
                throw new IllegalParamException("retry to query questions,catId->" + catId + ",pageNo->" + pageNo);
            }
            QuestionResultVo questionResultVo = JSON.parseObject(result, QuestionResultVo.class);
            if(Objects.isNull(questionResultVo) || Objects.isNull(questionResultVo.getResult()) || Objects.isNull(questionResultVo.getResult().getModel())){
//                log.error("QuestionServiceImpl.scrapyQuestions JSON.parseObject to QuestionResultVo error,url->{},result->{}", url, result);
                throw new IllegalParamException("retry to query questions,catId->" + catId + ",pageNo->" + pageNo);
            }
            if(CollectionUtils.isEmpty(questionResultVo.getResult().getList())){
                return CommonConst.NEGATIVE_ONE;
            }
            List<Question> questions = questionConverter.convertToList(questionResultVo.getResult().getList());
            ListUtils.emptyIfNull(questions)
                    .stream()
                    .filter(Objects::nonNull)
                    .forEach(question -> {
                        try {
                            questionMapper.insertSelective(question);
                        }catch (Exception e){
//                            log.warn("inset to db error,question->{}", JSON.toJSONString(question));
                        }
                    });
            return questionResultVo.getResult().getModel().getTotalPage();
        }catch (IllegalParamException ie){
//            log.error("retry to scrapy questions error,catId->" + catId + ",pageNo->" + pageNo);
            throw new IllegalParamException("retry to query questions,catId->" + catId + ",pageNo->" + pageNo);
        } catch (Exception e){
//            log.error("scrapy Questions error,catId->{},pageNo->{}", catId, pageNo);
            throw new BizException("scrapy Questions error,catId->" + catId + ",pageNo->" + pageNo);
        }

    }

    @Override
    public void scrapByScanCategory(Long beginId, Long endId) {
        Long id = beginId;
        while (id <= endId){
            try{
                log.info("scrapy scan category id->{}", id);
                List<Category> categories = categoryService.scanById(id);
                List<Long> catIds = JxjyMapUtils.toOtherList(categories, Category::getCatId);
                if(CollectionUtils.isEmpty(catIds)){
                    log.warn("scrapy scan category is empty,end to scan,id->{}", id);
                    break;
                }
                scrapyQuestions(catIds);
                categories.sort((x, y) -> Long.compare(y.getId(), x.getId()));
                id = categories.get(0).getId();
            }catch (Exception e){
                log.error("scrapy scan category error,id->{}", id);
            }
        }
    }
}
