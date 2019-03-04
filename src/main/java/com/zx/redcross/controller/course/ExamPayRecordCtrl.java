package com.zx.redcross.controller.course;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zx.redcross.service.course.IExamPayRecordServ;
import com.zx.redcross.tool.Constant;
import com.zx.redcross.tool.MapUtils;

@RestController
@RequestMapping("pay")
public class ExamPayRecordCtrl {

	@Autowired
	private IExamPayRecordServ payImpl;
	
	@RequestMapping("ali/examorder")
	public Map<String,Object> saveExamPayRecord(String orderNumber){
		Map<String, Object> map = MapUtils.getHashMapInstance();
		map.put(Constant.DATA, payImpl.getSignedParams(orderNumber));
		map.put(Constant.STATUS, Constant.STATUS_SUCCESS);
		return map;
	}
	
	
}
