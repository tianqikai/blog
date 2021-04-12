package com.tqk.blog.pojo;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;

/**
 * <p>
 * 收藏博客实体类
 * </p>
 *
 * @author tianqikai
 * @date 2020-02-07 14:04:12
 * @Version 1.0
 *
 */
@Data
public class BlogCollection implements Serializable {

    private static final long serialVersionUID = -535915810554536111L;

    /**
     * 收藏id
     */
    @Id
    private String collectionId;

    /**
     * 帖子id
     */
    private String blogId;

    private BlBlog blog;

    /**
     * 用户id
     */
    private String userId;

    private BlUser user;

    /**
     * 收藏时间
     */
    private String collectionTime;

}
