package com.zx.redcross.entity;

import java.io.Serializable;
import java.util.List;

import com.zx.redcross.tool.FileUtils;


public class Topic implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String content;
	private Integer customerId;
	private Boolean hasVideo;
	private String publishTime;
	private String title;
	private String imagUrl;
	private String videoUrl;
	private Integer totalShare;
	private List<Favorite> favorites;
	private List<KnowledgeComent> knowledgeComents;
	private Customer customer;
	private TopicType topicType;
	private List<TopicComent> topicComents;
	//=============逻辑字段================
	private Integer totalThumbsup;
	private Integer totalComment;
	private Integer flag;//判断是否关注

	public String getImagUrl() {
		return imagUrl;
	}


	public void setImagUrl(String imagUrl) {
		this.imagUrl = imagUrl;
	}


	public Topic() {
	}
	

	public Integer getFlag() {
		return flag;
	}


	public void setFlag(Integer flag) {
		this.flag = flag;
	}


	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Boolean getHasVideo() {
		return this.hasVideo;
	}

	public void setHasVideo(Boolean hasVideo) {
		this.hasVideo = hasVideo;
	}

	public String getPublishTime() {
		return this.publishTime;
	}

	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getVideoUrl() {
		return FileUtils.getFullUrl(this.videoUrl);
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}

	public List<Favorite> getFavorites() {
		return this.favorites;
	}

	public void setFavorites(List<Favorite> favorites) {
		this.favorites = favorites;
	}

	public Favorite addFavorite(Favorite favorite) {
		getFavorites().add(favorite);
		favorite.setTopic(this);

		return favorite;
	}

	public Favorite removeFavorite(Favorite favorite) {
		getFavorites().remove(favorite);
		favorite.setTopic(null);

		return favorite;
	}

	public List<KnowledgeComent> getKnowledgeComents() {
		return this.knowledgeComents;
	}

	public void setKnowledgeComents(List<KnowledgeComent> knowledgeComents) {
		this.knowledgeComents = knowledgeComents;
	}

	public KnowledgeComent addKnowledgeComent(KnowledgeComent knowledgeComent) {
		getKnowledgeComents().add(knowledgeComent);
		knowledgeComent.setTopic(this);

		return knowledgeComent;
	}

	public KnowledgeComent removeKnowledgeComent(KnowledgeComent knowledgeComent) {
		getKnowledgeComents().remove(knowledgeComent);
		knowledgeComent.setTopic(null);

		return knowledgeComent;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public TopicType getTopicType() {
		return this.topicType;
	}

	public void setTopicType(TopicType topicType) {
		this.topicType = topicType;
	}

	public List<TopicComent> getTopicComents() {
		return this.topicComents;
	}

	public void setTopicComents(List<TopicComent> topicComents) {
		this.topicComents = topicComents;
	}

	
	
	
	public Integer getCustomerId() {
		return customerId;
	}


	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}


	public Integer getTotalShare() {
		return totalShare;
	}


	public void setTotalShare(Integer totalShare) {
		this.totalShare = totalShare;
	}


	public Integer getTotalThumbsup() {
		return totalThumbsup;
	}


	public void setTotalThumbsup(Integer totalThumbsup) {
		this.totalThumbsup = totalThumbsup;
	}


	public Integer getTotalComment() {
		return totalComment;
	}


	public void setTotalComment(Integer totalComment) {
		this.totalComment = totalComment;
	}


	public TopicComent addTopicComent(TopicComent topicComent) {
		getTopicComents().add(topicComent);
		topicComent.setTopic(this);

		return topicComent;
	}

	public TopicComent removeTopicComent(TopicComent topicComent) {
		getTopicComents().remove(topicComent);
		topicComent.setTopic(null);

		return topicComent;
	}


	@Override
	public String toString() {
		return "Topic [id=" + id + ", content=" + content + ", hasVideo=" + hasVideo + ", publishTime=" + publishTime
				+ ", title=" + title + ", videoUrl=" + videoUrl + ", totalShare=" + totalShare + ", favorites="
				+ favorites + ", knowledgeComents=" + knowledgeComents + ", customer=" + customer + ", topicType="
				+ topicType + ", topicComents=" + topicComents + ", totalThumbsup=" + totalThumbsup + ", totalComment="
				+ totalComment + ", flag=" + flag + "]";
	}

	
}