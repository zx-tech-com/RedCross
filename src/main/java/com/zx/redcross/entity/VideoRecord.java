package com.zx.redcross.entity;

public class VideoRecord {

	private Customer customer;
	private Video video;
	private String recordTime;//记录时间，数据库里为时间戳
	private String currentMinute;//记录该视频播放到哪个时间点
	
	
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Video getVideo() {
		return video;
	}
	public void setVideo(Video video) {
		this.video = video;
	}
	public String getRecordTime() {
		return recordTime;
	}
	public void setRecordTime(String recordTime) {
		this.recordTime = recordTime;
	}
	public String getCurrentTime() {
		return currentMinute;
	}
	public void setCurrentTime(String currentTime) {
		this.currentMinute = currentTime;
	}
	@Override
	public String toString() {
		return "VideoRecord [customer=" + customer + ", video=" + video + ", recordTime=" + recordTime
				+ ", currentMinute=" + currentMinute + "]";
	}
	
}
