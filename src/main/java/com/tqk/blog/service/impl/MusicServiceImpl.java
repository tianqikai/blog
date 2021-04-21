package com.tqk.blog.service.impl;


import com.tqk.blog.enums.ResultEnum;
import com.tqk.blog.enums.StateEnums;
import com.tqk.blog.execption.BlogException;
import com.tqk.blog.mapper.BlMusicMapper;
import com.tqk.blog.pojo.BlMusic;
import com.tqk.blog.service.MusicService;
import com.tqk.blog.utils.FastDfsUtils;
import com.tqk.blog.utils.Page;
import com.tqk.blog.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * </p>
 *
 * @author tianqikai
 * @date 2020年10月13日23:35:40
 * @Version 1.0
 */
@Service
@Slf4j
public class MusicServiceImpl implements MusicService {

    @Autowired
    private BlMusicMapper musicMapper;
    @Autowired
    private  FastDfsUtils fastDfsUtils;

    @Override
    public void save(BlMusic music) {
        Integer maxId= musicMapper.selectMaxid();
        if(music.getId()==null){
           music.setId(maxId+1);
        }else if(maxId>=music.getId()){
            throw new BlogException(ResultEnum.ERROR, "音乐编号错误！");
        }
        music.setDeleted(StateEnums.NOT_DELETED.getCode());
        music.setEnable(StateEnums.ENABLED.getCode());
        music.setCreatedTime(new Date());
        musicMapper.insert(music);
    }

    @Override
    public void update(BlMusic music) {
        BlMusic oldMusic=musicMapper.selectByPrimaryKey(music.getId());
        musicMapper.updateByPrimaryKey(music);
        if(StringUtils.isNoneBlank(oldMusic.getCover())&&!oldMusic.getUrl().equals(music.getUrl())){
            //先删除原来的文件
            fastDfsUtils.deleteFile(oldMusic.getUrl());
        }
        if(StringUtils.isNoneBlank(oldMusic.getCover())&&!oldMusic.getCover().equals(music.getCover())){
            //先删除原来的封面图片
            fastDfsUtils.deleteFile(oldMusic.getCover());
        }
    }

    @Override
    public BlMusic getById(Integer id) {

        return musicMapper.selectByPrimaryKey(id);
    }

    @Override
    public void deleteById(Integer id) {
        BlMusic blMusic=new BlMusic();
        blMusic.setId(id);
        blMusic.setDeleted(1);
        musicMapper.updateByPrimaryKeySelective(blMusic);
    }

    @Override
    public void enableById(Integer id) {
        BlMusic music = musicMapper.selectByPrimaryKey(id);
        music.setEnable(StateEnums.ENABLED.getCode());
        musicMapper.updateByPrimaryKey(music);
    }

    @Override
    public void disableById(Integer id) {
        BlMusic music = musicMapper.selectByPrimaryKey(id);
        music.setEnable(StateEnums.NOT_ENABLE.getCode());
        musicMapper.updateByPrimaryKey(music);
    }

    @Override
    public Page<BlMusic> getByPage(Page<BlMusic> page) {
        // 查询数据
        List<BlMusic> musicList = musicMapper.getByPage(page);
        page.setList(musicList);
        // 查询总数
        int totalCount = musicMapper.getCountByPage(page);
        page.setTotalCount(totalCount);
        return page;
    }

    @Override
    public List<BlMusic> getList() {
        Example example=new Example(BlMusic.class);
        example.createCriteria().andEqualTo("enable",StateEnums.ENABLED.getCode()).andEqualTo("deleted",StateEnums.NOT_DELETED.getCode());
        return musicMapper.selectByExample(example);
    }
}
