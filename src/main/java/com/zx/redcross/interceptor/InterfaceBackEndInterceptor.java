package com.zx.redcross.interceptor;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSON;
import com.zx.redcross.annotation.BackEnd;
import com.zx.redcross.annotation.Open;
import com.zx.redcross.tool.Constant;
import com.zx.redcross.tool.MapUtils;


/**
 * 后台管理的拦截器.
 * @author Daryl
 */
public class InterfaceBackEndInterceptor  extends HandlerInterceptorAdapter{

	
	/*@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		Collection<String> headerNames = response.getHeaderNames();
		System.err.println("==============response===============");
		for(String header : headerNames) {
			System.out.println(header + " : " + response.getHeader(header));
		}
	}*/
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		if(!(handler instanceof HandlerMethod)) {
			return true;
		}
		
		HandlerMethod method = (HandlerMethod)handler;
		if(isOpenInterface(method))
			return true;
		else if(isBackEndInterface(request,method)) {//是后台接口
			return adminCheck(request,response);
		}
		else
			return true;
	}
	
	
	private boolean isOpenInterface(HandlerMethod method) {
		return null != method.getMethodAnnotation(Open.class);
	}
	
	
	private boolean isBackEndInterface(HttpServletRequest request,HandlerMethod method) {
		boolean backEndAnnotation = false;//是否被@BackEnd修饰
		boolean adminUrl = false;//路径中是否包括'admin'
		backEndAnnotation = null != method.getMethodAnnotation(BackEnd.class);
		adminUrl = request.getServletPath().contains("admin");
		
		return backEndAnnotation || adminUrl;
	}
	
	private boolean adminCheck(HttpServletRequest request,HttpServletResponse response) {
		if(request.getSession().getAttribute(Constant.ADMIN) != null)//已经登录
			return true;
		else {
			response.setContentType("application/json;charset=UTF-8");
			addLoginInfo(response);
			return false;
		}
	}
	
	private void addLoginInfo(HttpServletResponse response) {
		Map<String,Object> map = MapUtils.getHashMapInstance();
		try {
			PrintWriter writer = response.getWriter();
			map.put(Constant.STATUS, Constant.STATUS_FAILURE);
			map.put(Constant.ERROR_MESSAGE, "请先登录");
			writer.println(JSON.toJSONString(map));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
