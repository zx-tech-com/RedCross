package com.zx.redcross.controller.index;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zx.redcross.entity.Knowledge;
import com.zx.redcross.entity.KnowledgeType;
import com.zx.redcross.entity.Page;
import com.zx.redcross.service.index.IKnowledgeServ;
import com.zx.redcross.tool.Constant;
import com.zx.redcross.tool.MapUtils;

//@RestController
@RequestMapping("/knowledge")
public class KnowledgeCtrl {
	
	
	@Autowired
	private IKnowledgeServ knowledgeServImpl;
	
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

	@RequestMapping("/listKnowledge")
	public Map<String,Object> listKnowledgeByType(@RequestParam(required=true) Integer typeId,
			@RequestParam(required=true)Page page) {
		
		Map<String,Object> map = MapUtils.getHashMapInstance();
		List<Knowledge> knowledgeList = knowledgeServImpl.listKnowledgeByType(typeId, page);
		map.put(Constant.STATUS,Constant.STATUS_FAILURE);
		if(null != knowledgeList) {
			map.put(Constant.STATUS,Constant.STATUS_SUCCESS);
			map.put(Constant.DATA, knowledgeList);
		}
		return map;
		
	}
	
	@RequestMapping("/saveKnowledge")
	public Map<String,Object> saveKnowledge(@RequestParam(required=true) Knowledge knowledge) {
		Map<String,Object> map = MapUtils.getHashMapInstance();
		Boolean flag = knowledgeServImpl.saveKnowledge(knowledge);
		map.put(Constant.STATUS,flag ? Constant.STATUS_SUCCESS : Constant.STATUS_FAILURE);
		return map;
	}
	
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
	
}
