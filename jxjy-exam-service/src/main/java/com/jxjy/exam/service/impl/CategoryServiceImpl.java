package com.jxjy.exam.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.rholder.retry.Retryer;
import com.github.rholder.retry.RetryerBuilder;
import com.github.rholder.retry.StopStrategies;
import com.github.rholder.retry.WaitStrategies;
import com.jxjy.exam.common.CommonConst;
import com.jxjy.exam.common.exception.BizException;
import com.jxjy.exam.common.exception.IllegalParamException;
import com.jxjy.exam.common.httpclient.HttpClientUtils;
import com.jxjy.exam.domain.dao.Category;
import com.jxjy.exam.domain.vo.cat.CategoryResultVo;
import com.jxjy.exam.domain.vo.cat.CategoryVo;
import com.jxjy.exam.domain.vo.cat.ParentCategoryVo;
import com.jxjy.exam.mapper.CategoryMapper;
import com.jxjy.exam.service.AbstractTokenService;
import com.jxjy.exam.service.CategoryService;
import com.jxjy.exam.service.convert.CategoryConverter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * @author yyc
 * @version 1.0
 * @date 2022/6/15 20:21
 */
@Service
@Slf4j
public class CategoryServiceImpl extends AbstractTokenService implements CategoryService {

    @Autowired
    private CategoryConverter categoryConverter;

    @Resource
    private CategoryMapper categoryMapper;

    //定义guava重试机制
    private static Retryer<CategoryResultVo> retryer = RetryerBuilder.<CategoryResultVo>newBuilder()
            //设置异常重试源
            .retryIfExceptionOfType(IllegalParamException.class)
            //设置等待间隔时间
            .withWaitStrategy(WaitStrategies.incrementingWait(2, TimeUnit.SECONDS, 1, TimeUnit.SECONDS))
            //设置最大重试次数
            .withStopStrategy(StopStrategies.stopAfterAttempt(3))
            .build();

    /**
     * 类目url
     */
    private static final String cat_url = "https://www.juhaisoft.net/cloud/exam/practise/getdirs2?pid=%s";

    private static final String category_referer_url = "https://www.juhaisoft.net/cloud/exam/practise";

    @Override
    public void scrapyCategories(Long catId) {
        try {
            String url = String.format(cat_url, catId);
            CategoryResultVo categoryResultVo = scrapyByRetry(url, catId);
            for(ParentCategoryVo parentCategoryVo : categoryResultVo.getResult()){
                Category category = categoryConverter.convert(parentCategoryVo);
                if(Objects.isNull(category)){
                    log.error("CategoryServiceImpl.scrapyCategories parentCategoryVo is null,parentCategoryVo->{}", JSON.toJSONString(parentCategoryVo));
                    continue;
                }
                saveCategory(category);
                if(CollectionUtils.isEmpty(parentCategoryVo.getChilds())){
                    continue;
                }
                for(CategoryVo categoryVo : parentCategoryVo.getChilds()){
                    Category childCategory = categoryConverter.convert(categoryVo);
                    if(Objects.isNull(childCategory)){
                        log.error("CategoryServiceImpl.scrapyCategories childCategory is null,childCategory->{}", JSON.toJSONString(categoryVo));
                        continue;
                    }
                    saveCategory(childCategory);
                    if(Objects.equals(categoryVo.getIs_leaf(), Boolean.FALSE)){
                        /**先睡一小会儿*/
                        Thread.sleep(1000 + (int) (Math.random() * 1000));
                        scrapyCategories(categoryVo.getId());
                    }
                }
            }
        }catch (Exception e){
            log.error("scrapy category error,catId->{}", catId, e);
        }
    }

    /**
     * 保持类目信息
     * @param category
     */
    private void saveCategory(Category category) {
        try{
            categoryMapper.insertSelective(category);
        }catch (Exception e){
            log.warn("类目编码已存在---->{}", category.getCatId());
        }
    }

    /**
     * 通过guava-retry抓取类目数据
     * @param url 请求url
     * @param catId 类目id
     * @return
     */
    private CategoryResultVo scrapyByRetry(String url, Long catId){
        Callable<CategoryResultVo> callable = () -> {
            String result = HttpClientUtils.doPost(url, getHeaders(category_referer_url), null, CommonConst.TIME_OUT);
            if(StringUtils.isEmpty(result)){
                throw new IllegalParamException("retry to scrapy category,catId->" + catId);
            }
            CategoryResultVo categoryResultVo = JSON.parseObject(result, CategoryResultVo.class);
            if(Objects.isNull(categoryResultVo) || CollectionUtils.isEmpty(categoryResultVo.getResult())){
                throw new IllegalParamException("retry to scrapy category,catId->" + catId);
            }
            return categoryResultVo;
        };
        try {
            return retryer.call(callable);
        }catch (Exception e){
            log.warn("scrapy category by guava retry error,catId->{}", catId);
            throw new BizException("scrapy category by guava retry error,catId->" + catId);
        }
    }

    /**
     * 抓取指定类目信息
     * @param catId 类目id
     */
    @Override
    public void scrapyCategoryByRetry(Long catId) {
        try {
            String url = String.format(cat_url, catId);
            CategoryResultVo categoryResultVo = scrapyByRetry(url, catId);
            for(ParentCategoryVo parentCategoryVo : categoryResultVo.getResult()){
                Category category = categoryConverter.convert(parentCategoryVo);
                if(Objects.isNull(category)){
                    log.error("CategoryServiceImpl.scrapyCategories parentCategoryVo is null,parentCategoryVo->{}", JSON.toJSONString(parentCategoryVo));
                    continue;
                }
                saveCategory(category);
                if(CollectionUtils.isEmpty(parentCategoryVo.getChilds())){
                    continue;
                }
                for(CategoryVo categoryVo : parentCategoryVo.getChilds()){
                    Category childCategory = categoryConverter.convert(categoryVo);
                    if(Objects.isNull(childCategory)){
                        log.error("CategoryServiceImpl.scrapyCategories childCategory is null,childCategory->{}", JSON.toJSONString(categoryVo));
                        continue;
                    }
                    saveCategory(childCategory);
                }
            }
        }catch (Exception e){
            log.error("scrapy category error,catId->{}", catId);
        }
    }

    @Override
    public List<Category> scanById(Long id) {
        return categoryMapper.selectByScan(id);
    }


}
