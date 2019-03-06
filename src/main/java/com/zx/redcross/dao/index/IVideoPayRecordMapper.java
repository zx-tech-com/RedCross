package com.zx.redcross.dao.index;

import org.apache.ibatis.annotations.Param;

import com.zx.redcross.entity.VideoPayRecord;

public interface IVideoPayRecordMapper {

	boolean addVideoPayRecord(@Param("payRecord") VideoPayRecord payRecord);
	
	/**
	 * 只更新状态,其它字段不会更新
	 * @param payRecord
	 * @return
	 */
	boolean updateVideoPayRecordStatus(@Param("payRecord") VideoPayRecord payRecord);
	
	VideoPayRecord getVideoPayRecordByOrderNumber(@Param("orderNumber")String orderNumber);
	
	/**
	 * 只更新payMethod字段
	 * @param orderNumber
	 * @param payMethod
	 * @return
	 */
	boolean updatePayMethod(@Param("payMethod")String payMethod,
			@Param("orderNumber")String orderNumber);
	
	/**
	 * 同时更新video_buy_record和video_pay_record两张表的status字段
	 * @param examOrder
	 * @return
	 */
	boolean updateStatusByOrderNumber(@Param("orderNumber") String orderNumber);

}
