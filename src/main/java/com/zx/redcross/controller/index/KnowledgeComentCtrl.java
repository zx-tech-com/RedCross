package com.zx.redcross.controller.index;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zx.redcross.annotation.BackEnd;
import com.zx.redcross.annotation.FrontEnd;
import com.zx.redcross.annotation.Open;
import com.zx.redcross.entity.KnowledgeComent;
import com.zx.redcross.entity.Page;
import com.zx.redcross.service.index.KnowledgeComentServ;
import com.zx.redcross.tool.Constant;
import com.zx.redcross.tool.MapUtils;

@RestController
@RequestMapping("/knowledgeComent")
public class KnowledgeComentCtrl {
	@Autowired
	private KnowledgeComentServ knowledgeComentServImpl;
	
	
	/**
	 * 插入评论
	 */
	@FrontEnd
	@RequestMapping("/saveKnowledgeComent")
	public Map<String,Object> saveTopicComent(KnowledgeComent knowledgeComent){
		Map<String,Object> map = MapUtils.getHashMapInstance();
		Boolean flag=knowledgeComentServImpl.saveKnowledgeComent(knowledgeComent);
		map.put(Constant.STATUS,flag?
				Constant.STATUS_SUCCESS:Constant.STATUS_FAILURE);
		return map;
	}
	
	/**
	 * 评论点赞取消点赞
	 */
	@FrontEnd
	@RequestMapping("/saveComentThumbsup")
	public Map<String,Object> saveComentThumbsup(Integer customerId,Integer knowledgeComentId){
		Map<String,Object> map = MapUtils.getHashMapInstance();
		//通过用户id和评论id查询看是否有点赞，有则删除，没有就添加点赞
		Integer count=knowledgeComentServImpl.findKnowledgeComentThumbsupCountByKnowledgeIdAndCustomerId(knowledgeComentId, customerId);
		if(count==0) {
			//说明没有点赞，那么添加点赞
			knowledgeComentServImpl.saveKnowledgeComentThumbsup(knowledgeComentId, customerId);
			map.put(Constant.DATA,Constant.THUMB_SUCCESS);
		}else {
			//删除点赞
			knowledgeComentServImpl.deleteKnowledgeComentThumbsup(knowledgeComentId, customerId);
			map.put(Constant.DATA,Constant.THUMB_FAILURE);
		}
		map.put(Constant.STATUS,Constant.STATUS_SUCCESS);
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
	@RequestMapping("/findOnceKnowledgeComent")
	@Open
	public Map<String,Object> findOnceTopic(Page page,Integer knowledgeId,Integer customerId){
		Map<String,Object> map = MapUtils.getHashMapInstance();
		//通过帖子id获取评论信息（包含分页）(第一层评论)
		List<Map<String, Object>> KnowledgeComents= knowledgeComentServImpl.findKnowledgeTopicComent(knowledgeId,page,customerId);		
		Integer pageCount=knowledgeComentServImpl.findknowledgeOnceComentCount(knowledgeId);
		page.setPageCount(pageCount);
		if(KnowledgeComents!=null) {
			map.put(Constant.DATA, KnowledgeComents);
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
	@RequestMapping("/findLowerKnowledgeComent")
	@Open
	public Map<String,Object> findLowerComent(Page page,Integer knowledgeComentId
			,@RequestParam(required=false) Integer customerId){
		Map<String,Object> map = MapUtils.getHashMapInstance();
		map.put(Constant.STATUS,Constant.STATUS_FAILURE);
		List<Map<String,Object>> comentList = knowledgeComentServImpl.findKnowledgeLowerComent(knowledgeComentId, page, customerId);
		Integer pageCount=knowledgeComentServImpl.findLowerComentCount(knowledgeComentId);
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
	 * 后台接口（管理员删除评论）
	 */
	@BackEnd
	@RequestMapping("/adminDeleteKnowledgeComent")
	public Map<String,Object> adminDeleteTopicComent(Integer knowledgeComentId){
		Map<String,Object> map = MapUtils.getHashMapInstance();
		Boolean flag=knowledgeComentServImpl.adminDeleteKnowledgeComent(knowledgeComentId);
		map.put(Constant.STATUS, flag?Constant.STATUS_SUCCESS:Constant.STATUS_FAILURE);
		return map;	
	}
	
	
	/**
	 * 删除自己评论
	 */
	@FrontEnd
	@RequestMapping("/deleteKnowledgeComent")
	public Map<String,Object> deleteTopicComent(Integer knowledgeComentId){
		Map<String,Object> map = MapUtils.getHashMapInstance();
		Boolean flag=knowledgeComentServImpl.adminDeleteKnowledgeComent(knowledgeComentId);
		map.put(Constant.STATUS, flag?Constant.STATUS_SUCCESS:Constant.STATUS_FAILURE);
		return map;	
	}
	/**
	 * 判断是否是自己的回复
	 * @param knowledgeComentId
	 * @param customerId
	 * @return
	 */
	@FrontEnd
	@RequestMapping("/getKnowledgeComent")
	public Map<String,Object> getKnowledgeComent(@RequestParam(required=true)Integer knowledgeComentId,@RequestParam(required=true)Integer customerId){
		Map<String,Object> map = MapUtils.getHashMapInstance();
		Integer count=knowledgeComentServImpl.getKnowledgeComentCount(knowledgeComentId,customerId);
		if(count==0) {
			map.put(Constant.DATA, Constant.THUMB_FAILURE);
		}else {
			map.put(Constant.DATA,Constant.THUMB_SUCCESS);
		}
		map.put(Constant.STATUS,Constant.STATUS_SUCCESS);
		return map;	
	}
	
	
	
}
