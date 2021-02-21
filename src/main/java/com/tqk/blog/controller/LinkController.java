package com.tqk.blog.controller;


import com.tqk.blog.pojo.BlLink;
import com.tqk.blog.service.LinkService;
import com.tqk.blog.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: tianqikai
 * @Date: 2020年10月24日11:06:30
 * @Version 1.0
 */
@RestController
@RequestMapping("/link")
public class LinkController {

    @Autowired
    private LinkService linkService;

    /**
     * 添加
     * @param link
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Result<Object> save(@RequestBody BlLink link) {
        linkService.save(link);
        return new Result<>("添加成功！");
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public Result<Object> update(@RequestBody BlLink link) {
        linkService.update(link);
        return new Result<>("修改成功！");
    }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public Result<BlLink> get(@PathVariable Integer id) {
        BlLink link = linkService.getById(id);
        return new Result<>(link);
    }

    /**
     * 查询所有
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Result<List<BlLink>> list() {
        List<BlLink> linkList = linkService.getAll();
        return new Result<>(linkList);
    }

    /**
     * 根据id删除
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public Result<Object> delete(@PathVariable Integer id) {
        linkService.deleteById(id);
        return new Result<>("删除成功！");
    }

}
