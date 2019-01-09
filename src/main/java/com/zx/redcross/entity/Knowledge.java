package com.zx.redcross.entity;

import java.io.Serializable;
import java.util.List;

import com.zx.redcross.tool.FileUtils;

public class Knowledge implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String content;
	private Boolean hasVideo;
	private String publishTime;
	private String title;
	private String videoUrl;
	private KnowledgeType knowledgeType;
	private List<KnowledgeThumbsup> knowledgeThumbsups;

	
	public Knowledge() {
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
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


	public String getVideoUrl() {
		return FileUtils.getFullUrl(this.videoUrl);
	}


	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}


	public KnowledgeType getKnowledgeType() {
		return knowledgeType;
	}


	public void setKnowledgeType(KnowledgeType knowledgeType) {
		this.knowledgeType = knowledgeType;
	}


	public List<KnowledgeThumbsup> getKnowledgeThumbsups() {
		return knowledgeThumbsups;
	}


	public void setKnowledgeThumbsups(List<KnowledgeThumbsup> knowledgeThumbsups) {
		this.knowledgeThumbsups = knowledgeThumbsups;
	}


	@Override
	public String toString() {
		return "Knowledge [id=" + id + ", content=" + content + ", hasVideo=" + hasVideo + ", publishTime="
				+ publishTime + ", title=" + title + ", videoUrl=" + videoUrl + ", knowledgeType=" + knowledgeType
				+ ", knowledgeThumbsups=" + knowledgeThumbsups + "]";
	}
	
	
}