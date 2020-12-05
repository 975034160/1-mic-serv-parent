package com.zhbr.base.user;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.zhbr.base.common.ResponseData;
import com.zhbr.bean.Message;


@RestController
@RequestMapping("/message")
public class MessageController {

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@ResponseBody
	@RequestMapping(value = "/getMessage",method =RequestMethod.GET)
	public ResponseData getMessage() {
		ResponseData responseData = new ResponseData();
		Map<String, List<Message>> data = new HashMap<>();
		List<Message> list = new ArrayList<>();
		for(int i=0; i<=20;i++) {
			list.add(new Message(i, "好友消息"+(i+1), "你的好友孟祥龙要请你吃饭！", sdf.format(new Date())));
		}
		data.put("messages", list);
		responseData.setCode(1);
		responseData.setMsg("请求成功");
		responseData.setData(data);
		return responseData;
	}
	
	
}
