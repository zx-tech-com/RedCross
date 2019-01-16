package com.zx.redcross.controller.social;

import java.util.List;
import java.util.Map;


import javax.servlet.http.HttpServletRequest;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.zx.redcross.entity.Concern;
import com.zx.redcross.entity.Page;
import com.zx.redcross.entity.Topic;
import com.zx.redcross.entity.TopicComent;
import com.zx.redcross.entity.TopicType;
import com.zx.redcross.service.social.SocialService;
import com.zx.redcross.tool.Constant;
import com.zx.redcross.tool.FileUtils;
import com.zx.redcross.tool.MapUtils;

/**
 * 社交模块
 * @author 罗勇
 *
 */
@RestController("")
@RequestMapping("/social")
public class SocialController {
	
	@Autowired
	private SocialService socialService;
	 
	/**
	 * 社交主页面
	 */
	@RequestMapping("/findAllTopic")
	public Map<String, Object> listTopic(Page page,Integer customerId,Integer topicTypeId) {
		Map<String,Object> map = MapUtils.getHashMapInstance();	
		/**
		 * 查询所有帖子(分页的处理)
		 * 判断帖子的关注情况
		 */	
		List<Topic> topics=socialService.findAllTopic(page,customerId,topicTypeId);
		map.put(Constant.DATA,topics);
		map.put(Constant.STATUS,Constant.STATUS_SUCCESS);
		return map;
	}
	/**
	 * 关注
	 */
	@RequestMapping("/concerns")
	public	Map<String,Object>	updateConcerns(Concern concert,Integer topicId,Integer customerId){
		    Map<String,Object> map = MapUtils.getHashMapInstance();
			//通过帖子id，查找发帖人id
			Topic topic=socialService.findToicById(topicId,customerId);
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
	/**
	 * 点击帖子,获取发布帖子的详情
	 */
	@RequestMapping("/findTopic")
	public Map<String,Object> findTopic(Page page,Integer topicId,Integer customerId){
		Map<String,Object> map = MapUtils.getHashMapInstance();
		Map<String,Object> submap = MapUtils.getHashMapInstance();
		//根据一条帖子ID查询帖子信息
		Topic topic=socialService.findToicById(topicId,customerId);
		submap.put("topic", topic);
		//通过帖子id获取评论信息（包含分页）(第一层评论)
		List<TopicComent> topicComent= socialService.findTopicComent(topicId,page,customerId);		
		submap.put("topicComent", topicComent);
		map.put(Constant.DATA, submap);
		map.put(Constant.STATUS,Constant.STATUS_SUCCESS);
		return map;
	}
	/**
	 * 查询帖子的评论第二层评论
	 */
	@RequestMapping("/findTopicComent2")
	public Map<String,Object> findTopicComent2(Page page,Integer topicComentId,Integer customerId){
		Map<String,Object> map = MapUtils.getHashMapInstance();
		List<TopicComent> topicComents= socialService.findTopicComent2(topicComentId,page,customerId);
		map.put(Constant.DATA, topicComents);
		map.put(Constant.STATUS,Constant.STATUS_SUCCESS);
		return map;
	}
	/**
	 * 查询帖子的评论第三层评论
	 */
	@RequestMapping("/findTopicComent3")
	public Map<String,Object> findTopicComent3(Page page,Integer topicComentId,Integer customerId){
		Map<String,Object> map = MapUtils.getHashMapInstance();
		List<TopicComent> topicComents= socialService.findTopicComent3(topicComentId,page,customerId);
		map.put(Constant.DATA, topicComents);
		map.put(Constant.STATUS,Constant.STATUS_SUCCESS);
		return map;
	}
	/**
	 * 插入评论
	 */
	@RequestMapping("/saveTopicComent")
	public Map<String,Object> saveTopicComent(TopicComent topicComent){
		Map<String,Object> map = MapUtils.getHashMapInstance();
		socialService.saveTopicComent(topicComent);
		map.put(Constant.STATUS,Constant.STATUS_SUCCESS);
		return map;
	}
	
	
	/**
	 * 评论点赞点击点赞再点击
	 */
	@RequestMapping("/updateComentThumbsup")
	public Map<String,Object> updateComentThumbsup(Integer coustomerId,Integer topicComentId){
		Map<String,Object> map = MapUtils.getHashMapInstance();
		//通过用户id和评论id查询看是否有点赞，有则删除，没有就添加点赞
		Integer count=socialService.findThunsup(coustomerId,topicComentId);
		if(count==0) {
			//说明没有点赞，那么添加点赞
			socialService.saveThunsup(coustomerId,topicComentId);
		}else {
			//删除点赞
			socialService.deleteThunsup(coustomerId,topicComentId);
		}
		map.put(Constant.STATUS,Constant.STATUS_SUCCESS);
		return map;
	}
	/**
	 * 帖子点赞点击点赞再点击
	 */
	@RequestMapping("/updateTopicThumbsup")
	public Map<String,Object> updateTopicThumbsup(Integer coustomerId,Integer topicId){
		Map<String,Object> map = MapUtils.getHashMapInstance();
		//通过用户id和评论id查询看是否有点赞，有则删除，没有就添加点赞
		Integer count=socialService.findTopicThunsup(coustomerId,topicId);
		if(count==0) {
			//说明没有点赞，那么添加点赞
			socialService.saveTopicThunsup(coustomerId,topicId);
		}else {
			//删除点赞
			socialService.deleteTopicThunsup(coustomerId,topicId);
		}
		map.put(Constant.STATUS,Constant.STATUS_SUCCESS);
		return map;
	}
	/**
	 * 获取所有帖子类型
	 */
	@RequestMapping("/findTopicType")
	public Map<String,Object> findTopicType(){
		Map<String,Object> map = MapUtils.getHashMapInstance();
		//查询发布帖子的类别
		List<TopicType> topictypes=socialService.findAllTopicType();
		map.put(Constant.DATA,topictypes);
		map.put(Constant.STATUS, Constant.STATUS_SUCCESS);
		return map;	
	}
	/**
	 * 完成发帖模块
	 */
	@RequestMapping("/pushTopic")
	public Map<String,Object> pushTopic(MultipartFile file,Topic topic,HttpServletRequest req){
		Map<String,Object> map = MapUtils.getHashMapInstance();
		String absoluteBasePath = Constant.IMG_ABSOLUTE_BASE_PATH+"topic/";
		topic.setImagUrl(FileUtils.saveFile(absoluteBasePath, file));
		socialService.saveTopic(topic);
		map.put(Constant.STATUS, Constant.STATUS_SUCCESS);
		return map;	
	}
	//===============================后台管理需要用到的接口===================================
	/**
	 * 后台接口（管理员删除帖子）
	 */
	@RequestMapping("/adminDeleteTopic")
	public Map<String,Object> adminDeleteTopic(Integer topicId){
		Map<String,Object> map = MapUtils.getHashMapInstance();
		socialService.adminDeleteTopic(topicId);
		return map;	
	}
	/**
	 * 后台接口（管理员删除评论）
	 */
	@RequestMapping("/adminDeleteTopicComent")
	public Map<String,Object> adminDeleteTopicComent(Integer topicComentId){
		Map<String,Object> map = MapUtils.getHashMapInstance();
		socialService.adminDeleteTopicComent(topicComentId);
		return map;	
	}
		
}
