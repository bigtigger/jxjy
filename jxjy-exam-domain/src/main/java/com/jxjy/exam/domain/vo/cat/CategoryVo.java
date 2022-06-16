package com.jxjy.exam.domain.vo.cat;

import lombok.Data;

/**
 * 前端解析的类目对象
 * @author yyc
 * @version 1.0
 * @date 2022/6/15 11:02
 */
@Data
public class CategoryVo {
    /**
     * 类目id
     */
    private Long id;
    /**
     * 排序
     */
    private Long order;
    /**
     * 类目名称（中文）
     */
    private String name;
    /**
     * 父类目id
     */
    private Long parent_id;
    /**
     * 类目名称（英文）
     */
    private String ename;
    /**
     * 是否末级类目
     */
    private Boolean is_leaf;
}
