package com.zx.redcross.service.index;

import java.util.List;
import java.util.Map;

import com.zx.redcross.entity.Knowledge;
import com.zx.redcross.entity.KnowledgeType;
import com.zx.redcross.entity.Page;

public interface IKnowledgeServ {
	
	Map<String, Object> getKnowledgeById(Integer id);
	
	List<Map<String, Object>> listKnowledgeByType(Integer typeId,Page page);
	
	boolean saveKnowledge(Knowledge knowledge);	
	
	List<Map<String, Object>> listKnowledgeType();
	
	//========================后台管理=====================
	//后台管理保存知识类型
	Boolean adminSaveKnowledgeType(KnowledgeType knowledgeType);
	//后台管理删除知识类型
	Boolean adminDeleteKnowledgeType(Integer knowledgeTypeId);
	//后台管理修改知识类型
	Boolean adminUpdateKnowledgeType(KnowledgeType knowledgeType);
	//后台删除知识
	Boolean adminDeleteKnowledge(Integer knowledgeId);
	//后台修改知识
	Boolean adminUpdateKnowledge(Knowledge knowledge);
	
}
