package com.zx.redcross.entity;

import java.io.Serializable;
import java.util.List;

public class Knowledge implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String content;
	private Boolean hasVideo;
	private String publishTime;
	private String title;
	private String summary;
	private String keyWord;
	private Integer totalShare;
	private Customer customer;
	private KnowledgeType knowledgeType;
	private List<KnowledgeThumbsup> knowledgeThumbsups;
	private List<Img> imgs;
	//===========逻辑字段====================
	private Integer totalThumbsup;
	private Integer totalComent;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	
	public String getKeyWord() {
		return keyWord;
	}
	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
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
	public Integer getTotalShare() {
		return totalShare;
	}
	public void setTotalShare(Integer totalShare) {
		this.totalShare = totalShare;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
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
	public List<Img> getImgs() {
		return imgs;
	}
	public void setImgs(List<Img> imgs) {
		this.imgs = imgs;
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
	@Override
	public String toString() {
		return "Knowledge [id=" + id + ", content=" + content + ", hasVideo=" + hasVideo + ", publishTime="
				+ publishTime + ", title=" + title + ", summary=" + summary + ", keyWord=" + keyWord + ", totalShare="
				+ totalShare + ", customer=" + customer + ", knowledgeType=" + knowledgeType + ", knowledgeThumbsups="
				+ knowledgeThumbsups + ", imgs=" + imgs + ", totalThumbsup=" + totalThumbsup + ", totalComent="
				+ totalComent + "]";
	}
}