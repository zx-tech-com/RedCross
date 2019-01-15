package com.zx.redcross.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import com.zx.redcross.tool.FileUtils;


public class Video implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String description;
	private String thumbnailUrl;
	private String videoUrl;
	private Integer vindex;
	private BigDecimal price;
	private String vname;
	private VideoSubject videoSubject;

	public Video() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getThumbnailUrl() {
		return FileUtils.getFullUrl(this.thumbnailUrl);
	}

	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}

	public String getVideoUrl() {
		return FileUtils.getFullUrl(this.videoUrl);
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}

	public Integer getVindex() {
		return this.vindex;
	}

	public void setVindex(Integer vindex) {
		this.vindex = vindex;
	}

	public String getVname() {
		return this.vname;
	}

	public void setVname(String vname) {
		this.vname = vname;
	}

	public VideoSubject getVideoSubject() {
		return this.videoSubject;
	}

	public void setVideoSubject(VideoSubject videoSubject) {
		this.videoSubject = videoSubject;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Video [id=" + id + ", description=" + description + ", thumbnailUrl=" + thumbnailUrl + ", videoUrl="
				+ videoUrl + ", vindex=" + vindex + ", price=" + price + ", vname=" + vname + ", videoSubject="
				+ videoSubject + "]";
	}

	
}