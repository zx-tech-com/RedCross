package com.zx.redcross.dao.index;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zx.redcross.entity.KnowledgeComent;
import com.zx.redcross.entity.Page;

/**
 * 知识评论添加，查询，点赞
 * @author ly
 *
 */
public interface KnowledgeComentMapper {
	//添加评论
	Boolean saveKnowledgeComent(KnowledgeComent knowledgeComent);
	//添加知识评论点赞
	Boolean saveKnowledgeComentThumbsup(@Param("knowledgeComentId")Integer knowledgeComentId,@Param("customerId") Integer customerId);
	//删除知识评论点赞
	Boolean deleteKnowledgeComentThumbsup(@Param("knowledgeComentId")Integer knowledgeComentId,@Param("customerId") Integer customerId);
	//查询用户是否对知识评论点赞0（没没点赞）1（点赞）
	Integer findKnowledgeComentThumbsupCountByKnowledgeIdAndCustomerId(@Param("knowledgeComentId")Integer knowledgeComentId,@Param("customerId") Integer customerId);
	//查询知识第一层评论
	List<Map<String, Object>> findKnowledgeTopicComent(@Param("knowledgeId")Integer knowledgeId,@Param("page")Page page,@Param("customerId") Integer customerId);
	//查询知识第二层及以下评论
	List<Map<String, Object>> findKnowledgeLowerComent(@Param("knowledgeComentId")Integer knowledgeComentId,@Param("page") Page page,@Param("customerId") Integer customerId);
	//查询该常识下面第一层的评论数量
	Integer findknowledgeOnceComentCount(Integer knowledgeId);
	//查询该常识下面第二层以下的评论数量
	Integer findLowerComentCount(Integer knowledgeComentId);
	//后台删除常识评论
	Boolean adminDeleteKnowledgeComent(Integer knowledgeComentId);
	Integer getKnowledgeComentCount(@Param("knowledgeComentId")Integer knowledgeComentId,@Param("customerId") Integer customerId);
}
