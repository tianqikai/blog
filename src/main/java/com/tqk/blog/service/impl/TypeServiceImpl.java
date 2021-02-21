package com.tqk.blog.service.impl;

import com.tqk.blog.enums.StateEnums;
import com.tqk.blog.execption.BlogException;
import com.tqk.blog.mapper.BlTypeMapper;
import com.tqk.blog.pojo.BlType;
import com.tqk.blog.service.TypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * <p>
 * 帖子类型表服务实现类
 * </p>
 *
 * @author 田起凯
 * @date 2020年10月22日22:37:06
 * @Version 1.0
 */
@Service
@Slf4j
public class TypeServiceImpl implements TypeService {

    @Autowired
    private BlTypeMapper typeMapper;

    @Override
    public void save(BlType type) {
        // 查询分类是否存在
        BlType blType = typeMapper.selectOne(type);
        if (blType != null) {
            throw new BlogException("该分类已存在！");
        }
        typeMapper.insertBlType(type);
    }

    @Override
    public List<BlType> getAll() {

        return typeMapper.selectAll();
    }

    @Override
    public List<BlType> getTypeList() {
        //  `enable` 是否启用，0否1是',
        //  `deleted` '是否删除，0否1是',
        Example example =new Example(BlType.class);
        example.createCriteria().andEqualTo("enable",StateEnums.ENABLED.getCode()).andEqualTo("deleted",StateEnums.NOT_DELETED.getCode());
        return typeMapper.selectByExample(example);
    }

    @Override
    public void update(BlType type) {
        typeMapper.updateByPrimaryKey(type);
    }

    @Override
    public void enableById(Integer id) {
        // 先查再启用
        BlType type = typeMapper.selectByPrimaryKey(id);
        type.setEnable(StateEnums.ENABLED.getCode());
        typeMapper.updateByPrimaryKey(type);
    }

    @Override
    public void disableById(Integer id) {
        // 先查再弃用
        BlType type = typeMapper.selectByPrimaryKey(id);
        type.setEnable(StateEnums.NOT_ENABLE.getCode());
        typeMapper.updateByPrimaryKey(type);
    }

    @Override
    public void deleteById(Integer id) {

        typeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public BlType getById(Integer id) {

        return typeMapper.selectByPrimaryKey(id);
    }
}
