package com.tqk.blog.pojo;

import javax.persistence.*;

@Table(name = "bl_admin")
public class BlAdmin {
    /**
     * 管理员id
     */
    @Id
    private Integer id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 头像
     */
    private String header;

    /**
     * 签名
     */
    private String signature;

    /**
     * 介绍
     */
    private String comment;

    /**
     * 账号
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 获取管理员id
     *
     * @return id - 管理员id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置管理员id
     *
     * @param id 管理员id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取姓名
     *
     * @return name - 姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置姓名
     *
     * @param name 姓名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取头像
     *
     * @return header - 头像
     */
    public String getHeader() {
        return header;
    }

    /**
     * 设置头像
     *
     * @param header 头像
     */
    public void setHeader(String header) {
        this.header = header;
    }

    /**
     * 获取签名
     *
     * @return signature - 签名
     */
    public String getSignature() {
        return signature;
    }

    /**
     * 设置签名
     *
     * @param signature 签名
     */
    public void setSignature(String signature) {
        this.signature = signature;
    }

    /**
     * 获取介绍
     *
     * @return comment - 介绍
     */
    public String getComment() {
        return comment;
    }

    /**
     * 设置介绍
     *
     * @param comment 介绍
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * 获取账号
     *
     * @return username - 账号
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置账号
     *
     * @param username 账号
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取密码
     *
     * @return password - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }
}