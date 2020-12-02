package com.micuser.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.micuser.common.TokenUtil;


@Controller
@RequestMapping("/api")
public class UserApiController {

	@ResponseBody
	@RequestMapping("/test")
	public String test() {
		
		System.out.println(TokenUtil.getTokenUserId());
		
		return "您已登录成功！";
	}
	
	
}
