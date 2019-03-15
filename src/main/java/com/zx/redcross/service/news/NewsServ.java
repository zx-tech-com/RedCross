package com.zx.redcross.service.news;

import java.util.List;
import java.util.Map;

import com.zx.redcross.entity.News;
import com.zx.redcross.entity.Page;
/**
 * 新闻查询，增加，删除，修改
 * @author ly
 *
 */
public interface NewsServ {
	//通过类型查找新闻
	List<Map<String, Object>> listNewsByType(Integer typeId, Page page);
	//查询一则新闻的详情
	Map<String, Object> getNewsById(Integer id, Integer customerId);
	Boolean saveNews(News news);
	Boolean deleteNews(Integer newsId);
	Boolean updateNewse(News news);

}
