package com.tqk.blog.mapper;

import com.tqk.blog.pojo.BlMusic;
import com.tqk.blog.utils.MyMapper;
import com.tqk.blog.utils.Page;

import java.util.List;

/**
 * @author Administrator
 */
public interface BlMusicMapper extends MyMapper<BlMusic> {
    /**
     * 分页查询
     * @param page
     * @turn
     */
    List<BlMusic> getByPage(Page<BlMusic> page);

    /**
     * 查询总数
     * @param page
     * @return
     */
    int getCountByPage(Page<BlMusic> page);
    /**
     * 查询最大的编号
     * @return
     */
    int selectMaxid();
}