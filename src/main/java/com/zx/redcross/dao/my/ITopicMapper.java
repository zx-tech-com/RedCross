package com.zx.redcross.dao.my;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zx.redcross.entity.Page;
import com.zx.redcross.entity.Topic;

public interface ITopicMapper {
	
	List<Topic> listTopicByCustomerId(
			@Param("customerId")Integer customerId,@Param("page") Page page
			);
	
}
