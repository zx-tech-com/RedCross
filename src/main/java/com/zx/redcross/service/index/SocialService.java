package com.zx.redcross.service.index;

import java.util.List;

import com.zx.redcross.entity.Customer;
import com.zx.redcross.entity.Page;
import com.zx.redcross.entity.Topic;
import com.zx.redcross.entity.TopicType;

public interface SocialService {
	//查询所有帖子的分类
	public List<TopicType> findAllTopicType();
	//查询帖子所有条数
	public Integer findAllCountTopic();
	
	public List<Topic> findAllTopic(Page page, Integer coustemerId, Integer topicTypeId);

}
