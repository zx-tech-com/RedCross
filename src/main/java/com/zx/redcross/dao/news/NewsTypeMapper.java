package com.zx.redcross.dao.news;

import java.util.List;
import java.util.Map;

import com.zx.redcross.entity.NewsType;
/**
 * 新闻类型增删改查
 * @author ly
 *
 */
public interface NewsTypeMapper {
	//查找所有类型
	List<Map<String, Object>> listNewsType();
	//通过Id查找类型
	NewsType getNewsTypeById(Integer id);
	//通过Id删除类型
	Boolean deleteNewsTypeById(Integer id);
	//修改类型
	Boolean updateNewsTypeById(NewsType newsType);
	//添加类型
	Boolean saveNewsType(NewsType newsType);

	

}
