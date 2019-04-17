package com.zx.redcross.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;



public class CourseSubject implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String description;
	private String period;
	private BigDecimal price;
	private String sname;
	private String thumbnailUrl;
	private String certificateUrl;
	private String statusSub;
	private Course course;	
	private List<CourseSubjectPrice> prices;
	private List<Course> courses;
	private List<ExamOrder> examOrders;
	
	
	
	public String getStatusSub() {
		return statusSub;
	}
	public void setStatusSub(String statusSub) {
		this.statusSub = statusSub;
	}
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
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getThumbnailUrl() {
		return thumbnailUrl;
	}
	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}
	public String getCertificateUrl() {
		return certificateUrl;
	}
	public void setCertificateUrl(String certificateUrl) {
		this.certificateUrl = certificateUrl;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public List<CourseSubjectPrice> getPrices() {
		return prices;
	}
	public void setPrices(List<CourseSubjectPrice> prices) {
		this.prices = prices;
	}
	public List<Course> getCourses() {
		return courses;
	}
	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}
	public List<ExamOrder> getExamOrders() {
		return examOrders;
	}
	public void setExamOrders(List<ExamOrder> examOrders) {
		this.examOrders = examOrders;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "CourseSubject [id=" + id + ", description=" + description + ", period=" + period + ", price=" + price
				+ ", sname=" + sname + ", thumbnailUrl=" + thumbnailUrl + ", certificateUrl=" + certificateUrl
				+ ", statusSub=" + statusSub + ", course=" + course + ", prices=" + prices + ", courses=" + courses
				+ ", examOrders=" + examOrders + "]";
	}
	
	
	
}