package com.zx.redcross.controller.news;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zx.redcross.annotation.BackEnd;
import com.zx.redcross.annotation.Open;
import com.zx.redcross.entity.Knowledge;
import com.zx.redcross.entity.News;
import com.zx.redcross.entity.Page;
import com.zx.redcross.service.news.NewsServ;
import com.zx.redcross.tool.Constant;
import com.zx.redcross.tool.MapUtils;
import com.zx.redcross.tool.StringUtils;
import com.zx.redcross.tool.Utils;

@RestController
@RequestMapping("/news")
public class NewsCtrl {
	@Autowired
	private NewsServ newsServImpl;
	
	@RequestMapping("/listNews")
	@Open
	public Map<String,Object> listNewsByType(@RequestParam(required=false) Integer typeId,Page page) {
		
		Map<String,Object> map = MapUtils.getHashMapInstance();
		List<Map<String, Object>> newsList = newsServImpl.listNewsByType(typeId, page);
		map.put(Constant.STATUS,Constant.STATUS_FAILURE);
		if(null != newsList) {
			matchImgFromH5(newsList);
			splitKeyWord(newsList);
			map.put(Constant.STATUS,Constant.STATUS_SUCCESS);
			map.put(Constant.PAGE_SIZE,page.getPageSize());
			map.put(Constant.DATA, newsList);
		}
		return map;
	}
	/**
	 * 把keyWord变成数组
	 * @param newsList
	 */
	private void splitKeyWord(List<Map<String, Object>> newsList) {
		if(newsList == null) return;
		for(Map<String, Object> map : newsList) {
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
	private void matchImgFromH5(List<Map<String, Object>> newsList) {
		if(newsList == null) return;
		for(Map<String, Object> map : newsList) {
			String content = (String) map.get("content");
			map.put("imgUrl", Utils.matchImgFromH5(content));
			map.remove("content");
		}
	}
	
	/**
	 * 获取一则新闻
	 * @param id
	 * @param customerId
	 * @return
	 */
	@RequestMapping("/getNews")
	@Open
	public Map<String,Object> getNewsById(@RequestParam(required=true)Integer id,Integer customerId) {
		Map<String,Object> map = MapUtils.getHashMapInstance();
		Map<String, Object> news = newsServImpl.getNewsById(id,customerId);
		map.put(Constant.STATUS,Constant.STATUS_FAILURE);
		if(null != news) {
			map.put(Constant.STATUS,Constant.STATUS_SUCCESS);
			map.put(Constant.DATA, news);
		}
		return map;
	}
	
	
	/**
	 * 添加新闻
	 */
	@BackEnd
	@RequestMapping("/saveNews")
	public Map<String,Object> saveNews(@RequestBody News news) {
		Map<String,Object> map = MapUtils.getHashMapInstance();
		Boolean flag = newsServImpl.saveNews(news);
		map.put(Constant.STATUS,flag ? Constant.STATUS_SUCCESS : Constant.STATUS_FAILURE);
		return map;
	}
	
	
	
	/**
	 * 删除新闻
	 */
	@RequestMapping("/deleteNews")
	@BackEnd
	public Map<String,Object> deleteNews(Integer newsId) {
		Map<String,Object> map = MapUtils.getHashMapInstance();
		Boolean flag=newsServImpl.deleteNews(newsId);
		map.put(Constant.STATUS,flag ? Constant.STATUS_SUCCESS : Constant.STATUS_FAILURE);
		return map;
	}
	
	/**
	 * 修改新闻
	 */
	@RequestMapping("/updateNews")
	@BackEnd
	public Map<String,Object> updateNewse(@RequestBody News news) {
		Map<String,Object> map = MapUtils.getHashMapInstance();
		Boolean flag=newsServImpl.updateNewse(news);
		map.put(Constant.STATUS,flag ? Constant.STATUS_SUCCESS : Constant.STATUS_FAILURE);
		return map;
	}
}
