package com.jxjy.exam.service.convert;

import com.alibaba.fastjson.JSON;
import com.jxjy.exam.common.CommonConst;
import com.jxjy.exam.common.convert.Converter;
import com.jxjy.exam.domain.dao.Question;
import com.jxjy.exam.domain.enums.YesEnum;
import com.jxjy.exam.domain.vo.question.QuestionVo;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Objects;

/**
 * @author yyc
 * @version 1.0
 * @date 2022/6/16 12:13
 */
@Component
public class QuestionConverter implements Converter<QuestionVo, Question> {
    @Override
    public Question convert(QuestionVo questionVo) {
        if(Objects.nonNull(questionVo)){
            Question question = new Question();
            question.setCatId(questionVo.getDir_id());
            question.setCreateBy(CommonConst.SYSTEM);
            question.setCreateDate(new Date());
            question.setIsDelete(YesEnum.NO.getValue());
            String answer = StringUtils.isEmpty(questionVo.getAnswer()) ? CommonConst.EMPTY_STR : questionVo.getAnswer();
            question.setQuestionAnswer(answer.length() > 5000 ? answer.substring(0, 4996) : answer);
            question.setQuestionId(questionVo.getId());
            String questionTitle = StringUtils.isEmpty(questionVo.getTitle()) ? CommonConst.EMPTY_STR : questionVo.getTitle();
            /**考题索引字段长度为500*/
            question.setKeyTitle(questionTitle.length() > 500 ? questionTitle.substring(0, 496) : questionTitle);
            /**考题字段长度为5000*/
            question.setQuestionTitle(questionTitle.length() > 5000 ? questionTitle.substring(0, 4996) : questionTitle);
            question.setQuestionOptions(CollectionUtils.isEmpty(questionVo.getOptions()) ? CommonConst.EMPTY_STR : JSON.toJSONString(questionVo.getOptions()));
            return question;
        }
        return null;
    }

    @Override
    public QuestionVo reverseConvert(Question s) {
        return null;
    }
}
