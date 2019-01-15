package com.zx.redcross.service.my;

import java.util.List;

import com.zx.redcross.entity.Favorite;
import com.zx.redcross.entity.Page;

public interface IFavoriteService {

	
	Boolean saveFavorite(Favorite favorite);
	
	List<Favorite> listFavoriteByCustomerId(
			Integer customerId,Page page);
	
	Boolean removeFavorite(Favorite favorite);
	
	
}
