package com.zx.redcross.entity;

import java.io.Serializable;

import com.zx.redcross.tool.FileUtils;

public class Course implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer cindex;
	private String cname;
	private String description;
	private Boolean isVideo;
	private String thumbnailUrl;
	private String videoUrl;
	private CourseSubject courseSubject;

	public Course() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCindex() {
		return this.cindex;
	}

	public void setCindex(Integer cindex) {
		this.cindex = cindex;
	}

	public String getCname() {
		return this.cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	public Boolean isVideo() {
		return isVideo;
	}

	public void setVideo(Boolean isVideo) {
		this.isVideo = isVideo;
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

	public CourseSubject getCourseSubject() {
		return this.courseSubject;
	}

	public void setCourseSubject(CourseSubject courseSubject) {
		this.courseSubject = courseSubject;
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", cindex=" + cindex + ", cname=" + cname + ", description=" + description
				+ ", isVideo=" + isVideo + ", thumbnailUrl=" + thumbnailUrl + ", videoUrl=" + videoUrl
				+ ", courseSubject=" + courseSubject + "]";
	}

	
	
}