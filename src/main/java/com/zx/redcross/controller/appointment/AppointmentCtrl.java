package com.zx.redcross.controller.appointment;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zx.redcross.annotation.BackEnd;
import com.zx.redcross.annotation.Open;
import com.zx.redcross.entity.AppointmentDate;
import com.zx.redcross.entity.AppointmentPattern;
import com.zx.redcross.entity.Page;
import com.zx.redcross.service.appointment.IAppointmentService;
import com.zx.redcross.tool.Constant;
import com.zx.redcross.tool.MapUtils;

/**
 * 自定义预约时间
 * 
 * @author Daryl
 */
@RestController
@RequestMapping("appointment")
public class AppointmentCtrl {

	@Autowired
	private IAppointmentService appointmentImpl;

	// ======================pattern相关=========================
//	@BackEnd
//	@RequestMapping("back/addpattern")
	public Map<String, Object> addAppointmentPattern(@RequestBody AppointmentPattern pattern) {
		Map<String, Object> map = MapUtils.getHashMapInstance();
		map.put(Constant.STATUS, Constant.STATUS_FAILURE);
		if (appointmentImpl.addAppointmentPattern(pattern))
			map.put(Constant.STATUS, Constant.STATUS_SUCCESS);
		return map;
	}

	public Map<String, Object> getAppointmentPattern(Integer id) {
		Map<String, Object> map = MapUtils.getHashMapInstance();
		map.put(Constant.STATUS, Constant.STATUS_SUCCESS);
		map.put(Constant.DATA, appointmentImpl.getAppointmentPattern(id));
		return map;
	}

	@BackEnd
	@RequestMapping("back/listpattern")
	public Map<String, Object> listAppointmentPattern() {
		Map<String, Object> map = MapUtils.getHashMapInstance();
		map.put(Constant.STATUS, Constant.STATUS_SUCCESS);
		map.put(Constant.DATA, appointmentImpl.listAppointmentPattern());
		return map;
	}

//	@BackEnd
//	@RequestMapping("back/updateOnUsePattern")
	public Map<String, Object> updateOnUseAppointmentPattern(Integer id) {
		Map<String, Object> map = MapUtils.getHashMapInstance();
		map.put(Constant.STATUS, Constant.STATUS_FAILURE);
		if (appointmentImpl.updateOnUseAppointmentPattern(id))
			map.put(Constant.STATUS, Constant.STATUS_SUCCESS);
		return map;
	}

	@BackEnd
	@RequestMapping("back/removepattern")
	public Map<String, Object> removeAppointmentPattern(Integer id) {
		Map<String, Object> map = MapUtils.getHashMapInstance();
		map.put(Constant.STATUS, Constant.STATUS_FAILURE);
		if (appointmentImpl.removeAppointmentPattern(id))
			map.put(Constant.STATUS, Constant.STATUS_SUCCESS);
		return map;
	}

	@BackEnd
	@Open
	@RequestMapping("back/addfullpattern")
	public Map<String, Object> addAppointmentPatternDateTime(@RequestBody String jsonStr) {
		System.err.print(jsonStr);

		JSONObject obj = (JSONObject) JSON.parse(jsonStr);
		List<AppointmentDate> dateList = obj.getJSONArray("dvalue").toJavaList(AppointmentDate.class);
		obj.remove("dvalue");
		AppointmentPattern pattern = obj.toJavaObject(AppointmentPattern.class);

		Map<String, Object> map = MapUtils.getHashMapInstance();
		map.put(Constant.STATUS, Constant.STATUS_FAILURE);
		if (appointmentImpl.addAppointmentPatternDateTime(pattern, dateList))
			map.put(Constant.STATUS, Constant.STATUS_SUCCESS);
		return map;
	}

	// =======================Date相关==============================
	public Map<String, Object> addAppointmentDate(AppointmentDate date) {
		Map<String, Object> map = MapUtils.getHashMapInstance();
		map.put(Constant.STATUS, Constant.STATUS_FAILURE);
		if (appointmentImpl.addAppointmentDate(date))
			map.put(Constant.STATUS, Constant.STATUS_SUCCESS);
		return map;
	}

	@Open
	@RequestMapping("back/removedate")
	public Map<String, Object> removeAppointmentDate(Integer id) {
		Map<String, Object> map = MapUtils.getHashMapInstance();
		map.put(Constant.STATUS, Constant.STATUS_FAILURE);
		if (appointmentImpl.removeAppointmentDate(id))
			map.put(Constant.STATUS, Constant.STATUS_SUCCESS);
		return map;
	}

	public Map<String, Object> getAppointmentDate(Integer id) {
		Map<String, Object> map = MapUtils.getHashMapInstance();
		map.put(Constant.STATUS, Constant.STATUS_SUCCESS);
		map.put(Constant.DATA, appointmentImpl.getAppointmentDate(id));
		return map;
	}

	@BackEnd
	@RequestMapping("back/listdate")
	public Map<String, Object> listAppointmentDateByPattern(Integer patternId,Page page) {
		Map<String, Object> map = MapUtils.getHashMapInstance();
		map.put(Constant.STATUS, Constant.STATUS_SUCCESS);
		map.put(Constant.DATA, appointmentImpl.listAppointmentDateByPattern(patternId,page));
		return map;
	}

	
	@BackEnd
	@Open
	@RequestMapping("listavaliabledate")
	public Map<String, Object> listAvaliableAppointmentDate() {
		Map<String, Object> map = MapUtils.getHashMapInstance();
		map.put(Constant.STATUS, Constant.STATUS_SUCCESS);
		map.put(Constant.DATA, appointmentImpl.listAvaliableAppointmentDate());
		return map;
	}
	
	
	// =======================Time相关=============================
	/*
	 * public Map<String,Object> addAppointmentTime(AppointmentTime time){
	 * Map<String,Object> map = MapUtils.getHashMapInstance();
	 * map.put(Constant.STATUS, Constant.STATUS_FAILURE);
	 * if(appointmentImpl.addAppointmentTime(time)) map.put(Constant.STATUS,
	 * Constant.STATUS_SUCCESS); return map; }
	 * 
	 * public Map<String,Object> removeAppointmentTime(Integer id){
	 * Map<String,Object> map = MapUtils.getHashMapInstance();
	 * map.put(Constant.STATUS, Constant.STATUS_FAILURE);
	 * if(appointmentImpl.removeAppointmentTime(id)) map.put(Constant.STATUS,
	 * Constant.STATUS_SUCCESS); return map; }
	 * 
	 * public Map<String,Object> listAppointmentTimeByPattern(Integer patternId){
	 * Map<String,Object> map = MapUtils.getHashMapInstance();
	 * map.put(Constant.STATUS, Constant.STATUS_SUCCESS); map.put(Constant.DATA,
	 * appointmentImpl.listAppointmentTimeByPattern(patternId)); return map; }
	 */

}
