package com.zx.redcross.entity;

import java.io.Serializable;
import java.util.List;

public class GroupOrder implements Serializable{

	
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Customer customer;
	private CourseSubject courseSubject;
	
	//相比较ExamOrder多了这一个字段
	private CourseSubjectPrice courseSubjectPrice;
	
	private String orderNumber;
	private String payMethod;
	private String coment;
	private String submitTime;
	private byte status;
	private String invoice;
	private String tel;
	private String realName;
	private String method;
	private OsDistrict osDistrict;
	private String detailAddress;
	
	private List<GroupPayRecord> groupPayRecords;
	
	
	//逻辑字段
	private String submitTimeMin;


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Customer getCustomer() {
		return customer;
	}


	public void setCustomer(Customer customer) {
		this.customer = customer;
	}


	public CourseSubject getCourseSubject() {
		return courseSubject;
	}


	public void setCourseSubject(CourseSubject courseSubject) {
		this.courseSubject = courseSubject;
	}


	public CourseSubjectPrice getCourseSubjectPrice() {
		return courseSubjectPrice;
	}


	public void setCourseSubjectPrice(CourseSubjectPrice courseSubjectPrice) {
		this.courseSubjectPrice = courseSubjectPrice;
	}


	public String getOrderNumber() {
		return orderNumber;
	}


	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}


	public String getPayMethod() {
		return payMethod;
	}


	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}


	public String getComent() {
		return coment;
	}


	public void setComent(String coment) {
		this.coment = coment;
	}


	public String getSubmitTime() {
		return submitTime;
	}


	public void setSubmitTime(String submitTime) {
		this.submitTime = submitTime;
	}


	public byte getStatus() {
		return status;
	}


	public void setStatus(byte status) {
		this.status = status;
	}


	public String getInvoice() {
		return invoice;
	}


	public void setInvoice(String invoice) {
		this.invoice = invoice;
	}


	public String getTel() {
		return tel;
	}


	public void setTel(String tel) {
		this.tel = tel;
	}


	public String getRealName() {
		return realName;
	}


	public void setRealName(String realName) {
		this.realName = realName;
	}


	public String getMethod() {
		return method;
	}


	public void setMethod(String method) {
		this.method = method;
	}


	public OsDistrict getOsDistrict() {
		return osDistrict;
	}


	public void setOsDistrict(OsDistrict osDistrict) {
		this.osDistrict = osDistrict;
	}


	public String getDetailAddress() {
		return detailAddress;
	}


	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}


	public List<GroupPayRecord> getGroupPayRecords() {
		return groupPayRecords;
	}


	public void setGroupPayRecords(List<GroupPayRecord> groupPayRecords) {
		this.groupPayRecords = groupPayRecords;
	}


	public String getSubmitTimeMin() {
		return submitTimeMin;
	}


	public void setSubmitTimeMin(String submitTimeMin) {
		this.submitTimeMin = submitTimeMin;
	}


	@Override
	public String toString() {
		return "GroupOrder [id=" + id + ", customer=" + customer + ", courseSubject=" + courseSubject
				+ ", courseSubjectPrice=" + courseSubjectPrice + ", orderNumber=" + orderNumber + ", payMethod="
				+ payMethod + ", coment=" + coment + ", submitTime=" + submitTime + ", status=" + status + ", invoice="
				+ invoice + ", tel=" + tel + ", realName=" + realName + ", method=" + method + ", osDistrict="
				+ osDistrict + ", detailAddress=" + detailAddress + ", groupPayRecords=" + groupPayRecords
				+ ", submitTimeMin=" + submitTimeMin + "]";
	}
	
}
