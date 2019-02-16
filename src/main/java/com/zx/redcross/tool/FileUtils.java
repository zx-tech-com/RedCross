package com.zx.redcross.tool;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

public class FileUtils {
	
	
	/**
	 * 通过UUID返回文件名
	 * @param file
	 * @return
	 */
	private static String getFileName(MultipartFile file) {
		if(file == null)
			BusinessExceptionUtils.throwNewBusinessException("文件为空");
		return UUID.randomUUID().toString() + getFileSuffixWithDot(file);
	}

	/**
	 * 获取文件的后缀名eg：abc.jpg--->.jpg
	 * @param file
	 * @return
	 */
	private static String getFileSuffixWithDot(MultipartFile file) {
		if(file == null)
			BusinessExceptionUtils.throwNewBusinessException("文件为空");
		String fileName = file.getOriginalFilename();
		String name = fileName.substring(fileName.lastIndexOf('.'), fileName.length());
		return name;
	}
	
	/**
	 * 保存file到absoluteBasePath目录下
	 * @param absoluteBasePath
	 * @param file
	 * @return 返回该文件的相对路径
	 */
	public static String saveFile(String absoluteBasePath,MultipartFile file) {
		if(file == null)
			BusinessExceptionUtils.throwNewBusinessException("文件为空");
		if(absoluteBasePath == null)
			BusinessExceptionUtils.throwNewBusinessException("路径为空");
		File path = new File(absoluteBasePath);
		if(!path.exists()) {//不存在
			path.mkdirs();//创建
		}
		String fileName=getFileName(file);
		path = new File(absoluteBasePath + fileName);
		
		try {
			file.transferTo(path);
		} catch (IllegalStateException | IOException e) {
			BusinessExceptionUtils.throwNewBusinessException("文件存储失败");
		}
		return new String(absoluteBasePath.replace(Constant.ABSOLUTE_BASE_PATH, "") + fileName).replace("\\", "/");
	}
	
	/**
	 * 
	 * @param path 通过实体类调用相应的getXxxUrl()返回的字符串
	 * @return
	 */
	public static boolean removeFile(String path) {
		if(path == null || path.length() == 0)//不理会，认为删除成功
			return true;
		path = Constant.ABSOLUTE_BASE_PATH + path;
		File file = new File(path);
		if(file.exists() && !file.isDirectory())
			return file.delete();
		else {
			BusinessExceptionUtils.throwNewBusinessException("文件不存在");
			return false;
		}
	}
	
	public static String getFullUrl(String url) {
		/*if(url != null && url.length() > 0)
			return Constant.ACCESS_BASE_PATH + url;*/
		return url;
	}	
	
	
	/**
	 * 从file(必须是视频)中截取一帧保存成照片，保存起来
	 * @param absoluteBasePath
	 * @param accessBasePath
	 * @param file
	 * @return 返回截取的照片的相对路径
	 */
	public static String fetchImgFromVideo(String absoluteBasePath,String accessBasePath,MultipartFile file) {
		
		if(file == null)
			BusinessExceptionUtils.throwNewBusinessException("文件为空");
		if(absoluteBasePath == null)
			BusinessExceptionUtils.throwNewBusinessException("路径为空");
		
		String absouteVideoPath = absoluteBasePath + accessBasePath;
		
//		FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(absouteVideoPath);
		
		return null;
	}
	
	
}
