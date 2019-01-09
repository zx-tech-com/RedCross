package com.zx.redcross.entity;

import java.io.Serializable;

import com.zx.redcross.tool.FileUtils;

public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String avatarUrl;
	private String detailAddress;
	private String gender;
	private Boolean isVisitor;
	private String nickname;
	private String password;
	private String registerTime;
	private String tel;
	private OsDistrict osDistrict;


	public Customer() {
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getAvatarUrl() {
		return FileUtils.getFullUrl(this.avatarUrl);
	}


	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}


	public String getDetailAddress() {
		return detailAddress;
	}


	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public Boolean getIsVisitor() {
		return isVisitor;
	}


	public void setIsVisitor(Boolean isVisitor) {
		this.isVisitor = isVisitor;
	}


	public String getNickname() {
		return nickname;
	}


	public void setNickname(String nickname) {
		this.nickname = nickname;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getRegisterTime() {
		return registerTime;
	}


	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}


	public String getTel() {
		return tel;
	}


	public void setTel(String tel) {
		this.tel = tel;
	}


	public OsDistrict getOsDistrict() {
		return osDistrict;
	}


	public void setOsDistrict(OsDistrict osDistrict) {
		this.osDistrict = osDistrict;
	}


	@Override
	public String toString() {
		return "Customer [id=" + id + ", avatarUrl=" + avatarUrl + ", detailAddress=" + detailAddress + ", gender="
				+ gender + ", isVisitor=" + isVisitor + ", nickname=" + nickname + ", password=" + password
				+ ", registerTime=" + registerTime + ", tel=" + tel + ", osDistrict=" + osDistrict + "]";
	}

	
}