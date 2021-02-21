package com.tqk.blog.controller;

import com.tqk.blog.enums.ResultEnum;
import com.tqk.blog.pojo.BlAbout;
import com.tqk.blog.service.AboutService;
import com.tqk.blog.utils.Page;
import com.tqk.blog.utils.Result;
import com.tqk.blog.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: tianqikai
 * @Date: 2020年10月27日21:37:34
 * @Version 1.0
 */
@RestController
@RequestMapping("/about")
@Slf4j
public class AboutController {

    @Autowired
    private AboutService aboutService;

    /**
     * 保存
     *
     * @param about
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Result<Object> save(@RequestBody BlAbout about) {
        aboutService.save(about);
        return new Result<>("添加成功！");
    }

    /**
     * 更新
     *
     * @param about
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public Result<Object> update(@RequestBody BlAbout about) {
        aboutService.update(about);
        return new Result<>("修改成功！");
    }

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public Result<BlAbout> get(@PathVariable String id) {
        BlAbout about = aboutService.getById(id);
        return new Result<>(about);
    }

    /**
     * 阅读
     *
     * @return
     */
    @RequestMapping(value = "/read", method = RequestMethod.GET)
    public Result<BlAbout> read() {
        BlAbout about = aboutService.read();
        return new Result<>(about);
    }

    /**
     * 根据id删除
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public Result<Object> delete(@PathVariable String id) {
        aboutService.deleteById(id);
        return new Result<>("删除成功！");
    }

    /**
     * 启用
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/enable/{id}", method = RequestMethod.PUT)
    public Result<Object> enable(@PathVariable String id) {
        log.info("id[{}]",id);
        aboutService.enableById(id);
        return new Result<>("启用成功");
    }

    /**
     * 弃用
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/disable/{id}", method = RequestMethod.PUT)
    public Result<Object> disable(@PathVariable String id) {
        aboutService.disableById(id);
        return new Result<>("弃用成功");
    }

    /**
     * 分页查询
     *
     * @param page
     * @return
     */
    @RequestMapping(value = "/getByPage", method = RequestMethod.POST)
    public Result<Page<BlAbout>> getByPage(@RequestBody Page<BlAbout> page) {
        String sortColumn = page.getSortColumn();
        if (StringUtils.isNotBlank(sortColumn)) {
            // 排序列不为空
            String[] sortColumns = {"about_read", "created_time", "update_time"};
            List<String> sortList = Arrays.asList(sortColumns);
            if (!sortList.contains(sortColumn.toLowerCase())) {
                return new Result<>(ResultEnum.PARAMS_ERROR.getCode(), "排序参数不合法！");
            }
        }
        page = aboutService.getByPage(page);
        return new Result<>(page);
    }

}
