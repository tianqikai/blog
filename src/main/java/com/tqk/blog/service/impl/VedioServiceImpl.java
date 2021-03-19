package com.tqk.blog.service.impl;

import com.tqk.blog.enums.StateEnums;
import com.tqk.blog.mapper.BlVedioMapper;
import com.tqk.blog.pojo.BlVedio;
import com.tqk.blog.service.VedioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: blog
 * @description:
 * @author: tianqikai
 * @create: 2021-03-20 00:05
 **/
@Service
public class VedioServiceImpl  implements VedioService {
    @Autowired
    BlVedioMapper  blVedioMapper;

    @Override
    public void save(BlVedio vedio) {
        blVedioMapper.insert(vedio);
    }

    @Override
    public void update(BlVedio vedio) {
        blVedioMapper.updateByPrimaryKey(vedio);
    }

    @Override
    public BlVedio getById(Integer id) {
        BlVedio blVedio=blVedioMapper.selectByPrimaryKey(id);
        return blVedio;
    }

    @Override
    public void deleteById(Integer id) {
        blVedioMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void enableById(Integer id) {
        BlVedio vedio=new BlVedio();
        vedio.setId(id);
        vedio.setEnable(StateEnums.ENABLED.getCode());
        blVedioMapper.updateByPrimaryKey(vedio);
    }

    @Override
    public void disableById(Integer id) {
        BlVedio vedio=new BlVedio();
        vedio.setId(id);
        vedio.setEnable(StateEnums.NOT_ENABLE.getCode());
        blVedioMapper.updateByPrimaryKey(vedio);
    }
}
