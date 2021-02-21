package com.tqk.blog.service;

import com.tqk.blog.pojo.BlType;

import java.util.List;

/**
 * <p>
 * 帖子类型表服务层接口
 * </p>
 *
 * @author 田起凯
 * @date 2020年10月13日21:26:50
 * @Version 1.0
 *
 */
public interface TypeService {

    /**
     * 添加
     * @param type
     */
    void save(BlType type);

    /**
     * 查询所有
     * @return
     */
    List<BlType> getAll();

    /**
     * 前台查询所有
     * @return
     */
    List<BlType> getTypeList();

    /**
     * 更新
     * @param type
     */
    void update(BlType type);

    /**
     * 根据id启用
     * @param id
     */
    void enableById(Integer id);

    /**
     * 根据id弃用
     * @param id
     */
    void disableById(Integer id);

    /**
     * 根据id删除
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    BlType getById(Integer id);
}
