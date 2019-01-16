package com.zx.redcross.serviceimpl.my;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zx.redcross.dao.my.ITopicMapper;
import com.zx.redcross.entity.Page;
import com.zx.redcross.entity.Topic;
import com.zx.redcross.service.my.ITopicServ;

@Service
public class TopicServImpl implements ITopicServ {
	
	@Autowired
	private ITopicMapper topicMapper;
	
	@Override
	public List<Topic> listTopicByCustomerId(Integer customerId, Page page) {
		return topicMapper.listTopicByCustomerId(customerId, page);
	}

}
