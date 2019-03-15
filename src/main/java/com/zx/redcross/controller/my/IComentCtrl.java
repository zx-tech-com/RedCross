package com.zx.redcross.controller.my;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zx.redcross.entity.Page;
import com.zx.redcross.service.my.IComentServ;
import com.zx.redcross.tool.Constant;
import com.zx.redcross.tool.MapUtils;
import com.zx.redcross.tool.StringUtils;
import com.zx.redcross.tool.Utils;

@RestController
@RequestMapping("/tcoment")
public class IComentCtrl {
	
	@Autowired
	private IComentServ IComentServImpl;
	
	@RequestMapping("/listTComent")
	public Map<String,Object> listTComentByCustomerId(@RequestParam(required=true)Integer customerId, Page page) {
		Map<String,Object> map = MapUtils.getHashMapInstance();
		map.put(Constant.STATUS, Constant.STATUS_FAILURE);
		List<Map<String, Object>> comentList = IComentServImpl.listTopicComentByCustomerId(customerId, page);
		if(null != comentList) {
			map.put(Constant.DATA, comentList);
			map.put(Constant.STATUS, Constant.STATUS_SUCCESS);
			map.put(Constant.PAGE_SIZE, page.getPageSize());
		}
		return map;
	}
	
	
	
	@RequestMapping("/listKComent")
	public Map<String,Object> listKComentByCustomerId(@RequestParam(required=true)Integer customerId, Page page) {
		Map<String,Object> map = MapUtils.getHashMapInstance();
		map.put(Constant.STATUS, Constant.STATUS_FAILURE);
		List<Map<String, Object>> comentList = IComentServImpl.listKnowledgeComentByCustomerId(customerId, page);
		if(null != comentList) {
			matchImgFromH5(comentList);
			splitKeyWord(comentList);
			map.put(Constant.DATA, comentList);
			map.put(Constant.STATUS, Constant.STATUS_SUCCESS);
			map.put(Constant.PAGE_SIZE, page.getPageSize());
		}
		return map;
	}
	
	
	/**
	 * 把keyWord变成数组
	 * @param newsList
	 */
	private void splitKeyWord(List<Map<String, Object>> comentList) {
		if(comentList == null) return;
		for(Map<String, Object> map : comentList) {
			String keyword = (String) map.get("knowledgeKeyWord");
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
	private void matchImgFromH5(List<Map<String, Object>> comentList) {
		if(comentList == null) return;
		for(Map<String, Object> map : comentList) {
			String content = (String) map.get("knowledgeContent");
			map.put("imgUrl", Utils.matchImgFromH5(content));
			map.remove("knowledgeContent");
		}
	}

	
}
