package com.tqk.blog;

import com.tqk.blog.utils.BASE64DecodedMultipartFile;
import com.tqk.blog.utils.Base64Util;
import com.tqk.blog.utils.FastDfsUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@SpringBootTest
class FastDfsUtilsTests {
	@Autowired
    FastDfsUtils fastDfsUtils;

	@Test
	void contextLoads() throws IOException {
		String base64= Base64Util.getFileBase64("C:\\Users\\Administrator\\Desktop\\abc.jpeg");
		System.out.println(base64);
		base64="data:image/jpeg;base64,"+base64;
		MultipartFile multipartFile= BASE64DecodedMultipartFile.base64ToMultipart(base64);
		System.out.println(multipartFile);
		System.out.println("multipartFile:");
		String url= fastDfsUtils.uploadImage(multipartFile);
		System.out.println(url);
	}
	@Test
	void contextDownload() throws IOException {
		HttpServletResponse response = null;
		fastDfsUtils.downLoadFile(response,
				"http://49.232.21.151/group1/M00/00/00/rBUAEGBf7WaAJB1nAAAVsh5RkC8253.jpg",
				"C:\\Users\\Administrator\\Desktop\\abc111.jpg");
	}
	@Test
	void contextDelete() throws IOException {
		fastDfsUtils.deleteFile("http://49.232.21.151/group1/M00/00/00/rBUAEGBXYKmAQtLnAAQDVUz3LtQ445.jpg");
	}
}
