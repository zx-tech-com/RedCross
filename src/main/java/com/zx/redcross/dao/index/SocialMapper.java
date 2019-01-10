package com.zx.redcross.dao.index;

import java.util.List;

import com.zx.redcross.entity.Page;
import com.zx.redcross.entity.Topic;
import com.zx.redcross.entity.TopicType;

public interface SocialMapper {
	//查询帖子所有条数
	public Integer findAllTopicCount();

	//查询所有的帖子类型
	public List<TopicType> findAllTopicType();
	//分页查询帖子
	public List<Topic> findAllTopic(Page page);
}
