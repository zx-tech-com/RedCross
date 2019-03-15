package com.zx.redcross.controller.news;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zx.redcross.annotation.BackEnd;
import com.zx.redcross.annotation.Open;
import com.zx.redcross.entity.NewsType;
import com.zx.redcross.service.news.NewsTypeServ;
import com.zx.redcross.tool.Constant;
import com.zx.redcross.tool.MapUtils;
/**
 * 针对新闻类型增删改查
 * @author ly
 *
 */
@RestController
@RequestMapping("/news")
public class NewsTypeCtrl {
	
	@Autowired
	private NewsTypeServ newsTypeServImpl;
	/**
	 * 查询所有新闻类型
	 */
	@RequestMapping("/listNewsType")
	@Open
	public Map<String,Object> listNewsType() {
		Map<String,Object> map = MapUtils.getHashMapInstance();
		List<Map<String, Object>> newsTypeList = newsTypeServImpl.listNewsType();
		map.put(Constant.STATUS,Constant.STATUS_FAILURE);
		if(null != newsTypeList) {
			map.put(Constant.STATUS,Constant.STATUS_SUCCESS);
			map.put(Constant.DATA, newsTypeList);
		}
		return map;
	}
	
	
	
	//===============================后台接口=======================================
	/**
	 * 通过id查找类型
	 * @param id
	 * @return
	 */
	@RequestMapping("/getNewsType")
	@BackEnd
	public Map<String,Object> getNewsTypeById(Integer id) {
		Map<String,Object> map = MapUtils.getHashMapInstance();
		NewsType newsType = newsTypeServImpl.getNewsTypeById(id);
		map.put(Constant.STATUS,Constant.STATUS_FAILURE);
		if(null != newsType) {
			map.put(Constant.STATUS,Constant.STATUS_SUCCESS);
			map.put(Constant.DATA, newsType);
		}
		return map;
	}
	
	/**
	 * 通过id删除类型
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteNewsType")
	@BackEnd
	public Map<String,Object> deleteNewsTypeById(Integer id) {
		Map<String,Object> map = MapUtils.getHashMapInstance();
		Boolean flag = newsTypeServImpl.deleteNewsTypeById(id);
		map.put(Constant.STATUS,flag ? Constant.STATUS_SUCCESS : Constant.STATUS_FAILURE);
		return map;
	}
	
	
	/**
	 * 通过id删除类型
	 * @param id
	 * @return
	 */
	@RequestMapping("/saveNewsType")
	@BackEnd
	public Map<String,Object> saveNewsType(NewsType newsType) {
		Map<String,Object> map = MapUtils.getHashMapInstance();
		Boolean flag = newsTypeServImpl.saveNewsType(newsType);
		map.put(Constant.STATUS,flag ? Constant.STATUS_SUCCESS : Constant.STATUS_FAILURE);
		return map;
	}



	@RequestMapping("/updateNewsType")
	@BackEnd
	public Map<String,Object> updateNewsTypeById(NewsType newsType) {
		Map<String,Object> map = MapUtils.getHashMapInstance();
		Boolean flag = newsTypeServImpl.updateNewsTypeById(newsType);
		map.put(Constant.STATUS,flag ? Constant.STATUS_SUCCESS : Constant.STATUS_FAILURE);
		return map;
		}
	}
