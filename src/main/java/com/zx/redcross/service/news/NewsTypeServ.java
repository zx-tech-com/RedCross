package com.zx.redcross.service.news;

import java.util.List;
import java.util.Map;

import com.zx.redcross.entity.NewsType;
/**
 * 新闻类型增删改查
 * @author ly
 *
 */
public interface NewsTypeServ {
	//查找所有类型
	List<Map<String, Object>> listNewsType();
	//通过ID查找类型
	NewsType getNewsTypeById(Integer id);
	//通过ID删除类型
	Boolean deleteNewsTypeById(Integer id);
	//通过ID修改类型
	Boolean updateNewsTypeById(NewsType newsType);
	//添加新闻类型
	Boolean saveNewsType(NewsType newsType);

}
