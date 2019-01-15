package com.zx.redcross.controller.social;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zx.redcross.entity.Concern;
import com.zx.redcross.entity.Page;
import com.zx.redcross.entity.Topic;
import com.zx.redcross.entity.TopicType;
import com.zx.redcross.service.social.SocialService;
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
		Map<String,Object> subMap = MapUtils.getHashMapInstance();
		subMap.put("topictypes", topictypes);
		subMap.put("topics", topics);
		map.put(Constant.DATA,subMap);
		map.put(Constant.STATUS,Constant.STATUS_SUCCESS);
		return map;
	}
	/**
	 * 关注
	 */
	@RequestMapping("/concerns")
	public	Map<String,Object>	updateConcerns(Concern concert,Integer topicId){
		    Map<String,Object> map = MapUtils.getHashMapInstance();
			//通过帖子id，查找发帖人id
			Topic topic=socialService.findToicById(topicId);
			Integer pCustomerId=topic.getCustomer().getId();
			Integer aCustomerId=concert.getaCustomer().getId();
			//通过用户id和获取的发帖人id查询其关系
			Integer count=socialService.findConcert(aCustomerId,pCustomerId);
			if(count==0){
				//表示是添加关注
				socialService.saveConcert(aCustomerId,pCustomerId);
			}else{
				//表示取消关注
				socialService.deleteConcert(aCustomerId,pCustomerId);
			}
			map.put(Constant.STATUS, Constant.STATUS_SUCCESS);
			
		return map;
	}
	/*
	 * 点击帖子,获取发布帖子的详情
	 */
	@RequestMapping("/findTopic")
	public Map<String,Object> findTopic(){
		Map<String,Object> map = MapUtils.getHashMapInstance();
		
		return null;
		
	}
	
	
			
}
