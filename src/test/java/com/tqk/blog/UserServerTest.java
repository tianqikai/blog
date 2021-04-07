package com.tqk.blog;

import com.tqk.blog.pojo.BlUser;
import com.tqk.blog.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

/**
 * @program: UserServerTest
 * @description:
 * @author: tianqikai
 * @create: 2021-04-03 09:32
 **/
@SpringBootTest
public class UserServerTest {
    @Autowired
    UserService userService;

    @Test
    public void userServiceTest01(){
        BlUser blUser=new BlUser();
        blUser.setUsername("tianqikai");
        blUser.setPassword("953811");
        blUser.setUserEmail("3540015085@qq.com");
        blUser.setSex(1);
        blUser.setHeader("头像");
        blUser.setCreatedTime(new Date());
        blUser.setUpdateTime(new Date());
        blUser.setName("田起凯");
        blUser.setNickname("哈哈哈");
        userService.register(blUser);

    }
    @Test
    public void userServiceTest02(){
        BlUser blUser=new BlUser();
//        blUser.setUsername("tianqikai");
        blUser.setPassword("953811");
        blUser.setUserEmail("3540015085@qq.com");
//        blUser.setSex(1);
//        blUser.setHeader("头像");
//        blUser.setCreatedTime(new Date());
//        blUser.setUpdateTime(new Date());
//        blUser.setName("田起凯");
//        blUser.setNickname("哈哈哈");
        userService.login(blUser);
    }
}
