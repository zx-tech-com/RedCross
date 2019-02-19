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
		return url;
	}	
	
	
	/**
	 * 从视频中截取一帧保存成照片，保存起来
	 * @param relativePath视频的相对路径
	 * @return 返回截取的照片的相对路径
	 */
	public static String fetchImgFromVideo(String relativePath) {
		
		//先拿到视频的绝对路径. 路径分隔符Constant.ABSOLUTE_BASE_PATH用的是File.seperator,relativePath用的是 '/'
		String absoluteVideoPath = (Constant.ABSOLUTE_BASE_PATH + relativePath);
		absoluteVideoPath = absoluteVideoPath.replace("/", File.separator).replace("\\", File.separator);
		
		File video = new File(absoluteVideoPath);
		if(!video.isFile())
			BusinessExceptionUtils.throwNewBusinessException("文件路径不正确");
		
		String imgAbsolutePath = generateImgPathByVideoPath(absoluteVideoPath);
		
		FrameGrabberKit.fetchFrame(absoluteVideoPath, imgAbsolutePath);
		System.err.println(absoluteVideoPath);
		System.err.println(imgAbsolutePath);
		return imgAbsolutePath.replace(Constant.ABSOLUTE_BASE_PATH, "").replace("\\", "/");
		
	}
	
	/**
	 * 根据视频的绝对路径生成图像的绝对路径
	 * @param absouteVideoPath 视频的绝对路径
	 * @return
	 */
	private static String generateImgPathByVideoPath(String absouteVideoPath) {
		String absoluteImgPath = absouteVideoPath;
		if(absoluteImgPath == null || absoluteImgPath.length() == 0)
			BusinessExceptionUtils.throwNewBusinessException("文件路径不正确");
		//UUID生成照片名字.
		String imgName = UUID.randomUUID().toString() + ".jpg";
		absoluteImgPath = absoluteImgPath.replace("/", File.separator).replace("\\", File.separator);
		absoluteImgPath = absoluteImgPath.substring(0, absoluteImgPath.lastIndexOf(File.separator) + 1) + imgName;
		return absoluteImgPath;
	}
	
}
