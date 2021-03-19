package com.tqk.blog;

import com.tqk.blog.pojo.BlVedio;
import com.tqk.blog.service.VedioService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

/**
 * @program: blog
 * @description:
 * @author: tianqikai
 * @create: 2021-03-20 00:44
 **/
@SpringBootTest
public class VedioServerTest {
    @Autowired
    VedioService vedioService;

    @Test
    public void vedioSave(){
        BlVedio vedio=new BlVedio();
        vedio.setId(1);
        vedio.setEnable(1);
        vedio.setCover("http://pic33.nipic.com/20131007/13639685_123501617185_2.jpg");
        vedio.setCreatedTime(new Date());
        vedio.setName("test");
        vedio.setUrl("https://cdn.theguardian.tv/webM/2015/07/20/150716YesMen_synd_768k_vp8.webm");
        vedio.setDeleted(1);
        System.out.println(vedio.toString());
        vedioService.save(vedio);
    }

}
