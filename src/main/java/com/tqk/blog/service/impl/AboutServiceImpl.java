package com.tqk.blog.service.impl;


import com.tqk.blog.enums.ResultEnum;
import com.tqk.blog.enums.StateEnums;
import com.tqk.blog.execption.BlogException;
import com.tqk.blog.mapper.BlAboutMapper;
import com.tqk.blog.pojo.BlAbout;
import com.tqk.blog.service.AboutService;
import com.tqk.blog.utils.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * </p>
 *
 * @author 稽哥
 * @date 2020-02-07 14:04:12
 * @Version 1.0
 */
@Service
@Slf4j
public class AboutServiceImpl implements AboutService {

    @Autowired
    private BlAboutMapper aboutMapper;

    @Override
    public void save(BlAbout about) {

        aboutMapper.save(about);
    }

    @Override
    public void update(BlAbout about) {

        aboutMapper.updateById(about);
    }

    @Override
    public BlAbout getById(String id) {
        return aboutMapper.getById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BlAbout read() {
        BlAbout about = aboutMapper.getAbout();
        if (about == null) {
            return null;
        }
        // 更新阅读数
        about.setAboutRead(about.getAboutRead() + 1);
        aboutMapper.updateById(about);
        return about;
    }

    @Override
    public void deleteById(String id) {
        aboutMapper.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void enableById(String id) {
        // 查询是否已经存在启用的关于我的
        BlAbout about = aboutMapper.getAbout();
        if (about != null) {
            throw new BlogException(ResultEnum.ERROR.getCode(), "当前已存在启用中的关于我的");
        }
        about = aboutMapper.selectByPrimaryKey(id);
        about.setEnable(StateEnums.ENABLED.getCode());
        aboutMapper.updateByPrimaryKey(about);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void disableById(String id) {
        BlAbout about = aboutMapper.selectByPrimaryKey(id);
        about.setEnable(StateEnums.NOT_ENABLE.getCode());
        aboutMapper.updateByPrimaryKey(about);
    }

    @Override
    public Page<BlAbout> getByPage(Page<BlAbout> page) {
        // 查询数据
        List<BlAbout> aboutList = aboutMapper.getByPage(page);
        page.setList(aboutList);
        // 查询总数
        int totalCount = aboutMapper.getCountByPage(page);
        page.setTotalCount(totalCount);
        return page;
    }
}
