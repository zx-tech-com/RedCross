package com.zx.redcross.service.news;

public interface NewsFavoriteServ {
	
	Boolean saveNewsFavorite(Integer newsId,Integer customerId);
	
	Boolean deleteNewsFavorite(Integer newsId,Integer customerId);
	
	Integer findFavoriteCountByNewsIdAndCustomerId(Integer newsId,Integer customerId);
}
