package com.zx.redcross.dao.index;

import org.apache.ibatis.annotations.Param;

/**
 * 急救常识收藏
 * @author ly
 *
 */
public interface KnowledgeFavoriteMapper {
	//添加收藏
	Boolean saveKnowledgeFavorite(@Param("knowledgeId")Integer knowledgeId,@Param("customerId") Integer customerId);
	//删除收藏
	Boolean deleteKnowledgeFavorite(@Param("knowledgeId")Integer knowledgeId,@Param("customerId") Integer customerId);
	//查询用户是否对知识收藏0（没收藏）1（收藏）
	Integer findFavoriteCountByKnowledgeIdAndCustomerId(@Param("knowledgeId")Integer knowledgeId,@Param("customerId") Integer customerId);
	
}
