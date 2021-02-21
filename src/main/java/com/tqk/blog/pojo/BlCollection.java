package com.tqk.blog.pojo;

import java.util.Date;
import javax.persistence.*;

@Table(name = "bl_collection")
public class BlCollection {
    /**
     * 收藏id
     */
    @Id
    @Column(name = "collection_id")
    private Integer collectionId;

    /**
     * 帖子id
     */
    @Column(name = "blog_id")
    private String blogId;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 收藏时间
     */
    @Column(name = "collection_time")
    private Date collectionTime;

    /**
     * 获取收藏id
     *
     * @return collection_id - 收藏id
     */
    public Integer getCollectionId() {
        return collectionId;
    }

    /**
     * 设置收藏id
     *
     * @param collectionId 收藏id
     */
    public void setCollectionId(Integer collectionId) {
        this.collectionId = collectionId;
    }

    /**
     * 获取帖子id
     *
     * @return blog_id - 帖子id
     */
    public String getBlogId() {
        return blogId;
    }

    /**
     * 设置帖子id
     *
     * @param blogId 帖子id
     */
    public void setBlogId(String blogId) {
        this.blogId = blogId;
    }

    /**
     * 获取用户id
     *
     * @return user_id - 用户id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置用户id
     *
     * @param userId 用户id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取收藏时间
     *
     * @return collection_time - 收藏时间
     */
    public Date getCollectionTime() {
        return collectionTime;
    }

    /**
     * 设置收藏时间
     *
     * @param collectionTime 收藏时间
     */
    public void setCollectionTime(Date collectionTime) {
        this.collectionTime = collectionTime;
    }
}