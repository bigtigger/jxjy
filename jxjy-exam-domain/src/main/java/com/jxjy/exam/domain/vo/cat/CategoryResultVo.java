package com.jxjy.exam.domain.vo.cat;

import lombok.Data;

import java.util.List;

/**
 * @author yyc
 * @version 1.0
 * @date 2022/6/15 11:30
 */
@Data
public class CategoryResultVo {
    /**
     * 请求返回值
     */
    private Integer Code;

    /**
     * 类目对象列表
     */
    private List<ParentCategoryVo> Result;
 }
