package com.zx.redcross.controller.index;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zx.redcross.annotation.FrontEnd;
import com.zx.redcross.entity.Knowledge;
import com.zx.redcross.entity.KnowledgeType;
import com.zx.redcross.entity.Page;
import com.zx.redcross.entity.Video;
import com.zx.redcross.entity.VideoBuyRecord;
import com.zx.redcross.service.index.IKnowledgeServ;
import com.zx.redcross.tool.Constant;
import com.zx.redcross.tool.MapUtils;

@RestController
@RequestMapping("/knowledge")
public class KnowledgeCtrl {
	
	
	@Autowired
	private IKnowledgeServ knowledgeServImpl;
	
	
	@FrontEnd
	@RequestMapping("/getKnowledge")
	public Map<String,Object> getKnowledgeById(@RequestParam(required=true)Integer id) {
		Map<String,Object> map = MapUtils.getHashMapInstance();
		Knowledge knowledge = knowledgeServImpl.getKnowledgeById(id);
		map.put(Constant.STATUS,Constant.STATUS_FAILURE);
		if(null != knowledge) {
			map.put(Constant.STATUS,Constant.STATUS_SUCCESS);
			map.put(Constant.DATA, knowledge);
		}
		return map;
	}

	@FrontEnd
	@RequestMapping("/listKnowledge")
	public Map<String,Object> listKnowledgeByType(@RequestParam(required=false) Integer typeId,Page page) {
		
		Map<String,Object> map = MapUtils.getHashMapInstance();
		List<Knowledge> knowledgeList = knowledgeServImpl.listKnowledgeByType(typeId, page);
		map.put(Constant.STATUS,Constant.STATUS_FAILURE);
		if(null != knowledgeList) {
			map.put(Constant.STATUS,Constant.STATUS_SUCCESS);
			map.put(Constant.DATA, knowledgeList);
		}
		return map;
	}
	
	
	@FrontEnd
	@RequestMapping("/listKnowledgeType")
	public Map<String,Object> listKnowledgeType() {
		Map<String,Object> map = MapUtils.getHashMapInstance();
		List<KnowledgeType> knowledgeTypeList = knowledgeServImpl.listKnowledgeType();
		map.put(Constant.STATUS,Constant.STATUS_FAILURE);
		if(null != knowledgeTypeList) {
			map.put(Constant.STATUS,Constant.STATUS_SUCCESS);
			map.put(Constant.DATA, knowledgeTypeList);
		}
		return map;
		
	}
	/**
	 * 视频列表，查询所有视频，并判断用户是否购买了
	 * @param customerId
	 * @return
	 */
	@FrontEnd
	@RequestMapping("/listVideo")
	public Map<String,Object> listVideo(Integer customerId) {
		Map<String,Object> map = MapUtils.getHashMapInstance();
		List<Video> videos = knowledgeServImpl.listVideo(customerId);
		map.put(Constant.STATUS,Constant.STATUS_FAILURE);
		if(null != videos) {
			map.put(Constant.STATUS,Constant.STATUS_SUCCESS);
			map.put(Constant.DATA, videos);
		}
		return map;
	}
	
	@FrontEnd
	@RequestMapping("/getVideo")
	public Map<String,Object> getVideo(Integer customerId,Integer videoId) {
		Map<String,Object> map = MapUtils.getHashMapInstance();
		Video video = knowledgeServImpl.getVideo(customerId,videoId);
		if(null != video) {
			map.put(Constant.STATUS,Constant.STATUS_SUCCESS);
			map.put(Constant.DATA, video);
		}else {
			map.put(Constant.STATUS,Constant.STATUS_FAILURE);
		}
		return map;
	}
	
	@FrontEnd
	@RequestMapping("/saveVideoBuyRecord")
	public Map<String,Object> saveVideoBuyRecord(@RequestBody VideoBuyRecord videoBuyRecord) {
		//保存购买记录（实际保存的是正在购买状态为1）
		Map<String,Object> map = MapUtils.getHashMapInstance();
		Boolean flag = knowledgeServImpl.saveVideoBuyRecord(videoBuyRecord);
		map.put(Constant.STATUS,flag ? Constant.STATUS_SUCCESS : Constant.STATUS_FAILURE);
		return map;
	}
	
	@FrontEnd
	@RequestMapping("/updateVideoBuyRecord")
	public Map<String,Object> updateVideoBuyRecord(@RequestBody Integer videoBuyRecordId) {
		//完成支付（实际修改购买状态为2）
		Map<String,Object> map = MapUtils.getHashMapInstance();
		Boolean flag = knowledgeServImpl.updateVideoBuyRecord(videoBuyRecordId);
		map.put(Constant.STATUS,flag ? Constant.STATUS_SUCCESS : Constant.STATUS_FAILURE);
		return map;
	}
	
	
	//===============================后台管理需要用到的接口===================================
	/**
	 * 后台管理（管理知识类型）
	 * @param knowledgeType
	 * @return
	 */
	/**
	 * 查询所有知识的类型
	 * @return
	 */
	@RequestMapping("/adminListKnowledgeType")
	public Map<String,Object> adminListKnowledgeType() {
		Map<String,Object> map = MapUtils.getHashMapInstance();
		List<KnowledgeType> knowledgeTypeList = knowledgeServImpl.listKnowledgeType();
		if(null != knowledgeTypeList) {
			map.put(Constant.STATUS,Constant.STATUS_SUCCESS);
			map.put(Constant.DATA, knowledgeTypeList);
		}else {
			map.put(Constant.STATUS,Constant.STATUS_FAILURE);
		}
		return map;
	}
	/**
	 * 添加知识的类型
	 */
	@RequestMapping("/adminSaveKnowledgeType")
	public Map<String,Object> adminSaveKnowledgeType(KnowledgeType knowledgeType) {
		Map<String,Object> map = MapUtils.getHashMapInstance();
		Boolean flag=knowledgeServImpl.adminSaveKnowledgeType(knowledgeType);
		map.put(Constant.STATUS,flag ? Constant.STATUS_SUCCESS : Constant.STATUS_FAILURE);
		return map;
	}
	/**
	 * 删除知识类型
	 */
	@RequestMapping("/adminDeleteKnowledgeType")
	public Map<String,Object> adminDeleteKnowledgeType(Integer knowledgeTypeId) {
		Map<String,Object> map = MapUtils.getHashMapInstance();
		Boolean flag=knowledgeServImpl.adminDeleteKnowledgeType(knowledgeTypeId);
		map.put(Constant.STATUS,flag ? Constant.STATUS_SUCCESS : Constant.STATUS_FAILURE);
		return map;
	}
	
	/**
	 * 修改知识类型
	 */
	@RequestMapping("/adminUpdateKnowledgeType")
	public Map<String,Object> adminUpdateKnowledgeType(KnowledgeType knowledgeType) {
		Map<String,Object> map = MapUtils.getHashMapInstance();
		Boolean flag=knowledgeServImpl.adminUpdateKnowledgeType(knowledgeType);
		map.put(Constant.STATUS,flag ? Constant.STATUS_SUCCESS : Constant.STATUS_FAILURE);
		return map;
	}
	
	/**
	 * 后台管理（管理知识）
	 * @param knowledgeType
	 * @return
	 */
	/**
	 * 查询所有知识
	 * @return
	 */
	@RequestMapping("/adminListKnowledge")
	public Map<String,Object> adminListKnowledge(Integer typeId,Page page) {
		Map<String,Object> map = MapUtils.getHashMapInstance();
		List<Knowledge> knowledgeList = knowledgeServImpl.listKnowledgeByType(typeId, page);
		if(null != knowledgeList) {
			map.put(Constant.STATUS,Constant.STATUS_SUCCESS);
			map.put(Constant.DATA, knowledgeList);
		}else {
			map.put(Constant.STATUS,Constant.STATUS_FAILURE);
		}
		return map;
	}
	/**
	 * 添加知识
	 */
	@RequestMapping("/saveKnowledge")
	public Map<String,Object> saveKnowledge(@RequestParam(required=true) Knowledge knowledge) {
		Map<String,Object> map = MapUtils.getHashMapInstance();
		Boolean flag = knowledgeServImpl.saveKnowledge(knowledge);
		map.put(Constant.STATUS,flag ? Constant.STATUS_SUCCESS : Constant.STATUS_FAILURE);
		return map;
	}
	/**
	 * 删除知识
	 */
	@RequestMapping("/adminDeleteKnowledge")
	public Map<String,Object> adminDeleteKnowledge(Integer knowledgeId) {
		Map<String,Object> map = MapUtils.getHashMapInstance();
		Boolean flag=knowledgeServImpl.adminDeleteKnowledge(knowledgeId);
		map.put(Constant.STATUS,flag ? Constant.STATUS_SUCCESS : Constant.STATUS_FAILURE);
		return map;
	}
	
	/**
	 * 修改知识类型
	 */
	@RequestMapping("/adminUpdateKnowledge")
	public Map<String,Object> adminUpdateKnowledge(Knowledge knowledge) {
		Map<String,Object> map = MapUtils.getHashMapInstance();
		Boolean flag=knowledgeServImpl.adminUpdateKnowledge(knowledge);
		map.put(Constant.STATUS,flag ? Constant.STATUS_SUCCESS : Constant.STATUS_FAILURE);
		return map;
	}
}

