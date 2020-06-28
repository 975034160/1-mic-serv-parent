package com.miczuul.filter;

import com.miczuul.common.RedisUtil;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;


public class APILoginFilter extends ZuulFilter {

	
	@Autowired
	RedisUtil redisUtil;
	
	@Override
	public boolean shouldFilter() {
		// TODO Auto-generated method stub

        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
 
        System.out.println(request.getRequestURI()); 
        System.out.println(request.getRequestURL()); 
 
        //ACL
        //进行拦截，就会进入下面的 run方法中
        //检查路径请求是否需要拦截
        if (request.getRequestURI().contains("/api/")){
            return true;
        }
         //不拦截，放行
		return false;
	}

	@Override
	public Object run() throws ZuulException {
		// TODO Auto-generated method stub
		//获取上下文
		RequestContext requestContext = RequestContext.getCurrentContext();
		//获取request请求
		HttpServletRequest servletRequest = requestContext.getRequest();
		//从请求头中获取token,如果请求头中不存在，就尝试从请求参数中获取
		String token1 = servletRequest.getHeader("token");
		if(StringUtils.isBlank(token1)) {
			token1 = servletRequest.getParameter("token");
		}
		//验证token是否存在，如果不存在就返回错误信息
		if(StringUtils.isBlank(token1)) {
			//停止访问，并返回出错的消息
			requestContext.setSendZuulResponse(false);
			//防止中文乱码
            requestContext.getResponse().setContentType("text/html;charset=UTF-8");
            //设置返回的状态码和正文
            requestContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
            requestContext.setResponseBody("未登录！");		
		} else {
			//验证请求中的token是否在redis中存在
			String token = redisUtil.get(token1);
			if(StringUtils.isBlank(token)) {
				//停止访问，并返回出错的消息
				requestContext.setSendZuulResponse(false);
				//防止中文乱码
	            requestContext.getResponse().setContentType("text/html;charset=UTF-8");
	            //设置返回的状态码和正文
	            requestContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
	            requestContext.setResponseBody("登录已过期，请重新登录！");
			}
		}
	
		return null;
	}

	@Override
	public String filterType() {
		// TODO Auto-generated method stub
		return PRE_TYPE;
	}

	@Override
	public int filterOrder() {
		// TODO Auto-generated method stub
		return 4;
	}

}
