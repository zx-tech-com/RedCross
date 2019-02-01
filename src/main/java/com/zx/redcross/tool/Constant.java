package com.zx.redcross.tool;

import java.io.File;

/**
 * 一些常用的变量
 * @author Daryl
 */
public class Constant {

	public static final String ADMIN = "admin";
	
	public static final String ID = "id";
	
//	public static final String ABSOLUTE_BASE_PATH = "H:" + File.separator + "Tomcat" + File.separator
//			 + "8.0" + File.separator + "webapps" + File.separator + "red-cross-file" + File.separator;
//	public static final String ACCESS_BASE_PATH = "http://192.168.1.177:9090/red-cross-file/";
	

	public static final String ABSOLUTE_BASE_PATH = "D:\\git\\git\\redcross-back" + File.separator;
	public static final String ACCESS_BASE_PATH = "http://127.0.0.1:8020/redcross-back/";

	
/*	public static final String ABSOLUTE_BASE_PATH = File.separator + 
			"data" + File.separator + "apache-tomcat-8.5.37" + File.separator + "webapps"
			+ File.separator + "red-cross-file" + File.separator;*/
	
//	public static final String ACCESS_BASE_PATH = "http://39.98.204.123:8080/red-cross-file/";
	
	// 所有视频均存储在该路径下
	public static final String VIDEO_ABSOLUTE_BASE_PATH = ABSOLUTE_BASE_PATH + "videos" + File.separator;
	// 视频的访问路径的前缀
	public static final String VIDEO_ACCESS_BASE_PATH = ACCESS_BASE_PATH + "videos/";
	
	// 所有图片均存储在该路径下
	public static final String IMG_ABSOLUTE_BASE_PATH = ABSOLUTE_BASE_PATH + "imgs" + File.separator;
	// 图片的访问路径的前缀
	public static final String IMG_ACCESS_BASE_PATH = ACCESS_BASE_PATH + "imgs/";
	
	/**
	 * 存储图片视频用到的几个文件夹名称
	 */
	public static final String COURSE_SUBJECT = "courseSubject" + File.separator;
	public static final String TOPIC = "topic" + File.separator;
	
	

	public static final String PAYVIDEO = "payVideo" + File.separator;

	public static final String AVATAR_URL = "avatarurl" + File.separator;

	
	
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
	public static final String ERROR_PARAMETER_TYPE_ILEGAL = "参数类型不匹配";
	public static final String ERROR_REQUEST_METHOD_ILEGAL = "请求方法不匹配";
	
	
	
	/**
	 * 分页信息
	 */
	public static final Integer APP_PAGE_SIZE= 6;
	public static final String PAGE_SIZE = "pageSize";
	public static final String TOTAL_COUNT = "totalCount";
	public static final String PAGE_COUNT = "pageCount";
	
	
	
	
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
	public static final Integer NUMBER_8 = 300;
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
	
	
	/**
	 * img的imgType的两个类型
	 */
	public static final String IMG_TYPE_TOPIC = "1";
	public static final String IMG_TYPE_KNOWLEDGE = "0";
	
	/**
	 * 购买记录的三种状态
	 */
	public static final byte WAIT_TO_PAY = 1;
	public static final byte PAY_COMPLETE = 2;
	public static final byte PAY_CANCEL = 3;
	
	/**
	 * 用户默认头像的地址
	 */
	public static final String CUSTOMER_DEFAULT_AVATAR_URL = "";
	

	/**
	 * 发帖的视频或图片或多张图片状态
	 * 0啥都没有，1一张图片，2多张图片，3视频
	 */
	public static final String POPIC_STATUS0 = "0";
	public static final String POPIC_STATUS1 = "1";
	public static final String POPIC_STATUS2 = "2";
	public static final String POPIC_STATUS3 = "3";

	
	/**
	 * 生成的订单号相关 
	 */
	public static final int STAMP_LENGTH = 6;
	public static final int TEL_LENGTH = 4;
	public static final String BUSINESS_TYPE_EXAM_ORDER = "01";
	public static final String BUSINESS_TYPE_VIDEO_ORDER = "10";
	public static final String PAY_TYPE_WECHAT = "01";
	public static final String PAY_TYPE_ZFB = "10";
	public static final String PAY_TYPE_OTHERS = "00";

	

}
