package com.tqk.blog.service;

import java.util.Map;

/**
 * @ClassName：UploadService
 * @description: 上传文件
 * @author: tianqikai
 * @date : 21:55 2021/5/4
 */
public interface UploadService {
    public abstract String uploadFile(Map<String,Object> fileMap);
    public abstract void delFile(String fileID);
}
