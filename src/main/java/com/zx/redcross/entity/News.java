package com.zx.redcross.entity;

import java.io.Serializable;

public class News implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String content;
	private Boolean hasVideo;
	private String publishTime;
	private String title;
	private String summary;
	private String keyWord;
	private Integer totalShare;
	private NewsType newsType;
	
	
	public NewsType getNewsType() {
		return newsType;
	}
	public void setNewsType(NewsType newsType) {
		this.newsType = newsType;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Boolean getHasVideo() {
		return hasVideo;
	}
	public void setHasVideo(Boolean hasVideo) {
		this.hasVideo = hasVideo;
	}
	public String getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getKeyWord() {
		return keyWord;
	}
	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}
	public Integer getTotalShare() {
		return totalShare;
	}
	public void setTotalShare(Integer totalShare) {
		this.totalShare = totalShare;
	}
	@Override
	public String toString() {
		return "News [id=" + id + ", content=" + content + ", hasVideo=" + hasVideo + ", publishTime=" + publishTime
				+ ", title=" + title + ", summary=" + summary + ", keyWord=" + keyWord + ", totalShare=" + totalShare
				+ ", newsType=" + newsType + "]";
	}
	
	

}
