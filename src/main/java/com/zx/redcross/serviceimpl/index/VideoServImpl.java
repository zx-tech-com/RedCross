package com.zx.redcross.serviceimpl.index;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.zx.redcross.dao.index.IVideoMapper;
import com.zx.redcross.entity.Page;
import com.zx.redcross.entity.Video;
import com.zx.redcross.entity.VideoBuyRecord;
import com.zx.redcross.service.index.IVideoServ;
import com.zx.redcross.tool.BusinessExceptionUtils;
import com.zx.redcross.tool.Constant;
import com.zx.redcross.tool.FileUtils;

@Service
public class VideoServImpl implements IVideoServ {

	
	@Autowired
	private IVideoMapper mapper;
	
	@Override
	public List<Map<String,Object>> listVideo(Integer customerId,Page page) {
		return mapper.listVideo(customerId,page);
	}

	@Override
	public Map<String,Object> getVideo(Integer customerId, Integer videoId) {
		return mapper.getVideo(customerId, videoId);
	}

	@Override
	public Boolean saveVideoBuyRecord(VideoBuyRecord videoBuyRecord) {
		
		videoBuyRecord.setStatus(Constant.WAIT_TO_PAY);
		if(mapper.getVideo(videoBuyRecord.getCustomer().getId(), videoBuyRecord.getVideo().getId()) != null)
			return mapper.updateVideoBuyRecord(videoBuyRecord);
		else 
			return mapper.saveVideoBuyRecord(videoBuyRecord);
	}

	@Override
	public Boolean updateVideoBuyRecord(VideoBuyRecord videoBuyRecord) {
		if(videoBuyRecord.getStatus() != Constant.PAY_CANCEL 
				&& videoBuyRecord.getStatus() != Constant.PAY_COMPLETE
				&& videoBuyRecord.getStatus() != Constant.WAIT_TO_PAY
				)
			BusinessExceptionUtils.throwNewBusinessException("状态不合法");
		return mapper.updateVideoBuyRecord(videoBuyRecord);
	}
	
	
	//=========================后台管理接口================
	

	@Override
	public List<Video> adminListVideo() {
		return mapper.adminListVideo();
	}

	@Override
	public Boolean adminSaveVideo(Video video,MultipartFile file) {
		String videoAbsoluteBasePath = Constant.VIDEO_ABSOLUTE_BASE_PATH + Constant.PAYVIDEO;
		//存储视频
		if(file != null) {
			video.setVideoUrl(FileUtils.saveFile(videoAbsoluteBasePath, file));
		}
		//插入video
		if(!mapper.adminSaveVideo(video)) {
			FileUtils.removeFile(video.getVideoUrl());
			return false;
		}
		return true;
	}

	@Override
	public Boolean adminDeleteVideo(Integer videoId) {
		Video video=mapper.getVideoById(videoId);
		if(!mapper.adminDeleteVideo(videoId)) {
			return false;
		}
		String videoUrl =video.getVideoUrl();
		if(null != videoUrl && videoUrl.length() >0) {
			FileUtils.removeFile(videoUrl);
		}
		return true;
	}

	@Override
	public Boolean adminUpdateVideo(Video video) {
		return mapper.adminUpdateVideo(video);
	}

	@Override
	public List<VideoBuyRecord> adminListVideoBuyRecord() {
		return mapper.adminListVideoBuyRecord();
	}
	
	

}
