package com.zx.redcross.controller.my;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zx.redcross.entity.Favorite;
import com.zx.redcross.entity.Page;
import com.zx.redcross.service.my.IFavoriteService;
import com.zx.redcross.tool.Constant;
import com.zx.redcross.tool.MapUtils;

@RestController
@RequestMapping("/favorite")
public class FavoriteCtrl {

	@Autowired
	private IFavoriteService favoriteServimpl;
	
	@RequestMapping(value = "/saveFavoriteTopic",method=RequestMethod.POST)
	public Map<String,Object> saveFavorite(@RequestBody Favorite favorite){
		
		Map<String,Object> map = MapUtils.getHashMapInstance();
		Boolean flag = favoriteServimpl.saveFavorite(favorite);
		map.put(Constant.STATUS,flag ? Constant.STATUS_SUCCESS : Constant.STATUS_FAILURE);
		return map;
		
	}
	
	@RequestMapping(value= "listFavorite",method=RequestMethod.GET)
	public Map<String,Object> listFavoriteByCustomerId(Integer customerId,Page page){
		
		Map<String,Object> map = MapUtils.getHashMapInstance();
		map.put(Constant.STATUS, Constant.STATUS_FAILURE);
		List<Favorite> favoriteList = favoriteServimpl.listFavoriteByCustomerId(customerId, page);
		if(null != favoriteList) {
			map.put(Constant.DATA, favoriteList);
			map.put(Constant.STATUS, Constant.STATUS_SUCCESS);
		}
		return map;
	}
	
	@RequestMapping(value = "/removeFavorite",method=RequestMethod.POST)
	public Map<String,Object>  removeFavorite(@RequestBody Favorite favorite){
		
		Map<String,Object> map = MapUtils.getHashMapInstance();
		Boolean flag = favoriteServimpl.removeFavorite(favorite);
		map.put(Constant.STATUS,flag ? Constant.STATUS_SUCCESS : Constant.STATUS_FAILURE);
		return map;
		
	}
	
}
