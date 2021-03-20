package com.tqk.blog;

import com.tqk.blog.pojo.BlVideo;
import com.tqk.blog.service.VideoService;
import com.tqk.blog.utils.Page;

import org.junit.Ignore;
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
public class VideoServerTest {
    @Autowired
    VideoService videoService;

    @Test
    public void videoSave(){
        BlVideo video=new BlVideo();
//        video.setId(1);
        video.setEnable(1);
        video.setCover("http://pic33.nipic.com/20131007/13639685_123501617185_2.jpg");
        video.setCreatedTime(new Date());
        video.setName("test");
        video.setUrl("https://cdn.theguardian.tv/webM/2015/07/20/150716YesMen_synd_768k_vp8.webm");
        video.setDeleted(1);
        System.out.println(video.toString());
        videoService.save(video);
    }

    @Test
    @Ignore
    public void vedioUpdate(){
        BlVideo video=new BlVideo();
        video.setId(1);
        video.setEnable(1);
        video.setCover("http://49.232.21.151/group1/M00/00/00/rBUAEGA8-YGAFLe-AABYa04IBG0170.jpg");
        video.setCreatedTime(new Date());
        video.setName("test");
        video.setUrl("https://cdn.theguardian.tv/webM/2015/07/20/150716YesMen_synd_768k_vp8.webm");
        video.setDeleted(1);
        System.out.println(video.toString());
        videoService.update(video);
    }
    @Test
    @Ignore
    public void vediogetById(){

        BlVideo video= videoService.getById(1);
        System.out.println(video.toString());
    }
    @Test
    @Ignore
    public void enableById(){

        videoService.enableById(1);
    }
    @Test
    @Ignore
    public void disableById(){

        videoService.disableById(1);
    }
    @Test
    @Ignore
    public void deleteById(){

        videoService.deleteById(1);
    }
    @Test
    @Ignore
    public void getAll(){
        Page<BlVideo> Bl=new Page<>();
        Bl =videoService.getAll(Bl);
        System.out.println(Bl);
    }
}
