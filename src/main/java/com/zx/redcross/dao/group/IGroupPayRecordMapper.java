package com.zx.redcross.dao.group;

import org.apache.ibatis.annotations.Param;

import com.zx.redcross.entity.GroupPayRecord;

/**
 * 
 * @author Daryl
 *
 */
public interface IGroupPayRecordMapper {

	Boolean addGroupPayRecord(@Param("record") GroupPayRecord record) ;
	
	/**
	 * 只更新payMethod字段
	 * @param orderNumber
	 * @param payMethod
	 */
	boolean updatePayMethod(@Param("payMethod")String payMethod,
			@Param("orderNumber")String orderNumber);

	
	/**
	 * 同时更新exam_order和exam_pay_record两张表的status字段
	 * @param examOrder
	 * @return
	 */
	Boolean updateStatusByOrderNumber(@Param("orderNumber") String orderNumber);
	
	GroupPayRecord getGroupPayRecordByOrderNumber(@Param("orderNumber") String orderNumber);
}
