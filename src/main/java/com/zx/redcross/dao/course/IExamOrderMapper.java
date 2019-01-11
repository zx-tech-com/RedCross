package com.zx.redcross.dao.course;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zx.redcross.entity.ExamOrder;
import com.zx.redcross.entity.Page;

public interface IExamOrderMapper {
	
	ExamOrder getExamOrderById (Integer id);
	
	List<ExamOrder> listExamOrderByCustomerId (Integer customerId);
	
	List<ExamOrder> listExamOrderByOsDistrictId (
			Integer districtId,@Param("page") Page page);
	
	List<ExamOrder> listExamOrder (@Param("page") Page page);
	
	Boolean addExamOrder(@Param("examOrder") ExamOrder examOrder);
}
