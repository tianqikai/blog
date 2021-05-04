package com.tqk.blog;

import com.tqk.blog.service.impl.UploadServiceImpl;
import com.tqk.blog.utils.BASE64DecodedMultipartFile;
import com.tqk.blog.utils.Base64Util;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName：UploadServiceImplTest
 * @description:
 * @author: tianqikai
 * @date : 22:23 2021/5/4
 */
@SpringBootTest
public class UploadServiceImplTest {
    @Autowired
    UploadServiceImpl uploadService;

    @Test
    void UploadFile() throws IOException {
        String base64= Base64Util.getFileBase64("C:\\Users\\Administrator\\Desktop\\阿里巴巴Java开发手册v1.2.0.pdf");
//        System.out.println(base64);
        base64="data:file/pdf;base64,"+base64;
        MultipartFile multipartFile= BASE64DecodedMultipartFile.base64ToMultipart(base64);
        System.out.println(multipartFile);
        System.out.println("multipartFile:");
        Map<String,Object> map=new HashMap<>();
        map.put("MultipartFile",multipartFile);
        map.put("fileName","test文件");
        System.out.println(map.get("MultipartFile"));
        System.out.println(map.get("fileName"));
        String url= uploadService.uploadFile(map);
        System.out.println(url);
    }
    @Test
    void delFile(){
        //阿里巴巴Java开发手册v1.2.0
        uploadService.delFile("http://49.232.21.151/group1/M00/00/00/rBUAEGCRZD6AEfIdABInMJhYML452.jpeg");
        uploadService.delFile("http://49.232.21.151/group1/M00/00/00/rBUAEGCRZICAMe1kABInMJhYML490.jpeg");
    }
}
