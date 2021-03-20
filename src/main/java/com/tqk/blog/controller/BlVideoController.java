package com.tqk.blog.controller;

import com.tqk.blog.execption.BlogException;
import com.tqk.blog.pojo.BlVideo;
import com.tqk.blog.service.VideoService;
import com.tqk.blog.utils.Page;
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
public class BlVideoController {
    @Autowired
    private VideoService videoService;

    /**
     * 保存
     *
     * @param vedio
     * @returnR
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Result<Object> save(@RequestBody BlVideo vedio) {
        String msg="添加成功！";
        Result<Object> result =new Result(msg);
        try{
            videoService.save(vedio);
        }catch(Exception e){
            if(e instanceof BlogException){
                result.setMsg(e.getMessage());
            }else{
                result.setMsg("视频添加失败！");
            }
        }
        return result;
    }

    /**
     * 更新
     *
     * @param vedio
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public Result<Object> update(@RequestBody BlVideo vedio) {
        videoService.update(vedio);
        return new Result<>(20000,"修改成功！");
    }

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public Result<BlVideo> get(@PathVariable Integer id) {
        BlVideo vedio = videoService.getById(id);
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
        videoService.deleteById(id);
        return new Result<>(20000,"删除成功！");
    }

    /**
     * 启用
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/enable/{id}", method = RequestMethod.PUT)
    public Result<Object> enable(@PathVariable Integer id) {
        videoService.enableById(id);
        return new Result<>(20000,"启用成功");
    }

    /**
     * 弃用
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/disable/{id}", method = RequestMethod.PUT)
    public Result<Object> disable(@PathVariable Integer id) {
        videoService.disableById(id);
        return new Result<>(200000,"弃用成功");
    }
    /**
     * 查询
     * @return
     */
    @RequestMapping(value = "/getByPage", method = RequestMethod.POST)
    public Result<Object> getAll(@RequestBody Page<BlVideo> page) {
        page = videoService.getAll(page);
        return new Result<>(20000,"查询成功",page);
    }
}
