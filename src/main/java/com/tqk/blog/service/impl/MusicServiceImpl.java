package com.tqk.blog.service.impl;


import com.tqk.blog.enums.ResultEnum;
import com.tqk.blog.enums.StateEnums;
import com.tqk.blog.execption.BlogException;
import com.tqk.blog.mapper.BlMusicMapper;
import com.tqk.blog.pojo.BlMusic;
import com.tqk.blog.service.MusicService;
import com.tqk.blog.utils.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

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

    @Override
    public void save(BlMusic music) {
        Integer maxId= musicMapper.selectMaxid();
        if(music.getId()==null){
           music.setId(maxId+1);
        }else if(maxId>=music.getId()){
            throw new BlogException(ResultEnum.ERROR, "音乐编号错误！");
        }
        musicMapper.insert(music);
    }

    @Override
    public void update(BlMusic music) {

        musicMapper.updateByPrimaryKey(music);
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
