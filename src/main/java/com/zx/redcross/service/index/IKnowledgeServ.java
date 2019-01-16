package com.zx.redcross.service.index;

import java.util.List;

import com.zx.redcross.entity.Knowledge;
import com.zx.redcross.entity.KnowledgeType;
import com.zx.redcross.entity.Page;

public interface IKnowledgeServ {
	
	Knowledge getKnowledgeById(Integer id);
	
	List<Knowledge> listKnowledgeByType(Integer typeId,Page page);
	
	boolean saveKnowledge(Knowledge knowledge);	
	
	List<KnowledgeType> listKnowledgeType();
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
