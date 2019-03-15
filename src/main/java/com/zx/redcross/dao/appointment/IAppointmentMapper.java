package com.zx.redcross.dao.appointment;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zx.redcross.entity.AppointmentDate;
import com.zx.redcross.entity.AppointmentPattern;
import com.zx.redcross.entity.Page;

/**
 * VR预约时间相关
 * @author Daryl
 *
 */
public interface IAppointmentMapper {
	
	
	//=====================pattern===========================
	Boolean addAppointmentPattern(@Param("pattern") AppointmentPattern pattern);
	
	/**
	 * mapper.xml未实现,该方法暂时没意义
	 * @param pattern
	 * @return
	 */
	Boolean updateAppointmentPattern(@Param("pattern") AppointmentPattern pattern);
	
	AppointmentPattern getAppointmentPattern(@Param("id") Integer id);
	
	List<AppointmentPattern> listAppointmentPattern();
	
	Boolean removeAppointmentPattern(@Param("id") Integer id);
	
	/**
	 * 	修改当前正在使用的预约模式
	 * @param id
	 * @return
	 */
	Boolean updateOnUseAppointmentPattern(@Param("id") Integer id);
	
	//=====================date==================================
	Boolean addAppointmentDate(@Param("date") AppointmentDate date);
	
	Boolean removeAppointmentDate(@Param("id") Integer id);
	
	AppointmentDate getAppointmentDate(@Param("id") Integer id);
	
	List<AppointmentDate> listAppointmentDateByPattern(@Param("patternId") Integer patternId,@Param("page") Page page);
	
	List<AppointmentDate> listAvaliableAppointmentDate(@Param("date") String date);
	
	//=========================time==================================
	/*
	 * Boolean addAppointmentTime(@Param("time") AppointmentTime time);
	 * 
	 * Boolean removeAppointmentTime(@Param("id") Integer id);
	 * 
	 * AppointmentTime getAppointmentTime(@Param("id") Integer id);
	 * 
	 * List<AppointmentTime> listAppointmentTimeByPattern(@Param("patternId")
	 * Integer patternId);
	 */
	
	
}
