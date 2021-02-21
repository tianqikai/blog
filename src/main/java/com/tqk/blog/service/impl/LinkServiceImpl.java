package com.tqk.blog.service.impl;


import com.tqk.blog.mapper.BlLinkMapper;
import com.tqk.blog.pojo.BlLink;
import com.tqk.blog.service.LinkService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * </p>
 *
 * @author tianqikai
 * @date 2020年10月25日00:10:24
 * @Version 1.0
 *
 */
@Service
@Slf4j
public class LinkServiceImpl implements LinkService {

    @Autowired
    private BlLinkMapper linkMapper;

    @Override
    public void save(BlLink link) {
        linkMapper.insert(link);
    }

    @Override
    public void update(BlLink link) {
        linkMapper.updateByIdAndVersion(link);
    }

    @Override
    public BlLink getById(Integer id) {
        return linkMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<BlLink> getAll() {
        return linkMapper.getAll();
    }

    @Override
    public void deleteById(Integer id) {
        linkMapper.deleteByPrimaryKey(id);
    }
}
