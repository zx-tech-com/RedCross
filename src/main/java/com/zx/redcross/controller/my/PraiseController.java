package com.zx.redcross.controller.my;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zx.redcross.entity.Page;
import com.zx.redcross.service.my.IPraiseService;
import com.zx.redcross.tool.Constant;
import com.zx.redcross.tool.MapUtils;
/**
 * 我的页面（我的点赞）
 * @author 罗勇
 *
 */
@RestController
@RequestMapping("/praise")
public class PraiseController {
	
	@Autowired
	private IPraiseService praiseServImpl;
	
	
	@RequestMapping("/listPraise")
	public Map<String,Object> listComentByCustomerId(
			@RequestParam(required=true)Integer customerId, Page page) {
		Map<String,Object> map = MapUtils.getHashMapInstance();
		map.put(Constant.STATUS, Constant.STATUS_FAILURE);
		List<Map<String,Object>> praiseList = praiseServImpl.ListPraiseByCustomerId(customerId, page);
		if(null != praiseList) {
			map.put(Constant.DATA, praiseList);
			map.put(Constant.STATUS, Constant.STATUS_SUCCESS);
			map.put(Constant.PAGE_SIZE, page.getPageSize());
		}
		return map;
	}
}
