package com.tqk.blog.controller;

import lombok.Data;

/**
 * @program: blog
 * @description:
 * @author: tianqikai
 * @create: 2020-11-15 22:22
 **/
@Data
public class BlAdminRequest {
    /**
     * 账号
     */
    private String username;

    /**
     * 密码
     */
    private String password;
}
