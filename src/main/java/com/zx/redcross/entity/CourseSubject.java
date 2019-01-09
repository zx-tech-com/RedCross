package com.zx.redcross.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;



public class CourseSubject implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private String description;
	private String period;
	private BigDecimal price;
	private String sname;
	private String thumbnailUrl;
	private List<Course> courses;
	private List<ExamOrder> examOrders;
	

	public CourseSubject() {
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

	public String getPeriod() {
		return this.period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getSname() {
		return this.sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getThumbnailUrl() {
		return this.thumbnailUrl;
	}

	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}

	public List<Course> getCourses() {
		return this.courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	public Course addCours(Course cours) {
		getCourses().add(cours);
		cours.setCourseSubject(this);

		return cours;
	}

	public Course removeCours(Course cours) {
		getCourses().remove(cours);
		cours.setCourseSubject(null);

		return cours;
	}

	public List<ExamOrder> getExamOrders() {
		return this.examOrders;
	}

	public void setExamOrders(List<ExamOrder> examOrders) {
		this.examOrders = examOrders;
	}

	public ExamOrder addExamOrder(ExamOrder examOrder) {
		getExamOrders().add(examOrder);
		examOrder.setCourseSubject(this);

		return examOrder;
	}

	public ExamOrder removeExamOrder(ExamOrder examOrder) {
		getExamOrders().remove(examOrder);
		examOrder.setCourseSubject(null);

		return examOrder;
	}

	@Override
	public String toString() {
		return "CourseSubject [id=" + id + ", description=" + description + ", period=" + period + ", price=" + price
				+ ", sname=" + sname + ", thumbnailUrl=" + thumbnailUrl + ", courses=" + courses + ", examOrders="
				+ examOrders + "]";
	}

	
	
}