package com.zx.redcross.controller.my;

import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zx.redcross.tool.Constant;
import com.zx.redcross.tool.MapUtils;

/**
 * 用户Token不合法信息
 * @author Daryl
 */
//@RestController
public class AuthenticationController {
	
	@RequestMapping("authentication/{status}")
	public Map<String,Object> authentication (@PathVariable(required=true) int status){
		Map<String,Object> map = MapUtils.getHashMapInstance();
		if(status == 1) {//Token过期
			map.put(Constant.TOKEN_STATUS, Constant.TOKEN_EXPIRED);
			map.put(Constant.ERROR_MESSAGE, "Token已过期，请重新登录");
		}else {//Token伪造
			map.put(Constant.TOKEN_STATUS,Constant.TOKEN_INVALID);
			map.put(Constant.ERROR_MESSAGE, "请先登录");
		}
		return map;
	}
	
}
