package com.zx.redcross.resolver.argument;

import java.io.BufferedReader;
import java.util.HashMap;

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
import com.zx.redcross.entity.OsDistrict;


/**
 * 将用户id注入到指定的对象中
 * @author Daryl
 */
public class CustomerMethodArgumentResolver implements HandlerMethodArgumentResolver{

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		 return true;
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {		
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest();
		Integer id=123;
//		Admin admin=new Admin();
//		admin.setId(id);
		
		
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
	        	System.out.println(buf);
	        	System.out.println(rd);
	            sb.append(buf, 0, rd);
	        }
	        String osDistrictId="\"osDistrict\": {//从数据库查询地址最底层地址的ID\n" + 
	        		"        \"id\": 186\n" + 
	        		"    },";
	        sb.insert(1, osDistrictId);
//	        HashMap map=new HashMap();
//	        HashMap mapSub=new HashMap();
//	        mapSub.put("id", 123);
//	        map.put("osDistrict", mapSub);
//	        sb.append(new StringBuffer("STRINGBUFFER"), 6, 12);
	        System.out.println("11111="+sb);
	        System.out.println("222="+JSON.parseObject(sb.toString(), parameter.getParameterType()));
	        
	        // 利用fastjson转换为对应的类型
	        if(JSONObjectWrapper.class.isAssignableFrom(parameter.getParameterType())){
	        	 
	        	return new JSONObjectWrapper(JSON.parseObject(sb.toString()));
	        } else {
	        	
	            return JSON.parseObject(sb.toString(), parameter.getParameterType());
	        }


	}
	
}
