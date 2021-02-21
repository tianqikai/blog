package com.tqk.blog.service;

import com.tqk.blog.pojo.BlAbout;
import com.tqk.blog.utils.Page;

/**
 * <p>
 * </p>
 *
 * @author 田起凯
 * @date 2020年10月13日22:10:55
 * @Version 1.0
 */
public interface AboutService {

    /**
     * 保存
     * @param about
     */
    void save(BlAbout about);

    /**
     * 更新
     * @param about
     */
    void update(BlAbout about);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    BlAbout getById(String id);

    /**
     * 阅读
     * @return
     */
    BlAbout read();

    /**
     * 根据id删除
     * @param id
     */
    void deleteById(String id);

    /**
     * 根据id启用
     * @param id
     */
    void enableById(String id);

    /**
     * 根据id弃用
     * @param id
     */
    void disableById(String id);

    /**
     * 分页查询
     * @param page
     * @return
     */
    Page<BlAbout> getByPage(Page<BlAbout> page);
}
