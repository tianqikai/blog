package com.tqk.blog.realm;

import com.tqk.blog.pojo.BlAdmin;
import com.tqk.blog.service.AdminService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 权限控制的Realm
 *
 * @author
 */
public class AdminRealm extends AuthorizingRealm {

    @Autowired
    private AdminService adminService;

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
        BlAdmin admin = adminService.getByUsername(username);
        if (admin == null) {
            // 用户名不存在
            return null;
        }
        // 框架负责比对数据库中的密码和页面输入的密码是否一致
        return new SimpleAuthenticationInfo(admin, admin.getPassword(), this.getName());
    }
}
