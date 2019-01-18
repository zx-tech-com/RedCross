package com.zx.redcross.entity;

/**
 * 专门用于生成订单的实体类
 * @author Daryl
 */
public class OrderNumber {
	
	private String businessType;//2位
	private String tel;//4位
	private String timeStamp;//6位
	public String getBusinessType() {
		return businessType;
	}
	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getOrderNumber() {
		String orderNumber = "";
		orderNumber = businessType + tel + timeStamp;
		return orderNumber;
	}
}
