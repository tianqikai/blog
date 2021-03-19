package com.tqk.blog.utils;


import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.tqk.blog.config.UploadConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @program: fastdfs-demo
 * @author: 雷哥
 * @create: 2020-01-03 10:48
 **/
@Component
@EnableConfigurationProperties(UploadConfig.class)
@Slf4j
public class UploadService {
    @Autowired
    private FastFileStorageClient storageClient;

    @Autowired
    private UploadConfig uploadConfig;

    /**
     *
     * @param file
     * @return
     */
    public String uploadImage(MultipartFile file) {
        /**
         * MultipartFile 常见使用方法api
         * isEmpty()  判断是否为空，或者上传的文件是否有内容
         * getBytes() 将文件内容转化成一个byte[] 返回
         * getSize()  返回文件大小 以字节为单位
         * getContentType() 返回文件的内容类型
         *
         */
        // 1、校验文件类型
        String contentType = file.getContentType();
        log.info("contentType:"+contentType);
        log.info("uploadConfig.getAllowTypes():"+uploadConfig.getAllowTypes());
        if (!uploadConfig.getAllowTypes().contains(contentType)) {
            throw new RuntimeException("文件类型不支持");
        }
        // 2、校验文件内容
//        try {
//            BufferedImage image = ImageIO.read(file.getInputStream());
//            if (image == null || image.getWidth() == 0 || image.getHeight() == 0) {
//                throw new RuntimeException("上传文件有问题");
//            }
//        } catch (IOException e) {
//            log.error("校验文件内容失败....{}", e);
//            throw new RuntimeException("校验文件内容失败"+e.getMessage());
//        }
        try {
            // 3、上传到FastDFS
            // 3.1、获取扩展名
            String extension = StringUtils.substringAfterLast(file.getOriginalFilename(), ".");
            // 3.2、上传
            StorePath storePath = storageClient.uploadFile(file.getInputStream(), file.getSize(), extension, null);
            // 返回路径
            return uploadConfig.getBaseUrl() + storePath.getFullPath();
        } catch (IOException e) {
            log.error("【文件上传】上传文件失败！....{}", e);
            throw new RuntimeException("【文件上传】上传文件失败！" + e.getMessage());
        }
    }
}
