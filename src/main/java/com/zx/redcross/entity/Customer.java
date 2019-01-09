package com.zx.redcross.entity;

import java.io.Serializable;

public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private String avatarUrl;
	private String detailAddress;
	private String gender;
	private int isVisitor;
	private String nickname;
	private String password;
	private String registerTime;
	private String tel;
	private OsDistrict osDistrict;


	public Customer() {
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getAvatarUrl() {
		return avatarUrl;
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


	public int getIsVisitor() {
		return isVisitor;
	}


	public void setIsVisitor(int isVisitor) {
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