package com.tqk.blog.pojo;

import lombok.Data;

import javax.persistence.*;

@Table(name = "bl_admin")
@Data
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
     * 旧密码
     */
    private String oldpassword;

}