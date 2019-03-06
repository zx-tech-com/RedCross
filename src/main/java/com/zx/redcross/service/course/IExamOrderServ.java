package com.zx.redcross.service.course;

import java.util.List;
import java.util.Map;

import com.zx.redcross.entity.CourseSubject;
import com.zx.redcross.entity.ExamOrder;
import com.zx.redcross.entity.Page;

public interface IExamOrderServ {
	
	Map<String, Object> getExamOrderById (Integer id);
	
	List<ExamOrder> listExamOrderByCustomerId (Integer customerId);
	
	List<ExamOrder> listExamOrderByOsDistrictId (Integer districtId,Page page);
	
	List<Map<String, Object>> listExamOrder (ExamOrder examOrder);
	
	Boolean addExamOrder(ExamOrder examOrder);
	
	Boolean updateExamOrderStatus(ExamOrder examOrder);
	
	Boolean updateExamOrderPayMethod(String payMethod,String orderNumber);

	CourseSubject getCourseSubjectByExamOrderOrderNumber(String orderNumber);
}
