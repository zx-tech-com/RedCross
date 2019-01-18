package com.zx.redcross.controller.my;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zx.redcross.entity.Page;
import com.zx.redcross.service.my.IOrderServ;
import com.zx.redcross.tool.Constant;
import com.zx.redcross.tool.MapUtils;

/**
 * 我的页面，自己的订单列表
 * @author Daryl
 */
@RestController
@RequestMapping("/order")
public class OrderCtrl {
	
	@Autowired
	private IOrderServ orderServImpl;
	
	@RequestMapping("/listOrder")
	public Map<String,Object> listComentByCustomerId(@RequestParam(required=false)String status,
			@RequestParam(required=true)Integer customerId, Page page) {
		Map<String,Object> map = MapUtils.getHashMapInstance();
		map.put(Constant.STATUS, Constant.STATUS_FAILURE);
		List<Map<String,Object>> orderList = orderServImpl.ListOrderByCustomerId(status,customerId, page);
		if(null != orderList) {
			map.put(Constant.DATA, orderList);
			map.put(Constant.STATUS, Constant.STATUS_SUCCESS);
			map.put(Constant.PAGE_SIZE, page.getPageSize());
		}
		return map;
	}
}
