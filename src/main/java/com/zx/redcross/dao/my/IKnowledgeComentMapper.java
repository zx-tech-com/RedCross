package com.zx.redcross.dao.my;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zx.redcross.entity.KnowledgeComent;
import com.zx.redcross.entity.Page;

public interface IKnowledgeComentMapper {
	
	List<KnowledgeComent> listKnowledgeComentByCustomerId(
			@Param("customerId")Integer customerId,@Param("page") Page page);
	
}
