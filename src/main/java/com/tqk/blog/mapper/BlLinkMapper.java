package com.tqk.blog.mapper;

import com.tqk.blog.pojo.BlLink;
import com.tqk.blog.utils.MyMapper;

import java.util.List;

/**
 * @author Administrator
 */
public interface BlLinkMapper extends MyMapper<BlLink> {



    /**
     * 修改
     * @param link
     */
    void updateByIdAndVersion(BlLink link);



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