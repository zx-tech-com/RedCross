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
	
	boolean updatePayMethod(@Param("orderNumber")String orderNumber, 
			@Param("payMethod")String payMethod);
}
