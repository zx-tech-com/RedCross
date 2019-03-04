package com.zx.redcross.pay;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Utils {

	//交易关闭时间
	public static final int TIME_OUT_IN_MINUTE = 30;
	
	/**
	 * 
	 * @param time	    需要格式化的时间
	 * @param pattern 形如"yyyy-MM-dd HH:mm:ss"
	 * @return 格式化后的字符串
	 */
	public static String getFormatedTime(Calendar time,String pattern) {
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.format(time.getTime());
	}
	
	public static String getNowFormatedTime(String pattern) {
		return getFormatedTime(Calendar.getInstance(), pattern);
	}
	
	
}
