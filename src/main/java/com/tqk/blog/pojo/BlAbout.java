package com.tqk.blog.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "bl_about")
public class BlAbout {
    /**
     * 帖子id
     */
    @Id
    @Column(name = "about_id")
    private String aboutId;

    /**
     * 标题
     */
    @Column(name = "about_title")
    private String aboutTitle;

    /**
     * 阅读数
     */
    @Column(name = "about_read")
    private Integer aboutRead;

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
     * 是否启用，0否1是
     */
    private Integer enable;

    /**
     * 是否删除，0否1是
     */
    private Integer deleted;

    /**
     * 帖子内容
     */
    @Column(name = "about_content")
    private String aboutContent;

    /**
     * 获取帖子id
     *
     * @return about_id - 帖子id
     */
    public String getAboutId() {
        return aboutId;
    }

    /**
     * 设置帖子id
     *
     * @param aboutId 帖子id
     */
    public void setAboutId(String aboutId) {
        this.aboutId = aboutId;
    }

    /**
     * 获取标题
     *
     * @return about_title - 标题
     */
    public String getAboutTitle() {
        return aboutTitle;
    }

    /**
     * 设置标题
     *
     * @param aboutTitle 标题
     */
    public void setAboutTitle(String aboutTitle) {
        this.aboutTitle = aboutTitle;
    }

    /**
     * 获取阅读数
     *
     * @return about_read - 阅读数
     */
    public Integer getAboutRead() {
        return aboutRead;
    }

    /**
     * 设置阅读数
     *
     * @param aboutRead 阅读数
     */
    public void setAboutRead(Integer aboutRead) {
        this.aboutRead = aboutRead;
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

    /**
     * 获取帖子内容
     *
     * @return about_content - 帖子内容
     */
    public String getAboutContent() {
        return aboutContent;
    }

    /**
     * 设置帖子内容
     *
     * @param aboutContent 帖子内容
     */
    public void setAboutContent(String aboutContent) {
        this.aboutContent = aboutContent;
    }
}