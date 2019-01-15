package com.zx.redcross.serviceimpl.my;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zx.redcross.dao.my.IFavoriteMapper;
import com.zx.redcross.entity.Favorite;
import com.zx.redcross.entity.Page;
import com.zx.redcross.service.my.IFavoriteService;
import com.zx.redcross.tool.BusinessExceptionUtils;

@Service
public class FavoriteServiceImpl implements IFavoriteService {

	@Autowired
	private IFavoriteMapper favoriteMapper;
	
	@Override
	public Boolean saveFavorite(Favorite favorite) {
		if(favoriteMapper.getFavoriteByCustomerIdAndTopicId(favorite) != null)
			BusinessExceptionUtils.throwNewBusinessException("不可重复收藏");
		return favoriteMapper.saveFavorite(favorite);
	}

	@Override
	public List<Favorite> listFavoriteByCustomerId(Integer customerId, Page page) {
		return favoriteMapper.listFavoriteByCustomerId(customerId, page);
	}

	@Override
	public Boolean removeFavorite(Favorite favorite) {
		if(favoriteMapper.getFavoriteByCustomerIdAndTopicId(favorite) == null)
			BusinessExceptionUtils.throwNewBusinessException("尚未收藏该动态");
		return favoriteMapper.removeFavorite(favorite);
	}

}
