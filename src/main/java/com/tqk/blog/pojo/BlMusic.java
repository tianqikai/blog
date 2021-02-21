package com.tqk.blog.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "bl_music")
public class BlMusic {
    /**
     * 音乐id
     */
    @Id
    private Integer id;

    /**
     * 歌曲名
     */
    private String name;

    /**
     * 歌手
     */
    private String artist;

    /**
     * 歌曲链接
     */
    private String url;

    /**
     * 封面
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
     * 歌词
     */
    private String lrc;

    /**
     * 获取音乐id
     *
     * @return id - 音乐id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置音乐id
     *
     * @param id 音乐id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取歌曲名
     *
     * @return name - 歌曲名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置歌曲名
     *
     * @param name 歌曲名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取歌手
     *
     * @return artist - 歌手
     */
    public String getArtist() {
        return artist;
    }

    /**
     * 设置歌手
     *
     * @param artist 歌手
     */
    public void setArtist(String artist) {
        this.artist = artist;
    }

    /**
     * 获取歌曲链接
     *
     * @return url - 歌曲链接
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置歌曲链接
     *
     * @param url 歌曲链接
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取封面
     *
     * @return cover - 封面
     */
    public String getCover() {
        return cover;
    }

    /**
     * 设置封面
     *
     * @param cover 封面
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

    /**
     * 获取歌词
     *
     * @return lrc - 歌词
     */
    public String getLrc() {
        return lrc;
    }

    /**
     * 设置歌词
     *
     * @param lrc 歌词
     */
    public void setLrc(String lrc) {
        this.lrc = lrc;
    }
}