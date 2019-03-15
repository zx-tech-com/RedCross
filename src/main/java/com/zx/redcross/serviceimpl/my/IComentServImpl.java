package com.zx.redcross.serviceimpl.my;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zx.redcross.dao.my.IComentMapper;
import com.zx.redcross.entity.Page;
import com.zx.redcross.service.my.IComentServ;

@Service
public class IComentServImpl implements IComentServ{

	@Autowired
	private IComentMapper mapper;
	
	
	@Override
	public List<Map<String, Object>> listTopicComentByCustomerId(Integer customerId, Page page) {
		return mapper.listTopicComentByCustomerId(customerId, page);
	}


	@Override
	public List<Map<String, Object>> listKnowledgeComentByCustomerId(Integer customerId, Page page) {
		return mapper.listKnowledgeComentByCustomerId(customerId, page);
	}

}
