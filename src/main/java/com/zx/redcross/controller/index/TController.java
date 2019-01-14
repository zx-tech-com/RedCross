package com.zx.redcross.controller.index;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController
public class TController {

	@RequestMapping("abc")
	public Map<String,Object> abc() {
		
		
		
		
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("abc", "123");
		return map;
	}
	
}
