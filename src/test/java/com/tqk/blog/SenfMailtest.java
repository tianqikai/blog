package com.tqk.blog;

import com.tqk.blog.utils.SendmailUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @program: SenfMailtest
 * @description: 发送邮件
 * @author: tianqikai
 * @create: 2021-04-02 20:21
 **/
@SpringBootTest
public class SenfMailtest {
    @Test
    public void sendMail() throws Exception {
        SendmailUtil. sendEmail("3540015085@qq.com","test" ,"953811508");
    }
}
