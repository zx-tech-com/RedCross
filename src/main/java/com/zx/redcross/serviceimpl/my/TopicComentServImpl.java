package com.zx.redcross.serviceimpl.my;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zx.redcross.dao.my.ITopicComentMapper;
import com.zx.redcross.entity.Page;
import com.zx.redcross.entity.TopicComent;
import com.zx.redcross.service.my.ITopicComentServ;

@Service
public class TopicComentServImpl implements ITopicComentServ{

	@Autowired
	private ITopicComentMapper mapper;
	
	
	@Override
	public List<TopicComent> listTopicComentByCustomerId(Integer customerId, Page page) {
		return mapper.listTopicComentByCustomerId(customerId, page);
	}

}
