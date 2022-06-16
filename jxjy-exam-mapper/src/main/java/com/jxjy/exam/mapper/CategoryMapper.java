package com.jxjy.exam.mapper;

import com.jxjy.exam.domain.dao.Category;

public interface CategoryMapper {
    /**
     * 根据主键id删除类目
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 新增类目
     * @param record {@link Category}
     * @return
     */
    int insert(Category record);

    /**
     * 根据条件新增类目
     * @param record {@link Category}
     * @return
     */
    int insertSelective(Category record);

    /**
     * 根据主键id查询类目
     * @param id
     * @return
     */
    Category selectByPrimaryKey(Long id);

    /**
     * 根据条件更新类目信息
     * @param record {@link Category}
     * @return
     */
    int updateByPrimaryKeySelective(Category record);

    /**
     * 更新类目信息
     * @param record {@link Category}
     * @return
     */
    int updateByPrimaryKey(Category record);
}