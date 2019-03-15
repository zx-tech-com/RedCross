package com.zx.redcross.serviceimpl.news;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zx.redcross.dao.news.NewsTypeMapper;
import com.zx.redcross.entity.NewsType;
import com.zx.redcross.service.news.NewsTypeServ;
@Service
public class NewsTypeSerImpl implements NewsTypeServ{
	@Autowired
	private NewsTypeMapper mapper;

	@Override
	public List<Map<String, Object>> listNewsType() {
		return mapper.listNewsType();
	}

	@Override
	public NewsType getNewsTypeById(Integer id) {
		return mapper.getNewsTypeById(id);
	}

	@Override
	public Boolean deleteNewsTypeById(Integer id) {
		return mapper.deleteNewsTypeById(id);
	}

	@Override
	public Boolean updateNewsTypeById(NewsType newsType) {
		return mapper.updateNewsTypeById(newsType);
	}

	@Override
	public Boolean saveNewsType(NewsType newsType) {
		return mapper.saveNewsType(newsType);
	}
}
