package com.zx.redcross.controller.my;

import java.util.ArrayList;
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
import com.zx.redcross.tool.StringUtils;
import com.zx.redcross.tool.Utils;

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
	
	@RequestMapping(value= "listFavoriteKnowledge",method=RequestMethod.GET)
	public Map<String,Object> listFavoriteKnowledge(Integer customerId,Page page){
		
		Map<String,Object> map = MapUtils.getHashMapInstance();
		map.put(Constant.STATUS, Constant.STATUS_FAILURE);
		List<Map<String, Object>> favoriteList = favoriteServimpl.listFavoriteKnowledge(customerId, page);
		if(null != favoriteList) {
			matchImgFromH5(favoriteList);
			splitKeyWord(favoriteList);
			map.put(Constant.DATA, favoriteList);
			map.put(Constant.STATUS, Constant.STATUS_SUCCESS);
		}
		return map;
	}
	/**
	 * 把keyWord变成数组
	 * @param newsList
	 */
	private void splitKeyWord(List<Map<String, Object>> favoriteList) {
		if(favoriteList == null) return;
		for(Map<String, Object> map : favoriteList) {
			String keyword = (String) map.get("keyWord");
			String[] keywords = null;
			if(StringUtils.isNotBlank(keyword)) {
				keywords = keyword.split("#");
				map.put("keyWord", keywords);
			}else
				map.put("keyWord", new ArrayList<String>());
		}
	}
	/**
	 * 把img从H5中抓取出来
	 * @param newsList
	 */
	private void matchImgFromH5(List<Map<String, Object>> favoriteList) {
		if(favoriteList == null) return;
		for(Map<String, Object> map : favoriteList) {
			String content = (String) map.get("content");
			map.put("imgUrl", Utils.matchImgFromH5(content));
			map.remove("content");
		}
	}

	@RequestMapping(value = "/removeFavorite")
	public Map<String,Object>  removeFavorite(@RequestBody Favorite favorite){
		
		Map<String,Object> map = MapUtils.getHashMapInstance();
		Boolean flag = favoriteServimpl.removeFavorite(favorite);
		map.put(Constant.STATUS,flag ? Constant.STATUS_SUCCESS : Constant.STATUS_FAILURE);
		return map;
		
	}
	
}
