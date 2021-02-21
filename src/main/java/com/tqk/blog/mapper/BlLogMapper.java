package com.tqk.blog.mapper;

import com.tqk.blog.pojo.BlLog;
import com.tqk.blog.utils.MyMapper;
import com.tqk.blog.utils.Page;

import java.util.List;

/**
 * @author Administrator
 */
public interface BlLogMapper extends MyMapper<BlLog> {

    /**
     * 保存
     * @param logger
     */
    void save(BlLog logger);

    /**
     * 分页查询
     * @param page
     * @return
     */
    List<BlLog> getByPage(Page<BlLog> page);

    /**
     * 查询总数
     * @param page
     * @return
     */
    int getCountByPage(Page<BlLog> page);

    /**
     * 根据id删除
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 根据id列表删除
     * @param ids
     */
    void deleteByIds(List<Integer> ids);

    /**
     * 查询全部
     * @return
     */
    List<BlLog> getAll();
}