package com.zx.redcross.controller.my;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zx.redcross.entity.Page;
import com.zx.redcross.service.my.ITopicServ;
import com.zx.redcross.tool.Constant;
import com.zx.redcross.tool.MapUtils;

@RestController
@RequestMapping("/topic")
public class TopicController {
	
	@Autowired
	private ITopicServ topicServImpl;
	
	@RequestMapping("/listTopic")
	public Map<String,Object> listComentByCustomerId(@RequestParam(required=true)Integer customerId, Page page) {
		Map<String,Object> map = MapUtils.getHashMapInstance();
		map.put(Constant.STATUS, Constant.STATUS_FAILURE);
		List<Map<String, Object>> topicList = topicServImpl.listTopicByCustomerId(customerId, page);
		if(null != topicList) {
			map.put(Constant.DATA, topicList);
			map.put(Constant.STATUS, Constant.STATUS_SUCCESS);
			map.put(Constant.PAGE_SIZE, page.getPageSize());
		}
		return map;
	}
	
}
