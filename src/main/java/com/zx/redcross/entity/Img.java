package com.zx.redcross.entity;

import java.io.Serializable;


public class Img implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String description;
	private int foreignId;
	private String imgType;
	private String iname;
	
	public Img() {
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

	public int getForeignId() {
		return this.foreignId;
	}

	public void setForeignId(int foreignId) {
		this.foreignId = foreignId;
	}

	public String getImgType() {
		return this.imgType;
	}

	public void setImgType(String imgType) {
		this.imgType = imgType;
	}

	public String getIname() {
		return this.iname;
	}

	public void setIname(String iname) {
		this.iname = iname;
	}
	@Override
	public String toString() {
		return "Img [id=" + id + ", description=" + description + ", foreignId=" + foreignId + ", imgType=" + imgType
				+ ", iname=" + iname + "]";
	}

}