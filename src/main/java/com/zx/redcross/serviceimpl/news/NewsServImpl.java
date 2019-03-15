package com.zx.redcross.serviceimpl.news;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zx.redcross.dao.news.NewsMapper;
import com.zx.redcross.entity.News;
import com.zx.redcross.entity.Page;
import com.zx.redcross.service.news.NewsServ;
@Service
public class NewsServImpl implements NewsServ{
	@Autowired
	private NewsMapper mapper;

	@Override
	public List<Map<String, Object>> listNewsByType(Integer typeId, Page page) {
		return mapper.listNewsByType(typeId,page);
	}

	@Override
	public Map<String, Object> getNewsById(Integer id, Integer customerId) {
		return mapper.getNewsById(id,customerId);
	}

	@Override
	public Boolean saveNews(News news) {
		return mapper.saveNews(news);
	}

	@Override
	public Boolean deleteNews(Integer newsId) {
		return mapper.deleteNews(newsId);
	}

	@Override
	public Boolean updateNewse(News news) {
		return  mapper.updateNewse(news);
	}
}
