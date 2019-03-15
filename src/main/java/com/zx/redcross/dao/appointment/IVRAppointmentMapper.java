package com.zx.redcross.dao.appointment;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zx.redcross.entity.Page;
import com.zx.redcross.entity.VRAppointment;

/**
 * 	VR预约
 * @author Daryl
 *
 */
public interface IVRAppointmentMapper {
	
	Boolean addVRAppointment(@Param("vr") VRAppointment vr);
	
	/**
	 * 	更新状态
	 * @param vr
	 * @return
	 */
	Boolean updateVRAppointmentFlag(@Param("vr") VRAppointment vr);
	
	
	List<VRAppointment> listVRAppointment(@Param("vrAppointment")VRAppointment vrAppointment,@Param("page") Page page);
	
	Integer getVRAppointmentCounts(@Param("vrAppointment")VRAppointment vrAppointment,@Param("page") Page page);
	
	
	List<VRAppointment> getVRAppointmentByCustomerId(@Param("customerID") Integer customerID);
	
	
}
