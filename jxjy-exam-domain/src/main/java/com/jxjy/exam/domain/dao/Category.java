package com.jxjy.exam.domain.dao;

import java.util.Date;

/**
 * 类目dao对象
 */
public class Category {
    /**
     * 主键id
     */
    private Long id;

    /**
     * 类目编码
     */
    private Long catId;

    /**
     * 父类目编码
     */
    private Long parentCatId;

    /**
     * 类目排序
     */
    private Long catOrder;

    /**
     * 类目名称（中文）
     */
    private String catName;

    /**
     * 类目名称（英文）
     */
    private String catEnname;

    /**
     * 是否末级类目
     */
    private Integer isEnd;

    /**
     * 更新时间
     */
    private Date updateDate;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 更新人
     */
    private String updateBy;

    /**
     * 是否删除
     */
    private Integer isDelete;

    /**
     * 大数据抽数字段
     */
    private Date dt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCatId() {
        return catId;
    }

    public void setCatId(Long catId) {
        this.catId = catId;
    }

    public Long getParentCatId() {
        return parentCatId;
    }

    public void setParentCatId(Long parentCatId) {
        this.parentCatId = parentCatId;
    }

    public Long getCatOrder() {
        return catOrder;
    }

    public void setCatOrder(Long catOrder) {
        this.catOrder = catOrder;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName == null ? null : catName.trim();
    }

    public String getCatEnname() {
        return catEnname;
    }

    public void setCatEnname(String catEnname) {
        this.catEnname = catEnname == null ? null : catEnname.trim();
    }

    public Integer getIsEnd() {
        return isEnd;
    }

    public void setIsEnd(Integer isEnd) {
        this.isEnd = isEnd;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Date getDt() {
        return dt;
    }

    public void setDt(Date dt) {
        this.dt = dt;
    }
}