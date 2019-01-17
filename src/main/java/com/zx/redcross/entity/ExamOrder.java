package com.zx.redcross.entity;

import java.io.Serializable;
import java.util.List;


public class ExamOrder implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String coment;
	private String detailAddress;
	private String tel;
	private String realName;
	private String method;
	private String orderNumber;
	private String payMethod;
	private byte status;
	private String submitTime;
	private CourseSubject courseSubject;
	private Customer customer;
	private OsDistrict osDistrict;
	private List<ExamPayRecord> examPayRecords;

	public ExamOrder() {
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

	public String getDetailAddress() {
		return this.detailAddress;
	}

	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}

	public String getMethod() {
		return this.method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getOrderNumber() {
		return this.orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getPayMethod() {
		return this.payMethod;
	}

	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}



	public byte getStatus() {
		return status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	public String getSubmitTime() {
		return submitTime;
	}

	public void setSubmitTime(String submitTime) {
		this.submitTime = submitTime;
	}

	public CourseSubject getCourseSubject() {
		return this.courseSubject;
	}

	public void setCourseSubject(CourseSubject courseSubject) {
		this.courseSubject = courseSubject;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public OsDistrict getOsDistrict() {
		return this.osDistrict;
	}

	public void setOsDistrict(OsDistrict osDistrict) {
		this.osDistrict = osDistrict;
	}

	public List<ExamPayRecord> getExamPayRecords() {
		return this.examPayRecords;
	}

	public void setExamPayRecords(List<ExamPayRecord> examPayRecords) {
		this.examPayRecords = examPayRecords;
	}

	public ExamPayRecord addExamPayRecord(ExamPayRecord examPayRecord) {
		getExamPayRecords().add(examPayRecord);
		examPayRecord.setExamOrder(this);

		return examPayRecord;
	}

	public ExamPayRecord removeExamPayRecord(ExamPayRecord examPayRecord) {
		getExamPayRecords().remove(examPayRecord);
		examPayRecord.setExamOrder(null);

		return examPayRecord;
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

	@Override
	public String toString() {
		return "ExamOrder [id=" + id + ", coment=" + coment + ", detailAddress=" + detailAddress + ", tel=" + tel
				+ ", realName=" + realName + ", method=" + method + ", orderNumber=" + orderNumber + ", payMethod="
				+ payMethod + ", status=" + status + ", submitTime=" + submitTime + ", courseSubject=" + courseSubject
				+ ", customer=" + customer + ", osDistrict=" + osDistrict + ", examPayRecords=" + examPayRecords + "]";
	}

	
	
	

	

}