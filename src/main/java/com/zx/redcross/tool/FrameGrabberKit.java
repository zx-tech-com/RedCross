package com.zx.redcross.tool;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.bytedeco.javacpp.opencv_core;
import org.bytedeco.javacpp.opencv_core.IplImage;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber.Exception;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.bytedeco.javacv.OpenCVFrameConverter;

/**
 * javaCV截图
 * 
 * @author Daryl
 *
 */
public class FrameGrabberKit {

	/**
	 * 获取指定视频的帧并保存为图片至指定目录
	 * 
	 * @param videofile
	 *            源视频文件路径
	 * @param framefile
	 *            截取帧的图片存放路径
	 * @throws Exception
	 * @throws IOException
	 */
	public static void fetchFrame(String videofile, String framefile) {
		
		try (FFmpegFrameGrabber ff = new FFmpegFrameGrabber(videofile);) {
			ff.start();
			ff.getAudioChannels();
			String rotate = ff.getVideoMetadata("rotate");// 视频的旋转角度
			int maxGrabTimes = 500;//限制最大遍历次数
			int i = 0;
			Frame f = null;
			while (i <= maxGrabTimes) {
				f = ff.grabFrame();
				if (f != null && f.image != null) {
					IplImage src = null;
					if (null != rotate && rotate.length() > 1) {
						OpenCVFrameConverter.ToIplImage converter = new OpenCVFrameConverter.ToIplImage();
						src = converter.convert(f);
						f = converter.convert(rotate(src, Integer.valueOf(rotate)));
					}
					doExecuteFrame(f, framefile);
					break;
				}
				i++;
			}
			if (f == null || f.image == null)
				BusinessExceptionUtils.throwNewBusinessException("已遍历所有帧,视频截取缩略图失败");
		} catch (Exception e) {
			BusinessExceptionUtils.throwNewBusinessException("视频截取缩略图失败");
		}
	}

	public static IplImage rotate(IplImage src, int angle) {
		IplImage img = IplImage.create(src.height(), src.width(), src.depth(), src.nChannels());
		opencv_core.cvTranspose(src, img);
		opencv_core.cvFlip(img, img, angle);
		return img;
	}

	public static void doExecuteFrame(Frame f, String targetFileName) {
		if (null == f || null == f.image) {
			return;
		}
		Java2DFrameConverter converter = new Java2DFrameConverter();
		BufferedImage bi = converter.getBufferedImage(f);
		File output = new File(targetFileName);
		try {
			ImageIO.write(bi, "jpg", output);
		} catch (IOException e) {
			BusinessExceptionUtils.throwNewBusinessException("截取的缩略图保存失败");
		}
	}

}
