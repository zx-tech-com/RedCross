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
		String name = fileName.substring(fileName.lastIndexOf('.'), fileName.length()-1);
		return name;
	}
	
	/**
	 * 保存file到absoluteBasePath目录下
	 * @param absoluteBasePath
	 * @param file
	 * @return 返回该文件的访问路径
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
		
		path = new File(absoluteBasePath + getFileName(file));
		
		try {
			file.transferTo(path);
		} catch (IllegalStateException | IOException e) {
			BusinessExceptionUtils.throwNewBusinessException("文件存储失败");
		}
		return absoluteBasePath.replace(Constant.ABSOLUTE_BASE_PATH, "") + getFileName(file);
	}
	
	public static String getFullUrl(String url) {
		if(url != null && url.length() > 0)
			return Constant.ACCESS_BASE_PATH + url;
		return null;
	}	
}
