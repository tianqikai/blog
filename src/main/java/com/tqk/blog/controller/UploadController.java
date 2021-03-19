package com.tqk.blog.controller;

import com.tqk.blog.enums.ResultEnum;
import com.tqk.blog.utils.Result;
import com.tqk.blog.utils.UploadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * @program: blog
 * @description:
 * @author: tianqikai
 * @create: 2020-12-27 11:11
 **/

/**
 * @RestController注解相当于@ResponseBody ＋ @Controller合在一起的作用。
 * 如果只是使用@RestController注解Controller，则Controller中的方法无法返回jsp页面，
 * 或者html，配置的视图解析器 InternalResourceViewResolver不起作用，返回的内容就是Return 里的内容。
 */
@Controller
@ResponseBody
@RequestMapping("/upload")
@Slf4j
public class UploadController {
    @Autowired
    private UploadService uploadService;

    /**
     * 上传文件
     * @param file
     */
    @RequestMapping(value = "/uploadImage", method = RequestMethod.POST)
    public Result<String> uploadImage(@RequestBody MultipartFile file){
        int respCode= ResultEnum.SUCCESS.getCode();
        String url="";
        String msg="上传成功!";
        try{
            url = uploadService.uploadImage(file);
        }catch (Exception e){
            log.error("上传文件失败，[{}]",e);
            msg=e.getMessage();
            respCode=ResultEnum.ERROR.getCode();
        }
        return new Result<>(respCode,msg, url);
    }

}
