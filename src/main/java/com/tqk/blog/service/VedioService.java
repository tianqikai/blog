package com.tqk.blog.service;

import com.tqk.blog.pojo.BlVedio;

/**
 * @program: blog
 * @description:
 * @author: tianqikai
 * @create: 2021-03-19 23:57
 **/
public interface VedioService  {
    /**
     * 保存
     * @param vedio
     */
    void save(BlVedio vedio);

    /**
     * 更新
     * @param vedio
     */
    void update(BlVedio vedio);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    BlVedio getById(Integer id);

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
}
