package com.zx.redcross.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class VideoPayRecord implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Integer id;
	private BigDecimal amount;
	private String payMethod;
	private byte status;
	private VideoBuyRecord videoBuyRecord;

	public VideoPayRecord() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getAmount() {
		return this.amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getPayMethod() {
		return this.payMethod;
	}

	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}

	public byte getStatus() {
		return this.status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	public VideoBuyRecord getVideoBuyRecord() {
		return this.videoBuyRecord;
	}

	public void setVideoBuyRecord(VideoBuyRecord videoBuyRecord) {
		this.videoBuyRecord = videoBuyRecord;
	}

	@Override
	public String toString() {
		return "VideoPayRecord [id=" + id + ", amount=" + amount + ", payMethod=" + payMethod + ", status=" + status
				+ ", videoBuyRecord=" + videoBuyRecord + "]";
	}

}