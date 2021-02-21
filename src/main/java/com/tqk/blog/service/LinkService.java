package com.tqk.blog.service;

import com.tqk.blog.pojo.BlLink;

import java.util.List;

/**
 * <p>
 * </p>
 *
 * @author tianqikai
 * @date 2020年10月24日11:08:54
 * @Version 1.0
 */
public interface LinkService {

    /**
     * 添加
     * @ram link
     */
    void save(BlLink link);

    /**
     * 修改
     * @param link
     */
    void update(BlLink link);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    BlLink getById(Integer id);

    /**
     * 查询所有
     * @return
     */
    List<BlLink> getAll();

    /**
     * 根据id删除
     * @param id
     */
    void deleteById(Integer id);
}
