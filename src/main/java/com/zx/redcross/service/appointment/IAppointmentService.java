package com.zx.redcross.service.appointment;

import java.util.List;

import com.zx.redcross.entity.AppointmentDate;
import com.zx.redcross.entity.AppointmentPattern;
import com.zx.redcross.entity.Page;

public interface IAppointmentService {
	
	//=====================pattern===========================
	Boolean addAppointmentPattern(AppointmentPattern pattern);
	
	Boolean addAppointmentPatternDateTime(AppointmentPattern pattern, List<AppointmentDate> dateList);
	
	/**
	 * 	该方法尚未实现
	 * @param pattern
	 * @return
	 */
	Boolean updateAppointmentPattern(AppointmentPattern pattern);
	
	AppointmentPattern getAppointmentPattern(Integer id);
	
	List<AppointmentPattern> listAppointmentPattern();
	
	/**
	 * 	修改当前正在使用的预约模式
	 * @param id
	 * @return
	 */
	Boolean updateOnUseAppointmentPattern(Integer id);
	
	Boolean removeAppointmentPattern(Integer id);
	
	//=====================date==================================
	Boolean addAppointmentDate(AppointmentDate date);
	
	Boolean removeAppointmentDate(Integer id);

	AppointmentDate getAppointmentDate(Integer id);
	
	List<AppointmentDate> listAppointmentDateByPattern(Integer patternId,Page page);
	
	List<AppointmentDate> listAvaliableAppointmentDate();
	
	
	//=========================time==================================
	/*
	 * Boolean addAppointmentTime(AppointmentTime time);
	 * 
	 * Boolean removeAppointmentTime(Integer id);
	 * 
	 * List<AppointmentTime> listAppointmentTimeByPattern(Integer patternId);
	 */
	
}