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
	private String videoUrl;
	private String imgUrl;
	private Integer totalShare;
	private String status;
	private List<Favorite> favorites;
	private Customer customer;
	private TopicType topicType;
	private List<TopicComent> topicComents;
	private List<Img> imgs;
	//=============逻辑字段================
	private Integer totalThumbsup;
	private Integer totalComment;
	private Integer flag;//判断是否关注

	public Topic() {
	}
	
	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
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

	public List<Img> getImgs() {
		return imgs;
	}


	public void setImgs(List<Img> imgs) {
		this.imgs = imgs;
	}

	
	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	@Override
	public String toString() {
		return "Topic [id=" + id + ", content=" + content + ", customerId=" + customerId + ", hasVideo=" + hasVideo
				+ ", publishTime=" + publishTime + ", title=" + title + ", videoUrl=" + videoUrl + ", imgUrl=" + imgUrl
				+ ", totalShare=" + totalShare + ", status=" + status + ", favorites=" + favorites + ", customer="
				+ customer + ", topicType=" + topicType + ", topicComents=" + topicComents + ", imgs=" + imgs
				+ ", totalThumbsup=" + totalThumbsup + ", totalComment=" + totalComment + ", flag=" + flag + "]";
	}
	
}