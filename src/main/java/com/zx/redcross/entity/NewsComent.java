package com.zx.redcross.entity;

import java.io.Serializable;

public class NewsComent implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String comentTime;
	private Integer topicId;
	private Integer customerId;
	private String  content;
	private Boolean isTopcoment;
	private Integer comentId;
	private Integer topcomentId;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getComentTime() {
		return comentTime;
	}
	public void setComentTime(String comentTime) {
		this.comentTime = comentTime;
	}
	public Integer getTopicId() {
		return topicId;
	}
	public void setTopicId(Integer topicId) {
		this.topicId = topicId;
	}
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Boolean getIsTopcoment() {
		return isTopcoment;
	}
	public void setIsTopcoment(Boolean isTopcoment) {
		this.isTopcoment = isTopcoment;
	}
	public Integer getComentId() {
		return comentId;
	}
	public void setComentId(Integer comentId) {
		this.comentId = comentId;
	}
	public Integer getTopcomentId() {
		return topcomentId;
	}
	public void setTopcomentId(Integer topcomentId) {
		this.topcomentId = topcomentId;
	}
	@Override
	public String toString() {
		return "NewsComent [id=" + id + ", comentTime=" + comentTime + ", topicId=" + topicId + ", customerId="
				+ customerId + ", content=" + content + ", isTopcoment=" + isTopcoment + ", comentId=" + comentId
				+ ", topcomentId=" + topcomentId + "]";
	}
	
	
	
	
	
}
