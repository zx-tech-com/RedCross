package com.zx.redcross.controller.index;

import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zx.redcross.annotation.Open;
import com.zx.redcross.tool.Constant;
import com.zx.redcross.tool.MapUtils;

@RestController
public class VrAdddressCtrl {
	
	
	@RequestMapping("/getVrAdddress")
	@Open
	public Map<String,Object> saveVideoRecord() {
		Map<String,Object> map = MapUtils.getHashMapInstance();
		map.put(Constant.DATA, Constant.VR_ADDRESS);
		map.put(Constant.STATUS, Constant.STATUS_SUCCESS);
		return map;
	}
}
