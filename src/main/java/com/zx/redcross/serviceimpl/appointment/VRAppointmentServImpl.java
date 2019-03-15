package com.zx.redcross.serviceimpl.appointment;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zx.redcross.dao.appointment.IAppointmentMapper;
import com.zx.redcross.dao.appointment.IVRAppointmentMapper;
import com.zx.redcross.dao.my.CustomerMapper;
import com.zx.redcross.entity.AppointmentDate;
import com.zx.redcross.entity.Page;
import com.zx.redcross.entity.VRAppointment;
import com.zx.redcross.service.appointment.IVRAppointmentService;
import com.zx.redcross.tool.BusinessExceptionUtils;
import com.zx.redcross.tool.Constant;


@Service
public class VRAppointmentServImpl implements IVRAppointmentService {

	@Autowired
	private IVRAppointmentMapper vrAppointmentMapper;
	@Autowired
	private CustomerMapper customerMapper;
	@Autowired
	private IAppointmentMapper appointmentMapper;
	
	@Override
	public Boolean addVRAppointment(VRAppointment vr) {
		if(vr.getCustomer() == null || vr.getCustomer().getId() == null)
			BusinessExceptionUtils.throwNewBusinessException("用户id必传");
		if(customerMapper.getMyselfMessage(vr.getCustomer().getId()) == null)
			BusinessExceptionUtils.throwNewBusinessException("用户id不存在");
		AppointmentDate appointmentDate = appointmentMapper.getAppointmentDate(vr.getDate().getId());
		if(appointmentDate == null || !appointmentDate.getDvalue().equals(vr.getAppointmentDate()))
			BusinessExceptionUtils.throwNewBusinessException("传入date ID 和 appointmentDate 不匹配");
		//判断是否重复预约
		checkIfAppointBefore(vr);
		vr.setFlag(Constant.APPOINTMENT_SUCCESS);
		return vrAppointmentMapper.addVRAppointment(vr);
	}

	@Override
	public Boolean updateVRAppointmentFlag(VRAppointment vr) {
		return vrAppointmentMapper.updateVRAppointmentFlag(vr);
	}

	@Override
	public List<VRAppointment> listVRAppointment(VRAppointment vrAppointment,Page page) {
		return vrAppointmentMapper.listVRAppointment(vrAppointment, page);
	}
	
	
	
	

	@Override
	public List<VRAppointment> getVRAppointmentByCustomerId(Integer customerID) {
		return vrAppointmentMapper.getVRAppointmentByCustomerId(customerID);
	}
	
	/**
	 * 	条件：<br>
	 * 1.vr需要包含customer信息<br>
	 * 2.vr的appointmentDate字段不可为信息
	 * @param vr
	 */
	private void checkIfAppointBefore(VRAppointment vr) {
		List<VRAppointment> tempAppointment = vrAppointmentMapper.getVRAppointmentByCustomerId(vr.getCustomer().getId());
		Iterator<VRAppointment> iterator = tempAppointment.iterator();
		while(iterator.hasNext()) {
			VRAppointment temp = iterator.next();
			if(temp.getAppointmentDate().equals(vr.getAppointmentDate()))
				BusinessExceptionUtils.throwNewBusinessException("不可重复预约");
		}
	}

	@Override
	public Integer getVRAppointmentCounts(VRAppointment vrAppointment, Page page) {
		return vrAppointmentMapper.getVRAppointmentCounts(vrAppointment, page);
	}
}
