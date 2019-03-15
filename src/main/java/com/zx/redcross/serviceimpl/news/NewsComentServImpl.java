package com.zx.redcross.serviceimpl.news;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zx.redcross.dao.news.NewsComentMapper;
import com.zx.redcross.entity.NewsComent;
import com.zx.redcross.entity.Page;
import com.zx.redcross.service.news.NewsComentServ;


@Service
public class NewsComentServImpl implements NewsComentServ{
	@Autowired
	private NewsComentMapper mapper;

	@Override
	public Boolean saveNewsComent(NewsComent newsComent) {
		return mapper.saveNewsComent(newsComent);
	}

	@Override
	public Boolean saveNewsComentThumbsup(Integer newsComentId, Integer customerId) {
		return mapper.saveNewsComentThumbsup(newsComentId,customerId);
	}

	@Override
	public Boolean deleteNewsComentThumbsup(Integer newsComentId, Integer customerId) {
		return  mapper.deleteNewsComentThumbsup(newsComentId,customerId);
	}

	@Override
	public Integer findNewsComentThumbsupCountByKnowledgeIdAndCustomerId(Integer newsComentId, Integer customerId) {
		return  mapper.findNewsComentThumbsupCountByKnowledgeIdAndCustomerId(newsComentId,customerId);
	}

	@Override
	public List<Map<String, Object>> findNewsTopicComent(Integer newsId, Page page, Integer customerId) {
		return mapper.findNewsTopicComent(newsId,page,customerId);
	}

	@Override
	public List<Map<String, Object>> findKnowNewsLowerComent(Integer newsComentId, Page page, Integer customerId) {
		return mapper.findKnowNewsLowerComent(newsComentId,page,customerId);
	}

}
