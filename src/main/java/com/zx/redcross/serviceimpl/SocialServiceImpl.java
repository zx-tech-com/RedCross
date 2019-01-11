package com.zx.redcross.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zx.redcross.dao.index.SocialMapper;
import com.zx.redcross.entity.Customer;
import com.zx.redcross.entity.Page;
import com.zx.redcross.entity.Topic;
import com.zx.redcross.entity.TopicType;
import com.zx.redcross.service.index.SocialService;

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
	public List<Topic> findAllTopic(Page page, Integer coustemerId, Integer topicTypeId) {
		// TODO Auto-generated method stub
		return socialMapper.findAllTopic(page, coustemerId, topicTypeId);
	}



}
