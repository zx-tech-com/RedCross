package com.zx.redcross.entity;

import java.io.Serializable;

import com.zx.redcross.tool.FileUtils;


public class Img implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String description;
	private Integer foreignId;
	private String imgType;
	private String iname;
	private byte iindex;
	private String imgUrl;
	
	public Img() {
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

	public Integer getForeignId() {
		return this.foreignId;
	}

	public void setForeignId(Integer foreignId) {
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
	
	public byte getIindex() {
		return iindex;
	}
	public void setIindex(byte iindex) {
		this.iindex = iindex;
	}
	public String getImgUrl() {
		return FileUtils.getFullUrl(this.imgUrl);
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	@Override
	public String toString() {
		return "Img [id=" + id + ", description=" + description + ", foreignId=" + foreignId + ", imgType=" + imgType
				+ ", iname=" + iname + ", iindex=" + iindex + ", imgUrl=" + imgUrl + "]";
	}

	
}