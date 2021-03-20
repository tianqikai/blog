package com.tqk.blog.mapper;


import com.tqk.blog.pojo.BlVideo;
import com.tqk.blog.utils.MyMapper;
//BlVideoMapper
//BlVideoMapper
public interface BlVideoMapper extends MyMapper<BlVideo> {
    int selectMaxVedioId();
}