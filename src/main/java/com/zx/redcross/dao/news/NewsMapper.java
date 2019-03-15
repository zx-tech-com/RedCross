package com.zx.redcross.dao.news;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zx.redcross.entity.News;
import com.zx.redcross.entity.Page;
/**
 * 新闻查询，增加，删除，修改
 * @author ly
 *
 */
public interface NewsMapper {
	//通过类型查找新闻
	List<Map<String, Object>> listNewsByType(@Param("typeId")Integer typeId, @Param("page")Page page);
	//查询一则帖子详情
	Map<String, Object> getNewsById(@Param("id")Integer id,@Param("customerId") Integer customerId);
	Boolean saveNews(News news);
	Boolean deleteNews(Integer newsId);
	Boolean updateNewse(News news);

}
