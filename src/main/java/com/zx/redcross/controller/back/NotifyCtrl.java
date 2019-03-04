package com.zx.redcross.controller.back;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 支付宝/微信的回调接口
 * @author Daryl
 */
@Controller
public class NotifyCtrl {

	@RequestMapping("alipay/authResult")
	public void aliNotify(HttpServletRequest request) {
		Enumeration<String> names = request.getParameterNames();
		while(names.hasMoreElements()) {
			String name = names.nextElement();
			System.err.println(name + " : " + request.getParameter(name));
		}
	}
	
	@RequestMapping("wechat/authResult")
	public void weChatNotify(HttpServletRequest request) {
		Enumeration<String> names = request.getParameterNames();
		while(names.hasMoreElements()) {
			String name = names.nextElement();
			System.err.println(name + " : " + request.getParameter(name));
		}
	}
	
}
