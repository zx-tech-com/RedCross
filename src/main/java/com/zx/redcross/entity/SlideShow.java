package com.zx.redcross.entity;

public class SlideShow {
	private Integer id;
	private String	imgUrl;
	private String  description;
	private String	url;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Override
	public String toString() {
		return "SlideShow [id=" + id + ", imgUrl=" + imgUrl + ", description=" + description + ", url=" + url + "]";
	}
	
}
