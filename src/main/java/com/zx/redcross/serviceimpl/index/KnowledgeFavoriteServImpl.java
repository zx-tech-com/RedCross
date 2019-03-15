package com.zx.redcross.serviceimpl.index;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zx.redcross.dao.index.KnowledgeFavoriteMapper;
import com.zx.redcross.service.index.KnowledgeFavoriteServ;

@Service
public class KnowledgeFavoriteServImpl implements KnowledgeFavoriteServ{
	@Autowired
	private KnowledgeFavoriteMapper mapper;
	@Override
	public Boolean saveKnowledgeFavorite(Integer knowledgeId, Integer customerId) {
		return mapper.saveKnowledgeFavorite(knowledgeId,customerId);
	}
	@Override
	public Boolean deleteKnowledgeFavorite(Integer knowledgeId, Integer customerId) {
		return mapper.deleteKnowledgeFavorite(knowledgeId,customerId);
	}
	@Override
	public Integer findFavoriteCountByKnowledgeIdAndCustomerId(Integer knowledgeId, Integer customerId) {
		return mapper.findFavoriteCountByKnowledgeIdAndCustomerId(knowledgeId,customerId);
	}

}
