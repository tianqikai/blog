package com.tqk.blog.service.impl;


import com.tqk.blog.dao.CollectionDao;
import com.tqk.blog.dao.CommentDao;
import com.tqk.blog.enums.ResultEnum;
import com.tqk.blog.enums.StateEnums;
import com.tqk.blog.execption.BlogException;
import com.tqk.blog.mapper.BlUserMapper;
import com.tqk.blog.pojo.BlUser;
import com.tqk.blog.service.UserService;
import com.tqk.blog.token.UsernamePasswordToken;
import com.tqk.blog.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户表服务实现类
 * </p>
 *
 * @author tianqikai
 * @date 2020年10月29日00:11:37
 * @Version 1.0
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private BlUserMapper userMapper;
    @Autowired
    private IdWorker idWorker;
    @Autowired
    private CommentDao commentDao;
    @Autowired
    private CollectionDao collectionDao;
    @Autowired
    FastDfsUtils fastDfsUtils;

    @Override
    public void save(BlUser user) {
        userMapper.save(user);
    }

    @Override
    public void update(BlUser user) {
        BlUser oldBlUser=userMapper.selectByPrimaryKey(user.getUserId());
        userMapper.updateInfo(user);
        if(StringUtils.isNoneBlank(oldBlUser.getHeader())&&!oldBlUser.getHeader().equals(user.getHeader())){
            fastDfsUtils.deleteFile(oldBlUser.getHeader());
        }
    }

    @Override
    public BlUser getById(String id) {
        return userMapper.getById(id);
    }

    @Override
    public void deleteById(String id) {
        userMapper.deleteById(id);
    }

    @Override
    public Page<BlUser> getByPage(Page<BlUser> page) {
        // 查询数据
        List<BlUser> userList = userMapper.getByPage(page);
        page.setList(userList);
        // 查询总数
        int totalCount = userMapper.getCountByPage(page);
        page.setTotalCount(totalCount);
        return page;
    }
    /**
     * @Method： resetPwd
     * @Description： 重置密码
     * @param userIds
     * @Date： 2021/4/14 22:31
     * @Author： Administrator
     * @Version  1.0
     */
    @Override
    public void resetPwd(List<String> userIds) {
        // JDK8新特性：集合的流式操作，这里使用到了Lambda表达式
        // 如果仅仅是遍历的话，增强for循环的效率比流式操作要高
        // 但是可读性没有流式操作高。
        // 当遍历时操作的业务逻辑较多时，流式操作的性能比普通的for循环要高
        List<BlUser> userList = userMapper.getByIds(userIds);
        userList.forEach(e -> {
            e.setPassword(Md5Utils.toMD5("123456"));
            // 在for循环中修改数据 case when
            userMapper.update(e);
        });
    }
    /**
     * @Method： register
     * @Description：注册用户
     * @Date： 2021/4/14 22:33
     * @Author： Administrator
     * @Version  1.0
     */
    @Override
    public BlUser register(BlUser user) {
        // 先根据用户名查询用户是否存在
        BlUser blUser = userMapper.getByUserEmail(user.getUserEmail());
        // 如果存在，提示用户已存在
        if (blUser != null) {
            throw new BlogException(ResultEnum.PARAMS_ERROR.getCode(), "用户已存在！");
        }else{
            //生成用户ID
            String ID= String.valueOf(idWorker.nextId());
            user.setUserId(ID);
        }
        // 如果不存在，插入数据
        user.setCreatedTime(new Date());
        user.setUpdateTime(new Date());
        userMapper.save(user);
        //发送邮件告知注册用户信息
        StringBuilder emailBuilder=new StringBuilder();
        emailBuilder.append("您的账号："+user.getUserId()+";感谢您的支持!! 您可以使用邮箱直接登陆或者使用账号进行登陆！");
        try {
            SendmailUtil.sendEmail(user.getUserEmail(),"恭喜您注册用户成功",emailBuilder.toString());
        } catch (Exception e) {
            //可以登记下来，让用户用邮箱登录，然后进行定时任务处理重复发送，或者让用户进行再次注册
            throw  new BlogException(ResultEnum.ERROR,"给用户发送邮件失败！");
        }
        return blUser;
    }

    @Override
    public BlUser getByUsername(String username) {
        return userMapper.getByUserName(username);
    }

    @Override
    public void updateInfo(BlUser user) {
        BlUser oldBlUser=userMapper.selectByPrimaryKey(user.getUserId());
        userMapper.updateInfo(user);
        if(!oldBlUser.getHeader().equals(user.getHeader())){
            fastDfsUtils.deleteFile(oldBlUser.getHeader());
        }
    }
    /**
     * @Method： getCommentAndCollectionCount
     * @Description：获取用户评论信息和收藏数
     * @Date： 2021/4/14 22:34
     * @Author： tianqikai
     * @Version  1.0
     */
    @Override
    public Map<String, Object> getCommentAndCollectionCount() {
        BlUser user = (BlUser) ShiroUtils.getLoginUser();
        int commentCount = commentDao.countByCommentUser(user.getUserId());
        int collectionCount = collectionDao.countByUserId(user.getUserId());
        Map<String, Object> map = new HashMap<>(4);
        map.put("commentCount", commentCount);
        map.put("collectionCount", collectionCount);
        return map;
    }
    /**
     * @Method： logOut
     * @Description：注销登录
     * @Date： 2021/4/14 22:37
     * @Author： tianqikai
     * @Version  1.0
     */
    @Override
    public void logOut(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
    }
    @Override
    public  Map<String, Object> login(BlUser user){
        log.info(user.toString());
        if (user == null || StringUtils.isBlank(user.getPassword())||(StringUtils.isBlank(user.getUserId())&&StringUtils.isBlank(user.getUserEmail()))) {
            throw  new BlogException(ResultEnum.PARAMS_NULL.getCode(), "用户名或密码不能为空！");
        }
        //查询用户信息
        if(StringUtils.isNotBlank(user.getUserEmail())&&StringUtils.isBlank(user.getUserId())){
            Example example=new Example(BlUser.class);
            example.createCriteria().andEqualTo("userEmail",user.getUserEmail());
            BlUser blUser= userMapper.selectOneByExample(example);
            if(blUser==null){
                throw  new BlogException(ResultEnum.PARAMS_NULL.getCode(), "该用户不存在！");
            }else{
                user.setUserId(blUser.getUserId());
            }
        }
        Subject subject = SecurityUtils.getSubject();
        //设置用户登陆后，永不失效
//        subject.getSession().setTimeout(-1000L);
        subject.getSession().setTimeout(3600000);
        AuthenticationToken authenticationToken = new UsernamePasswordToken(user.getUserId(), user.getPassword(), StateEnums.USER.getCode());
        try {
            subject.login(authenticationToken);
        } catch (Exception e) {
            log.info(e.getMessage());
            throw  new BlogException(ResultEnum.PARAMS_NULL.getCode(), "用户名或密码错误！");
        }
        // 登录成功
        Serializable sessionId = subject.getSession().getId();
        BlUser u = (BlUser) subject.getPrincipal();
        System.out.println("Bluser:"+u.toString());
        u.setPassword("");
        u.setDeleted(null);
        Map<String, Object> returnMap = new HashMap<>(2);
        returnMap.put("token", sessionId);
        returnMap.put("user", u);
        return returnMap;
    }
}
