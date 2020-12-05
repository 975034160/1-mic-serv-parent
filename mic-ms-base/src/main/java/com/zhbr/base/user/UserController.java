package com.zhbr.base.user;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.zhbr.base.common.ResponseData;


@RestController
@RequestMapping("/user")
public class UserController {
	
	
	
	@ResponseBody
	@RequestMapping(value = "/login",method =RequestMethod.POST)
	public ResponseData login(@RequestParam String username,@RequestParam String password,HttpServletResponse response) {
		/**
		 * code 1 登录成功 0 用户不存在或密码错误
		 */

		ResponseData responseData = new ResponseData();
		Map<String, Object> data = new HashMap<>();

		
		if (username.equals("admin")) {
			if (password.equals("123456")) {
				responseData.setCode(1);
				responseData.setMsg("登录成功");
				data.put("username", username);
				data.put("token", username+"helloworld");
			}else {
				responseData.setCode(0);
				responseData.setMsg("密码错误");
			}
		}else {
			responseData.setCode(0);
			responseData.setMsg("用户不存在");
		}
		

		responseData.setData(data);
		return responseData;
	}

}
