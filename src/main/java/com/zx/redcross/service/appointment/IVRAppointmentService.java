package com.zx.redcross.service.appointment;

import java.util.List;

import com.zx.redcross.entity.Page;
import com.zx.redcross.entity.VRAppointment;

public interface IVRAppointmentService {
	
	Boolean addVRAppointment(VRAppointment vr);
	
	/**
	 * 	更新状态
	 * @param vr
	 * @return
	 */
	Boolean updateVRAppointmentFlag(VRAppointment vr);
	
	/**
	 * 	
	 * @param vrAppointment	注意查看vrAppointment的各个字段的含义
	 * @param page
	 * @return
	 */
	List<VRAppointment> listVRAppointment(VRAppointment vrAppointment,Page page);
	
	List<VRAppointment> getVRAppointmentByCustomerId(Integer id);
	
	Integer getVRAppointmentCounts(VRAppointment vrAppointment, Page page);
}
