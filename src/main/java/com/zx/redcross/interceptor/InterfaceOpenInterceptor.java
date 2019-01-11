package com.zx.redcross.interceptor;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.zx.redcross.annotation.Open;
import com.zx.redcross.tool.Constant;
import com.zx.redcross.tool.JWTUtils;

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
			if(null == method.getMethodAnnotation(Open.class))//未被@Open修饰直接返回true
				return true;
			else {//验证token
				String token = request.getHeader(Constant.TOKEN);
				Map<String,Object> map = JWTUtils.validateToken(token);
				switch((String)map.get(Constant.TOKEN_STATUS)) {
					case Constant.TOKEN_VALID :
						return true;
					case Constant.TOKEN_EXPIRED  :
						response.setStatus(Constant.HTTP_STATUS_302);
						response.sendRedirect("/RedCross/authentication/1");
						return false;
					case Constant.TOKEN_INVALID  :
						response.setStatus(Constant.HTTP_STATUS_302);
						response.sendRedirect("/RedCross/authentication/0");
						return false;
				}
			}
		}
		return true;
	}
	
	
}
