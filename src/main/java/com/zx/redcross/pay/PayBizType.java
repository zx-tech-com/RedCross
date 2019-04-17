package com.zx.redcross.pay;

/**
 * 每一种支付场景对应一个枚举类型.<br/>
 * eg:培训课程购买，付费视频购买
 * @author Daryl
 */
public enum PayBizType {
	
	EXAMORDER("救护培训-培训课程购买"),
	GROUPORDER("救护培训-培训课程团购"),
	PAYFULVIDEO("救护培训-视频教学购买");
	
	private String bizType;
	
	
	private PayBizType(String bizType) {
		this.bizType = bizType;
	}


	public String getBizType() {
		return bizType;
	}
	
}
