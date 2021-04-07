package com.tqk.blog.controller;


import com.tqk.blog.enums.ResultEnum;
import com.tqk.blog.execption.BlogException;
import com.tqk.blog.mapper.BlUserMapper;
import com.tqk.blog.pojo.BlUser;
import com.tqk.blog.service.UserService;
import com.tqk.blog.utils.Page;
import com.tqk.blog.utils.Result;
import com.tqk.blog.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @Author: tianqikai
 * @Date: 2020年10月29日00:04:27
 * @Version 1.0
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private BlUserMapper blUserMapper;

    /**
     * 保存
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Result<Object> save(@RequestBody BlUser user) {
        userService.save(user);
        return new Result<>("添加成功！");
    }

    /**
     * 更新
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public Result<Object> update(@RequestBody BlUser user) {
        userService.update(user);
        return new Result<>("修改成功！");
    }

    /**
     * 修改个人信息
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/updateInfo", method = RequestMethod.PUT)
    public Result<Object> updateInfo(@RequestBody BlUser user) {
        userService.updateInfo(user);
        return new Result<>("修改成功！");
    }

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public Result<BlUser> get(@PathVariable String id) {
        log.info("查询编号："+id);
        BlUser user = userService.getById(id);
        return new Result<>(user);
    }

    /**
     * 根据id删除
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public Result<Object> delete(@PathVariable String id) {
        userService.deleteById(id);
        return new Result<>("删除成功！");
    }

    /**
     * 分页查询
     *
     * @param page
     * @return
     */
    @RequestMapping(value = "/getByPage", method = RequestMethod.POST)
    public Result<Page<BlUser>> getByPage(@RequestBody Page<BlUser> page) {
        String sortColumn = page.getSortColumn();
        if (StringUtils.isNotBlank(sortColumn)) {
            // 排序列不为空
            String[] sortColumns = {"sex", "created_time", "update_time"};
            List<String> sortList = Arrays.asList(sortColumns);
            if (!sortList.contains(sortColumn.toLowerCase())) {
                return new Result<>(ResultEnum.PARAMS_ERROR.getCode(), "排序参数不合法！");
            }
        }
        page = userService.getByPage(page);
        return new Result<>(page);
    }

    /**
     * 重置密码
     *
     * @return
     */
    @RequestMapping(value = "/resetPwd", method = RequestMethod.PUT)
    public Result<Object> resetPwd(@RequestBody List<String> userIds) {
        userService.resetPwd(userIds);
        return new Result<>("重置完毕！");
    }

    /**
     * 注册
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Result<Object> register(@RequestBody BlUser user) {
        Result<Object> result=new Result<>();
        try{
            user=userService.register(user);
            result.setCode(ResultEnum.SUCCESS.getCode());
            result.setMsg("注册用户成功");
        }catch (Exception e){
            result.setCode(ResultEnum.ERROR.getCode());
            result.setMsg("注册用户失败！");
        }
        return result;
    }
    /**
     * 注销当前登录用户
     *
     * @return
     */
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public Result<Object> logOut() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return new Result<>("用户注销成功！");
    }
    /**
     * 登录
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result<Object> login(@RequestBody BlUser user) {
        Result<Object> result=new Result<>();
        Map<String, Object> returnMap=null;
        try{
            returnMap=userService.login(user);
            System.out.println("user:"+returnMap.get("user").toString());
            result.setData(returnMap);
            result.setCode(ResultEnum.SUCCESS.getCode());
            result.setMsg("登陆成功");
        }catch (Exception e){
            log.info("登陆失败："+e.getMessage());
            String errMsg = "系统错误，登陆失败！";
            if(e instanceof BlogException){
                errMsg= e.getMessage();
            }
            result=new Result<Object>(ResultEnum.ERROR.getCode(),errMsg);
        }
        return result;
    }

    /**
     * 查询当前用户的评论数和收藏数
     * @return
     */
    @RequestMapping(value = "/commentAndCollectionCount", method = RequestMethod.GET)
    public Result<Map<String, Object>> commentAndCollectionCount() {
        Map<String, Object> countMap = userService.getCommentAndCollectionCount();
        return new Result<>(countMap);
    }

}
