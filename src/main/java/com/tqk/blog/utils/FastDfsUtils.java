package com.tqk.blog.utils;


import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.domain.proto.storage.DownloadByteArray;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.tqk.blog.config.UploadConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: fastdfs-tool
 * @author: tianqikai
 * @create: 2020-01-03 10:48
 **/
@Component
@EnableConfigurationProperties(UploadConfig.class)
@Slf4j
public class FastDfsUtils {
    @Autowired
    private FastFileStorageClient storageClient;

    @Autowired
    private UploadConfig uploadConfig;

    /**
     * @Method： uploadImage
     * @Description： 上传文件
     * @Date： 2021/4/16 22:56
     * @Author： tianqikai
     * @Version 1.0
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
        log.info("contentType:" + contentType);
        log.info("uploadConfig.getAllowTypes():" + uploadConfig.getAllowTypes());
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

    /**
     * @param fileUrl
     * @Method： deleteFile
     * @Description： 根据fileUrl删除fastdfs中的文件
     * @Date： 2021/4/16 23:09
     * @Author： tianqikai
     * @Version 1.0
     */
    public  synchronized void deleteFile(String fileUrl) {
        if (StringUtils.isEmpty(fileUrl)) {
            return;
        }
        StorePath storePath = StorePath.parseFromUrl(fileUrl);
        System.out.println(storePath.getGroup());
        System.out.println(storePath.getPath());
        storageClient.deleteFile(storePath.getGroup(), storePath.getPath());
    }


    /**
     * @param fileUrl  fasturl文件url
     * @param fileName 下载的文件命名名字
     * @Method： downLoadFile
     * @Description：下载文件
     * @Date： 2021/4/16 23:08
     * @Author： tianqikai
     * @Version 1.0
     */
    public void downLoadFile(HttpServletResponse response, String fileUrl, String fileName) {
        ReentrantLock lock = new ReentrantLock();
        byte[] bs = null;
        lock.lock();
        try {
            FileOutputStream fileOutputStream=new FileOutputStream(fileName);
//            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            StorePath storePath = StorePath.parseFromUrl(fileUrl);
            bs = storageClient.downloadFile(storePath.getGroup(), storePath.getPath(), new DownloadByteArray());
//            response.getOutputStream().write(bs);
            fileOutputStream.write(bs);
        } catch (Exception e) {
            log.error("【文件下载】下载文件失败！....{}", e);
            throw new RuntimeException("【文件下载】下载文件失败！" + e.getMessage());
        } finally {
            lock.unlock();
        }
    }
}

