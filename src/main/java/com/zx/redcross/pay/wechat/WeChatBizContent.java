package com.zx.redcross.pay.wechat;

import com.zx.redcross.pay.PayBizType;

/**
 * 支付请求的业务参数,每一个不同的支付请求,均需要实例化该类
 * @author Daryl
 *
 */
public class WeChatBizContent {
	//不提供set方法
	private String body;

	private String timeExpire;
	private String nonceStr;
	private String outTradeNo;
	private String spbillCreateIp;//客户使用机器ip 点分十进制
	private Integer totalFee;//注意单位是分. 必须是int类型
	
	
	public WeChatBizContent(PayBizType bizType){
		this(bizType,null,null,null,null,null);
	}
	

	public WeChatBizContent(PayBizType bizType,
			String outTradeNo,String spbillCreateIp,Integer totalFee) {
		this(bizType,outTradeNo,spbillCreateIp,totalFee,null,null);
	}

	
	public WeChatBizContent(PayBizType bizType, String outTradeNo,
			String spbillCreateIp,Integer totalFee,String timeExpire,String nonceStr) {
		this.body = bizType.getBizType();
		this.outTradeNo = outTradeNo;
		this.spbillCreateIp = spbillCreateIp;
		this.totalFee = totalFee;
		this.timeExpire = timeExpire;
		this.nonceStr = nonceStr;
	}

	public String getBody() {
		return body;
	}

	public String getTimeExpire() {
		return timeExpire;
	}

	public void setTimeExpire(String timeExpire) {
		this.timeExpire = timeExpire;
	}

	public String getNonceStr() {
		return nonceStr;
	}

	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	public String getSpbillCreateIp() {
		return spbillCreateIp;
	}

	public void setSpbillCreateIp(String spbillCreateIp) {
		this.spbillCreateIp = spbillCreateIp;
	}

	public Integer getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(Integer totalFee) {
		this.totalFee = totalFee;
	}
	
	
}
