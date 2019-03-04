package com.zx.redcross.pay.ali;

import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.zx.redcross.pay.Utils;
import com.zx.redcross.tool.BusinessExceptionUtils;
import com.zx.redcross.tool.MapUtils;

/**
 * 阿里支付的工具类
 * @author Daryl
 */
public class AlipayService {
	
	
	/**
	 * 组装客户端需要的订单信息
	 * @param <AlipayContent>
	 */
	public static String generateAndSignOrderInfo(AlipayBizContent bizContent) {
		Map<String, String> map = MapUtils.getStringHashMapInstance();
		assembleBizParams(map,bizContent);
		assembleCommonParams(map,true);
		return getFinalParamsString(map);
	}
	
	

	/**
	 * 组装业务参数
	 * @param map
	 * @param bizContent	每一条支付记录对应的信息
	 */
	private static void assembleBizParams(Map<String, String> map, AlipayBizContent bizContent) {
		Map<String, Object> bizMap = MapUtils.getHashMapInstance();
		bizMap.put("subject",bizContent.getSubject());
		bizMap.put("out_trade_no",bizContent.getOutTradeNo());
		bizMap.put("product_code",bizContent.getProductCode());
		bizMap.put("goods_type",bizContent.getGoodsType());
		bizMap.put("timeout_express",bizContent.getTimeoutExpress());
		bizMap.put("total_amount",bizContent.getTotalAmount());
		map.put("biz_content", JSON.toJSONString(bizMap));
	}
	
	/**
	 * 组装公共参数
	 * @param map	
	 * @param isSandBox	是否是沙箱环境测试
	 */
	private static void assembleCommonParams(Map<String, String> map,boolean isSandBox) {
		
		map.put("app_id", isSandBox ? AlipayConfig.APP_ID_SANDBOX : AlipayConfig.APP_ID);
		map.put("method", AlipayConfig.METHOD);
		map.put("format", AlipayConfig.FORMAT);
		map.put("charset", AlipayConfig.CHARSET);
		map.put("sign_type", AlipayConfig.SIGN_TYPE);
		map.put("timestamp", Utils.getNowFormatedTime("yyyy-MM-dd HH:mm:ss"));
		map.put("version", AlipayConfig.VERSION);
		map.put("notify_url", AlipayConfig.NOTIFY_URL);
		
		try {
			map.put("sign", AlipayUtils.rsaSign(map));
		} catch (AlipayApiException e) {
			System.err.println(e.getErrCode()+e.getErrMsg());
			BusinessExceptionUtils.throwNewBusinessException("签名出现异常");
		}
		
	}

	private static String getFinalParamsString(Map<String, String> map) {
		String finalStr = "";
		try {
			finalStr = AlipayUtils.getSignContent(map);
		} catch (AlipayApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return finalStr;
	}

	
	public static AlipayClient getAlipayClient(boolean isSandBox) {
		
		AlipayClient alipayClient = 
				isSandBox 
				? new DefaultAlipayClient(
						AlipayConfig.GATEWAY_URL_SANDBOX,
						AlipayConfig.APP_ID_SANDBOX,
						AlipayConfig.APP_PRIVATE_KEY,
						AlipayConfig.FORMAT,
						AlipayConfig.CHARSET,
						AlipayConfig.ALIPAY_PUBLIC_KEY,
						AlipayConfig.SIGN_TYPE) 
				: new DefaultAlipayClient(
						AlipayConfig.GATEWAY_URL,
						AlipayConfig.APP_ID,
						AlipayConfig.APP_PRIVATE_KEY,
						AlipayConfig.FORMAT,
						AlipayConfig.CHARSET,
						AlipayConfig.ALIPAY_PUBLIC_KEY,
						AlipayConfig.SIGN_TYPE	);
		return alipayClient;
	}
	
}
