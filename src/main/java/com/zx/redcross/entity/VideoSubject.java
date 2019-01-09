package com.zx.redcross.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.zx.redcross.tool.FileUtils;


public class VideoSubject implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String description;
	private BigDecimal price;
	private String thumbnailUrl;
	private String vname;
	private List<Video> videos;
	private List<VideoBuyRecord> videoBuyRecords;

	public VideoSubject() {
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

	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getThumbnailUrl() {
		return FileUtils.getFullUrl(this.thumbnailUrl);
	}

	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}

	public String getVname() {
		return this.vname;
	}

	public void setVname(String vname) {
		this.vname = vname;
	}

	public List<Video> getVideos() {
		return this.videos;
	}

	public void setVideos(List<Video> videos) {
		this.videos = videos;
	}

	public Video addVideo(Video video) {
		getVideos().add(video);
		video.setVideoSubject(this);

		return video;
	}

	public Video removeVideo(Video video) {
		getVideos().remove(video);
		video.setVideoSubject(null);

		return video;
	}

	public List<VideoBuyRecord> getVideoBuyRecords() {
		return this.videoBuyRecords;
	}

	public void setVideoBuyRecords(List<VideoBuyRecord> videoBuyRecords) {
		this.videoBuyRecords = videoBuyRecords;
	}

	public VideoBuyRecord addVideoBuyRecord(VideoBuyRecord videoBuyRecord) {
		getVideoBuyRecords().add(videoBuyRecord);
		videoBuyRecord.setVideoSubject(this);

		return videoBuyRecord;
	}

	public VideoBuyRecord removeVideoBuyRecord(VideoBuyRecord videoBuyRecord) {
		getVideoBuyRecords().remove(videoBuyRecord);
		videoBuyRecord.setVideoSubject(null);

		return videoBuyRecord;
	}

	@Override
	public String toString() {
		return "VideoSubject [id=" + id + ", description=" + description + ", price=" + price + ", thumbnailUrl="
				+ thumbnailUrl + ", vname=" + vname + ", videos=" + videos + ", videoBuyRecords=" + videoBuyRecords
				+ "]";
	}

}