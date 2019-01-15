package com.zx.redcross.entity;

import java.io.Serializable;
import java.util.List;

import com.zx.redcross.tool.FileUtils;

public class Knowledge implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String content;
	private Boolean hasVideo;
	private String publishTime;
	private String title;
	private String videoUrl;
	private Integer totalShare;
	private Customer customer;
	private KnowledgeType knowledgeType;
	private List<KnowledgeThumbsup> knowledgeThumbsups;
	private List<Img> imgs;
	//===========逻辑字段====================
	private Integer totalThumbsup;
	private Integer totalComent;
	
	public Knowledge() {
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


	
	
	public Customer getCustomer() {
		return customer;
	}


	public void setCustomer(Customer customer) {
		this.customer = customer;
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


	public Integer getTotalComent() {
		return totalComent;
	}


	public void setTotalComent(Integer totalComent) {
		this.totalComent = totalComent;
	}


	public List<Img> getImgs() {
		return imgs;
	}


	public void setImgs(List<Img> imgs) {
		this.imgs = imgs;
	}


	@Override
	public String toString() {
		return "Knowledge [id=" + id + ", content=" + content + ", hasVideo=" + hasVideo + ", publishTime="
				+ publishTime + ", title=" + title + ", videoUrl=" + videoUrl + ", totalShare=" + totalShare
				+ ", customer=" + customer + ", knowledgeType=" + knowledgeType + ", knowledgeThumbsups="
				+ knowledgeThumbsups + ", imgs=" + imgs + ", totalThumbsup=" + totalThumbsup + ", totalComent="
				+ totalComent + "]";
	}

	
	
}