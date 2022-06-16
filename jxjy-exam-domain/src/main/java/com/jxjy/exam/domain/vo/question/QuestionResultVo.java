package com.jxjy.exam.domain.vo.question;

import lombok.Data;

/**
 * @author yyc
 * @version 1.0
 * @date 2022/6/15 14:28
 */
@Data
public class QuestionResultVo {
    /**
     * 考题分页对象
     */
    private PageQuestionVo Result;

    /**
     * 是否成功
     */
    private Boolean IsSuccess;

    /**
     * 请求返回码
     */
    private Integer Code;

    /**
     * 请求是否成功标识
     */
    private Boolean flag;

    /**
     * 错误描述
     */
    private String Msg;
}
