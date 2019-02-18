package com.zx.redcross.serviceimpl.social;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.zx.redcross.dao.social.IImgMapper;
import com.zx.redcross.dao.social.SocialMapper;
import com.zx.redcross.entity.Img;
import com.zx.redcross.entity.Page;
import com.zx.redcross.entity.Topic;
import com.zx.redcross.entity.TopicComent;
import com.zx.redcross.entity.TopicType;
import com.zx.redcross.service.social.SocialService;
import com.zx.redcross.tool.Constant;
import com.zx.redcross.tool.FileUtils;

@Service
public class SocialServiceImpl implements SocialService{
	@Autowired
	private SocialMapper socialMapper;
	@Autowired
	private IImgMapper imgMapper;
	
	@Override
	public List<TopicType> findAllTopicType() {
		return socialMapper.findAllTopicType();
	}

	@Override
	public Integer findAllCountTopic(Integer topicTypeId) {
		return socialMapper.findAllTopicCount(topicTypeId);
	}

	@Override
	public List<Map<String, Object>> findAllTopic(Page page, Integer customerId, Integer topicTypeId) {
		return socialMapper.findAllTopic(page, customerId, topicTypeId);
	}


	@Override
	public Map<String, Object> findToicById(Integer topicId,Integer customerId) {
		return socialMapper.findTopicById(topicId,customerId);
	}

	@Override
	public Integer findConcert(Integer aCustomerId, Integer pCustomerId) {
		return socialMapper.findConcert(aCustomerId,pCustomerId);
	}

	@Override
	public void saveConcert(Integer aCustomerId, Integer pCustomerId) {
		socialMapper.saveConcert(aCustomerId,pCustomerId);
	}

	@Override
	public void deleteConcert(Integer aCustomerId, Integer pCustomerId) {
		socialMapper.deleteConcert(aCustomerId,pCustomerId);
	}
	
	@Override
	public Boolean saveTopicComent(TopicComent topicComent) {
		return socialMapper.saveTopicComent(topicComent);
	}

	@Override
	public List<Map<String, Object>> findTopicComent(Integer topicId, Page page, Integer customerId) {
		return socialMapper.findTopicComent(topicId, page,customerId);
	}

	

	@Override
	public Integer findThunsup(Integer coustomerId, Integer topicComentId) {
		return socialMapper.findThunsup(coustomerId,topicComentId);
	}

	@Override
	public void saveThunsup(Integer coustomerId, Integer topicComentId) {
		socialMapper.saveThunsup(coustomerId,topicComentId);
	}

	@Override
	public void deleteThunsup(Integer coustomerId, Integer topicComentId) {
		socialMapper.deleteThunsup(coustomerId,topicComentId);
	}

	@Override
	public Integer findTopicThunsup(Integer coustomerId, Integer topicId) {
		return socialMapper.findTopicThunsup(coustomerId,topicId);
	}

	@Override
	public void saveTopicThunsup(Integer coustomerId, Integer topicId) {
		socialMapper.saveTopicThunsup(coustomerId,topicId);
	}

	@Override
	public void deleteTopicThunsup(Integer coustomerId, Integer topicId) {
		socialMapper.deleteTopicThunsup(coustomerId,topicId);
	}

	@Override
	public void saveTopic(Topic topic) {
		socialMapper.saveTopic(topic);
	}
	
	
	@Override
	public List<Map<String, Object>> findLowerComent(Integer topicComentId, Page page, Integer customerId) {
		return socialMapper.findLowerComent(topicComentId, page, customerId);
	}
	
	
	@Override
	public Boolean saveTopic(MultipartFile[] images, MultipartFile video, Topic topic) {
		String imgAbsoluteBasePath = Constant.IMG_ABSOLUTE_BASE_PATH + Constant.TOPIC;
		String videoAbsoluteBasePath = Constant.VIDEO_ABSOLUTE_BASE_PATH + Constant.TOPIC;
		topic.setHasVideo(false);
		//存储视频
		if(video != null) {
			topic.setStatus(Constant.POPIC_STATUS3);
			topic.setHasVideo(true);
			topic.setVideoUrl(FileUtils.saveFile(videoAbsoluteBasePath, video));
		}else if(images.length == 1) {
			topic.setStatus(Constant.POPIC_STATUS1);
		}else if(images.length >= 2){
			topic.setStatus(Constant.POPIC_STATUS2);
		}else {
			topic.setStatus(Constant.POPIC_STATUS0);
		}
		
		//插入topic
		if(!socialMapper.saveTopic(topic)) {
			FileUtils.removeFile(topic.getVideoUrl());
			return false;
		}
		System.err.println(topic.getId());
		
		//存储照片,插入img
		if(images != null && images.length > 0) {
			Img img = new Img();
			img.setForeignId(topic.getId());
			img.setImgType(Constant.IMG_TYPE_TOPIC);
			
			for(int i = 0; i<images.length; i++) {//可以做一步优化，将多条记录一起插入
				String imgUrl = FileUtils.saveFile(imgAbsoluteBasePath, images[i]);
				img.setImgUrl(imgUrl);
				img.setIindex((byte) i);
				imgMapper.saveImg(img);
			}
		}
		
		return true;
	}
	
	@Override
	public Boolean updateTopicSetShareAdd1(Integer topicId) {
		return socialMapper.updateTopicSetShareAdd1(topicId);
	}	
	
	//===============================后台管理需要用到的接口===================================
	@Override
	public Boolean adminDeleteTopic(Integer topicId) {
		Integer customerId=null;
		Map<String,Object> topic=socialMapper.findTopicById(topicId,customerId);
		if(!socialMapper.adminDeleteTopic(topicId)) {
			return false;
		}
		if((boolean) topic.get("hasVideo")) {
			FileUtils.removeFile((String)topic.get("videoUrl"));
		}else {
			@SuppressWarnings("unchecked")
			List<Map<String,Object>> imgs=(List<Map<String,Object>>) topic.get("imgs");
			if(imgs.size()>0) {
				for(Map<String,Object> img:imgs) {
					FileUtils.removeFile((String) img.get("imgUrl"));
				}	
			}
		}
		return true;
	}

	@Override
	public Boolean adminDeleteTopicComent(Integer topicComentId) {
		return socialMapper.adminDeleteTopicComent(topicComentId);
	}

	@Override
	public Boolean addTopicType(TopicType topicType, MultipartFile imgUrl) {
		String imgAbsoluteBasePath = Constant.IMG_ABSOLUTE_BASE_PATH + Constant.TOPIC;
		//存储图片
		if(imgUrl!=null) {
			topicType.setThumbnailUrl(FileUtils.saveFile(imgAbsoluteBasePath, imgUrl));
		}
		if(!socialMapper.addTopicType(topicType)) {
			FileUtils.removeFile(topicType.getThumbnailUrl());
			return false;
		}
		return true;
	}

	@Override
	public Boolean adminDeleteTopicType(Integer topicTypeId) {
		String thumbnailUrl= socialMapper.findTopicTypeById(topicTypeId);
		if(!socialMapper.adminDeleteTopicType(topicTypeId)) {
			return false;
		}
		FileUtils.removeFile(thumbnailUrl);
		return true;
	}

	@Override
	public Boolean adminUpdateTopicType(TopicType topicType, MultipartFile imgUrl) {
		String imgAbsoluteBasePath = Constant.IMG_ABSOLUTE_BASE_PATH + Constant.TOPIC;
		String thumbnailUrl= socialMapper.findTopicTypeById(topicType.getId());
		//存储图片
		if(imgUrl!=null) {
			topicType.setThumbnailUrl(FileUtils.saveFile(imgAbsoluteBasePath, imgUrl));
		}
		if(!socialMapper.adminUpdateTopicType(topicType)) {
			FileUtils.removeFile(topicType.getThumbnailUrl());
			return false;
		}
		if(imgUrl!=null) {
			FileUtils.removeFile(thumbnailUrl);
		}
		return true;
	}

	@Override
	public Integer findOnceTopicComentCount(Integer topicId) {
		return socialMapper.findOnceTopicComentCount(topicId);
	}

	@Override
	public Integer findLowerComentCount(Integer topicComentId) {
		return socialMapper.findLowerComentCount(topicComentId);
	}

	@Override
	public Integer getTopicThumbsupCount(Integer topicId) {
		return socialMapper.getTopicThumbsupCount(topicId);
	}

	@Override
	public Integer getThunsupCount(Integer topicComentId) {
		return socialMapper.getThunsupCount(topicComentId);
	}

	@Override
	public Integer getTopicComentCount(Integer id) {
		return socialMapper.getTopicComentCount(id);
	}


	


}
