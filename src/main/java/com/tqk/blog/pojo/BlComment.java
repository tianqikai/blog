package com.tqk.blog.pojo;

import java.util.Date;
import javax.persistence.*;

@Table(name = "bl_comment")
public class BlComment {
    /**
     * 评论id
     */
    @Id
    @Column(name = "comment_id")
    private String commentId;

    /**
     * 评价人
     */
    @Column(name = "comment_user")
    private Integer commentUser;

    /**
     * 评论帖子id
     */
    @Column(name = "comment_blog")
    private String commentBlog;

    /**
     * 点赞数
     */
    @Column(name = "comment_good")
    private Integer commentGood;

    /**
     * 评论时间
     */
    @Column(name = "created_time")
    private Date createdTime;

    /**
     * 是否删除，0否1是
     */
    private Integer deleted;

    /**
     * 评论内容
     */
    @Column(name = "comment_content")
    private String commentContent;

    /**
     * 获取评论id
     *
     * @return comment_id - 评论id
     */
    public String getCommentId() {
        return commentId;
    }

    /**
     * 设置评论id
     *
     * @param commentId 评论id
     */
    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    /**
     * 获取评价人
     *
     * @return comment_user - 评价人
     */
    public Integer getCommentUser() {
        return commentUser;
    }

    /**
     * 设置评价人
     *
     * @param commentUser 评价人
     */
    public void setCommentUser(Integer commentUser) {
        this.commentUser = commentUser;
    }

    /**
     * 获取评论帖子id
     *
     * @return comment_blog - 评论帖子id
     */
    public String getCommentBlog() {
        return commentBlog;
    }

    /**
     * 设置评论帖子id
     *
     * @param commentBlog 评论帖子id
     */
    public void setCommentBlog(String commentBlog) {
        this.commentBlog = commentBlog;
    }

    /**
     * 获取点赞数
     *
     * @return comment_good - 点赞数
     */
    public Integer getCommentGood() {
        return commentGood;
    }

    /**
     * 设置点赞数
     *
     * @param commentGood 点赞数
     */
    public void setCommentGood(Integer commentGood) {
        this.commentGood = commentGood;
    }

    /**
     * 获取评论时间
     *
     * @return created_time - 评论时间
     */
    public Date getCreatedTime() {
        return createdTime;
    }

    /**
     * 设置评论时间
     *
     * @param createdTime 评论时间
     */
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
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
     * 获取评论内容
     *
     * @return comment_content - 评论内容
     */
    public String getCommentContent() {
        return commentContent;
    }

    /**
     * 设置评论内容
     *
     * @param commentContent 评论内容
     */
    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }
}