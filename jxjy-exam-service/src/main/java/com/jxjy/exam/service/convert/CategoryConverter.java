package com.jxjy.exam.service.convert;

import com.jxjy.exam.common.CommonConst;
import com.jxjy.exam.common.convert.Converter;
import com.jxjy.exam.domain.dao.Category;
import com.jxjy.exam.domain.enums.YesEnum;
import com.jxjy.exam.domain.vo.cat.CategoryVo;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Objects;

/**
 * 类目dao对象转换类
 * @author yyc
 * @version 1.0
 * @date 2022/6/15 20:50
 */
@Component
public class CategoryConverter implements Converter<CategoryVo, Category> {
    @Override
    public Category convert(CategoryVo categoryVo) {
        if(Objects.nonNull(categoryVo)){
            Category category = new Category();
            category.setCatId(categoryVo.getId());
            category.setCatEnname(categoryVo.getEname());
            category.setCatName(categoryVo.getName());
            category.setCatOrder(categoryVo.getOrder());
            category.setCreateBy(CommonConst.SYSTEM);
            category.setCreateDate(new Date());
            category.setIsDelete(YesEnum.NO.getValue());
            category.setParentCatId(categoryVo.getParent_id());
            category.setIsEnd(Objects.equals(categoryVo.getIs_leaf(), Boolean.TRUE) ? YesEnum.YES.getValue() : YesEnum.NO.getValue());
            return category;
        }
        return null;
    }

    @Override
    public CategoryVo reverseConvert(Category s) {
        return null;
    }
}
