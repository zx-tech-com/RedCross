package com.zx.redcross.service.social;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.zx.redcross.entity.Page;
import com.zx.redcross.entity.Topic;
import com.zx.redcross.entity.TopicComent;
import com.zx.redcross.entity.TopicType;

public interface SocialService {
	//查询所有帖子的分类
	public List<TopicType> findAllTopicType();
	//查询帖子所有条数
	public Integer findAllCountTopic();
	
	public List<Map<String, Object>> findAllTopic(Page page, Integer customerId, Integer topicTypeId);
	//通过帖子id查找帖子全部信息
	public Map<String, Object> findToicById(Integer topicId, Integer coustomerId);
	//通过用户id和被关注者id查询数据库是否关注
	public Integer findConcert(Integer aCustomerId, Integer pCustomerId);
	//添加关注
	public void saveConcert(Integer aCustomerId, Integer pCustomerId);
	//取消关注
	public void deleteConcert(Integer aCustomerId, Integer pCustomerId);
	//通过帖子id获取一级评论的全面信息
	public List<Map<String, Object>> findTopicComent(Integer topicId, Page page, Integer customerId);
	
	//通过帖子id获取二级评论的全面信息
	public List<Map<String,Object>> findLowerComent(Integer topicComentId, Page page, Integer customerId);
	
	//通过帖子id获取二级评论的全面信息
	//public List<TopicComent> findTopicComent3(Integer topicComentId, Page page, Integer customerId);
	
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
	//发表帖子
	public Boolean saveTopic(MultipartFile[] images, MultipartFile video, Topic topic);
	
	/**
	 * 帖子的分享数+1
	 * @param topicId
	 * @return
	 */
	public Boolean updateTopicSetShareAdd1(Integer topicId);
	
	//===============================后台管理需要用到的接口===================================
	//管理员删除帖子
	public Boolean adminDeleteTopic(Integer topicId);
	//管理员删除评论
	public Boolean adminDeleteTopicComent(Integer topicComentId);

}
