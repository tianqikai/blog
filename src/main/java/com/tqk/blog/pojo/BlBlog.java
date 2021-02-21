package com.tqk.blog.pojo;

import java.util.Date;
import javax.persistence.*;

@Table(name = "bl_blog")
public class BlBlog {
    /**
     * 帖子id
     */
    @Id
    @Column(name = "blog_id")
    private String blogId;

    /**
     * 标题
     */
    @Column(name = "blog_title")
    private String blogTitle;

    /**
     * 封面
     */
    @Column(name = "blog_image")
    private String blogImage;

    /**
     * 点赞数
     */
    @Column(name = "blog_goods")
    private Integer blogGoods;

    /**
     * 阅读数
     */
    @Column(name = "blog_read")
    private Integer blogRead;

    /**
     * 收藏数
     */
    @Column(name = "blog_collection")
    private Integer blogCollection;

    /**
     * 博客分类
     */
    @Column(name = "blog_type")
    private Integer blogType;

    /**
     * 简介
     */
    @Column(name = "blog_remark")
    private String blogRemark;

    /**
     * 评论数
     */
    @Column(name = "blog_comment")
    private Integer blogComment;

    /**
     * 文章来源（爬虫时使用）
     */
    @Column(name = "blog_source")
    private String blogSource;

    /**
     * 创建时间
     */
    @Column(name = "created_time")
    private Date createdTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 乐观锁
     */
    private Integer version;

    /**
     * 是否删除，0否1是
     */
    private Integer deleted;

    /**
     * 帖子内容
     */
    @Column(name = "blog_content")
    private String blogContent;

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
     * 获取标题
     *
     * @return blog_title - 标题
     */
    public String getBlogTitle() {
        return blogTitle;
    }

    /**
     * 设置标题
     *
     * @param blogTitle 标题
     */
    public void setBlogTitle(String blogTitle) {
        this.blogTitle = blogTitle;
    }

    /**
     * 获取封面
     *
     * @return blog_image - 封面
     */
    public String getBlogImage() {
        return blogImage;
    }

    /**
     * 设置封面
     *
     * @param blogImage 封面
     */
    public void setBlogImage(String blogImage) {
        this.blogImage = blogImage;
    }

    /**
     * 获取点赞数
     *
     * @return blog_goods - 点赞数
     */
    public Integer getBlogGoods() {
        return blogGoods;
    }

    /**
     * 设置点赞数
     *
     * @param blogGoods 点赞数
     */
    public void setBlogGoods(Integer blogGoods) {
        this.blogGoods = blogGoods;
    }

    /**
     * 获取阅读数
     *
     * @return blog_read - 阅读数
     */
    public Integer getBlogRead() {
        return blogRead;
    }

    /**
     * 设置阅读数
     *
     * @param blogRead 阅读数
     */
    public void setBlogRead(Integer blogRead) {
        this.blogRead = blogRead;
    }

    /**
     * 获取收藏数
     *
     * @return blog_collection - 收藏数
     */
    public Integer getBlogCollection() {
        return blogCollection;
    }

    /**
     * 设置收藏数
     *
     * @param blogCollection 收藏数
     */
    public void setBlogCollection(Integer blogCollection) {
        this.blogCollection = blogCollection;
    }

    /**
     * 获取博客分类
     *
     * @return blog_type - 博客分类
     */
    public Integer getBlogType() {
        return blogType;
    }

    /**
     * 设置博客分类
     *
     * @param blogType 博客分类
     */
    public void setBlogType(Integer blogType) {
        this.blogType = blogType;
    }

    /**
     * 获取简介
     *
     * @return blog_remark - 简介
     */
    public String getBlogRemark() {
        return blogRemark;
    }

    /**
     * 设置简介
     *
     * @param blogRemark 简介
     */
    public void setBlogRemark(String blogRemark) {
        this.blogRemark = blogRemark;
    }

    /**
     * 获取评论数
     *
     * @return blog_comment - 评论数
     */
    public Integer getBlogComment() {
        return blogComment;
    }

    /**
     * 设置评论数
     *
     * @param blogComment 评论数
     */
    public void setBlogComment(Integer blogComment) {
        this.blogComment = blogComment;
    }

    /**
     * 获取文章来源（爬虫时使用）
     *
     * @return blog_source - 文章来源（爬虫时使用）
     */
    public String getBlogSource() {
        return blogSource;
    }

    /**
     * 设置文章来源（爬虫时使用）
     *
     * @param blogSource 文章来源（爬虫时使用）
     */
    public void setBlogSource(String blogSource) {
        this.blogSource = blogSource;
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
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取乐观锁
     *
     * @return version - 乐观锁
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * 设置乐观锁
     *
     * @param version 乐观锁
     */
    public void setVersion(Integer version) {
        this.version = version;
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
     * 获取帖子内容
     *
     * @return blog_content - 帖子内容
     */
    public String getBlogContent() {
        return blogContent;
    }

    /**
     * 设置帖子内容
     *
     * @param blogContent 帖子内容
     */
    public void setBlogContent(String blogContent) {
        this.blogContent = blogContent;
    }
}