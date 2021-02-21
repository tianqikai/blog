package com.tqk.blog.mapper;

import com.tqk.blog.pojo.BlUser;
import com.tqk.blog.utils.MyMapper;
import com.tqk.blog.utils.Page;

import java.util.List;

/**
 * @author tianqikai
 */
public interface BlUserMapper extends MyMapper<BlUser> {
    /**
     * 添加
     * @param user
     */
    void save(BlUser user);

    /**
     * 更新
     * @param user
     */
    void update(BlUser user);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    BlUser getById(Integer id);

    /**
     * 根据id删除
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 分页查询
     * @param page
     * @return
     */
    List<BlUser> getByPage(Page<BlUser> page);

    /**
     * 查询总数
     * @param page
     * @return
     */
    int getCountByPage(Page<BlUser> page);

    /**
     * 根据id列表查询
     * @param userIds
     * @return
     */
    List<BlUser> getByIds(List<Integer> userIds);

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
}