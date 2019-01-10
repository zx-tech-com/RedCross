package com.zx.redcross.service;

import java.util.List;

import com.zx.redcross.entity.Topic;
import com.zx.redcross.entity.TopicType;

public interface SocialService {
	//查询所有帖子的分类
	public List<TopicType> findAllTopicType();
	//查询所有帖子
	public List<Topic> findAllTopic();
	//查询帖子所有条数
	public Integer findAllCountTopic();
}
