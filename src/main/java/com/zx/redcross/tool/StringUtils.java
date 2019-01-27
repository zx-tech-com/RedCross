package com.zx.redcross.tool;

/**
 * 字符串的常用功能
 * @author Daryl
 *
 */
public class StringUtils {

	/**
	 * int[]数组转换成符合SQL in语句查询的字符串
	 */
	public static String convertArrayToString(int[] ids) {
		StringBuffer sb = new StringBuffer();
		for(int id : ids) {
			sb.append("'" + id + "',");
		}
		if(sb.length() > 0)
			return sb.substring(0, sb.length()-1);
		return null;
	}
	/**
	 * int[]数组转换成符合SQL in语句查询的字符串
	 */
	public static String convertArrayToString(Integer[] ids) {
		StringBuffer sb = new StringBuffer();
		for(int id : ids) {
			sb.append("'" + id + "',");
		}
		if(sb.length() > 0)
			return sb.substring(0, sb.length()-1);
		return null;
	}
	
}
