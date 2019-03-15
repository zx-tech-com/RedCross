package com.zx.redcross.controller.course;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zx.redcross.annotation.FrontEnd;
import com.zx.redcross.entity.ExamOrder;
import com.zx.redcross.service.course.GroupOrderServ;
import com.zx.redcross.service.course.IExamOrderServ;
import com.zx.redcross.tool.Constant;
import com.zx.redcross.tool.MapUtils;

@RestController
@RequestMapping("grouporder")
public class GroupOrder {
	@Autowired
	private GroupOrderServ groupOrderServImpl;
	
	
	
	@FrontEnd
	@RequestMapping("/addExamOrder")
	public Map<String,Object> addGroupOrder(@RequestBody ExamOrder examOrder){
		Map<String, Object> map = MapUtils.getHashMapInstance();
		Map<String, Object> dataMap = MapUtils.getHashMapInstance();
		
		map.put(Constant.STATUS,Constant.STATUS_FAILURE);
		
		//Boolean flag = examOrderServImpl.addExamOrder(examOrder);
		
		
		return map;
	}

}
