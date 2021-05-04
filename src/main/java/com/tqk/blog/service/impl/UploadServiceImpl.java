package com.tqk.blog.service.impl;

import com.tqk.blog.enums.ResultEnum;
import com.tqk.blog.execption.BlogException;
import com.tqk.blog.mapper.BlFileMapper;
import com.tqk.blog.pojo.BlFile;
import com.tqk.blog.service.UploadService;
import com.tqk.blog.utils.FastDfsUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.entity.Example;

import java.util.Map;

/**
 * @ClassName：UploadServiceImpl
 * @description: 上传文件
 * @author: tianqikai
 * @date : 21:57 2021/5/4
 */
@Slf4j
@Service
public class UploadServiceImpl implements UploadService {
    @Autowired
    private FastDfsUtils fastDfsUtils;

    @Autowired
    private  BlFileMapper blFileMapper;
    @Override
    public String uploadFile(Map<String,Object> fileMap) {
        String url="";
        try{
            url = fastDfsUtils.uploadImage((MultipartFile)fileMap.get("MultipartFile"));
        }catch (Exception e){
            log.error("上传文件失败，[{}]",e);
            throw new BlogException(ResultEnum.ERROR.getCode(),e.getMessage());
        }
        BlFile blFile=new BlFile();
        blFile.setFileconnet(url);
        blFile.setName((String) fileMap.get("fileName"));
        blFileMapper.insertFile(blFile);
        return url;
    }

    @Override
    public void delFile(String filepath) {
        try{
            fastDfsUtils.deleteFile(filepath);
        }catch (Exception e){
            log.error("删除文件失败，[{}]",e);
            throw new BlogException(ResultEnum.ERROR.getCode(),e.getMessage());
        }

        Example example=new Example(BlFile.class);
        example.createCriteria().andEqualTo("fileconnet",filepath);
        blFileMapper.deleteByExample(example);
    }
}
