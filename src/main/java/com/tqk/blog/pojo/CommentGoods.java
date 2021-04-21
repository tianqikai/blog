package com.tqk.blog.pojo;

import lombok.Data;
import javax.persistence.*;

/**
 * 评论点赞的实体
 * @Author: tianqikai
 * @Date: 2020/2/16 10:22
 * @Version 1.0
 */
@Data
public class CommentGoods {

    @Id
    private String id;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 评论id
     */
    private String commentId;

}
