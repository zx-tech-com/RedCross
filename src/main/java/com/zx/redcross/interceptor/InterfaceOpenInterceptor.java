package com.zx.redcross.interceptor;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.zx.redcross.annotation.Open;
import com.zx.redcross.tool.Constant;

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
			return null == method.getMethodAnnotation(Open.class) ? true : this.tokenValidate(request);
		}
		return true;
	}
	
	private boolean tokenValidate(HttpServletRequest request) {
		String uri = request.getRequestURI();
		String token = request.getHeader(Constant.TOKEN);
		return false;
	}
	
}
