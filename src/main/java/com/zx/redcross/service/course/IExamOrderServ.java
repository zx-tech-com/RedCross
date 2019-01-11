package com.zx.redcross.service.course;

import java.util.List;

import com.zx.redcross.entity.ExamOrder;
import com.zx.redcross.entity.Page;

public interface IExamOrderServ {
	
	ExamOrder getExamOrderById (Integer id);
	
	List<ExamOrder> listExamOrderByCustomerId (Integer customerId);
	
	List<ExamOrder> listExamOrderByOsDistrictId (Integer districtId,Page page);
	
	List<ExamOrder> listExamOrder (Page page);
	
	Boolean addExamOrder(ExamOrder examOrder);
	
}
