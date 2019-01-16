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
	public Integer findAllCountTopic() {
		return socialMapper.findAllTopicCount();
	}

	@Override
	public List<Topic> findAllTopic(Page page, Integer customerId, Integer topicTypeId) {
		return socialMapper.findAllTopic(page, customerId, topicTypeId);
	}


	@Override
	public Topic findToicById(Integer topicId,Integer customerId) {
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
	public void saveTopicComent(TopicComent topicComent) {
		socialMapper.saveTopicComent(topicComent);
	}

	@Override
	public List<TopicComent> findTopicComent(Integer topicId, Page page, Integer customerId) {
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
		
		//存储视频
		if(video != null) {
			topic.setHasVideo(true);
			topic.setVideoUrl(FileUtils.saveFile(videoAbsoluteBasePath, video));
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
	public void adminDeleteTopic(Integer topicId) {
		socialMapper.adminDeleteTopic(topicId);
	}

	@Override
	public void adminDeleteTopicComent(Integer topicComentId) {
		socialMapper.adminDeleteTopicComent(topicComentId);
	}


}
