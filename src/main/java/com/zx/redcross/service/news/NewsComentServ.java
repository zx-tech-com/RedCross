package com.zx.redcross.service.news;

import java.util.List;
import java.util.Map;
import com.zx.redcross.entity.NewsComent;
import com.zx.redcross.entity.Page;

public interface NewsComentServ {
	//添加评论
	Boolean saveNewsComent(NewsComent newsComent);
	//添加新闻评论点赞
	Boolean saveNewsComentThumbsup(Integer newsComentId,Integer customerId);
	//删除新闻评论点赞
	Boolean deleteNewsComentThumbsup(Integer newsComentId,Integer customerId);
	//查询用户是否对新闻评论点赞0（没没点赞）1（点赞）
	Integer findNewsComentThumbsupCountByKnowledgeIdAndCustomerId(Integer newsComentId,Integer customerId);
	//查询新闻第一层评论
	public List<Map<String, Object>> findNewsTopicComent(Integer newsId, Page page, Integer customerId);
	
	//查询新闻第二三评论
	public List<Map<String, Object>> findKnowNewsLowerComent(Integer newsComentId, Page page, Integer customerId);
	
	
}
