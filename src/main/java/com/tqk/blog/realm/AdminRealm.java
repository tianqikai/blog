package com.tqk.blog.realm;

import com.tqk.blog.enums.ResultEnum;
import com.tqk.blog.enums.StateEnums;
import com.tqk.blog.execption.BlogException;
import com.tqk.blog.mapper.BlUserMapper;
import com.tqk.blog.pojo.BlAdmin;
import com.tqk.blog.pojo.BlUser;
import com.tqk.blog.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import com.tqk.blog.token.UsernamePasswordToken;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 权限控制的Realm
 *
 * @author
 */
@Slf4j
public class AdminRealm extends AuthorizingRealm {

    @Autowired
    private AdminService adminService;

    @Autowired
    private BlUserMapper blUserMapper;

    /**
     * 授权方法
     *
     * @param principalCollection
     * @return
     * @author 杨德石
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return new SimpleAuthorizationInfo();
    }

    /**
     * 认证
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken passwordToken = (UsernamePasswordToken) token;
        String username = passwordToken.getUsername();
        if(passwordToken.getState()== StateEnums.ADMIN.getCode()){
            //查询admin数据库信息
            BlAdmin admin = adminService.getByUsername(username);
            if (admin == null) {
                log.error(ResultEnum.ERROR.getCode().toString(),"管理员用户名不存在！");
                // 用户名不存在
                return null;
            }
            // 框架负责比对数据库中的密码和页面输入的密码是否一致
            return new SimpleAuthenticationInfo(admin, admin.getPassword(), this.getName());
        }else if(passwordToken.getState()== StateEnums.USER.getCode()){
            //username 赋值的实际值是userId
            BlUser user =blUserMapper.selectByPrimaryKey(username) ;
            if(user==null){
                log.error(ResultEnum.ERROR.getCode().toString(),"用户名不存在！");
                // 用户名不存在
                return null;
            }
            // 框架负责比对数据库中的密码和页面输入的密码是否一致
            return new SimpleAuthenticationInfo(user, user.getPassword(), this.getName());
        }else{
            throw  new BlogException(ResultEnum.ERROR.getCode(),"验证用户信息失败！");
        }

    }
}
