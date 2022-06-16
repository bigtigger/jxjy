package com.jxjy.exam.domain.vo.question;

import lombok.Data;

import java.util.List;

/**
 * @author yyc
 * @version 1.0
 * @date 2022/6/15 14:19
 */
@Data
public class PageQuestionVo {
    /**
     * 考题列表
     */
    private List<QuestionVo> list;

    /**
     * 分页信息
     */
    private QuestionModelVo model;
}
