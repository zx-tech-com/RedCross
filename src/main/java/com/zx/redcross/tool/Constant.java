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
}
