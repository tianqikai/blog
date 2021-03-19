package com.tqk.blog.controller;


import com.tqk.blog.enums.ResultEnum;
import com.tqk.blog.pojo.BlBlog;
import com.tqk.blog.service.BlogService;
import com.tqk.blog.utils.Page;
import com.tqk.blog.utils.Result;
import com.tqk.blog.utils.StringUtils;
import com.tqk.blog.vo.BlogVo;
import com.tqk.blog.vo.TimeLineVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: tianqikai
 * @Date: 2020年10月31日23:09:23
 * @Version 1.0
 */
@RestController
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    private BlogService blogService;

    /**
     * 保存
     *
     * @param blog
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Result<Object> save(@RequestBody BlBlog blog) {
        blogService.save(blog);
        return new Result<>("添加成功！");
    }

    /**
     * 根据id查询
     */
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public Result<BlBlog> get(@PathVariable String id) {
        BlBlog blog = blogService.getById(id);
        return new Result<>(blog);
    }

    /**
     * 更新
     *
     * @param blog
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public Result<Object> update(@RequestBody BlBlog blog) {
        blogService.update(blog);
        return new Result<>("更新成功！");
    }

    /**
     * 根据id阅读
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/read/{id}", method = RequestMethod.GET)
    public Result<BlogVo> read(@PathVariable String id) {
        BlogVo blog = blogService.readById(id);
        return new Result<>(blog);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public Result<Object> delete(@PathVariable String id) {
        blogService.deleteById(id);
        return new Result<>("删除成功！");
    }

    /**
     * 查询时间轴
     *
     * @return
     */
    @RequestMapping(value = "/getTimeLine", method = RequestMethod.GET)
    public Result<List<TimeLineVo>> getTimeLine() {
        List<TimeLineVo> timeList = new ArrayList<>(16);
        List<BlogVo> blogVoList = blogService.getTimeLine();
        blogVoList.forEach(e -> {
            String blogMonth = e.getBlogMonth();
            TimeLineVo timeLineVo = new TimeLineVo();
            timeLineVo.setMonth(blogMonth);
            if(timeList.contains(timeLineVo)) {
                // 取出对应的数据
                TimeLineVo timeLine = getTimeLineForList(timeList, timeLineVo);
                List<BlogVo> list = timeLine.getList();
                if(list == null) {
                    list = new ArrayList<>(8);
                }
                list.add(e);
                timeLine.setList(list);
            } else {
                List<BlogVo> list = timeLineVo.getList();
                if(list == null) {
                    list = new ArrayList<>(8);
                }
                list.add(e);
                timeLineVo.setList(list);
                timeList.add(timeLineVo);
            }
        });
        return new Result<>(timeList);
    }

    /**
     * 分页查询
     *
     * @param page
     * @return
     */
    @RequestMapping(value = "/getByPage", method = RequestMethod.POST)
    public Result<Page<BlogVo>> getByPage(@RequestBody Page<BlogVo> page) {
        System.out.println(page.toString());
        String sortColumn = page.getSortColumn();
        if (StringUtils.isNotBlank(sortColumn)) {
            // 排序列不为空
            String[] sortColumns = {"blog_goods", "blog_read", "blog_collection",
                    "type_name", "blog_comment", "created_time", "update_time"};
            //字符串转化为list
            List<String> sortList = Arrays.asList(sortColumns);
            //判断排序参数是否合法
            if (!sortList.contains(sortColumn.toLowerCase())) {
                return new Result<>(ResultEnum.PARAMS_ERROR.getCode(), "排序参数不合法！");
            }
        }
        page = blogService.getByPage(page);
        return new Result<>(page);
    }

    /**
     * 分页查询
     *
     * @return
     */
    @RequestMapping(value = "/recomRead", method = RequestMethod.GET)
    public Result<List<BlogVo>> recomRead() {
        List<BlogVo> blogList = blogService.recomRead();
        return new Result<>(blogList);
    }

//    /**
//     * 点赞
//     * @param blogGoods
//     * @return
//     */
//    @RequestMapping(value = "/good", method = RequestMethod.POST)
//    public Result<Object> good(@RequestBody BlogGoods blogGoods) {
//        if(StringUtils.isBlank(blogGoods.getBlogId())) {
//            return new Result<>("博客id不能为空！");
//        }
//        blogService.goodByBlogAndUser(blogGoods);
//        return new Result<>("点赞成功！");
//    }
//
//    /**
//     * 根据博客id和当前登录用户查询点赞记录
//     * @param blogId
//     * @return
//     */
//    @RequestMapping(value = "/getGood/{blogId}", method = RequestMethod.GET)
//    public Result<Integer> getGood(@PathVariable String blogId) {
//        int count = blogService.getGoodsCount(blogId);
//        return new Result<>(count);
//    }

//    /**
//     * 收藏
//     * @param blogCollection
//     * @return
//     */
//    @RequestMapping(value = "/collection", method = RequestMethod.POST)
//    public Result<Object> collection(@RequestBody BlogCollection blogCollection) {
//        if(StringUtils.isBlank(blogCollection.getBlogId())) {
//            return new Result<>("博客id不能为空！");
//        }
//        blogService.collectionByBlogId(blogCollection);
//        return new Result<>("收藏成功！");
//    }
//
//    /**
//     * 根据博客id和当前登录用户查询收藏记录
//     * @param blogId
//     * @return
//     */
//    @RequestMapping(value = "/getCollection/{blogId}", method = RequestMethod.GET)
//    public Result<Integer> getCollection(@PathVariable String blogId) {
//        int count = blogService.getCollectionCount(blogId);
//        return new Result<>(count);
//    }
//
//    /**
//     * 分页查询我的收藏
//     * @param page
//     * @return
//     */
//    @RequestMapping(value = "/getCollectionList", method = RequestMethod.POST)
//    public Result<Page<BlogCollection>> getCollectionList(@RequestBody Page<BlogCollection> page) {
//        page = blogService.getCollectionByPage(page);
//        return new Result<>(page);
//    }

    /**
     * 获取对应的timeLine
     * @param timeList
     * @param timeLineVo
     * @return
     */
    private TimeLineVo getTimeLineForList(List<TimeLineVo> timeList, TimeLineVo timeLineVo) {
        for (TimeLineVo lineVo : timeList) {
            if(timeLineVo.getMonth().equals(lineVo.getMonth())) {
                return lineVo;
            }
        }
        return null;
    }

}
