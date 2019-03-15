package com.zx.redcross.serviceimpl.index;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zx.redcross.dao.index.KnowledgeComentMapper;
import com.zx.redcross.entity.KnowledgeComent;
import com.zx.redcross.entity.Page;
import com.zx.redcross.service.index.KnowledgeComentServ;


@Service
public class KnowledgeComentServImpl implements KnowledgeComentServ{
	@Autowired
	private KnowledgeComentMapper mapper;

	@Override
	public Boolean saveKnowledgeComent(KnowledgeComent knowledgeComent) {
		return mapper.saveKnowledgeComent(knowledgeComent);
	}

	@Override
	public Boolean saveKnowledgeComentThumbsup(Integer knowledgeComentId, Integer customerId) {
		return mapper.saveKnowledgeComentThumbsup(knowledgeComentId,customerId);
	}

	@Override
	public Boolean deleteKnowledgeComentThumbsup(Integer knowledgeComentId, Integer customerId) {
		return mapper.deleteKnowledgeComentThumbsup(knowledgeComentId,customerId);
	}

	@Override
	public Integer findKnowledgeComentThumbsupCountByKnowledgeIdAndCustomerId(Integer knowledgeComentId,
			Integer customerId) {
		return mapper.findKnowledgeComentThumbsupCountByKnowledgeIdAndCustomerId(knowledgeComentId,customerId);
	}

	@Override
	public List<Map<String, Object>> findKnowledgeTopicComent(Integer knowledgeId, Page page, Integer customerId) {
		return mapper.findKnowledgeTopicComent(knowledgeId,page,customerId);
	}

	@Override
	public List<Map<String, Object>> findKnowledgeLowerComent(Integer knowledgeComentId, Page page, Integer customerId) {
		return mapper.findKnowledgeLowerComent(knowledgeComentId,page,customerId);
	}

	@Override
	public Integer findknowledgeOnceComentCount(Integer knowledgeId) {
		return mapper.findknowledgeOnceComentCount(knowledgeId);
	}

	@Override
	public Integer findLowerComentCount(Integer knowledgeComentId) {
		return mapper.findLowerComentCount(knowledgeComentId);
	}

	@Override
	public Boolean adminDeleteKnowledgeComent(Integer knowledgeComentId) {
		return mapper.adminDeleteKnowledgeComent(knowledgeComentId);
	}

	@Override
	public Integer getKnowledgeComentCount(Integer knowledgeComentId, Integer customerId) {
		return mapper.getKnowledgeComentCount(knowledgeComentId,customerId);
	}

	
	

}
