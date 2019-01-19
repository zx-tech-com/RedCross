package com.zx.redcross.service.my;

import java.util.List;
import java.util.Map;

import com.zx.redcross.entity.Page;

public interface ITopicServ {
	List<Map<String, Object>> listTopicByCustomerId(Integer customerId,Page page);
}
