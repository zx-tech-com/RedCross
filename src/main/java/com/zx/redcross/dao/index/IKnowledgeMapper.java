package com.zx.redcross.dao.index;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zx.redcross.entity.Knowledge;
import com.zx.redcross.entity.KnowledgeType;
import com.zx.redcross.entity.Page;

/**
 * 急救知识
 * @author Daryl
 */
public interface IKnowledgeMapper {
	
	Knowledge getKnowledgeById(@Param("id")Integer id);
	
	List<Knowledge> listKnowledgeByType(@Param("typeId") Integer typeId,@Param("page")Page page);
	
	boolean saveKnowledge(@Param("knowledge")Knowledge knowledge);	
	
	List<KnowledgeType> listKnowledgeType();
	//后台管理添加知识类型
	Boolean adminSaveKnowledgeType(KnowledgeType knowledgeType);
	//后台管理删除知识类型
	Boolean adminDeleteKnowledgeType(@Param("knowledgeTypeId")Integer knowledgeTypeId);
	////后台管理修改知识类型
	Boolean adminUpdateKnowledgeType(KnowledgeType knowledgeType);
	//后台删除知识
	Boolean adminDeleteKnowledge(Integer knowledgeId);
	//后台修改知识
	Boolean adminUpdateKnowledge(@Param("knowledge")Knowledge knowledge);
	
}
