package com.tqk.blog.pojo;

import lombok.Data;
import javax.persistence.*;

/**
 * 点赞实体
 * @Author: tianqikai
 * @Date: 2020/2/16 10:22
 * @Version 1.0
 */
@Data
public class BlogGoods {

    @Id
    private String id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 博客id
     */
    private String blogId;

}
