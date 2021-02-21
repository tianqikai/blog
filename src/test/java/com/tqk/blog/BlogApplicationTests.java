package com.tqk.blog;

import com.tqk.blog.utils.BASE64DecodedMultipartFile;
import com.tqk.blog.utils.Base64Util;
import com.tqk.blog.utils.UploadService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@SpringBootTest
class BlogApplicationTests {
	@Autowired
	UploadService uploadService;

	@Test
	void contextLoads() throws IOException {
		String base64= Base64Util.getFileBase64("C:\\Users\\Administrator\\Desktop\\abc.jpeg");
		System.out.println(base64);
		base64="data:image/jpeg;base64,"+base64;
		MultipartFile multipartFile= BASE64DecodedMultipartFile.base64ToMultipart(base64);
		System.out.println(multipartFile);
		uploadService.uploadImage(multipartFile);
	}

}
