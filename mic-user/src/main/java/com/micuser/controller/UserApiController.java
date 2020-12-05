package com.micuser.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.micuser.common.ResponseData;
import com.micuser.common.TokenUtil;


@Controller
@RequestMapping("/api")
public class UserApiController {

	@ResponseBody
	@RequestMapping("/test")
	public ResponseData test() {
		ResponseData responseData = new ResponseData();
		responseData.setCode(200);
		responseData.setMsg("请求成功");
		responseData.setDesc("返回测试数据");
		Map<String, Object> data = new HashMap<>();
		data.put("name", "李朋朋");
		data.put("age", 26);
		data.put("weight", "60KG");
		responseData.setData(data);		
		return responseData; 
	}
	
	
}
