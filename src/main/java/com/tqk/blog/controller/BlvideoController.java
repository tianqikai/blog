package com.tqk.blog.controller;

import com.tqk.blog.pojo.BlVedio;
import com.tqk.blog.service.VedioService;
import com.tqk.blog.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName: blog
 * @description:
 * @author: tianqikai
 * @create: 2021-03-19 23:35
 **/
@RestController
@RequestMapping("/video")
public class BlvideoController {
    @Autowired
    private VedioService vedioService;

    /**
     * 保存
     *
     * @param vedio
     * @returnR
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Result<Object> save(@RequestBody BlVedio vedio) {
        vedioService.save(vedio);
        return new Result<>("添加成功！");
    }

    /**
     * 更新
     *
     * @param vedio
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public Result<Object> update(@RequestBody BlVedio vedio) {
        vedioService.update(vedio);
        return new Result<>("修改成功！");
    }

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public Result<BlVedio> get(@PathVariable Integer id) {
        BlVedio vedio = vedioService.getById(id);
        return new Result<>(vedio);
    }

    /**
     * 根据id删除
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public Result<Object> delete(@PathVariable Integer id) {
        vedioService.deleteById(id);
        return new Result<>("删除成功！");
    }

    /**
     * 启用
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/enable/{id}", method = RequestMethod.PUT)
    public Result<Object> enable(@PathVariable Integer id) {
        vedioService.enableById(id);
        return new Result<>("启用成功");
    }

    /**
     * 弃用
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/disable/{id}", method = RequestMethod.PUT)
    public Result<Object> disable(@PathVariable Integer id) {
        vedioService.disableById(id);
        return new Result<>("弃用成功");
    }

}
