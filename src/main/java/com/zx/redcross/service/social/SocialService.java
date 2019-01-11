package com.zx.redcross.service.social;

import java.util.List;

import com.zx.redcross.entity.Concern;
import com.zx.redcross.entity.Page;
import com.zx.redcross.entity.Topic;
import com.zx.redcross.entity.TopicType;

public interface SocialService {
	//查询所有帖子的分类
	public List<TopicType> findAllTopicType();
	//查询帖子所有条数
	public Integer findAllCountTopic();
	
	public List<Topic> findAllTopic(Page page, Integer coustemerId, Integer topicTypeId);
	//通过帖子id查找帖子全部信息
	public Topic findToicById(Integer topicId);
	//通过用户id和被关注者id查询数据库是否关注
	public Integer findConcert(Integer aCustomerId, Integer pCustomerId);
	//添加关注
	public void saveConcert(Integer aCustomerId, Integer pCustomerId);
	//取消关注
	public void deleteConcert(Integer aCustomerId, Integer pCustomerId);

}
