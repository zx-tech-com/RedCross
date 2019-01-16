package com.zx.redcross.service.my;

import java.util.List;

import com.zx.redcross.entity.Page;
import com.zx.redcross.entity.TopicComent;

public interface ITopicComentServ {
	
	/**
	 * 根据用户id查找社交评论
	 * @param customerId 用户id
	 * @param page		分页信息
	 * @return
	 */
	List<TopicComent> listTopicComentByCustomerId(Integer customerId,Page page);
	
}
