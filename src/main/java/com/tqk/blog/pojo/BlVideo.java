package com.tqk.blog.pojo;

import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "bl_video")
@ToString
public class BlVideo {
    /**
     * 视频id
     */
    @Id
    private Integer id;

    /**
     * 视频名
     */
    private String name;

    /**
     * 视频链接
     */
    private String url;

    /**
     * 视频封面
     */
    private String cover;

    /**
     * 创建时间
     */
    @Column(name = "created_time")
    private Date createdTime;

    /**
     * 是否启用，0否1是
     */
    private Integer enable;

    /**
     * 是否删除，0否1是
     */
    private Integer deleted;

    /**
     * 获取视频id
     *
     * @return id - 视频id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置视频id
     *
     * @param id 视频id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取视频名
     *
     * @return name - 视频名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置视频名
     *
     * @param name 视频名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取视频链接
     *
     * @return url - 视频链接
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置视频链接
     *
     * @param url 视频链接
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取视频封面
     *
     * @return cover - 视频封面
     */
    public String getCover() {
        return cover;
    }

    /**
     * 设置视频封面
     *
     * @param cover 视频封面
     */
    public void setCover(String cover) {
        this.cover = cover;
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