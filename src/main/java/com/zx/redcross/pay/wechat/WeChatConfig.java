package com.zx.redcross.pay.wechat;

/**
 * 微信支付 一些不会改变的字段/参数
 * @author Daryl
 */
public class WeChatConfig {

	public static final String APPID = "wx9de6ddb653af7d42";  //小程序ID
	public static final String MCH_ID = "1507191151";	//商户号
	public static final String DEVICE_INFO = "WEB";//设备号  可以自定义
	public static final String SIGN_TYPE = "MD5";
	public static final String TRADE_TYPE = "APP";//交易类型
	public static final String NOTIFY_URL = "http://39.98.204.123/RedCross/wechat/authResult";//通知地址
	
	
	//下面的字段不是参数，不参与参数组装
	public static final String UNIFIEDORDER_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";//统一下单接口
	public static final String KEY = "";//这里要在后台设置32位秘钥
	
}
