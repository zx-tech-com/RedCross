package com.zx.redcross.serviceimpl.social;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zx.redcross.dao.social.SocialMapper;
import com.zx.redcross.entity.Concern;
import com.zx.redcross.entity.Customer;
import com.zx.redcross.entity.Page;
import com.zx.redcross.entity.Topic;
import com.zx.redcross.entity.TopicComent;
import com.zx.redcross.entity.TopicType;
import com.zx.redcross.service.social.SocialService;

@Service
public class SocialServiceImpl implements SocialService{
	@Autowired
	private SocialMapper socialMapper;
	
	@Override
	public List<TopicType> findAllTopicType() {
		return socialMapper.findAllTopicType();
	}

	@Override
	public Integer findAllCountTopic() {
		// TODO Auto-generated method stub
		return socialMapper.findAllTopicCount();
	}

	@Override
	public List<Topic> findAllTopic(Page page, Integer customerId, Integer topicTypeId) {
		// TODO Auto-generated method stub
		return socialMapper.findAllTopic(page, customerId, topicTypeId);
	}


	@Override
	public Topic findToicById(Integer topicId,Integer customerId) {
		// TODO Auto-generated method stub
		return socialMapper.findTopicById(topicId,customerId);
	}

	@Override
	public Integer findConcert(Integer aCustomerId, Integer pCustomerId) {
		// TODO Auto-generated method stub
		return socialMapper.findConcert(aCustomerId,pCustomerId);
	}

	@Override
	public void saveConcert(Integer aCustomerId, Integer pCustomerId) {
		// TODO Auto-generated method stub
		socialMapper.saveConcert(aCustomerId,pCustomerId);
	}

	@Override
	public void deleteConcert(Integer aCustomerId, Integer pCustomerId) {
		// TODO Auto-generated method stub
		socialMapper.deleteConcert(aCustomerId,pCustomerId);
	}



	

	@Override
	public void saveTopicComent(TopicComent topicComent) {
		// TODO Auto-generated method stub
		socialMapper.saveTopicComent(topicComent);
	}

	@Override
	public List<TopicComent> findTopicComent(Integer topicId, Page page, Integer customerId) {
		// TODO Auto-generated method stub
		return socialMapper.findTopicComent(topicId, page,customerId);
	}

	@Override
	public List<TopicComent> findTopicComent2(Integer topicComentId, Page page, Integer customerId) {
		// TODO Auto-generated method stub
		return socialMapper.findTopicComent2(topicComentId, page,customerId);
	}

	@Override
	public List<TopicComent> findTopicComent3(Integer topicComentId, Page page, Integer customerId) {
		// TODO Auto-generated method stub
		return socialMapper.findTopicComent3(topicComentId, page,customerId);
	}

	@Override
	public Integer findThunsup(Integer coustomerId, Integer topicComentId) {
		// TODO Auto-generated method stub
		return socialMapper.findThunsup(coustomerId,topicComentId);
	}

	@Override
	public void saveThunsup(Integer coustomerId, Integer topicComentId) {
		// TODO Auto-generated method stub
		socialMapper.saveThunsup(coustomerId,topicComentId);
	}

	@Override
	public void deleteThunsup(Integer coustomerId, Integer topicComentId) {
		// TODO Auto-generated method stub
		socialMapper.deleteThunsup(coustomerId,topicComentId);
	}

	@Override
	public Integer findTopicThunsup(Integer coustomerId, Integer topicId) {
		// TODO Auto-generated method stub
		return socialMapper.findTopicThunsup(coustomerId,topicId);
	}

	@Override
	public void saveTopicThunsup(Integer coustomerId, Integer topicId) {
		// TODO Auto-generated method stub
		socialMapper.saveTopicThunsup(coustomerId,topicId);
	}

	@Override
	public void deleteTopicThunsup(Integer coustomerId, Integer topicId) {
		// TODO Auto-generated method stub
		socialMapper.deleteTopicThunsup(coustomerId,topicId);
	}

	@Override
	public void saveTopic(Topic topic) {
		// TODO Auto-generated method stub
		socialMapper.saveTopic(topic);
	}



}
