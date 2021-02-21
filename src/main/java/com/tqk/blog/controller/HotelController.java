package com.tqk.blog.controller;







import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;









@RestController
@RequestMapping("/hotel")
public class HotelController {
	/**
	 * 网络调试
	 * @return
	 */
	@GetMapping("/welcome")
	@ResponseBody
	//get请求不能使用 @RequestBody LoginResquest loginResquest
	public Object welcome() {
		return "欢迎登陆莫兰迪酒店！";
	}

}
