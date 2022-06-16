package com.jxjy.exam.domain.vo.question;

import lombok.Data;

/**
 * @author yyc
 * @version 1.0
 * @date 2022/6/15 11:42
 */
@Data
public class QuestionModelVo {
    /**
     * 标题
     */
    private String title;
    /**
     *
     */
    private String targetName;
    /**
     * 类型
     */
    private Integer type;
    /**
     * 类目id
     */
    private Long tid;
    /**
     * 页数
     */
    private Integer page;
    /**
     * 记录页数
     */
    private Integer recodePage;
    /**
     * 总条数
     */
    private Long total;

    private Long takeTotal;
    /**
     * 总页数
     */
    private Integer totalPage;
    /**
     * 每页条数
     */
    private Integer pageSize;

    private Boolean isTakeNew;

    private Boolean keepr;

    private Boolean isDir;

    private Boolean isSet;

    private Boolean pageAble;
    /**
     * 排序列
     */
    private String order_column;
}
