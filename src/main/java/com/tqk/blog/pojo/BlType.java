package com.tqk.blog.pojo;

import javax.persistence.*;

@Table(name = "bl_type")
public class BlType {
    /**
     * 分类id
     */
    @Id
    @Column(name = "type_id")
    private Integer typeId;

    /**
     * 分类名称
     */
    @Column(name = "type_name")
    private String typeName;

    /**
     * 帖子数
     */
    @Column(name = "type_blog_count")
    private Integer typeBlogCount;

    /**
     * 是否启用，0否1是
     */
    private Integer enable;

    /**
     * 是否删除，0否1是
     */
    private Integer deleted;

    /**
     * 获取分类id
     *
     * @return type_id - 分类id
     */
    public Integer getTypeId() {

        return typeId;
    }

    /**
     * 设置分类id
     *
     * @param typeId 分类id
     */
    public void setTypeId(Integer typeId) {

        this.typeId = typeId;
    }

    /**
     * 获取分类名称
     *
     * @return type_name - 分类名称
     */
    public String getTypeName() {

        return typeName;
    }

    /**
     * 设置分类名称
     *
     * @param typeName 分类名称
     */
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    /**
     * 获取帖子数
     *
     * @return type_blog_count - 帖子数
     */
    public Integer getTypeBlogCount() {
        return typeBlogCount;
    }

    /**
     * 设置帖子数
     *
     * @param typeBlogCount 帖子数
     */
    public void setTypeBlogCount(Integer typeBlogCount) {
        this.typeBlogCount = typeBlogCount;
    }

    /**
     * 获取是否启用，0否1是
     *
     * @return enable - 是否启用，0否1是
     */
    public Integer getEnable() {
        return enable;
    }

    /**
     * 设置是否启用，0否1是
     *
     * @param enable 是否启用，0否1是
     */
    public void setEnable(Integer enable) {
        this.enable = enable;
    }

    /**
     * 获取是否删除，0否1是
     *
     * @return deleted - 是否删除，0否1是
     */
    public Integer getDeleted() {
        return deleted;
    }

    /**
     * 设置是否删除，0否1是
     *
     * @param deleted 是否删除，0否1是
     */
    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }
}