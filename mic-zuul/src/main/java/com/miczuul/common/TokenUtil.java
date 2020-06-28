package com.miczuul.common;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class TokenUtil {

	public static String getTokenFromRequest() {
		String token = getRequest().getHeader("token");// 从 http 请求头中取出 token
		if(token==null) {
			token = getRequest().getParameter("token");
		}
		return token;
	}

	/**
	 * 获取request
	 * 
	 * @return
	 */
	public static HttpServletRequest getRequest() {
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		return requestAttributes == null ? null : requestAttributes.getRequest();
	}
}