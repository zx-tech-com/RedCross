package com.zx.redcross.serviceimpl.index;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.zx.redcross.dao.index.IVideoMapper;
import com.zx.redcross.dao.my.CustomerMapper;
import com.zx.redcross.entity.Customer;
import com.zx.redcross.entity.OrderNumber;
import com.zx.redcross.entity.Page;
import com.zx.redcross.entity.Video;
import com.zx.redcross.entity.VideoBuyRecord;
import com.zx.redcross.service.index.IVideoServ;
import com.zx.redcross.tool.BusinessExceptionUtils;
import com.zx.redcross.tool.Constant;
import com.zx.redcross.tool.FileUtils;
import com.zx.redcross.tool.Utils;

@Service
public class VideoServImpl implements IVideoServ {

	
	@Autowired
	private IVideoMapper videoMapper;
	@Autowired
	private CustomerMapper customerMapper;
	
	@Override
	public List<Map<String,Object>> listVideo(Integer customerId,Page page) {
		return videoMapper.listVideo(customerId,page);
	}

	@Override
	public Map<String,Object> getVideo(Integer customerId, Integer videoId) {
		return videoMapper.getVideo(customerId, videoId);
	}

	@Override
	public Boolean saveVideoBuyRecord(VideoBuyRecord videoBuyRecord) {
		
		if(null == videoMapper.getVideo(videoBuyRecord.getCustomer().getId(), videoBuyRecord.getVideo().getId()))
			BusinessExceptionUtils.throwNewBusinessException("所购买的视频不存在");
		videoBuyRecord.setStatus(Constant.WAIT_TO_PAY);
		VideoBuyRecord oldRecord = videoMapper.getVideoBuyRecordByCustomerAndVideoId(videoBuyRecord.getCustomer().getId(), videoBuyRecord.getVideo().getId());
		if(oldRecord != null ) {//已完成订单
			if(oldRecord.getStatus() == Constant.PAY_COMPLETE) {
				BusinessExceptionUtils.throwNewBusinessException("已购买此视频，不可重复购买");
				return true;
			}
			else {
				videoBuyRecord.setId(oldRecord.getId());
				return updateVideoBuyRecord(videoBuyRecord);
			}
		}else {
			videoBuyRecord.setOrderNumber(
					getVideoOrderNumber(videoBuyRecord.getCustomer().getId())
					);
			return videoMapper.saveVideoBuyRecord(videoBuyRecord);
		}
	}

	@Override
	public Boolean updateVideoBuyRecord(VideoBuyRecord videoBuyRecord) {
		if(videoBuyRecord.getStatus() != Constant.PAY_CANCEL 
				&& videoBuyRecord.getStatus() != Constant.PAY_COMPLETE
				&& videoBuyRecord.getStatus() != Constant.WAIT_TO_PAY
				)
			BusinessExceptionUtils.throwNewBusinessException("状态不合法");
		return videoMapper.updateVideoBuyRecord(videoBuyRecord);
	}
	
	
	private String getVideoOrderNumber(Integer customerId) {
		Customer customer = null;
		if(customerId == null || (customer = customerMapper.findCustomerById(customerId)) == null)
			BusinessExceptionUtils.throwNewBusinessException("下单失败，用户id不合法");
		OrderNumber orderNumber = new OrderNumber();
		orderNumber.setBusinessType(Constant.BUSINESS_TYPE_VIDEO_ORDER);
		String tel = customer.getTel();
		orderNumber.setTel(tel.substring(tel.length() - Constant.TEL_LENGTH, tel.length()));
		orderNumber.setTimeStamp(Utils.newTimeStamp());
		return orderNumber.getOrderNumber();
	}
	
	
	//=========================后台管理接口================
	

	@Override
	public List<Video> adminListVideo() {
		return videoMapper.adminListVideo();
	}

	@Override
	public Boolean adminSaveVideo(Video video,MultipartFile file,MultipartFile imgUrl) {
		String imgAbsoluteBasePath=Constant.IMG_ABSOLUTE_BASE_PATH+Constant.PAYVIDEO;
		String videoAbsoluteBasePath = Constant.VIDEO_ABSOLUTE_BASE_PATH + Constant.PAYVIDEO;
		//存储图片
		if(imgUrl != null) {
			video.setThumbnailUrl(FileUtils.saveFile(imgAbsoluteBasePath, imgUrl));
		}
		//存储视频
		if(file != null) {
			video.setVideoUrl(FileUtils.saveFile(videoAbsoluteBasePath, file));
		}
		//插入video
		if(!videoMapper.adminSaveVideo(video)) {
			FileUtils.removeFile(video.getThumbnailUrl());
			FileUtils.removeFile(video.getVideoUrl());
			return false;
		}
		return true;
	}

	
	
	@Override
	public Boolean adminDeleteVideo(Integer videoId) {
		Video video=videoMapper.getVideoById(videoId);
		if(!videoMapper.adminDeleteVideo(videoId)) {
			return false;
		}
		String videoUrl =video.getVideoUrl();
		String imgUrl=video.getThumbnailUrl();
		if(null != videoUrl && videoUrl.length() >0) {
			FileUtils.removeFile(videoUrl);
		}
		if(null != imgUrl && imgUrl.length() >0) {
			FileUtils.removeFile(imgUrl);
		}
		return true;
	}

	@Override
	public Boolean adminUpdateVideo(Video video,MultipartFile file,MultipartFile imgUrl) {
		Video videoSub=videoMapper.getVideoById(video.getId());
		String imgAbsoluteBasePath=Constant.IMG_ABSOLUTE_BASE_PATH+Constant.PAYVIDEO;
		String videoAbsoluteBasePath = Constant.VIDEO_ABSOLUTE_BASE_PATH + Constant.PAYVIDEO;
		//存储图片
		if(imgUrl != null) {
			video.setThumbnailUrl(FileUtils.saveFile(imgAbsoluteBasePath, imgUrl));
		}
		//存储视频
		if(file != null) {
			video.setVideoUrl(FileUtils.saveFile(videoAbsoluteBasePath, file));
		}
		if(!videoMapper.adminUpdateVideo(video)) {
			return false;
		}
		String videoUrl =videoSub.getVideoUrl();
		String imgUrlOld=videoSub.getThumbnailUrl();
		if(null != videoUrl && videoUrl.length() >0) {
			FileUtils.removeFile(videoUrl);
		}
		if(null != imgUrlOld && imgUrlOld.length() >0) {
			FileUtils.removeFile(imgUrlOld);
		}
		return true;
	}

	@Override
	public List<VideoBuyRecord> adminListVideoBuyRecord() {
		return videoMapper.adminListVideoBuyRecord();
	}
	
	

}
