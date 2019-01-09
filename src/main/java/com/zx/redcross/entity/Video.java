package com.zx.redcross.entity;

import java.io.Serializable;


public class Video implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String description;
	private String thumbnailUrl;
	private String videoUrl;
	private int vindex;
	private String vname;
	private VideoSubject videoSubject;

	public Video() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getThumbnailUrl() {
		return this.thumbnailUrl;
	}

	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}

	public String getVideoUrl() {
		return this.videoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}

	public int getVindex() {
		return this.vindex;
	}

	public void setVindex(int vindex) {
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

	@Override
	public String toString() {
		return "Video [id=" + id + ", description=" + description + ", thumbnailUrl=" + thumbnailUrl + ", videoUrl="
				+ videoUrl + ", vindex=" + vindex + ", vname=" + vname + ", videoSubject=" + videoSubject + "]";
	}

}