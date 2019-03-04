package com.zx.redcross.pay.ali;

import com.zx.redcross.pay.PayBizType;
import com.zx.redcross.pay.Utils;

/**
 * 支付请求的业务参数,每一个不同的支付请求,均需要实例化该类
 * @author Daryl
 *
 */
public class AlipayBizContent {

	//timeoutExpress,goodsType,productCode均是固定值,不需要改变,因此不提供set方法
	private String timeoutExpress = Utils.TIME_OUT_IN_MINUTE + "m";
	private String goodsType = "0";
	private String productCode = "QUICK_MSECURITY_PAY";
	
	/**
	 * @see com.zx.redcross.pay.PayBizType
	 * PayBizType的值
	 */
	private String subject;
	
	
	private String outTradeNo;
	private String totalAmount;
	
	public AlipayBizContent(PayBizType bizType) {
		this.subject = bizType.getBizType();
		this.outTradeNo = null;
		this.totalAmount = null;
	}

	public AlipayBizContent(PayBizType bizType,String outTradeNo,String totalAmount) {
		this.subject = bizType.getBizType();
		this.outTradeNo = outTradeNo;
		this.totalAmount = totalAmount;
	}
	
	
	public String getTimeoutExpress() {
		return timeoutExpress;
	}

	public String getGoodsType() {
		return goodsType;
	}

	public String getProductCode() {
		return productCode;
	}

	public String getSubject() {
		return subject;
	}

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}
	
}
