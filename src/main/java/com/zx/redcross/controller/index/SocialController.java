package com.zx.redcross.controller.index;

import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zx.redcross.entity.Customer;
import com.zx.redcross.entity.Page;
import com.zx.redcross.entity.Topic;
import com.zx.redcross.entity.TopicType;
import com.zx.redcross.service.index.SocialService;
import com.zx.redcross.tool.Constant;
import com.zx.redcross.tool.MapUtils;

@RestController("")
@RequestMapping("/social")
public class SocialController {
	
	@Autowired(required=true)
	private SocialService socialService;
	 
	/**
	 * 社交主页面
	 */
	@RequestMapping("/findAllTopic")
	public Map<String, Object> listTopic(Page page,Integer coustemerId,Integer topicTypeId) {
		Map<String,Object> map = MapUtils.getHashMapInstance();
		//1.查询发布帖子的类别
		List<TopicType> topictypes=socialService.findAllTopicType();	
		/**
		 * 2.查询所有帖子(分页的处理)
		 * 3.判断帖子的关注情况
		 */	
		List<Topic> topics=socialService.findAllTopic(page,coustemerId,topicTypeId);
		map.put(Constant.DATA, topictypes);
		map.put(Constant.DATA, topics);
		map.put(Constant.STATUS,Constant.STATUS_SUCCESS);
		return map;
	}
	
			
}
