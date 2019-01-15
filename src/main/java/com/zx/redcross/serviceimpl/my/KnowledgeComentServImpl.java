package com.zx.redcross.serviceimpl.my;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zx.redcross.dao.my.IKnowledgeComentMapper;
import com.zx.redcross.entity.KnowledgeComent;
import com.zx.redcross.entity.Page;
import com.zx.redcross.service.my.IKnowledgeComentServ;

@Service
public class KnowledgeComentServImpl implements IKnowledgeComentServ{

	@Autowired
	private IKnowledgeComentMapper mapper;
	
	
	@Override
	public List<KnowledgeComent> listKnowledgeComentByCustomerId(Integer customerId, Page page) {
		return mapper.listKnowledgeComentByCustomerId(customerId, page);
	}

}
