package com.zx.redcross.dao.index;

import org.apache.ibatis.annotations.Param;

/**
 * 急救常识点赞
 * @author ly
 *
 */
public interface KnowledgeThumbsupMapper {
	//添加常识点赞
	Boolean saveKnowledgeThumbsup(@Param("knowledgeId")Integer knowledgeId,@Param("customerId") Integer customerId);
	//删除常识点赞
	Boolean deleteKnowledgeThumbsup(@Param("knowledgeId")Integer knowledgeId,@Param("customerId") Integer customerId);
	//查询用户是否对知识点赞0（没点赞）1（点赞）
	Integer findCountThumbsupByKnowledgeIdAndCustomerId(@Param("knowledgeId")Integer knowledgeId,@Param("customerId") Integer customerId);
}
