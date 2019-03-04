package com.zx.redcross.pay.wechat;

import java.util.Map;

import com.zx.redcross.tool.MapUtils;
import com.zx.redcross.tool.http.HttpRequest;
import com.zx.redcross.tool.http.contenttype.RequestContentType;
import com.zx.redcross.tool.http.requestcontenttype.ApplicationXMLContentType;

/**
 * 腾讯未提供相应的SDK,这里需要手动实现
 * @author Daryl
 *
 */
public class WeChatService {
	
	/**
	 * 获取prepay_id
	 * @return
	 */
	public static String getPrepayInfo(WeChatBizContent bizContent) {
		
		Map<String, Object> map = MapUtils.getHashMapInstance();
		//组装参数
		assembleBizParams(map,bizContent);
		assembleCommonParams(map);
		assembleSign(map);
		String xmlSchemaParams = WeChatUtils.getXMLSchemaString(map);
		
		//发送请求
		RequestContentType contentType = new ApplicationXMLContentType(xmlSchemaParams);
		HttpRequest request = new HttpRequest(WeChatConfig.UNIFIEDORDER_URL);
		request.setContentType(contentType);
		request.connectAndFetchResult();
		
		//获取请求结果
		String prepayId = (String) request.getResponseContent();
		System.err.println(prepayId);
		return prepayId;
	}

	
	/**
	 * 组装公共参数
	 * @param map	
	 */
	private static void assembleCommonParams(Map<String, Object> map) {
		
		map.put("appid", WeChatConfig.APPID);
		map.put("device_info", WeChatConfig.DEVICE_INFO);
		map.put("mch_id", WeChatConfig.MCH_ID);
		map.put("notify_url", WeChatConfig.NOTIFY_URL);
		map.put("sign_type", WeChatConfig.SIGN_TYPE);
		map.put("trade_type", WeChatConfig.TRADE_TYPE);
		
	}

	/**
	 * 组装业务参数
	 * @param map
	 * @param bizContent	每一条支付记录对应的信息
	 */
	private static void assembleBizParams(Map<String, Object> map, WeChatBizContent bizContent) {
		
		bizContent.setNonceStr(WeChatUtils.getRandomString());
		bizContent.setTimeExpire(WeChatUtils.getFormatedExpiredTime());
		
		map.put("body", bizContent.getBody());
		map.put("out_trade_no", bizContent.getOutTradeNo());
		map.put("spbill_create_ip", bizContent.getSpbillCreateIp());
		map.put("total_fee", bizContent.getTotalFee());
		
		map.put("time_expire", bizContent.getTimeExpire());
		map.put("nonce_str", bizContent.getNonceStr());
	}
	
	/**
	 * 组装签名
	 * @param map
	 */
	private static void assembleSign(Map<String, Object> map) {
		map.put("sign", WeChatUtils.getSignContent(map));
	}
}
