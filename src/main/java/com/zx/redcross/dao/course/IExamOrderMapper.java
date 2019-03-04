package com.zx.redcross.dao.course;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zx.redcross.entity.ExamOrder;
import com.zx.redcross.entity.Page;

public interface IExamOrderMapper {
	
	Map<String, Object> getExamOrderById (Integer id);
	
	List<ExamOrder> listExamOrderByCustomerId (@Param("customerId") Integer customerId);
	
	List<ExamOrder> listExamOrderByOsDistrictId (
			Integer districtId,@Param("page") Page page);
	
	List<Map<String, Object>> listExamOrder (@Param("examOrder") ExamOrder examOrder);
	
	Boolean addExamOrder(@Param("examOrder") ExamOrder examOrder);
	
	Boolean updateExamOrderStatus(@Param("examOrder") ExamOrder examOrder);
	
}
