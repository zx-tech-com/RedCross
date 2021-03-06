package com.zx.redcross.controller.course;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zx.redcross.annotation.Open;
import com.zx.redcross.entity.CourseSubject;
import com.zx.redcross.service.course.IExamOrderServ;
import com.zx.redcross.service.course.IExamPayRecordServ;
import com.zx.redcross.tool.BusinessExceptionUtils;
import com.zx.redcross.tool.Constant;
import com.zx.redcross.tool.MapUtils;

@RestController
@RequestMapping("pay")
public class ExamPayRecordCtrl {

	@Autowired
	private IExamPayRecordServ payImpl;
	
	@Autowired
	private IExamOrderServ examOrderImpl;
	
	private final String payMethod = "1";//支付宝支付
	private final String name = "name";
	private final String price = "price";
	private final String signedParams = "signedParams";
	@Open
	@RequestMapping("ali/examorder")
	public Map<String,Object> getSignedParams(String orderNumber){
		Map<String, Object> map = MapUtils.getHashMapInstance();

		if(examOrderImpl.updateExamOrderPayMethod(payMethod,orderNumber)) {
			CourseSubject cs = examOrderImpl.getCourseSubjectByExamOrderOrderNumber(orderNumber);
			Map<String, Object> dataMap = MapUtils.getHashMapInstance();
			dataMap.put(signedParams, payImpl.getSignedParams(orderNumber));
			dataMap.put(name, cs.getSname());
			dataMap.put(price, cs.getPrice());
			map.put(Constant.DATA, dataMap);
			map.put(Constant.STATUS, Constant.STATUS_SUCCESS);
		}else
			BusinessExceptionUtils.throwNewBusinessException("设置支付方式失败");
		return map;
	}
	
}
