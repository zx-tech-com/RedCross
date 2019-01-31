package com.zx.redcross.controller.social;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.zx.redcross.annotation.BackEnd;
import com.zx.redcross.annotation.Open;
import com.zx.redcross.entity.Concern;
import com.zx.redcross.entity.Page;
import com.zx.redcross.entity.Topic;
import com.zx.redcross.entity.TopicComent;
import com.zx.redcross.entity.TopicType;
import com.zx.redcross.service.social.SocialService;
import com.zx.redcross.tool.BusinessExceptionUtils;
import com.zx.redcross.tool.Constant;
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
	@BackEnd
	@RequestMapping("/findAllTopic")
	@Open
	public Map<String, Object> listTopic(Page page,Integer customerId,Integer topicTypeId) {
		Map<String,Object> map = MapUtils.getHashMapInstance();	
		/**
		 * 查询所有帖子(分页的处理)
		 * 判断帖子的关注情况
		 */	
		List<Map<String, Object>> topics=socialService.findAllTopic(page,customerId,topicTypeId);
		//查詢所有帖子的條數
		Integer pageCount=socialService.findAllCountTopic(topicTypeId);
		page.setPageCount(pageCount);
		if(topics!=null) {
			map.put(Constant.DATA,topics);
			map.put(Constant.STATUS,Constant.STATUS_SUCCESS);
		}else {
			map.put(Constant.STATUS, Constant.STATUS_FAILURE);
		}
		map.put(Constant.TOTAL_COUNT, page.getTotalPage());
		map.put(Constant.PAGE_SIZE, page.getPageSize());
		return map;
	}
	
	/**
	 * @deprecated 关注功能已取消
	 * @param concert
	 * @param topicId
	 * @param customerId
	 * @return
	 */
	@RequestMapping("/concerns")
	public	Map<String,Object>	updateConcerns(Concern concert,Integer topicId,Integer customerId){
		    Map<String,Object> map = MapUtils.getHashMapInstance();
			//通过帖子id，查找发帖人id
			Map<String, Object> topic=socialService.findToicById(topicId,customerId);
			Integer pCustomerId=((Topic) topic).getCustomer().getId();
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
	@Open
	public Map<String,Object> findTopic(Integer topicId,Integer customerId){
		Map<String,Object> map = MapUtils.getHashMapInstance();
		//根据一条帖子ID查询帖子信息
		Map<String,Object> topic= socialService.findToicById(topicId,customerId);
		if(topic!=null) {
			map.put(Constant.DATA, topic);
			map.put(Constant.STATUS, Constant.STATUS_SUCCESS);
		}else {
			map.put(Constant.STATUS, Constant.STATUS_FAILURE);
		}
		return map;
	}
	/**
	 * 获取帖子第一层评论信息
	 * @param page（用于分页）
	 * @param topicId（帖子ID）
	 * @param customerId（登录用户信息，判断是点赞）
	 * @return
	 */
	@BackEnd
	@RequestMapping("/findOnceTopicComent")
	@Open
	public Map<String,Object> findOnceTopic(Page page,Integer topicId,Integer customerId){
		Map<String,Object> map = MapUtils.getHashMapInstance();
		//通过帖子id获取评论信息（包含分页）(第一层评论)
		List<Map<String, Object>> topicComents= socialService.findTopicComent(topicId,page,customerId);		
		Integer pageCount=socialService.findOnceTopicComentCount(topicId);
		page.setPageCount(pageCount);
		if(topicComents!=null) {
			map.put(Constant.DATA, topicComents);
			map.put(Constant.STATUS,Constant.STATUS_SUCCESS);
		}else {
			map.put(Constant.STATUS, Constant.STATUS_FAILURE);
		}
		map.put(Constant.TOTAL_COUNT, page.getTotalPage());
		map.put(Constant.PAGE_SIZE, page.getPageSize());
		return map;
	}

	/**
	 * @param page
	 * @param topicComentId
	 * @param customerId
	 * @return
	 */
	@BackEnd
	@RequestMapping("/findLowerComent")
	@Open
	public Map<String,Object> findLowerComent(Page page,Integer topicComentId
			,@RequestParam(required=false) Integer customerId){
		Map<String,Object> map = MapUtils.getHashMapInstance();
		map.put(Constant.STATUS,Constant.STATUS_FAILURE);
		List<Map<String,Object>> comentList = socialService.findLowerComent(topicComentId, page, customerId);
		Integer pageCount=socialService.findLowerComentCount(topicComentId);
		page.setPageCount(pageCount);
		if(comentList != null){
			map.put(Constant.STATUS,Constant.STATUS_SUCCESS);
			map.put(Constant.DATA, comentList);
		}else {
			map.put(Constant.STATUS, Constant.STATUS_FAILURE);
		}
		map.put(Constant.TOTAL_COUNT, page.getTotalPage());
		map.put(Constant.PAGE_SIZE, page.getPageSize());
		return map;
	}
	
	/**
	 * 分享帖子
	 * @param topicId
	 * @return
	 */
	@RequestMapping("/share")
	@Open
	public Map<String,Object> topicShare(@RequestParam(required = true) Integer topicId){
		Map<String,Object> map = MapUtils.getHashMapInstance();
		if(topicId == null) {
			BusinessExceptionUtils.throwNewBusinessException("id为必须字段");
		}
		map.put(Constant.STATUS,socialService.updateTopicSetShareAdd1(topicId)?
				Constant.STATUS_SUCCESS:Constant.STATUS_FAILURE);
		return map;
	}
	
	/**
	 * 插入评论
	 */
	@RequestMapping("/saveTopicComent")
	public Map<String,Object> saveTopicComent(TopicComent topicComent){
		Map<String,Object> map = MapUtils.getHashMapInstance();
		Boolean flag=socialService.saveTopicComent(topicComent);
		map.put(Constant.STATUS,flag?
				Constant.STATUS_SUCCESS:Constant.STATUS_FAILURE);
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
	@BackEnd
	@RequestMapping("/findTopicType")
	@Open
	public Map<String,Object> findTopicType(){
		Map<String,Object> map = MapUtils.getHashMapInstance();
		//查询发布帖子的类别
		List<TopicType> topictypes=socialService.findAllTopicType();
		if(topictypes!=null) {
			map.put(Constant.DATA,topictypes);
			map.put(Constant.STATUS, Constant.STATUS_SUCCESS);
		}else {
			map.put(Constant.STATUS, Constant.STATUS_FAILURE);
		}
		
		return map;	
	}
	/**
	 * 完成发帖模块
	 */
	@RequestMapping("/pushTopic")
	public Map<String,Object> pushTopic(MultipartFile[] images,MultipartFile video,
			Topic topic,@RequestParam(required=true) Integer topicTypeId){
		TopicType type = new TopicType();
		type.setId(topicTypeId);
		topic.setTopicType(type);		
		Map<String,Object> map = MapUtils.getHashMapInstance();
		map.put(Constant.STATUS, socialService.saveTopic(images,video,topic)?Constant.STATUS_SUCCESS:Constant.STATUS_FAILURE);
		return map;	
	}
	//===============================后台管理需要用到的接口===================================
	/**
	 * 后台接口（管理员删除帖子）
	 */
	@RequestMapping("/adminDeleteTopic")
	public Map<String,Object> adminDeleteTopic(Integer topicId){
		Map<String,Object> map = MapUtils.getHashMapInstance();
		Boolean flag=socialService.adminDeleteTopic(topicId);
		map.put(Constant.STATUS, flag?Constant.STATUS_SUCCESS:Constant.STATUS_FAILURE);
		return map;
	}
	/**
	 * 后台接口（管理员删除评论）
	 */
	@RequestMapping("/adminDeleteTopicComent")
	public Map<String,Object> adminDeleteTopicComent(Integer topicComentId){
		Map<String,Object> map = MapUtils.getHashMapInstance();
		Boolean flag=socialService.adminDeleteTopicComent(topicComentId);
		map.put(Constant.STATUS, flag?Constant.STATUS_SUCCESS:Constant.STATUS_FAILURE);
		return map;	
	}
	/**
	 * 添加发帖类型
	 * @return
	 */
	@BackEnd
	@RequestMapping("/adminAddTopicType")
	@Open
	public Map<String,Object> adminAddTopicType(TopicType topicType,
					@Param(value = "imgUrl")MultipartFile imgUrl) {		
		Map<String,Object> map = MapUtils.getHashMapInstance();
		Boolean flag = socialService.addTopicType(topicType,imgUrl);
		map.put(Constant.STATUS,flag ? Constant.STATUS_SUCCESS : Constant.STATUS_FAILURE);
		return map; 
	}
	/**
	 * 删除发帖类型
	 * @return
	 */
	@BackEnd
	@RequestMapping("/adminDeleteTopicType")
	@Open
	public Map<String,Object> adminDeleteTopicType(Integer topicTypeId) {
		Map<String,Object> map = MapUtils.getHashMapInstance();
		Boolean flag = socialService.adminDeleteTopicType(topicTypeId);
		map.put(Constant.STATUS,flag ? Constant.STATUS_SUCCESS : Constant.STATUS_FAILURE);
		return map; 
	}
	
	/**
	 * 修改发帖类型
	 * @return
	 */
	@BackEnd
	@RequestMapping("/adminUpdateTopicType")
	@Open
	public Map<String,Object> adminUpdateTopicType(TopicType topicType,
			@Param(value = "imgUrl")MultipartFile imgUrl) {
		Map<String,Object> map = MapUtils.getHashMapInstance();
		Boolean flag=socialService.adminUpdateTopicType(topicType,imgUrl);
		map.put(Constant.STATUS,flag ? Constant.STATUS_SUCCESS : Constant.STATUS_FAILURE);
		return map; 
	}
		
}
