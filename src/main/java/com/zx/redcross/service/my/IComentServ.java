package com.zx.redcross.service.my;

import java.util.List;
import java.util.Map;

import com.zx.redcross.entity.Page;

public interface IComentServ {
	
	/**
	 * 根据用户id查找社交评论
	 * @param customerId 用户id
	 * @param page		分页信息
	 * @return
	 */
	List<Map<String, Object>> listTopicComentByCustomerId(Integer customerId,Page page);
	
	
	//根据用户id查找知识评论
	List<Map<String, Object>> listKnowledgeComentByCustomerId(Integer customerId, Page page);
	
}
