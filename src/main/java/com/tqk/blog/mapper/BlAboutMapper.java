package com.tqk.blog.mapper;

import com.tqk.blog.pojo.BlAbout;
import com.tqk.blog.utils.MyMapper;
import com.tqk.blog.utils.Page;

import java.util.List;

/**
 * @author Administrator
 */
public interface BlAboutMapper extends MyMapper<BlAbout> {
    /**
     * 保存
     * @param about
     */
    void save(BlAbout about);
    /**
     * 根据id更新
     * @param about
     */
    void updateById(BlAbout about);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    BlAbout getById(String id);

    /**
     * 查询关于
     * @return
     */
    BlAbout getAbout();

    /**
     * 根据id删除
     * @param id
     */
    void deleteById(String id);

    /**
     * 更新启用状态
     * @param about
     */
    void updateEnable(BlAbout about);

    /**
     * 分页查询
     * @param page
     * @return
     */
    List<BlAbout> getByPage(Page<BlAbout> page);

    /**
     * 查询总数
     * @param page
     * @return
     */
    int getCountByPage(Page<BlAbout> page);
}