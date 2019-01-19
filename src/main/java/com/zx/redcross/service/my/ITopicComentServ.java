package com.zx.redcross.service.my;

import java.util.List;
import java.util.Map;

import com.zx.redcross.entity.Page;

public interface ITopicComentServ {
	
	/**
	 * 根据用户id查找社交评论
	 * @param customerId 用户id
	 * @param page		分页信息
	 * @return
	 */
	List<Map<String, Object>> listTopicComentByCustomerId(Integer customerId,Page page);
	
}
