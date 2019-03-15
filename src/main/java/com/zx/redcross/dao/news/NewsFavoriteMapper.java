package com.zx.redcross.dao.news;

import org.apache.ibatis.annotations.Param;

/**
 * 新闻收藏 添加 删除
 * @author ly
 *
 */
public interface NewsFavoriteMapper {
	//新闻收藏添加 
	Boolean saveNewsFavorite(@Param("newsId")Integer newsId,@Param("customerId") Integer customerId);
	//新闻收藏删除
	Boolean deleteNewsFavorite(@Param("newsId")Integer newsId,@Param("customerId") Integer customerId);
	//新闻收藏判断0（添加）1（删除）
	Integer findFavoriteCountByNewsIdAndCustomerId(@Param("newsId")Integer newsId,@Param("customerId") Integer customerId);
}
