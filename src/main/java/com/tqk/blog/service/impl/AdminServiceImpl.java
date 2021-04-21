package com.tqk.blog.service.impl;

import com.tqk.blog.mapper.BlAdminMapper;
import com.tqk.blog.pojo.BlAdmin;
import com.tqk.blog.service.AdminService;
import com.tqk.blog.utils.FastDfsUtils;
import com.tqk.blog.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 管理员表服务实现类
 * </p>
 *
 * @author 稽哥
 * @date 2020-02-07 14:04:12
 * @Version 1.0
 */
@Service
@Slf4j
public class AdminServiceImpl implements AdminService {

    @Autowired
    private BlAdminMapper adminMapper;
    @Autowired
    FastDfsUtils fastDfsUtils;
    /**
     * 根据用户名查询
     * @param username
     * @return
     **/
    @Override
    public BlAdmin getByUsername(String username) {
        return adminMapper.getByUsername(username);
    }

    @Override
    public BlAdmin getAdmin() {
        return adminMapper.getAdmin();
    }

    @Override
    public void updateInfo(BlAdmin admin) {
        BlAdmin oldAdmin=adminMapper.selectByPrimaryKey(admin.getId());
        adminMapper.update(admin);
        if(StringUtils.isNoneBlank(oldAdmin.getHeader())&&!admin.getHeader().equals(oldAdmin.getHeader())){
            fastDfsUtils.deleteFile(oldAdmin.getHeader());
        }
    }

    @Override
    public boolean checkPassword(BlAdmin admin) {
        BlAdmin oldAdmin=adminMapper.selectByPrimaryKey(admin.getId());
        if(oldAdmin.getPassword().equalsIgnoreCase(admin.getPassword())){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public void updatePassword(BlAdmin admin) {
        BlAdmin oldAdmin = adminMapper.getAdmin();
        oldAdmin.setPassword(admin.getPassword());
        adminMapper.update(oldAdmin);
    }
}
