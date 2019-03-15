package com.zx.redcross.serviceimpl.appointment;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zx.redcross.dao.appointment.IAppointmentMapper;
import com.zx.redcross.dao.appointment.IVRAppointmentMapper;
import com.zx.redcross.entity.AppointmentDate;
import com.zx.redcross.entity.AppointmentPattern;
import com.zx.redcross.entity.Page;
import com.zx.redcross.entity.VRAppointment;
import com.zx.redcross.service.appointment.IAppointmentService;
import com.zx.redcross.tool.BusinessExceptionUtils;

@Service
public class AppointmentServImpl implements IAppointmentService{
	
	@Autowired
	private IAppointmentMapper appointMaper;
	@Autowired
	private IVRAppointmentMapper vrMapper;
	
	
	
	//=====================pattern===========================
	@Override
	public Boolean addAppointmentPattern(AppointmentPattern pattern) {
		
		return appointMaper.addAppointmentPattern(pattern);
	}

	@Override
	public Boolean updateAppointmentPattern(AppointmentPattern pattern) {
		BusinessExceptionUtils.throwNewBusinessException("暂时不支持预约模式修改操作");
		return null;
	}

	@Override
	public AppointmentPattern getAppointmentPattern(Integer id) {
		
		return appointMaper.getAppointmentPattern(id);
	}

	
	@Override
	public List<AppointmentPattern> listAppointmentPattern() {
		return appointMaper.listAppointmentPattern();
	}

	@Override
	public Boolean updateOnUseAppointmentPattern(Integer id) {
		if( appointMaper.getAppointmentPattern(id) == null)
			BusinessExceptionUtils.throwNewBusinessException("传入的patternID不存在");
		return appointMaper.updateOnUseAppointmentPattern(id);
	}

	@Override
	public Boolean removeAppointmentPattern(Integer id) {
		if( appointMaper.getAppointmentPattern(id) == null)
			BusinessExceptionUtils.throwNewBusinessException("传入的patternID不存在");
		return appointMaper.removeAppointmentPattern(id);
	}
	
	//=====================date===========================
	@Override
	public Boolean addAppointmentDate(AppointmentDate date) {
		if(date.getPattern() == null || date.getPattern().getId() == null)
			BusinessExceptionUtils.throwNewBusinessException("patternID 必须传值");
		if(appointMaper.getAppointmentPattern(date.getPattern().getId()) == null)
			BusinessExceptionUtils.throwNewBusinessException("传入的patternID不存在");
		return appointMaper.addAppointmentDate(date);
	}

	@Override
	public Boolean removeAppointmentDate(Integer id) {
		if( appointMaper.getAppointmentDate(id) == null)
			BusinessExceptionUtils.throwNewBusinessException("传入的Date ID不存在");
		//查看该日期下是否已经有顾客预约,如果有不允许删除
		Page page = new Page();
		page.setPageNo(1);
		VRAppointment vr = new VRAppointment();
		vr.setId(id);//注意看 listVRAppointment接口的vrAppointment的各个字段的含义
		List<VRAppointment> listVRAppointment = vrMapper.listVRAppointment(vr, page);
		if(listVRAppointment == null || listVRAppointment.size() == 0) {
			return appointMaper.removeAppointmentDate(id);
		}else
			BusinessExceptionUtils.throwNewBusinessException("该日期下已经有顾客预约,不可删除！");
		return false;
	}

	@Override
	public AppointmentDate getAppointmentDate(Integer id) {
		return appointMaper.getAppointmentDate(id);
	}

	@Override
	public List<AppointmentDate> listAppointmentDateByPattern(Integer patternId,Page page) {
		
		return appointMaper.listAppointmentDateByPattern(patternId,page);
	}

	
	@Override
	public List<AppointmentDate> listAvaliableAppointmentDate() {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH, 1);
		String date = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
		return appointMaper.listAvaliableAppointmentDate(date);
	}
	//=====================time===========================
	/*
	 * @Override public Boolean addAppointmentTime(AppointmentTime time) {
	 * 
	 * return appointMaper.addAppointmentTime(time); }
	 * 
	 * @Override public Boolean removeAppointmentTime(Integer id) { if(
	 * appointMaper.getAppointmentTime(id) == null)
	 * BusinessExceptionUtils.throwNewBusinessException("传入的Time ID不存在"); return
	 * appointMaper.removeAppointmentTime(id); }
	 * 
	 * @Override public List<AppointmentTime> listAppointmentTimeByPattern(Integer
	 * patternId){ return appointMaper.listAppointmentTimeByPattern(patternId); }
	 */

	@Override
	public Boolean addAppointmentPatternDateTime(
			AppointmentPattern pattern, List<AppointmentDate> dateList) {
		//1.  先添加pattern.
		appointMaper.addAppointmentPattern(pattern);
		//2.添加date
		Iterator<AppointmentDate> iterator = dateList.iterator();
		while(iterator.hasNext()) {
			AppointmentDate ad = iterator.next();
			ad.setPattern(pattern);
			this.addAppointmentDate(ad);
		}
		return true;
	}


}
