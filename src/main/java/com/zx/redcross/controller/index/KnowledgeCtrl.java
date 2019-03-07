package com.zx.redcross.controller.index;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zx.redcross.annotation.BackEnd;
import com.zx.redcross.annotation.FrontEnd;
import com.zx.redcross.annotation.Open;
import com.zx.redcross.entity.Knowledge;
import com.zx.redcross.entity.KnowledgeType;
import com.zx.redcross.entity.Page;
import com.zx.redcross.service.index.IKnowledgeServ;
import com.zx.redcross.tool.Constant;
import com.zx.redcross.tool.MapUtils;
import com.zx.redcross.tool.Utils;

@RestController
@RequestMapping("/knowledge")
public class KnowledgeCtrl {
	
	
	@Autowired
	private IKnowledgeServ knowledgeServImpl;
	

	@BackEnd
	@RequestMapping("/getKnowledge")
	@Open
	public Map<String,Object> getKnowledgeById(@RequestParam(required=true)Integer id) {
		Map<String,Object> map = MapUtils.getHashMapInstance();
		Map<String, Object> knowledge = knowledgeServImpl.getKnowledgeById(id);
		map.put(Constant.STATUS,Constant.STATUS_FAILURE);
		if(null != knowledge) {
			map.put(Constant.STATUS,Constant.STATUS_SUCCESS);
			map.put(Constant.DATA, knowledge);
		}
		return map;
	}

	@FrontEnd
	@RequestMapping("/listKnowledge")
	@Open
	public Map<String,Object> listKnowledgeByType(@RequestParam(required=false) Integer typeId,Page page) {
		
		Map<String,Object> map = MapUtils.getHashMapInstance();
		List<Map<String, Object>> knowledgeList = knowledgeServImpl.listKnowledgeByType(typeId, page);
		map.put(Constant.STATUS,Constant.STATUS_FAILURE);
		if(null != knowledgeList) {
			matchImgFromH5(knowledgeList);
			map.put(Constant.STATUS,Constant.STATUS_SUCCESS);
			map.put(Constant.PAGE_SIZE,page.getPageSize());
			map.put(Constant.DATA, knowledgeList);
		}
		return map;
	}
	
	/**
	 * 把img从H5中抓取出来
	 * @param knowledgeList
	 */
	private void matchImgFromH5(List<Map<String, Object>> knowledgeList) {
		if(knowledgeList == null) return;
		for(Map<String, Object> map : knowledgeList) {
			String content = (String) map.get("content");
			map.put("imgUrl", Utils.matchImgFromH5(content));
		}
	}

	@FrontEnd
	@RequestMapping("/listKnowledgeType")
	@Open
	public Map<String,Object> listKnowledgeType() {
		Map<String,Object> map = MapUtils.getHashMapInstance();
		List<Map<String, Object>> knowledgeTypeList = knowledgeServImpl.listKnowledgeType();
		map.put(Constant.STATUS,Constant.STATUS_FAILURE);
		if(null != knowledgeTypeList) {
			map.put(Constant.STATUS,Constant.STATUS_SUCCESS);
			map.put(Constant.DATA, knowledgeTypeList);
		}
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
		List<Map<String, Object>> knowledgeTypeList = knowledgeServImpl.listKnowledgeType();
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
		List<Map<String, Object>> knowledgeList = knowledgeServImpl.listKnowledgeByType(typeId, page);
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
	@BackEnd
	@RequestMapping("/saveKnowledge")
	public Map<String,Object> saveKnowledge(@RequestBody Knowledge knowledge) {
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
	 * 修改知识
	 */
	@RequestMapping("/adminUpdateKnowledge")
	public Map<String,Object> adminUpdateKnowledge(@RequestBody Knowledge knowledge) {
		Map<String,Object> map = MapUtils.getHashMapInstance();
		Boolean flag=knowledgeServImpl.adminUpdateKnowledge(knowledge);
		map.put(Constant.STATUS,flag ? Constant.STATUS_SUCCESS : Constant.STATUS_FAILURE);
		return map;
	}
}

