package com.micuser.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.micuser.common.RedisUtil;
import com.micuser.common.ResponseData;
import com.micuser.entity.User;
import com.micuser.service.UserService;
import com.micuser.token.RedisTokenManager;
import com.micuser.token.TokenManager;

@Controller
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	TokenManager redisTokenManager;
	
	@Autowired
	RedisUtil redisUtil;
	
	
	
	@ResponseBody
	@RequestMapping("/findAllUser")
	public List<User> findAllUser(){
		List<User> list = userService.findAllUser();
		if (list.size()==0) {
			return null;
		}
		System.out.println(list.get(0));
		return list;
	}
	
	@ResponseBody
	@RequestMapping(value = "/login",method = RequestMethod.POST)
	public  ResponseData  login(@RequestParam String username,@RequestParam String password,HttpServletResponse response) {
		User user= userService.findByUsername(username);
		ResponseData responseData= new ResponseData();
		Map data = new HashMap<>();
		//验证失败时返回信息
		if(user==null) {
			responseData.setCode(-1);
			responseData.setMsg("登录失败,用户不存在");
			return responseData;
		}
		if(!user.getPassword().equals(password)) {
			responseData.setCode(-1);
			responseData.setMsg("登录失败,密码错误");
			return responseData;
		}
		//验证通过后  token 写到cookie中  返回到数据中
//		String token ="12346";
		String token = redisTokenManager.getToken(user);
		Cookie cookie = new Cookie("token", token);
		cookie.setPath("/");
		response.addCookie(cookie);
		
		responseData.setCode(0);
		data.put("token", token);
		responseData.setData(data);
		return responseData;
	}
	
	
	
}
