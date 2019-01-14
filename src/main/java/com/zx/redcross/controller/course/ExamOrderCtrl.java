package com.zx.redcross.controller.course;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zx.redcross.annotation.FrontEnd;
import com.zx.redcross.entity.ExamOrder;
import com.zx.redcross.entity.Page;
import com.zx.redcross.service.course.IExamOrderServ;
import com.zx.redcross.tool.Constant;
import com.zx.redcross.tool.MapUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags="考试相关接口")
public class ExamOrderCtrl {
	
	@Autowired
	private IExamOrderServ examOrderServImpl;
	
	@RequestMapping("getExamOrderById")
	public Map<String,Object> getExamOrderById (Integer id) {
		
		Map<String,Object> map = MapUtils.getHashMapInstance();
		ExamOrder examOrder = examOrderServImpl.getExamOrderById(id);
		map.put(Constant.STATUS,Constant.STATUS_FAILURE);
		if(null != examOrder) {
			map.put(Constant.STATUS,Constant.STATUS_SUCCESS);
			map.put(Constant.DATA, examOrder);
		}
		return map;
	}
	
	@RequestMapping("listExamOrderByCustomerId")
	public Map<String,Object> listExamOrderByCustomerId (Integer customerId){
		
		Map<String,Object> map = MapUtils.getHashMapInstance();
		map.put(Constant.STATUS, Constant.STATUS_FAILURE);
		List<ExamOrder> examOrderList = examOrderServImpl.listExamOrderByCustomerId(customerId);
		if(null != examOrderList) {
			map.put(Constant.DATA, examOrderList);
			map.put(Constant.STATUS, Constant.STATUS_SUCCESS);
		}
		return map;
		
	}
	
	@RequestMapping("listExamOrderByOsDistrictId")
	public Map<String,Object> listExamOrderByOsDistrictId (Integer districtId,Page page){
		
		Map<String,Object> map = MapUtils.getHashMapInstance();
		map.put(Constant.STATUS, Constant.STATUS_FAILURE);
		List<ExamOrder> examOrderList = examOrderServImpl.listExamOrderByOsDistrictId(districtId, page);
		if(null != examOrderList) {
			map.put(Constant.DATA, examOrderList);
			map.put(Constant.STATUS, Constant.STATUS_SUCCESS);
		}
		return map;
		
	}
	
	@RequestMapping("listExamOrder")
	public Map<String,Object> listExamOrder (Page page){
		
		Map<String,Object> map = MapUtils.getHashMapInstance();
		map.put(Constant.STATUS, Constant.STATUS_FAILURE);
		List<ExamOrder> examOrderList = examOrderServImpl.listExamOrder(page);
		if(null != examOrderList) {
			map.put(Constant.DATA, examOrderList);
			map.put(Constant.STATUS, Constant.STATUS_SUCCESS);
		}
		return map;
	}
	
	@FrontEnd
	@RequestMapping("addExamOrder")
	@ApiOperation(value = "提交考试报名",
		httpMethod= "POST",produces="application/json",consumes="application/json")
	public Map<String,Object> addExamOrder(@RequestBody ExamOrder examOrder){
		
		Map<String,Object> map = MapUtils.getHashMapInstance();
		Boolean flag = examOrderServImpl.addExamOrder(examOrder);
		map.put(Constant.STATUS,flag ? Constant.STATUS_SUCCESS : Constant.STATUS_FAILURE);
		return map;
	}
	
}
