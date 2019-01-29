package com.zx.redcross.interceptor;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSON;
import com.nimbusds.jose.JOSEException;
import com.zx.redcross.annotation.Open;
import com.zx.redcross.tool.Constant;
import com.zx.redcross.tool.JWTUtils;
import com.zx.redcross.tool.MapUtils;

import net.minidev.json.parser.ParseException;

/**
 * 检测是否需要登录才可访问该接口
 * @author Daryl
 */
public class InterfaceOpenInterceptor  extends HandlerInterceptorAdapter{
	
//	private Logger logger = LogManager.getLogger();

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if(handler instanceof HandlerMethod) {
			HandlerMethod method = (HandlerMethod)handler;
			if(null != method.getMethodAnnotation(Open.class))//被@Open修饰直接返回true
				return true;
			else {//验证token
				response.setContentType("application/json;charset=UTF-8");
				String token = getToken(request);
				Map<String,Object> map = null;
				try {
					map = JWTUtils.validateToken(token);
				}catch(ParseException | JOSEException | java.text.ParseException e) {
					addTokenInvalidInfo(response);
					return false;
				}
				switch((String)map.get(Constant.TOKEN_STATUS)) {
					case Constant.TOKEN_VALID :
						return true;
					case Constant.TOKEN_EXPIRED  :
						addTokenExpiredInfo(response);
						return false;
					case Constant.TOKEN_INVALID  :
						addTokenInvalidInfo(response);
						return false;
				}
			}
		}
		return true;
	}
	
	private void addTokenInvalidInfo(HttpServletResponse response) {
		Map<String,Object> map = MapUtils.getHashMapInstance();
		try {
			PrintWriter writer = response.getWriter();
			map.put(Constant.TOKEN_STATUS,Constant.TOKEN_INVALID);
			map.put(Constant.ERROR_MESSAGE, "请先登录");
			writer.println(JSON.toJSONString(map));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void addTokenExpiredInfo(HttpServletResponse response) {
		Map<String,Object> map = MapUtils.getHashMapInstance();
		try {
			PrintWriter writer = response.getWriter();
			map.put(Constant.TOKEN_STATUS, Constant.TOKEN_EXPIRED);
			map.put(Constant.ERROR_MESSAGE, "Token已过期，请重新登录");
			writer.println(JSON.toJSONString(map));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private String getToken(HttpServletRequest request) {
		//尝试从header中获取token
		String token = request.getHeader(Constant.TOKEN);
		if(token == null) {
			//从url中获取
			token = request.getParameter(Constant.TOKEN);
		}
		return token;
	}
	
}
