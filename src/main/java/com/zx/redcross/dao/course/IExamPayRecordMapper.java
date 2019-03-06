package com.zx.redcross.dao.course;

import org.apache.ibatis.annotations.Param;

import com.zx.redcross.entity.ExamPayRecord;

public interface IExamPayRecordMapper {
	
	boolean saveExamPayRecord(@Param("record") ExamPayRecord record);
	
	boolean updateExamPayRecord(@Param("record") ExamPayRecord record);
	
	/**
	 * 同时更新exam_order和exam_pay_record两张表的status字段
	 * @param examOrder
	 * @return
	 */
	boolean updateStatusByOrderNumber(@Param("orderNumber") String orderNumber);
	
	ExamPayRecord getExamPayRecordByOrderNumber(@Param("orderNumber")String orderNumber);
	
	/**
	 * 只更新payMethod字段
	 * @param orderNumber
	 * @param payMethod
	 */
	boolean updatePayMethod(@Param("payMethod")String payMethod,
			@Param("orderNumber")String orderNumber);
}
