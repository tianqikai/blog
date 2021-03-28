package com.tqk.blog;

import com.tqk.blog.pojo.BlAdmin;
import com.tqk.blog.service.AdminService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @program: blog
 * @description:
 * @author: tianqikai
 * @create: 2021-03-28 20:58
 **/
@SpringBootTest
public class Admintest {
    @Autowired
    AdminService adminService;

    @Test
    void checkPassword(){
        BlAdmin admin=new BlAdmin();
        admin.setId(1);
        admin.setPassword("96e79218965eb72c92a549dd5a330112");
       boolean bool= adminService.checkPassword(admin);
        System.out.println("reslut:"+bool);
        if(bool){
            System.out.println("reslut:校验密码成功");
        }else{
            System.out.println("reslut:校验密码失败");
        }
    }
}
