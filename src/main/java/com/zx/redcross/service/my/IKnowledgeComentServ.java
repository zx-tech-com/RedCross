package com.zx.redcross.service.my;

import java.util.List;

import com.zx.redcross.entity.KnowledgeComent;
import com.zx.redcross.entity.Page;

public interface IKnowledgeComentServ {
	
	/**
	 * 根据用户id查找急救常识评论
	 * @param customerId 用户id
	 * @param page		分页信息
	 * @return
	 */
	List<KnowledgeComent> listKnowledgeComentByCustomerId(Integer customerId,Page page);
	
}
