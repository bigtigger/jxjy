package com.jxjy.exam.domain.vo.cat;

import lombok.Data;

import java.util.List;

/**
 * @author yyc
 * @version 1.0
 * @date 2022/6/15 11:25
 */
@Data
public class ParentCategoryVo extends CategoryVo {
    /**
     * 子类目列表
     */
    private List<CategoryVo> childs;
}
