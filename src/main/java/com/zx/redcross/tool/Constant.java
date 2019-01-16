package com.zx.redcross.tool;

import java.io.File;

/**
 * 一些常用的变量
 * @author Daryl
 */
public class Constant {

	
	public static final String ABSOLUTE_BASE_PATH = "D:" + File.separator;
	public static final String ACCESS_BASE_PATH = "http://192.168.1.177:8080/";
	
	// 所有视频均存储在该路径下
	public static final String VIDEO_ABSOLUTE_BASE_PATH = ABSOLUTE_BASE_PATH + "videos" + File.separator;
	// 视频的访问路径的前缀
	public static final String VIDEO_ACCESS_BASE_PATH = ACCESS_BASE_PATH + "videos/";
	
	// 所有图片均存储在该路径下
	public static final String IMG_ABSOLUTE_BASE_PATH = ABSOLUTE_BASE_PATH + "imgs" + File.separator;
	// 图片的访问路径的前缀
	public static final String IMG_ACCESS_BASE_PATH = ACCESS_BASE_PATH + "imgs/";
	
	
	/**
	 * 返回状态信息
	 */
	public static final String STATUS = "status"; 
	public static final String STATUS_SUCCESS = "success";
	public static final String STATUS_FAILURE = "failure";
	public static final String ERROR_MESSAGE = "message";
	public static final String TIPS = "tips";
	public static final String DATA = "data";
	
	/**
	 * 返回错误信息
	 */
	public static final String ERROR_MISSIN_GPARAMETER = "缺少参数";
	public static final String ERROR_UNKNOW = "服务端错误";
	
	
	
	/**
	 * 分页信息
	 */
	public static final Integer APP_PAGE_SIZE= 6;
	public static final String PAGE_SIZE = "pageSize";
	
	
	/**
	 * Token相关
	 */
	public static final String TOKEN= "token";
	public static final String TOKEN_STATUS= "tokenStatus";
	public static final String PAY_LOAD= "payLoad";
	public static final String SECRET = "fb63cf7d-385c-4b2c-b89d-3d4c8a9f";
	public static final String EXPIRES = "expires";
	public static final String TOKEN_VALID = "valid";
	public static final String TOKEN_INVALID = "invalid";
	public static final String TOKEN_EXPIRED = "expired";
	public static final String CUSTOMERID = "customerId";
	public static final Integer NUMBER_8 = 8;
	public static final Integer HOUR_FIELD = 11;
	
	/**
	 * HTTP重定向
	 */
	public static final Integer HTTP_STATUS_302 = 302;
	
	
	
	/**
	 * OrderMapper.xml中用到的几个常量
	 */
	public static final String VIDEO_ORDER_TITLE = "'付费视频购买-'";
	public static final String VIDEO_ORDER_DESCRIPTION = "'购买'";
	public static final String EXAM_ORDER_TITLE = "'考试项目购买-'";
	
	
	
}
