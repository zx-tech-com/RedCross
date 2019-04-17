package com.zx.redcross.service.group;

public interface IGroupPayRecordService {

	/**
	 * 考试报名支付,点击支付时触发.此时record的status应该是1(购买中...)
	 * @param orderNumber
	 * @return
	 */
	String getSignedParams(String orderNumber);
	
	/**
	 * 支付宝,微信接口回调时需要更新status字段.
	 * 同时更新exam_order和exam_pay_record两张表的status字段
	 * @param examOrder
	 * @return
	 */
	boolean updateStatusByOrderNumber(String orderNumber);
	
}
