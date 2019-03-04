package com.zx.redcross.dao.course;

import org.apache.ibatis.annotations.Param;

import com.zx.redcross.entity.ExamPayRecord;

public interface IExamPayRecordMapper {
	
	boolean saveExamPayRecord(@Param("record") ExamPayRecord record);
	
	boolean updateExamPayRecord(@Param("record") ExamPayRecord record);
	
	ExamPayRecord getExamPayRecordByOrderNumber(@Param("orderNumber")String orderNumber);
	
}
