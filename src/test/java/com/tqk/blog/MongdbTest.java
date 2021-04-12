package com.tqk.blog;

import com.tqk.blog.dao.BlogGoodsDao;
import com.tqk.blog.dao.CommentDao;
import com.tqk.blog.pojo.BlBlog;
import com.tqk.blog.pojo.BlUser;
import com.tqk.blog.pojo.Comment;
import com.tqk.blog.utils.IdWorker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @program: blog
 * @description:
 * @author: tianqikai
 * @create: 2021-04-11 16:01
 **/
@SpringBootTest
public class MongdbTest {
    @Autowired
    CommentDao commentDao;
    @Autowired
    IdWorker idWorker;
    @Autowired
    BlogGoodsDao blogGoodsDao;

    @Test
    public void testSave() {
        Comment comment = new Comment();
        comment.setId(idWorker.nextId() + "");
        comment.setCommentBlog("123");
        comment.setCommentContent("我是评论1");
        comment.setCommentUser("1378269269464440832");
        comment.setCommentGood(0);
        comment.setCreatedTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        // 插入帖子
        BlBlog blog = new BlBlog();
        blog.setBlogId("123123");
        blog.setBlogTitle("我是帖子");
        comment.setBlog(blog);
        // 插入用户
        BlUser user = new BlUser();
        user.setUserId("123");
        user.setName("鸡哥");
        comment.setUser(user);
        commentDao.save(comment);
    }

    @Test
    public void commentFind(){
        List<Comment> commentList =commentDao.findAll();
        commentList.forEach(item->{
            System.out.println("评论：");
            System.out.println(item);
        });
    }

    @Test
    public void blogCount(){
       int num= blogGoodsDao.countByUserIdAndBlogId("11111","213414wfsavgsdfg");
        System.out.println("点赞次数："+num);
    }

    @Test
    public void
}
