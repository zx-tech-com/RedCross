package com.zx.redcross.pay.wechat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.zx.redcross.pay.MD5Util;
import com.zx.redcross.pay.Utils;
import com.zx.redcross.tool.BusinessExceptionUtils;
import com.zx.redcross.tool.MapUtils;
import com.zx.redcross.tool.StringUtils;

public class WeChatUtils {


	/**
	 * 获取随机字符串 length + 13 个字符
	 * @param length
	 * @return
	 */
	public static String getRandomString(int length) {

		long stamp = new Date().getTime();
		char[] chars = { 
				'a', 'b', 'c', 'd', 'e', 'f', 'g', 
				'h', 'i', 'j', 'k', 'l', 'm', 'n', 
				'o', 'p', 'q', 'r', 's','t', 
				'u', 'v', 'w', 'x', 'y', 'z' };
		StringBuffer randomString = new StringBuffer();
		for (int i = 0; i < length; i++) {
			randomString.append(chars[(int) Math.round(Math.random() * 25)]);
		}
		randomString.append(stamp);
		System.out.println(randomString.toString().toUpperCase());
		return randomString.toString().toUpperCase();
	}
	/**
	 * 随机获取23个字符
	 */
	public static String getRandomString() {
		return getRandomString(10);
	}

	public static String getFormatedExpiredTime() {
		Calendar now = Calendar.getInstance();
		System.err.println(Utils.getFormatedTime(now, "yyyyMMddHHmmss"));
		now.add(Calendar.MINUTE, Utils.TIME_OUT_IN_MINUTE);
		System.err.println(Utils.getFormatedTime(now, "yyyyMMddHHmmss"));
		return Utils.getFormatedTime(now, "yyyyMMddHHmmss");
	}

	/**
	 * 将参数转化为 key1=value1&key2=value2 格式的字符串,并按照字典升序排序
	 * @param params
	 * @return
	 */
	public static String getQueryString(Map<String, Object> params){
		StringBuffer content = new StringBuffer();
        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);
        int index = 0;
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = (String)params.get(key);
            if (StringUtils.isNotBlank(value)) {
                content.append((index == 0 ? "" : "&") + key + "=" + value);
                index++;
            }
        }
        return content.toString();
    }
	
	
	/**
	 * 将参数转化为 xml 格式的字符串
	 * @param params
	 * @return
	 */
	public static String getXMLSchemaString(Map<String, Object> params){
		StringBuffer content = new StringBuffer();
		content.append("<xml>");
		Iterator<String> keys = params.keySet().iterator();
		while(keys.hasNext()) {
			String key = keys.next();
			content.append("<" + key + ">");
			content.append(params.get(key));
			content.append("</" + key + ">");
		}
		content.append("</xml>");
        return content.toString();
    }
	
	
	public static String getSignContent(Map<String, Object> params) {
		return MD5Util.getMD5(getQueryString(params) + "&key" + WeChatConfig.KEY).toUpperCase();
	}

	/**
	 * 将xml格式的字符串转换成key/value对，放在map中并返回
	 * @param xmlStr
	 * @return 保存有xml键值对的hashmap
	 */
	public static Map<String,String> processXMLString(String xmlStr) {
		
		xmlStr = "<xml>" + 
				"   <return_code><![CDATA[SUCCESS]]></return_code>" + 
				"   <return_msg><![CDATA[OK]]></return_msg>" + 
				"   <appid><![CDATA[wx2421b1c4370ec43b]]></appid>" + 
				"   <mch_id><![CDATA[10000100]]></mch_id>" + 
				"   <nonce_str><![CDATA[IITRi8Iabbblz1Jc]]></nonce_str>" + 
				"   <sign><![CDATA[7921E432F65EB8ED0CE9755F0E86D72F]]></sign>" + 
				"   <result_code><![CDATA[SUCCESS]]></result_code>" + 
				"   <prepay_id><![CDATA[wx201411101639507cbf6ffd8b0779950874]]></prepay_id>" + 
				"   <trade_type><![CDATA[APP]]></trade_type>" + 
				"</xml>";
		
		if(!StringUtils.isNotBlank(xmlStr)) 
			BusinessExceptionUtils.throwNewBusinessException("字符串为空");
		if(!xmlStr.contains("xml"))
			BusinessExceptionUtils.throwNewBusinessException("非xml格式字符串");
		
		Map<String, String> map = MapUtils.getStringHashMapInstance();
		
		Pattern regx = Pattern.compile("<([\\w]+)><!\\[CDATA\\[([\\w\\W]+?)\\]\\].</\\1>");
		Matcher matcher = regx.matcher(xmlStr);
		while(matcher.find()) {
			map.put(matcher.group(1), matcher.group(2));
		}
		
		return map;
	}
}
