package com.tqk.blog.service;

import com.tqk.blog.pojo.BlAdmin;

/**
 * <p>
 * 管理员表服务层接口
 * </p>
 *
 * @author tianqikai
 * @date 2020年10月13日00:04:50
 * @Version 1.0
 */
public interface AdminService {

    /**
     * 根据用户名查询
     * @param username
     * @return
     */
    BlAdmin getByUsername(String username);

    /**
     * 查询管理员
     * @return
     */
    BlAdmin getAdmin();

    /**
     * 更新个人信息
     * @param admin
     */
    void updateInfo(BlAdmin admin);

    /**
     * 更新密码
     * @param admin
     */
    void updatePassword(BlAdmin admin);

    /**
     * @Method：校验密码
     * @Date： 2021/3/28 20:13
     * @Author： Administrator
     * @Version  1.0
     */
    boolean checkPassword(BlAdmin admin);
}
