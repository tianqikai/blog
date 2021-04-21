package com.tqk.blog.service.impl;

import com.tqk.blog.dao.CommentDao;
import com.tqk.blog.dao.CommentGoodsDao;
import com.tqk.blog.mapper.BlBlogMapper;
import com.tqk.blog.mapper.BlUserMapper;
import com.tqk.blog.pojo.BlBlog;
import com.tqk.blog.pojo.BlUser;
import com.tqk.blog.pojo.Comment;
import com.tqk.blog.pojo.CommentGoods;
import com.tqk.blog.service.CommentService;
import com.tqk.blog.utils.IdWorker;
import com.tqk.blog.utils.Page;
import com.tqk.blog.utils.ShiroUtils;
import com.tqk.blog.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @classname : CommentServiceImpl
 * @description:  评论表服务实现类
 * @author: tianqikai
 * @date : 2021/4/12 21:31
 */
@Service
@Slf4j
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao commentDao;
    @Autowired
    private IdWorker idWorker;
    @Autowired
    private BlUserMapper userMapper;
    @Autowired
    private BlBlogMapper blogMapper;
    @Autowired
    private CommentGoodsDao commentGoodsDao;
    /**
     * 保存--当前用户评论
     * @param comment
     * @return
     */
    @Override
    public void save(Comment comment) {
        // 评论数+1
        // 查询博客
        BlBlog blog = blogMapper.getById(comment.getCommentBlog());
        blog.setBlogComment(blog.getBlogComment() + 1);
        blogMapper.update(blog);
        comment.setBlog(blog);
        comment.setId(idWorker.nextId() + "");
        comment.setCommentGood(0);
        comment.setCreatedTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        // 查询用户
        BlUser user = userMapper.getById(comment.getCommentUser());
        comment.setUser(user);
        commentDao.save(comment);
    }
    /**
     * 查询当前博客的评论
     * @param blogId
     * @return
     */
    @Override
    public List<Comment> getByBlog(String blogId) {
        // 查询博客评论
        List<Comment> commentList = commentDao.findByCommentBlogOrderByCreatedTimeDescCommentGoodDesc(blogId);
        // 查询点赞情况
        // 取出评论id
        List<String> commentIds = commentList.stream().map(Comment::getId).collect(Collectors.toList());
        List<CommentGoods> commentGoodsList = commentGoodsDao.findByCommentIdIn(commentIds);
        // 遍历去封装评论情况
        commentList.forEach(e -> {
            commentGoodsList.forEach(good -> {
                if (e.getId().equals(good.getCommentId())) {
                    // 匹配到了评论记录
                    e.setCommentFlag(true);
                }
            });
        });
        return commentList;
    }

    @Override
    public void deleteById(String id) {
        commentDao.deleteById(id);
    }

    /**
     * 给博客点赞
     * @param commentGoods
     * @return
     */
    @Override
    public void goodByCommentIdAndUser(CommentGoods commentGoods) {
        BlUser user = (BlUser) ShiroUtils.getLoginUser();
        commentGoods.setUserId(user.getUserId());
        // 取出评论id，点赞数+1
        String commentId = commentGoods.getCommentId();
        Comment comment = commentDao.findById(commentId).get();
        comment.setCommentGood(comment.getCommentGood() + 1);
        commentDao.save(comment);
        try {
            commentGoods.setId(idWorker.nextId() + "");
            commentGoodsDao.save(commentGoods);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    /**
     * @Method： getGoodsCount
     * @Description： 获取点赞数量
     * @Date： 2021/4/7 20:09
     * @Author： Administrator
     * @Version  1.0
     */
    @Override
    public int getGoodsCount(String commentId) {
        BlUser user = (BlUser) ShiroUtils.getLoginUser();
        int count = commentGoodsDao.countByUserIdAndCommentId(user.getUserId(), commentId);
        return count;
    }
    /**
     * 后台分页查询
     * @param page
     * @return
     */
    @Override
    public Page<Comment> getByPage(Page<Comment> page) {
        BlUser user = (BlUser) ShiroUtils.getLoginUser();
        Comment comment = new Comment();
        comment.setCommentUser(user.getUserId());
        Example<Comment> example = Example.of(comment);
        Pageable pageable = PageRequest.of(page.getCurrentPage() - 1, page.getPageSize());
        org.springframework.data.domain.Page<Comment> p = commentDao.findAll(example, pageable);
        // 封装总页数、总条数、数据
        page.setTotalCount((int)p.getTotalElements());
        page.setTotalPage(p.getTotalPages());
        page.setList(p.getContent());
        return page;
    }

    @Override
    public Page<Comment> getByPageBack(Page<Comment> page) {
        Comment comment = new Comment();
        String blogTitle = (String) page.getParams().get("blogTitle");
        if(StringUtils.isBlank(blogTitle)) {
            blogTitle = "";
        }
        String nickname = (String) page.getParams().get("nickname");
        if(StringUtils.isBlank(nickname)) {
            nickname = "";
        }
        BlBlog blog = new BlBlog();
        blog.setBlogTitle(blogTitle);
        comment.setBlog(blog);
        BlUser user = new BlUser();
        user.setNickname(nickname);
        comment.setUser(user);
        Pageable pageable = PageRequest.of(page.getCurrentPage() - 1, page.getPageSize());
        org.springframework.data.domain.Page<Comment> p = commentDao.getByBlogTitleAndNickname(comment, pageable);
        // 封装总页数、总条数、数据
        page.setTotalCount((int)p.getTotalElements());
        page.setTotalPage(p.getTotalPages());
        page.setList(p.getContent());
        return page;
    }
}
