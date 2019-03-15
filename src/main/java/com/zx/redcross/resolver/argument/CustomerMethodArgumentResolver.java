package com.zx.redcross.resolver.argument;

import java.io.BufferedReader;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.alibaba.fastjson.JSON;
import com.zx.redcross.annotation.ListAttribute;
/**
 * 将用户id注入到指定的对象中
 * @author Daryl
 */
public class CustomerMethodArgumentResolver implements HandlerMethodArgumentResolver{


	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.hasParameterAnnotation(ListAttribute.class);
	}

	
	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {		
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest();
		Integer id=(Integer) request.getAttribute("id");	
		 HttpServletRequest requestsub = webRequest.getNativeRequest(HttpServletRequest.class);
	        // content-type不是json的不处理
	        if (!requestsub.getContentType().contains("application/json")) {
	            return null;
	        }
	        // 把reqeust的body读取到StringBuilder
	        BufferedReader reader = requestsub.getReader();
	        StringBuilder sb = new StringBuilder();
	        System.out.println(sb);
	        char[] buf = new char[1024];
	        int rd;
	        while((rd = reader.read(buf)) != -1){
	            sb.append(buf, 0, rd);
	        }
	        Class<?>  parameterType=parameter.getParameterType();
	        if(id==null) {
	        	return null;
	        }
			 if(parameterType.getTypeName().substring(
					 parameterType.getTypeName().lastIndexOf(".")+1
					 ).equals("Customer")) {
				 String customerId="\"id\": \""+id+"\",";
			     sb.insert(1, customerId);
			 }else {
				 String customerId="\"customer\": {\n" + 
			        		"        \"id\":"+id+"\n" + 
			        		"    },";
			     sb.insert(1, customerId);
			 }
	        // 利用fastjson转换为对应的类型
	        if(JSONObjectWrapper.class.isAssignableFrom(parameter.getParameterType())){ 
	        	return new JSONObjectWrapper(JSON.parseObject(sb.toString()));
	        } else {	        	
	            return JSON.parseObject(sb.toString(), parameter.getParameterType());
	        }
	}
	
	
	
	
}
