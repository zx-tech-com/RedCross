package com.zx.redcross.entity;

import java.io.Serializable;

public class Address implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private Integer districtId;
	private String customerId;
	private String detailAddress;
	private Integer flag;
	private String description;
	private String nickName;
	private String tel;
	
	private OsDistrict osDistrict;
	
	
	public OsDistrict getOsDistrict() {
		return osDistrict;
	}
	public void setOsDistrict(OsDistrict osDistrict) {
		this.osDistrict = osDistrict;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getDistrictId() {
		return districtId;
	}
	public void setDistrictId(Integer districtId) {
		this.districtId = districtId;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getDetailAddress() {
		return detailAddress;
	}
	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	@Override
	public String toString() {
		return "Address [id=" + id + ", districtId=" + districtId + ", customerId=" + customerId + ", detailAddress="
				+ detailAddress + ", flag=" + flag + ", description=" + description + ", nickName=" + nickName
				+ ", tel=" + tel + ", osDistrict=" + osDistrict + "]";
	}
	
	
	
	
	
	
	
	

}
