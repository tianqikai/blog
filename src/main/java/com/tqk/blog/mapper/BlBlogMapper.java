package com.tqk.blog.mapper;

import com.tqk.blog.pojo.BlBlog;
import com.tqk.blog.utils.MyMapper;
import com.tqk.blog.utils.Page;
import com.tqk.blog.vo.BlogVo;

import java.util.List;

/**
 * @author Administrator
 */
public interface BlBlogMapper extends MyMapper<BlBlog> {
    /**
     * 保存
     * @param blog
     */
    void save(BlBlog blog);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    BlBlog getById(String id);

    /**
     * 更新
     * @param blog
     */
    void update(BlBlog blog);

    /**
     * 根据id删除
     * @param id
     */
    void deleteById(String id);

    /**
     * 分页查询
     * @param page
     * @return
     */
    List<BlogVo> getByPage(Page<BlogVo> page);

    /**
     * 查询总数
     * @param page
     * @return
     */
    int getCountByPage(Page<BlogVo> page);

    /**
     * 查询推荐阅读
     * @return
     */
    List<BlogVo> recomRead();

    /**
     * 查询时间轴
     * @return
     */
    List<BlogVo> getTimeLine();

    /**
     * 更新点赞量
     * @param blogId
     */
    void updateGoods(String blogId);
}