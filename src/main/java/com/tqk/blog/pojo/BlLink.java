package com.tqk.blog.pojo;

import java.util.Date;
import javax.persistence.*;

@Table(name = "bl_link")
public class BlLink {
    /**
     * 链接id
     */
    @Id
    @Column(name = "link_id")
    private Integer linkId;

    /**
     * 名称
     */
    @Column(name = "link_name")
    private String linkName;

    /**
     * 链接地址
     */
    @Column(name = "link_url")
    private String linkUrl;

    /**
     * 创建时间
     */
    @Column(name = "created_time")
    private Date createdTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 乐观锁
     */
    private Integer version;

    /**
     * 是否删除，0否1是
     */
    private Integer deleted;

    /**
     * 获取链接id
     *
     * @return link_id - 链接id
     */
    public Integer getLinkId() {
        return linkId;
    }

    /**
     * 设置链接id
     *
     * @param linkId 链接id
     */
    public void setLinkId(Integer linkId) {
        this.linkId = linkId;
    }

    /**
     * 获取名称
     *
     * @return link_name - 名称
     */
    public String getLinkName() {
        return linkName;
    }

    /**
     * 设置名称
     *
     * @param linkName 名称
     */
    public void setLinkName(String linkName) {
        this.linkName = linkName;
    }

    /**
     * 获取链接地址
     *
     * @return link_url - 链接地址
     */
    public String getLinkUrl() {
        return linkUrl;
    }

    /**
     * 设置链接地址
     *
     * @param linkUrl 链接地址
     */
    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    /**
     * 获取创建时间
     *
     * @return created_time - 创建时间
     */
    public Date getCreatedTime() {
        return createdTime;
    }

    /**
     * 设置创建时间
     *
     * @param createdTime 创建时间
     */
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取乐观锁
     *
     * @return version - 乐观锁
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * 设置乐观锁
     *
     * @param version 乐观锁
     */
    public void setVersion(Integer version) {
        this.version = version;
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