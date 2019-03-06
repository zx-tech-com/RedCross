package com.zx.redcross.serviceimpl.index;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.zx.redcross.dao.index.IVideoMapper;
import com.zx.redcross.dao.index.IVideoPayRecordMapper;
import com.zx.redcross.dao.my.CustomerMapper;
import com.zx.redcross.entity.Customer;
import com.zx.redcross.entity.OrderNumber;
import com.zx.redcross.entity.Page;
import com.zx.redcross.entity.Video;
import com.zx.redcross.entity.VideoBuyRecord;
import com.zx.redcross.entity.VideoPayRecord;
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
	@Autowired
	private IVideoPayRecordMapper payRecordMapper;
	
	
	
	@Override
	public List<Map<String,Object>> listVideo(Integer customerId,Page page) {
		return videoMapper.listVideo(customerId,page);
	}

	@Override
	public Map<String,Object> getVideo(Integer customerId, Integer videoId) {
		return videoMapper.getVideo(customerId, videoId);
	}
	
	/**
	 * 用户下单
	 */
	@Override
	public Boolean saveVideoBuyRecord(VideoBuyRecord videoBuyRecord) {
		
		if(null == videoMapper.getVideo(videoBuyRecord.getCustomer().getId(), videoBuyRecord.getVideo().getId()))
			BusinessExceptionUtils.throwNewBusinessException("所购买的视频不存在");
		videoBuyRecord.setStatus(Constant.WAIT_TO_PAY);
		VideoBuyRecord oldRecord = videoMapper.getVideoBuyRecordByCustomerAndVideoId(videoBuyRecord.getCustomer().getId(), videoBuyRecord.getVideo().getId());
		if(oldRecord != null && oldRecord.getStatus() == Constant.PAY_COMPLETE ) {//订单已支付,无需重复支付
			BusinessExceptionUtils.throwNewBusinessException("已购买此视频，不可重复购买");
			return false;
		}
		else if(oldRecord != null){//已下订单,但未支付或支付已取消
			videoBuyRecord.setId(oldRecord.getId());
			//updateVideoBuyRecord该方法只更新status,因此给下面的orderNumber赋值可有可无,数据库都不会更新该字段
			//这里依然赋值是为了在上层controller中能够获取到订单号
			videoBuyRecord.setOrderNumber(oldRecord.getOrderNumber());
			return updateVideoBuyRecordStatus(videoBuyRecord);
		}
		else{//新订单,生成订单号后存入数据库
			videoBuyRecord.setOrderNumber(getVideoOrderNumber(videoBuyRecord.getCustomer().getId()));
			boolean flag = videoMapper.saveVideoBuyRecord(videoBuyRecord);
			if(!flag)
				BusinessExceptionUtils.throwNewBusinessException("视频教学购买失败");
			return payRecordMapper.addVideoPayRecord(getVideoPayRecord(videoBuyRecord));
		}	
	}
	
	/**
	 * 只更新status
	 */
	@Override
	public Boolean updateVideoBuyRecordStatus(VideoBuyRecord videoBuyRecord) {
		if(videoBuyRecord.getStatus() != Constant.PAY_CANCEL 
				&& videoBuyRecord.getStatus() != Constant.PAY_COMPLETE
				&& videoBuyRecord.getStatus() != Constant.WAIT_TO_PAY
				)
			BusinessExceptionUtils.throwNewBusinessException("视频购买状态不合法");
		boolean flag = videoMapper.updateVideoBuyRecord(videoBuyRecord);
		if(!flag)
			BusinessExceptionUtils.throwNewBusinessException("视频购买状态更新失败");
		//先查询orderNumber
		VideoBuyRecord videoBuyRecord2 = videoMapper.getVideoBuyRecordByCustomerAndVideoId(
				videoBuyRecord.getCustomer().getId(), videoBuyRecord.getVideo().getId());
		VideoPayRecord payRecord = payRecordMapper.getVideoPayRecordByOrderNumber(videoBuyRecord2.getOrderNumber());
		payRecord.setStatus(videoBuyRecord.getStatus());
		return payRecordMapper.updateVideoPayRecordStatus(payRecord);
	}
	
	
	/**
	 * 根据videoBuyRecord生成VideoPayRecord
	 * @param videoBuyRecord
	 * @return
	 */
	private VideoPayRecord getVideoPayRecord(VideoBuyRecord videoBuyRecord) {
		VideoPayRecord record = new VideoPayRecord();
		record.setVideoBuyRecord(videoBuyRecord);
		Customer cus = new Customer();
		cus.setId(videoBuyRecord.getCustomer().getId());
		record.setCustomer(cus);
		record.setPayMethod(videoBuyRecord.getPayMethod());
		//设置金额
		Video video = videoMapper.getVideoById(videoBuyRecord.getVideo().getId());
		record.setAmount(video.getPrice());
		record.setStatus(Constant.WAIT_TO_PAY);
		record.setOrderNumber(videoBuyRecord.getOrderNumber());
		return record;
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
	
	
	/**
	 * 设置支付方式
	 */
	@Override
	public Boolean updatePayMethod(String payMethod,String orderNumber) {
		return payRecordMapper.updatePayMethod(payMethod,orderNumber) 
				&& videoMapper.updateVideoBuyRecordPayMethod(payMethod,orderNumber);
	}
	
	
	//=========================后台管理接口================
	

	@Override
	public List<Video> adminListVideo(Page page) {
		return videoMapper.adminListVideo(page);
	}

	@Override
	public Boolean adminSaveVideo(Video video,MultipartFile file/*,MultipartFile imgUrl*/) {
		String videoAbsoluteBasePath = Constant.VIDEO_ABSOLUTE_BASE_PATH + Constant.PAYVIDEO;
		/*//存储图片
		if(imgUrl != null) {
			video.setThumbnailUrl(FileUtils.saveFile(imgAbsoluteBasePath, imgUrl));
		}*/
		//存储视频
		if(file != null) {
			video.setVideoUrl(FileUtils.saveFile(videoAbsoluteBasePath, file));
			video.setThumbnailUrl(FileUtils.fetchImgFromVideo(video.getVideoUrl()));
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

	/**
	 * 记得先保存视频，然后修改记录，最后删除旧视频
	 */
	@Override
	public Boolean adminUpdateVideo(Video video,MultipartFile file/*,MultipartFile imgUrl*/) {
		Video videoSub=videoMapper.getVideoById(video.getId());
		String videoUrl =videoSub.getVideoUrl();
		String imgUrlOld=videoSub.getThumbnailUrl();
		String videoAbsoluteBasePath = Constant.VIDEO_ABSOLUTE_BASE_PATH + Constant.PAYVIDEO;
		//存储图片
		/*if(imgUrl != null) {
			video.setThumbnailUrl(FileUtils.saveFile(imgAbsoluteBasePath, imgUrl));
		}*/
		//存储视频
		if(file != null) {
			video.setVideoUrl(FileUtils.saveFile(videoAbsoluteBasePath, file));
			video.setThumbnailUrl(FileUtils.fetchImgFromVideo(video.getVideoUrl()));
		}else {
			video.setVideoUrl(videoUrl);
			video.setThumbnailUrl(imgUrlOld);
		}
		if(!videoMapper.adminUpdateVideo(video)) {
			return false;
		}
		if(file != null) {
			if(null != videoUrl && videoUrl.trim().length() >0) {
				FileUtils.removeFile(videoUrl);
			}
			if(null != imgUrlOld && imgUrlOld.trim().length() >0) {
				FileUtils.removeFile(imgUrlOld);
			}
		}
		return true;
	}

	@Override
	public List<VideoBuyRecord> adminListVideoBuyRecord(VideoBuyRecord record) {
		return videoMapper.adminListVideoBuyRecord(record);
	}

	
	@Override
	public Boolean adminDeleteBatchVideo(String ids) {
		if(ids == null || ids.trim().length() == 0)
			return true;
		//1 首先获取ids对应的videoURL
		List<Video> list = videoMapper.listVideoByIds(ids);
		//2尝试删除ids记录
		Boolean flag = videoMapper.adminDeleteBatchVideo(ids);
		//3尝试删除相应的video
		if(flag)
			for(Video video : list) 
				FileUtils.removeFile(video.getVideoUrl());
		return flag;
	}
	@Override
	public Integer findVideoCount() {
		return videoMapper.findVideoCount();
	}

	@Override
	public Video getVideoByVideoBuyOrderNumber(String orderNumber) {
		return videoMapper.getVideoByVideoBuyOrderNumber(orderNumber);
	}


}
