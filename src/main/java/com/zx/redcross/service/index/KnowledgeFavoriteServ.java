package com.zx.redcross.service.index;

public interface KnowledgeFavoriteServ {
	
	Boolean saveKnowledgeFavorite(Integer knowledgeId,Integer customerId);
	
	Boolean deleteKnowledgeFavorite(Integer knowledgeId,Integer customerId);
	
	Integer findFavoriteCountByKnowledgeIdAndCustomerId(Integer knowledgeId,Integer customerId);
}
