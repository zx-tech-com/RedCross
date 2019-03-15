package com.zx.redcross.serviceimpl.news;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zx.redcross.dao.news.NewsFavoriteMapper;
import com.zx.redcross.service.news.NewsFavoriteServ;

@Service
public class NewsFavoriteServImpl implements NewsFavoriteServ{
	@Autowired
	private NewsFavoriteMapper mapper;
	@Override
	public Boolean saveNewsFavorite(Integer newsId, Integer customerId) {
		return mapper.saveNewsFavorite(newsId,customerId);
	}

	@Override
	public Boolean deleteNewsFavorite(Integer newsId, Integer customerId) {
		return  mapper.deleteNewsFavorite(newsId,customerId);
	}

	@Override
	public Integer findFavoriteCountByNewsIdAndCustomerId(Integer newsId, Integer customerId) {
		return mapper.findFavoriteCountByNewsIdAndCustomerId(newsId,customerId);
	}
	

}
