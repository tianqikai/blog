package com.tqk.blog.service.impl;

import com.tqk.blog.mapper.BlBlogMapper;
import com.tqk.blog.mapper.BlTypeMapper;
import com.tqk.blog.pojo.BlBlog;
import com.tqk.blog.pojo.BlType;
import com.tqk.blog.service.BlogService;
import com.tqk.blog.utils.IdWorker;
import com.tqk.blog.utils.Page;
import com.tqk.blog.vo.BlogVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 博客表服务实现类
 * </p>
 *
 * @author tianqikai
 * @date 2020年11月5日00:02:18
 * @Version 1.0
 */
@Service
@Slf4j
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlBlogMapper blogMapper;
    @Autowired
    private BlTypeMapper typeMapper;
//    @Autowired
//    private BlBlogGoodsDao blogGoodsDao;
//    @Autowired
//    private BlCollectionDao collectionDao;
    @Autowired
    private IdWorker idWorker;

    /**
     * 在项目中，@Transactional(rollbackFor=Exception.class)，如果类加了这个注解，那么这个类里面的方法抛出异常，就会回滚，数据库里面的数据也会回滚。
     * 在@Transactional注解中如果不配置rollbackFor属性,那么事物只会在遇到RuntimeException的时候才会回滚,加上rollbackFor=Exception.class,可以让事物在遇到非运行时异常时也回滚
     * @param blog
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(BlBlog blog) {
        blog.setBlogId(idWorker.nextId() + "");
        blogMapper.save(blog);
        // 取出分类，当前分类博客数+1
        Integer blogType = blog.getBlogType();
        BlType type = typeMapper.selectByPrimaryKey(blogType);
        type.setTypeBlogCount(type.getTypeBlogCount() + 1);
        typeMapper.updateByPrimaryKey(type);
    }

    @Override
    public BlBlog getById(String id) {
        return blogMapper.getById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(BlBlog blog) {
        // 修改之前先进行查询
        BlBlog oldBlog = blogMapper.getById(blog.getBlogId());
        blogMapper.update(blog);
        // 判断分类有没有被修改，如果被修改了，旧的分类博客数-1，新的分类博客数+1
        Integer oldTypeId = oldBlog.getBlogType();
        Integer nowTypeId = blog.getBlogType();
        if (!oldTypeId.equals(nowTypeId)) {
            BlType oldType = typeMapper.selectByPrimaryKey(oldTypeId);
            oldType.setTypeBlogCount(oldType.getTypeBlogCount() - 1);
            typeMapper.updateByPrimaryKey(oldType);

            BlType nowType = typeMapper.selectByPrimaryKey(nowTypeId);
            nowType.setTypeBlogCount(nowType.getTypeBlogCount() + 1);
            typeMapper.updateByPrimaryKey(nowType);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BlogVo readById(String id) {
        BlBlog blog = blogMapper.getById(id);
        // 阅读，需要更新阅读数
        blog.setBlogRead(blog.getBlogRead() + 1);
        blogMapper.update(blog);
        // 将blog转为blogVo
        BlogVo blogVo = new BlogVo();
        /**
         * BeanUtils.copyProperties(a, b);
         * b中的存在的属性，a中一定要有，但是a中可以有多余的属性；
         * a中与b中相同的属性都会被替换，不管是否有值；
         * a、 b中的属性要名字相同，才能被赋值，不然的话需要手动赋值；
         */
        BeanUtils.copyProperties(blog, blogVo);
        // 查询分类
        BlType type = typeMapper.selectByPrimaryKey(blog.getBlogType());
        blogVo.setTypeName(type.getTypeName());
        return blogVo;
    }

    @Override
    public void deleteById(String id) {
        blogMapper.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Page<BlogVo> getByPage(Page<BlogVo> page) {
        System.out.println("page:");
        System.out.println(page.toString());
        // 查询数据
        List<BlogVo> blogVoList = blogMapper.getByPage(page);
        page.setList(blogVoList);
        // 查询总数
        int totalCount = blogMapper.getCountByPage(page);
        page.setTotalCount(totalCount);
        return page;
    }

    @Override
    public List<BlogVo> recomRead() {
        return blogMapper.recomRead();
    }

    @Override
    public List<BlogVo> getTimeLine() {
        return blogMapper.getTimeLine();
    }

//    @Override
//    public void goodByBlogAndUser(BlogGoods blogGoods) {
//        BlUser user = (BlUser) ShiroUtils.getLoginUser();
//        blogGoods.setUserId(user.getUserId());
//        // 取出博客id，点赞数+1
//        String blogId = blogGoods.getBlogId();
//        blogMapper.updateGoods(blogId);
//        try {
//            blogGoods.setId(idWorker.nextId() + "");
//            blogGoodsDao.save(blogGoods);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public int getGoodsCount(String blogId) {
//        BlUser user = (BlUser) ShiroUtils.getLoginUser();
//        int count = blogGoodsDao.countByUserIdAndBlogId(user.getUserId(), blogId);
//        return count;
//    }

//    @Override
//    public void collectionByBlogId(BlogCollection blogCollection) {
//        BlUser user = (BlUser) ShiroUtils.getLoginUser();
//        blogCollection.setUserId(user.getUserId());
//        blogCollection.setUser(user);
//        // 查询博客
//        BlBlog blog = blogMapper.getById(blogCollection.getBlogId());
//        blog.setBlogContent(null);
//        blogCollection.setBlog(blog);
//
//        blog.setBlogCollection(blog.getBlogCollection() + 1);
//        blogMapper.update(blog);
//        try {
//            blogCollection.setCollectionId(idWorker.nextId() + "");
//            collectionDao.save(blogCollection);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

//    @Override
//    public int getCollectionCount(String blogId) {
//        BlUser user = (BlUser) ShiroUtils.getLoginUser();
//        int count = collectionDao.countByBlogIdAndUserId(blogId, user.getUserId());
//        return count;
//    }
//
//    @Override
//    public Page<BlogCollection> getCollectionByPage(Page<BlogCollection> page) {
//        BlUser user = (BlUser) ShiroUtils.getLoginUser();
//        BlogCollection blogCollection = new BlogCollection();
//        blogCollection.setUserId(user.getUserId());
//        Example example = Example.of(blogCollection);
//        Pageable pageable = PageRequest.of(page.getCurrentPage() - 1, page.getPageSize());
//        org.springframework.data.domain.Page p = collectionDao.findAll(example, pageable);
//        page.setTotalCount((int) p.getTotalElements());
//        page.setTotalPage(p.getTotalPages());
//        page.setList(p.getContent());
//        return page;
//    }
}
