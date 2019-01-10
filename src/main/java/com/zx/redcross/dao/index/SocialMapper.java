package com.zx.redcross.dao.index;

import java.util.List;

import com.zx.redcross.entity.TopicType;

public interface SocialMapper {
	//查询帖子所有条数
	public Integer findAllTopicCount();

	//查询所有的帖子类型
	public List<TopicType> findAllTopicType();
}
