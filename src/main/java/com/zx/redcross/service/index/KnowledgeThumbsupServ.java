package com.zx.redcross.service.index;
/**
 * 急救常识点赞
 * @author ly
 *
 */
public interface KnowledgeThumbsupServ {
	
	Boolean saveKnowledgeThumbsup(Integer knowledgeId, Integer customerId);

	Boolean deleteKnowledgeThumbsup(Integer knowledgeId, Integer customerId);

	Integer findCountThumbsupByKnowledgeIdAndCustomerId(Integer knowledgeId, Integer customerId);

}
