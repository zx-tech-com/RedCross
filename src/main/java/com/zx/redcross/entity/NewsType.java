package com.zx.redcross.entity;

import java.io.Serializable;

public class NewsType implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String description;
	private String kname;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getKname() {
		return kname;
	}
	public void setKname(String kname) {
		this.kname = kname;
	}
	@Override
	public String toString() {
		return "NewsType [id=" + id + ", description=" + description + ", kname=" + kname + "]";
	}
	
	
}
