package com.jxjy.exam.domain.dao;

import lombok.Data;

import java.util.Date;

/**
 * 用户实体对象
 * @author yusheng
 */
@Data
public class User {

    /**
     * 主键id
     */
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;
    /**
     * 部门id
     */
    private Integer departmentId;
    /**
     * 职位id
     */
    private Integer professionId;

    /**
     * 有效状态
     */
    private Integer yn;

    /**
     * 修改时间
     */
    private Date modified;

    /**
     * 创建时间
     */
    private Date created;

    /**
     * 部门名称
     */
    private String departmentName;

    /**
     * 职位名称
     */
    private String professionName;

}