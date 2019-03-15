package com.zx.redcross.service.index;

import java.util.List;
import java.util.Map;

import com.zx.redcross.entity.KnowledgeComent;
import com.zx.redcross.entity.Page;

public interface KnowledgeComentServ {
	//添加评论
	Boolean saveKnowledgeComent(KnowledgeComent knowledgeComent);
	//添加知识评论点赞
	Boolean saveKnowledgeComentThumbsup(Integer knowledgeComentId,Integer customerId);
	//删除知识评论点赞
	Boolean deleteKnowledgeComentThumbsup(Integer knowledgeComentId,Integer customerId);
	//查询用户是否对知识评论点赞0（没没点赞）1（点赞）
	Integer findKnowledgeComentThumbsupCountByKnowledgeIdAndCustomerId(Integer knowledgeComentId,Integer customerId);
	//查询知识第一层评论
	public List<Map<String, Object>> findKnowledgeTopicComent(Integer knowledgeId, Page page, Integer customerId);
	
	//查询知识第二三评论
	public List<Map<String, Object>> findKnowledgeLowerComent(Integer knowledgeComentId, Page page, Integer customerId);
	//查询该常识下面第一层的评论数量
	Integer findknowledgeOnceComentCount(Integer knowledgeId);
	//查询该常识下面第二层以下的评论数量
	Integer findLowerComentCount(Integer knowledgeComentId);
	//后台删除常识评论
	Boolean adminDeleteKnowledgeComent(Integer knowledgeComentId);
	Integer getKnowledgeComentCount(Integer knowledgeComentId, Integer customerId);
	
	
}
