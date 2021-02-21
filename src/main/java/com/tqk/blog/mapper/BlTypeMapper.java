package com.tqk.blog.mapper;

import com.tqk.blog.pojo.BlType;
import com.tqk.blog.utils.MyMapper;

import java.util.List;

/**
 * @author Administrator
 */
public interface BlTypeMapper extends MyMapper<BlType> {
    /**
     * 查询分类列表
     * @turn
     */
    List<BlType> getTypeList();
    /**
     * 插入
     * @param type
     */
    void insertBlType(BlType type);
}