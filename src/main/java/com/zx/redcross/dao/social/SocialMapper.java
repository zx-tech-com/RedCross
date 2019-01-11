package com.zx.redcross.dao.social;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zx.redcross.entity.Concern;
import com.zx.redcross.entity.Customer;
import com.zx.redcross.entity.Page;
import com.zx.redcross.entity.Topic;
import com.zx.redcross.entity.TopicType;

public interface SocialMapper {
	//查询帖子所有条数
	public Integer findAllTopicCount();

	//查询所有的帖子类型
	public List<TopicType> findAllTopicType();
	//分页查询帖子
	public List<Topic> findAllTopic(@Param("page")Page page, @Param("coustemerId")Integer coustemerId, @Param("topicTypeId")Integer topicTypeId);

	
	public Topic findTopicById(Integer topicId);

	public Integer findConcert(@Param("aCustomerId")Integer aCustomerId,@Param("pCustomerId") Integer pCustomerId);

	public void saveConcert(@Param("aCustomerId")Integer aCustomerId, @Param("pCustomerId")Integer pCustomerId);

	public void deleteConcert(@Param("aCustomerId")Integer aCustomerId, @Param("pCustomerId")Integer pCustomerId);
}
