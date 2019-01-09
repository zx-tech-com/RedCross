package com.zx.redcross.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;


public class VideoBuyRecord implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String coment;
	private String orderNumber;
	private byte status;
	private Timestamp submitTime;
	private VideoSubject videoSubject;
	private Customer customer;
	private List<VideoPayRecord> videoPayRecords;

	public VideoBuyRecord() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getComent() {
		return this.coment;
	}

	public void setComent(String coment) {
		this.coment = coment;
	}

	public String getOrderNumber() {
		return this.orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public byte getStatus() {
		return this.status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	public Timestamp getSubmitTime() {
		return this.submitTime;
	}

	public void setSubmitTime(Timestamp submitTime) {
		this.submitTime = submitTime;
	}

	public VideoSubject getVideoSubject() {
		return this.videoSubject;
	}

	public void setVideoSubject(VideoSubject videoSubject) {
		this.videoSubject = videoSubject;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<VideoPayRecord> getVideoPayRecords() {
		return this.videoPayRecords;
	}

	public void setVideoPayRecords(List<VideoPayRecord> videoPayRecords) {
		this.videoPayRecords = videoPayRecords;
	}

	public VideoPayRecord addVideoPayRecord(VideoPayRecord videoPayRecord) {
		getVideoPayRecords().add(videoPayRecord);
		videoPayRecord.setVideoBuyRecord(this);

		return videoPayRecord;
	}

	public VideoPayRecord removeVideoPayRecord(VideoPayRecord videoPayRecord) {
		getVideoPayRecords().remove(videoPayRecord);
		videoPayRecord.setVideoBuyRecord(null);

		return videoPayRecord;
	}

	@Override
	public String toString() {
		return "VideoBuyRecord [id=" + id + ", coment=" + coment + ", orderNumber=" + orderNumber + ", status=" + status
				+ ", submitTime=" + submitTime + ", videoSubject=" + videoSubject + ", customer=" + customer
				+ ", videoPayRecords=" + videoPayRecords + "]";
	}

}