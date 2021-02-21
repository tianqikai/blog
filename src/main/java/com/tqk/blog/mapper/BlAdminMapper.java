package com.tqk.blog.mapper;

import com.tqk.blog.pojo.BlAbout;
import com.tqk.blog.pojo.BlAdmin;
import com.tqk.blog.utils.MyMapper;

/**
 * @program: blog
 * @description:
 * @author: tianqikai
 * @create: 2020-10-12 23:59
 **/
public interface BlAdminMapper extends MyMapper<BlAbout> {
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
     * 更新
     * @param admin
     */
    void update(BlAdmin admin);
}
