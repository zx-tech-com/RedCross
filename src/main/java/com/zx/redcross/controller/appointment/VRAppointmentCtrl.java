package com.zx.redcross.controller.appointment;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zx.redcross.annotation.BackEnd;
import com.zx.redcross.entity.Page;
import com.zx.redcross.entity.VRAppointment;
import com.zx.redcross.service.appointment.IVRAppointmentService;
import com.zx.redcross.tool.Constant;
import com.zx.redcross.tool.MapUtils;

@RestController
@RequestMapping("appointment")
public class VRAppointmentCtrl {

	
	@Autowired
	private IVRAppointmentService vrImpl;
	
	@RequestMapping("vr/add")
	public Map<String,Object> addVRAppointment(@RequestBody VRAppointment vr){
		Map<String,Object> map = MapUtils.getHashMapInstance();
		map.put(Constant.STATUS, Constant.STATUS_FAILURE);
		if(vrImpl.addVRAppointment(vr))
			map.put(Constant.STATUS, Constant.STATUS_SUCCESS);
		return map;
	}
	
	/**
	 * 	暂时不用
	 * @param vr
	 * @return
	 */
	@RequestMapping("vr/updateflag")
	public Map<String,Object> updateVRAppointmentFlag(VRAppointment vr){
		Map<String,Object> map = MapUtils.getHashMapInstance();
		map.put(Constant.STATUS, Constant.STATUS_FAILURE);
		if(vrImpl.updateVRAppointmentFlag(vr))
			map.put(Constant.STATUS, Constant.STATUS_SUCCESS);
		return map;
	}
	
	@BackEnd
	@RequestMapping("vr/back/list")
	public Map<String,Object> listVRAppointment(VRAppointment vr,Page page){
		Map<String,Object> map = MapUtils.getHashMapInstance();
		map.put(Constant.STATUS, Constant.STATUS_SUCCESS);
		map.put(Constant.DATA, vrImpl.listVRAppointment(vr,page));
		page.setPageCount(vrImpl.getVRAppointmentCounts(vr, page));
		map.put(Constant.TOTAL_COUNT, page.getTotalPage());
		map.put(Constant.PAGE_SIZE, page.getPageSize());
		return map;
	}
	
	@RequestMapping("vr/get")
	public Map<String,Object> getVRAppointmentByCustomerId(Integer customerID){
		Map<String,Object> map = MapUtils.getHashMapInstance();
		map.put(Constant.STATUS, Constant.STATUS_SUCCESS);
		map.put(Constant.DATA, vrImpl.getVRAppointmentByCustomerId(customerID));
		return map;
	}
	
}
