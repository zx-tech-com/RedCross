package com.zx.redcross.service.course;

public interface IExamPayRecordServ {

	/**
	 * 考试报名支付,点击支付时触发.此时record的status应该是1(购买中...)
	 * @param record
	 * @return
	 */
	String getSignedParams(String examOrder);
	
}
