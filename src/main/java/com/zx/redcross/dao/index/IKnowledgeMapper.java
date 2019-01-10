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
	
}
