package com.tqk.blog.service.impl;

import com.tqk.blog.enums.ResultEnum;
import com.tqk.blog.enums.StateEnums;
import com.tqk.blog.execption.BlogException;
import com.tqk.blog.mapper.BlVideoMapper;
import com.tqk.blog.pojo.BlVideo;
import com.tqk.blog.service.VideoService;
import com.tqk.blog.utils.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * @program: blog
 * @description:
 * @author: tianqikai
 * @create: 2021-03-20 00:05
 **/
@Service
@Slf4j
public class VideoServiceImpl implements VideoService {
    @Autowired
    BlVideoMapper  blVideoMapper;

    @Override
    public void save(BlVideo video) {
        Integer maxId= blVideoMapper.selectMaxVedioId();
        if(video.getId()==null){
            video.setId(maxId+1);
        }else if(maxId>=video.getId()){
            throw new BlogException(ResultEnum.ERROR, "音乐编号错误！");
        }
        video.setEnable(1);
        video.setDeleted(0);
        video.setCreatedTime(new Date());
        blVideoMapper.insert(video);
    }

    @Override
    public void update(BlVideo video) {
        blVideoMapper.updateByPrimaryKey(video);
    }

    @Override
    public BlVideo getById(Integer id) {
        BlVideo video=blVideoMapper.selectByPrimaryKey(id);
        return video;
    }

    @Override
    public void deleteById(Integer id) {
        BlVideo video=new BlVideo();
        video.setId(id);
        video.setDeleted(StateEnums.DELETED.getCode());
        blVideoMapper.updateByPrimaryKeySelective(video);
    }

    @Override
    public void enableById(Integer id) {
        BlVideo video=new BlVideo();
        video.setId(id);
        video.setEnable(StateEnums.ENABLED.getCode());
        blVideoMapper.updateByPrimaryKeySelective(video);
    }

    @Override
    public void disableById(Integer id) {
        BlVideo video=new BlVideo();
        video.setId(id);
        video.setEnable(StateEnums.NOT_ENABLE.getCode());
        blVideoMapper.updateByPrimaryKeySelective(video);
    }

    @Override
    public Page<BlVideo> getAll(Page<BlVideo> page) {
        Example example =new Example(BlVideo.class);
        example.createCriteria().andEqualTo("enable",StateEnums.ENABLED.getCode()).andEqualTo("deleted", StateEnums.NOT_DELETED.getCode());
        List<BlVideo> blVideoList=blVideoMapper.selectByExample(example);
        page.setList(blVideoList);
        return page;
    }
}
