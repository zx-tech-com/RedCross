package com.zx.redcross.tool;

import java.util.Date;

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
	
}
