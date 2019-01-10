package com.zx.redcross.controller.index;

import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zx.redcross.entity.Page;
import com.zx.redcross.entity.Topic;
import com.zx.redcross.entity.TopicType;
import com.zx.redcross.service.SocialService;
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
	public Map<String, Object> listTopic(Page page) {
		Map<String,Object> map = MapUtils.getHashMapInstance();
		//1.查询发布帖子的类别
		List<TopicType> topictypes=socialService.findAllTopicType();
		map.put("topictypes", topictypes);	
		/**
		 * 2.查询所有帖子(分页的处理)
		 */	
		List<Topic> topics=socialService.findAllTopic(page);
		/**
		 * 3.对关注和被关注的操作
		 *a.遍历集合的中的帖子
		 */
		
		
		
		map.put("topics", topics);
		map.put("status","success");
		return map;
	}
	
			
}
