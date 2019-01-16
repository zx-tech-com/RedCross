package com.zx.redcross.serviceimpl.index;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zx.redcross.dao.index.IKnowledgeMapper;
import com.zx.redcross.entity.Knowledge;
import com.zx.redcross.entity.KnowledgeType;
import com.zx.redcross.entity.Page;
import com.zx.redcross.service.index.IKnowledgeServ;

@Service
public class KnowledgeServImpl implements IKnowledgeServ{

	@Autowired
	private IKnowledgeMapper mapper;
	
	@Override
	public Knowledge getKnowledgeById(Integer id) {
		return mapper.getKnowledgeById(id);
	}

	@Override
	public List<Knowledge> listKnowledgeByType(Integer typeId, Page page) {
		return mapper.listKnowledgeByType(typeId, page);
	}

	@Override
	public boolean saveKnowledge(Knowledge knowledge) {
		return mapper.saveKnowledge(knowledge);
	}

	@Override
	public List<KnowledgeType> listKnowledgeType() {
		return mapper.listKnowledgeType();
	}

	@Override
	public Boolean adminSaveKnowledgeType(KnowledgeType knowledgeType) {
		return mapper.adminSaveKnowledgeType(knowledgeType);
	}

	@Override
	public Boolean adminDeleteKnowledgeType(Integer knowledgeTypeId) {
		return mapper.adminDeleteKnowledgeType(knowledgeTypeId);
	}

	@Override
	public Boolean adminUpdateKnowledgeType(KnowledgeType knowledgeType) {
		return mapper.adminUpdateKnowledgeType(knowledgeType);
	}

	@Override
	public Boolean adminDeleteKnowledge(Integer knowledgeId) {
		return mapper.adminDeleteKnowledge(knowledgeId);
	}

	@Override
	public Boolean adminUpdateKnowledge(Knowledge knowledge) {
		return mapper.adminUpdateKnowledge(knowledge);
	}

}
