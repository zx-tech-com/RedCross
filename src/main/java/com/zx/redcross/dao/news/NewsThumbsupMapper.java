package com.zx.redcross.dao.news;

import org.apache.ibatis.annotations.Param;

/**
 * 新闻点赞
 * @author ly
 *
 */
public interface NewsThumbsupMapper {
	//添加新闻点赞
	Boolean saveNewsThumbsup(@Param("newsId")Integer newsId,@Param("customerId") Integer customerId);
	//删除新闻点赞
	Boolean deleteNewsThumbsup(@Param("newsId")Integer newsId,@Param("customerId") Integer customerId);
	//查询用户是否对新闻点赞0（没点赞）1（点赞）
	Integer findCountThumbsupByNewsIdAndCustomerId(@Param("newsId")Integer newsId,@Param("customerId") Integer customerId);
}
