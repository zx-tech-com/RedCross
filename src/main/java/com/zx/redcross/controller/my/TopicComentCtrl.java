package com.zx.redcross.controller.my;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zx.redcross.entity.Page;
import com.zx.redcross.entity.TopicComent;
import com.zx.redcross.service.my.ITopicComentServ;
import com.zx.redcross.tool.Constant;
import com.zx.redcross.tool.MapUtils;

@RestController
@RequestMapping("/tcoment")
public class TopicComentCtrl {
	
	@Autowired
	private ITopicComentServ topicComentServImpl;
	
	@RequestMapping("/listComentByCustomerId")
	public Map<String,Object> listComentByCustomerId(@RequestParam(required=true)Integer customerId, Page page) {
		Map<String,Object> map = MapUtils.getHashMapInstance();
		map.put(Constant.STATUS, Constant.STATUS_FAILURE);
		List<TopicComent> comentList = topicComentServImpl.listTopicComentByCustomerId(customerId, page);
		if(null != comentList) {
			map.put(Constant.DATA, comentList);
			map.put(Constant.STATUS, Constant.STATUS_SUCCESS);
		}
		return map;
	}
	
}
