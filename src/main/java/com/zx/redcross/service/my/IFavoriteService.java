package com.zx.redcross.service.my;

import java.util.List;
import java.util.Map;

import com.zx.redcross.entity.Favorite;
import com.zx.redcross.entity.Page;

public interface IFavoriteService {

	
	Boolean saveFavorite(Favorite favorite);
	//我的收藏 社交
	List<Favorite> listFavoriteByCustomerId(
			Integer customerId,Page page);
	
	Boolean removeFavorite(Favorite favorite);
	//我的收藏 知识
	List<Map<String, Object>> listFavoriteKnowledge(Integer customerId, Page page);
	
	
}
