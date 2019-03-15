package com.zx.redcross.serviceimpl.news;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zx.redcross.dao.news.NewsThumbsupMapper;
import com.zx.redcross.service.news.NewsThumbsupServ;
@Service
public class NewsThumbsupServImpl implements NewsThumbsupServ{
	@Autowired NewsThumbsupMapper mapper;

	@Override
	public Boolean saveNewsThumbsup(Integer newsId, Integer customerId) {
		return mapper.saveNewsThumbsup(newsId,customerId);
	}

	@Override
	public Boolean deleteNewsThumbsup(Integer newsId, Integer customerId) {
		return mapper.deleteNewsThumbsup(newsId,customerId);
	}

	@Override
	public Integer findCountThumbsupByNewsIdAndCustomerId(Integer newsId, Integer customerId) {
		return mapper.findCountThumbsupByNewsIdAndCustomerId(newsId,customerId);
	}
	
	
	

}
