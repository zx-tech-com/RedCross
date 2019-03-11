package com.zx.redcross.tool;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 生成订单号
 * @author Daryl
 */
public class Utils {

	public static synchronized String newTimeStamp() {
		long stamp = new Date().getTime();
		String timeStamp = "" + stamp;
		timeStamp = timeStamp.substring(timeStamp.length()- Constant.STAMP_LENGTH,timeStamp.length());
		return timeStamp;
	}
	
	/**
	 * 返回匹配的第一张照片
	 * @return
	 */
	public static String matchImgFromH5(String html) {
		Pattern regex = Pattern.compile("<img[\\d\\D]*?src=\"([\\d\\D]*?)\"[\\d\\D]*?>");
		Matcher matcher = regex.matcher(html);
		while(matcher.find()) {
			String url = matcher.group(1);
			if(isUeditorUrl(url))
				return url;
		}
		return null;
	}
	
	
	private static  boolean isUeditorUrl(String url) {
		return StringUtils.isNotBlank(url) && url.contains("ueditor/image");
	}
	
}
