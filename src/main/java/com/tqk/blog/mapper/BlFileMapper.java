package com.tqk.blog.mapper;


import com.tqk.blog.pojo.BlFile;
import com.tqk.blog.utils.MyMapper;

public interface BlFileMapper extends MyMapper<BlFile> {
    /**
     * @classname : BlFileMapper
     * @description: 上传文件入表
     */
    void insertFile(BlFile blFile);
}