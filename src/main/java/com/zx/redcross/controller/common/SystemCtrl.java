package com.zx.redcross.controller.common;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zx.redcross.annotation.Open;
import com.zx.redcross.tool.Constant;
import com.zx.redcross.tool.MapUtils;

/**
 * 用于获取一些常用的数据
 * @author Daryl
 */
@RestController
@RequestMapping("common")
public class SystemCtrl {

	@RequestMapping("fileBasePath")
	@Open
	public Map<String,Object> getFileBasePath(HttpSession session,
			HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> map = MapUtils.getHashMapInstance();
		map.put(Constant.STATUS, Constant.STATUS_SUCCESS);
		map.put(Constant.DATA, Constant.ACCESS_BASE_PATH);
		return map;
	}
	
}
