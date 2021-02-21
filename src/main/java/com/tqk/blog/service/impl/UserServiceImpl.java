package com.tqk.blog.service.impl;


import com.tqk.blog.enums.ResultEnum;
import com.tqk.blog.execption.BlogException;
import com.tqk.blog.mapper.BlUserMapper;
import com.tqk.blog.pojo.BlUser;
import com.tqk.blog.service.UserService;
import com.tqk.blog.utils.Md5Utils;
import com.tqk.blog.utils.Page;
import com.tqk.blog.utils.ShiroUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
//    @Autowired
//    private CommentDao commentDao;
//    @Autowired
//    private CollectionDao collectionDao;


    @Override
    public void save(BlUser user) {
        userMapper.save(user);
    }

    @Override
    public void update(BlUser user) {
        userMapper.update(user);
    }

    @Override
    public BlUser getById(Integer id) {
        return userMapper.getById(id);
    }

    @Override
    public void deleteById(Integer id) {
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

    @Override
    public void resetPwd(List<Integer> userIds) {
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

    @Override
    public void register(BlUser user) {
        // 先根据用户名查询用户是否存在
        BlUser u = userMapper.getByUsername(user.getUsername());
        // 如果存在，提示用户已存在
        if (u != null) {
            throw new BlogException(ResultEnum.PARAMS_ERROR.getCode(), "用户已存在！");
        }
        // 如果不存在，插入数据
        userMapper.save(user);
    }

    @Override
    public BlUser getByUsername(String username) {
        return userMapper.getByUsername(username);
    }

    @Override
    public void updateInfo(BlUser user) {
        userMapper.updateInfo(user);
    }

    @Override
    public Map<String, Object> getCommentAndCollectionCount() {
        BlUser user = (BlUser) ShiroUtils.getLoginUser();
//        int commentCount = commentDao.countByCommentUser(user.getUserId());
//        int collectionCount = collectionDao.countByUserId(user.getUserId());
        Map<String, Object> map = new HashMap<>(4);
//        map.put("commentCount", commentCount);
//        map.put("collectionCount", collectionCount);
        return map;
    }
}
