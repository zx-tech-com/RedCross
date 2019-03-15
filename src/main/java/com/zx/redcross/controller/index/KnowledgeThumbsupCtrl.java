package com.zx.redcross.controller.index;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zx.redcross.annotation.FrontEnd;
import com.zx.redcross.annotation.Open;
import com.zx.redcross.service.index.KnowledgeThumbsupServ;
import com.zx.redcross.tool.Constant;
import com.zx.redcross.tool.MapUtils;


/**
 * 急救常识点赞
 * @author ly
 *
 */
@RestController
@RequestMapping("/knowledgeThumbsup")
public class KnowledgeThumbsupCtrl {
	@Autowired
	private KnowledgeThumbsupServ knowledgeThumbsupServImpl;
	
	
	@FrontEnd
	@RequestMapping("/saveKnowledgeThumbsup")
	@Open
	public Map<String,Object> saveKnowledgeThumbsup(@RequestParam(required=true)Integer knowledgeId,@RequestParam(required=true)Integer customerId) {
		Map<String,Object> map = MapUtils.getHashMapInstance();
		Integer count=knowledgeThumbsupServImpl.findCountThumbsupByKnowledgeIdAndCustomerId(knowledgeId,customerId);
		if(count==0) {
			knowledgeThumbsupServImpl.saveKnowledgeThumbsup(knowledgeId,customerId);
			map.put(Constant.DATA,Constant.THUMB_SUCCESS);
		}else {
			knowledgeThumbsupServImpl.deleteKnowledgeThumbsup(knowledgeId,customerId);
			map.put(Constant.DATA,Constant.THUMB_FAILURE);
		}
		map.put(Constant.STATUS,Constant.STATUS_SUCCESS);
		return map;
	}
}
