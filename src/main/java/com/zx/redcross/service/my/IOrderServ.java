package com.zx.redcross.service.my;

import java.util.List;
import java.util.Map;

import com.zx.redcross.entity.Page;

public interface IOrderServ {
	
	List<Map<String,Object>> ListOrderByCustomerId(
			Integer customerId,Page page);
}
