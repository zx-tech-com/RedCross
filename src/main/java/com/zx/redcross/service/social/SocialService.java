package com.zx.redcross.service.social;

import java.util.List;

import com.zx.redcross.entity.Concern;
import com.zx.redcross.entity.Page;
import com.zx.redcross.entity.Topic;
import com.zx.redcross.entity.TopicComent;
import com.zx.redcross.entity.TopicType;

public interface SocialService {
	//查询所有帖子的分类
	public List<TopicType> findAllTopicType();
	//查询帖子所有条数
	public Integer findAllCountTopic();
	
	public List<Topic> findAllTopic(Page page, Integer customerId, Integer topicTypeId);
	//通过帖子id查找帖子全部信息
	public Topic findToicById(Integer topicId, Integer coustomerId);
	//通过用户id和被关注者id查询数据库是否关注
	public Integer findConcert(Integer aCustomerId, Integer pCustomerId);
	//添加关注
	public void saveConcert(Integer aCustomerId, Integer pCustomerId);
	//取消关注
	public void deleteConcert(Integer aCustomerId, Integer pCustomerId);
	//通过帖子id获取一级评论的全面信息
	public List<TopicComent> findTopicComent(Integer topicId, Page page, Integer customerId);
	//通过帖子id获取二级评论的全面信息
	public List<TopicComent> findTopicComent2(Integer topicComentId, Page page, Integer customerId);
	//通过帖子id获取二级评论的全面信息
	public List<TopicComent> findTopicComent3(Integer topicComentId, Page page, Integer customerId);
	//插入一级评论
	public void saveTopicComent(TopicComent topicComent);
	//查询用户在评论上是否点赞了
	public Integer findThunsup(Integer coustomerId, Integer topicComentId);
	//添加评论点赞
	public void saveThunsup(Integer coustomerId, Integer topicComentId);
	//删除评论点赞
	public void deleteThunsup(Integer coustomerId, Integer topicComentId);
	//查询用户在帖子上是否点赞了
	public Integer findTopicThunsup(Integer coustomerId, Integer topicId);
	//添加帖子点赞
	public void saveTopicThunsup(Integer coustomerId, Integer topicId);
	//删除帖子点赞
	public void deleteTopicThunsup(Integer coustomerId, Integer topicId);
	//发表帖子
	public void saveTopic(Topic topic);

}
