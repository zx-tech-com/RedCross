package com.zx.redcross.dao.social;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zx.redcross.entity.Customer;
import com.zx.redcross.entity.Page;
import com.zx.redcross.entity.Topic;
import com.zx.redcross.entity.TopicComent;
import com.zx.redcross.entity.TopicType;

public interface SocialMapper {
	//查询帖子所有条数
	public Integer findAllTopicCount(@Param("topicTypeId") Integer topicTypeId);

	//查询所有的帖子类型
	public List<TopicType> findAllTopicType();
	//分页查询帖子
	public List<Map<String, Object>> findAllTopic(@Param("page")Page page, @Param("customerId")Integer customerId, @Param("topicTypeId")Integer topicTypeId);

	public Map<String, Object> findTopicById(@Param("topicId")Integer topicId,@Param("customerId") Integer customerId);

	public Integer findConcert(@Param("aCustomerId")Integer aCustomerId,@Param("pCustomerId") Integer pCustomerId);

	public void saveConcert(@Param("aCustomerId")Integer aCustomerId, @Param("pCustomerId")Integer pCustomerId);

	public void deleteConcert(@Param("aCustomerId")Integer aCustomerId, @Param("pCustomerId")Integer pCustomerId);
	//查询会员全部信息
	public Customer findCustomer(@Param("customerId")Integer customerId);
	//通过帖子id获取一级评论全部信息及完成分页
	public List<Map<String, Object>> findTopicComent(@Param("topicId")Integer topicId,@Param("page") Page page,@Param("customerId") Integer customerId);
	
	//获取非顶层评论
	public List<Map<String, Object>> findLowerComent(@Param("topicComentId")Integer topicComentId, 
			@Param("page")Page page, @Param("customerId")Integer customerId);
	
	//插入评论
	public Boolean saveTopicComent(TopicComent topicComent);
	//查询用户是否点过赞评论
	public Integer findThunsup(@Param("coustomerId")Integer coustomerId,@Param("topicComentId") Integer topicComentId);
	//加入点赞评论
	public void saveThunsup(@Param("coustomerId")Integer coustomerId,@Param("topicComentId") Integer topicComentId);
	//删除点赞评论
	public void deleteThunsup(@Param("coustomerId")Integer coustomerId,@Param("topicComentId") Integer topicComentId);
	//查询用户是否点过赞帖子
	public Integer findTopicThunsup(@Param("coustomerId")Integer coustomerId,@Param("topicId") Integer topicId);
	//加入点赞帖子
	public void saveTopicThunsup(@Param("coustomerId")Integer coustomerId,@Param("topicId") Integer topicId);
	//删除点赞帖子
	public void deleteTopicThunsup(@Param("coustomerId")Integer coustomerId,@Param("topicId") Integer topicId);
	//发表帖子
	public Boolean saveTopic(@Param("topic") Topic topic);
	
	
	//===============================后台管理需要用到的接口===================================
	//删除帖子
	public Boolean adminDeleteTopic(@Param("topicId")Integer topicId);
	//删除评论
	public Boolean adminDeleteTopicComent(@Param("topicComentId")Integer topicComentId);

	public Boolean updateTopicSetShareAdd1(@Param("topicId")Integer topicId);

	public boolean addTopicType(TopicType topicType);

	public boolean adminUpdateTopicType(TopicType topicType);

	public String findTopicTypeById(Integer topicTypeId);

	public boolean adminDeleteTopicType(Integer topicTypeId);
	//查詢頂層評論的條數
	public Integer findOnceTopicComentCount(Integer topicId);

	public Integer findLowerComentCount(Integer topicComentId);

	public Integer getTopicThumbsupCount(Integer topicId);

	public Integer getThunsupCount(Integer topicComentId);

	public Integer getTopicComentCount(Integer id);


	
}
