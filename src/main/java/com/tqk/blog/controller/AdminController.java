package com.tqk.blog.controller;

import com.tqk.blog.enums.ResultEnum;
import com.tqk.blog.enums.StateEnums;
import com.tqk.blog.execption.BlogException;
import com.tqk.blog.pojo.BlAdmin;
import com.tqk.blog.service.AdminService;
import com.tqk.blog.token.UsernamePasswordToken;
import com.tqk.blog.utils.Result;
import com.tqk.blog.utils.ShiroUtils;
import com.tqk.blog.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: blog
 * @description:
 * @author: tianqikai
 * @create: 2020-10-12 23:03
 **/
@Slf4j
@RestController
@RequestMapping("/BlAdmin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    
    @RequestMapping(value="/login",method = RequestMethod.POST)
    public Result<Object> login(@RequestBody BlAdmin blAdmin){
        log.info(blAdmin.toString());
        if (blAdmin == null || StringUtils.isBlank(blAdmin.getUsername()) || StringUtils.isBlank(blAdmin.getPassword())) {
            return new Result<>(ResultEnum.PARAMS_NULL.getCode(), "用户名或密码错误！");
        }
        Subject subject = SecurityUtils.getSubject();
        //设置用户登录失效时间为半个小时
        subject.getSession().setTimeout(1800000);
        AuthenticationToken authenticationToken = new UsernamePasswordToken(blAdmin.getUsername(), blAdmin.getPassword(), StateEnums.ADMIN.getCode());
        try {
            //怎么实现和数据库内的用户名密码校验的？
            subject.login(authenticationToken);
        } catch (Exception e) {
            return new Result<>(ResultEnum.PARAMS_NULL.getCode(), "用户名或密码错误！");
        }
        //登录成功
        Serializable sessionId = subject.getSession().getId();
        Map<String, Object> returnMap = new HashMap<>(2);
        returnMap.put("token", sessionId);
        return new Result<>(returnMap);

    }
    /**
     * 获取当前登录用户信息
     *
     * @return
     */
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public Result<BlAdmin> getLoginInfo() {
        BlAdmin loginBlAdmin = (BlAdmin) ShiroUtils.getLoginUser();
        loginBlAdmin.setPassword("");
        return new Result<>(loginBlAdmin);
    }
    /**
     * 注销当前登录用户
     *
     * @return
     */
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public Result<BlAdmin> logOut() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return new Result<>("用户注销成功！");
    }
    /**
     * 查询管理员
     */
    @RequestMapping(value = "/getBlAdmin", method = RequestMethod.GET)
    public Result<BlAdmin> getBlAdmin() {
        BlAdmin blAdmin = adminService.getAdmin();
        return new Result<>(blAdmin);
    }

    /**
     * 更新个人信息
     * @param blAdmin
     * @return
     */
    @RequestMapping(value = "/updateInfo", method = RequestMethod.PUT)
    public Result<Object> updateInfo(@RequestBody BlAdmin blAdmin) {
        adminService.updateInfo(blAdmin);
        return new Result<>("更新成功！");
    }
    /**
     * 校验密码
     * @param blAdmin
     * @return
     */
    @RequestMapping(value = "/checkPassword", method = RequestMethod.POST)
    public Result<Object> checkPassword(@RequestBody BlAdmin blAdmin) {

        boolean bool=adminService.checkPassword(blAdmin);
        if(bool){
            return new Result<>("校验密码成功！");
        }else{
            return new Result<>(ResultEnum.ERROR.getCode(),"原校验密码失败！");
        }
    }
    /**
     * 更新密码
     * @param blAdmin
     * @return
     */
    @RequestMapping(value = "/updatePassword", method = RequestMethod.PUT)
    public Result<Object> updatePassword(@RequestBody BlAdmin blAdmin) {
        adminService.updatePassword(blAdmin);
        return new Result<>("更新成功！");
    }

    /**
     * HelloTest
     * @param flag
     * @return
     */
    @RequestMapping(value = "/welcome", method = RequestMethod.POST)
    public Result<Object> helloTest(@RequestBody int flag) throws Exception {
            if(flag==1){
                throw new BlogException(ResultEnum.ERROR);
            }else if(flag==2){
                throw new Exception("程序异常错误！");
            }else{
                throw new RuntimeException("程序运行错误！");
            }
    }
}
