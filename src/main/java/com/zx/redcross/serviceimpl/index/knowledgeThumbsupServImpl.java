package com.zx.redcross.serviceimpl.index;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zx.redcross.dao.index.KnowledgeThumbsupMapper;
import com.zx.redcross.service.index.KnowledgeThumbsupServ;
@Service
public class knowledgeThumbsupServImpl implements KnowledgeThumbsupServ{
	@Autowired
	private KnowledgeThumbsupMapper mapper;
	
	
	@Override
	public Boolean saveKnowledgeThumbsup(Integer knowledgeId, Integer customerId) {
		return mapper.saveKnowledgeThumbsup(knowledgeId,customerId);
	}


	@Override
	public Boolean deleteKnowledgeThumbsup(Integer knowledgeId, Integer customerId) {
		return  mapper.deleteKnowledgeThumbsup(knowledgeId,customerId);
	}



	@Override
	public Integer findCountThumbsupByKnowledgeIdAndCustomerId(Integer knowledgeId, Integer customerId) {
		return mapper.findCountThumbsupByKnowledgeIdAndCustomerId(knowledgeId,customerId);
	}

}
