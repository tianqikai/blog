package com.tqk.blog.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "bl_file")
public class BlFile {
    /**
     * 文件id
     */
    @Id
    private Integer id;

    /**
     * 文件名
     */
    private String name;

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
     * 文件内容
     */
    private String fileconnet;

    /**
     * 获取文件id
     *
     * @return id - 文件id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置文件id
     *
     * @param id 文件id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取文件名
     *
     * @return name - 文件名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置文件名
     *
     * @param name 文件名
     */
    public void setName(String name) {
        this.name = name;
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
     * 获取文件内容
     *
     * @return fileconnet - 文件内容
     */
    public String getFileconnet() {
        return fileconnet;
    }

    /**
     * 设置文件内容
     *
     * @param fileconnet 文件内容
     */
    public void setFileconnet(String fileconnet) {
        this.fileconnet = fileconnet;
    }
}