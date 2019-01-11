package com.zx.redcross.serviceimpl.course;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zx.redcross.dao.course.IExamOrderMapper;
import com.zx.redcross.entity.ExamOrder;
import com.zx.redcross.entity.Page;
import com.zx.redcross.service.course.IExamOrderServ;

@Service
public class ExamOrderServImpl implements IExamOrderServ {

	@Autowired
	private IExamOrderMapper mapper;
	
	@Override
	public ExamOrder getExamOrderById(Integer id) {
		return mapper.getExamOrderById(id);
	}

	@Override
	public List<ExamOrder> listExamOrderByCustomerId(Integer customerId) {
		return mapper.listExamOrderByCustomerId(customerId);
	}

	@Override
	public List<ExamOrder> listExamOrderByOsDistrictId(Integer districtId, Page page) {
		return mapper.listExamOrderByOsDistrictId(districtId, page);
	}

	@Override
	public List<ExamOrder> listExamOrder( Page page) {
		return mapper.listExamOrder(page);
	}

	@Override
	public Boolean addExamOrder(ExamOrder examOrder) {
		return mapper.addExamOrder(examOrder);
	}

}
