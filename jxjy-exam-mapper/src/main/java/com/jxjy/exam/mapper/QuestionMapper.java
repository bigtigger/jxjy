package com.jxjy.exam.mapper;

import com.jxjy.exam.domain.dao.Question;

public interface QuestionMapper {
    /**
     * 根据主键id删除考题
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 新增考题
     * @param record {@link Question}
     * @return
     */
    int insert(Question record);

    /**
     * 根据条件新增考题
     * @param record {@link Question}
     * @return
     */
    int insertSelective(Question record);

    /**
     * 根据主键id查询考题信息
     * @param id
     * @return
     */
    Question selectByPrimaryKey(Long id);

    /**
     * 根据条件更新考题信息
     * @param record {@link Question}
     * @return
     */
    int updateByPrimaryKeySelective(Question record);

    /**
     * 更新考题信息
     * @param record {@link Question}
     * @return
     */
    int updateByPrimaryKey(Question record);
}