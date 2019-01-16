package com.zx.redcross.serviceimpl.index;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zx.redcross.dao.index.IKnowledgeMapper;
import com.zx.redcross.entity.Knowledge;
import com.zx.redcross.entity.KnowledgeType;
import com.zx.redcross.entity.Page;
import com.zx.redcross.entity.Video;
import com.zx.redcross.entity.VideoBuyRecord;
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

	@Override
	public List<Video> listVideo(Integer customerId) {
		return mapper.listVideo(customerId);
	}

	@Override
	public Video getVideo(Integer customerId, Integer videoId) {
		return mapper.getVideo(customerId,videoId);
	}

	@Override
	public Boolean saveVideoBuyRecord(VideoBuyRecord videoBuyRecord) {
		videoBuyRecord.setStatus((byte) 1);
		return mapper.saveVideoBuyRecord(videoBuyRecord);
	}

	@Override
	public Boolean updateVideoBuyRecord(Integer videoBuyRecordId) {
		return mapper.uptateVideoBuyRecord(videoBuyRecordId);
	}

}
