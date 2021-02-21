package com.tqk.blog.service;


import com.tqk.blog.pojo.BlUser;
import com.tqk.blog.utils.Page;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户表服务层接口
 * </p>
 *
 * @author tianqikai
 * @date 2020年10月29日00:10:05
 * @Version 1.0
 */
public interface UserService {

    /**
     * 保存
     *
     * @ram user
     */
    void save(BlUser user);

    /**
     * 更新
     *
     * @param user
     */
    void update(BlUser user);

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    BlUser getById(Integer id);

    /**
     * 根据id删除
     *
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 分页查询
     *
     * @param page
     * @return
     */
    Page<BlUser> getByPage(Page<BlUser> page);

    /**
     * 重置密码为123456
     * @param userIds
     */
    void resetPwd(List<Integer> userIds);

    /**
     * 注册
     * @param user
     */
    void register(BlUser user);

    /**
     * 根据用户名查询
     * @param username
     * @return
     */
    BlUser getByUsername(String username);

    /**
     * 修改个人信息
     * @param user
     */
    void updateInfo(BlUser user);

    /**
     * 查询评论数和收藏数
     * @return
     */
    Map<String, Object> getCommentAndCollectionCount();
}
