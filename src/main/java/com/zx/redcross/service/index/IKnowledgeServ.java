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
}
