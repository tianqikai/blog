package com.tqk.blog.service;

import com.tqk.blog.pojo.BlMusic;
import com.tqk.blog.utils.Page;

import java.util.List;

/**
 * <p>
 * </p>
 *
 * @author tianqikai
 * @date 2020年10月25日23:58:50
 * @Version 1.0
 */
public interface MusicService {

    /**
     * 保存
     * @param music
     */
    void save(BlMusic music);

    /**
     * 更新
     * @param music
     */
    void update(BlMusic music);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    BlMusic getById(Integer id);

    /**
     * 根据id删除
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 启用
     * @param id
     */
    void enableById(Integer id);

    /**
     * 弃用
     * @param id
     */
    void disableById(Integer id);

    /**
     * 分页
     * @param page
     * @return
     */
    Page<BlMusic> getByPage(Page<BlMusic> page);

    /**
     * 前台查询
     * @return
     */
    List<BlMusic> getList();
}
