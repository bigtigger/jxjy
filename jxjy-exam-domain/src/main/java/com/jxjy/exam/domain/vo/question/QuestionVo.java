package com.jxjy.exam.domain.vo.question;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author yyc
 * @version 1.0
 * @date 2022/6/15 11:53
 */
@Data
public class QuestionVo {

    /**
     * 考题主键id
     */
    private Long id;
    /**
     * 考题名称
     */
    private String title;

    /**
     * 考题编码
     */
    private Long qid;

    private Integer vtype;
    /**
     * 考题编码
     */
    private Long question_id;

    private Long count;

    private Long type_id;

    private Long ts_id;

    /**
     * 类目编码
     */
    private Long dir_id;

    /**
     * 考题答案
     */
    private String answer;

    private BigDecimal difficulty;

    /**
     * 考题难度类型
     */
    private Integer difficulty_value;

    /**
     * 考题难度描述
     */
    private String difficulty_format;

    private Boolean is_tm;

    private Boolean is_like;

    /**
     * 考题选项
     */
    private List<String> options;

    private String options_str;

}
