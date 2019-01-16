package com.zx.redcross.service.my;

import java.util.List;

import com.zx.redcross.entity.Page;
import com.zx.redcross.entity.Topic;

public interface ITopicServ {
	List<Topic> listTopicByCustomerId(Integer customerId,Page page);
}
