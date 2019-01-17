package com.zx.redcross.entity;

public class CourseRecord {
	private Integer courseRecordId;
	private Integer customerId;
	private Integer courseSubjectId;
	private Integer courseId; 
	private String  recordTime;
	
	//增加属性
	private Course cosrse;
	
	public Course getCosrse() {
		return cosrse;
	}
	public void setCosrse(Course cosrse) {
		this.cosrse = cosrse;
	}
	public Integer getCourseRecordId() {
		return courseRecordId;
	}
	public void setCourseRecordId(Integer courseRecordId) {
		this.courseRecordId = courseRecordId;
	}
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public Integer getCourseId() {
		return courseId;
	}
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}
	public String getRecordTime() {
		return recordTime;
	}
	public void setRecordTime(String recordTime) {
		this.recordTime = recordTime;
	}
	public Integer getCourseSubjectId() {
		return courseSubjectId;
	}
	public void setCourseSubjectId(Integer courseSubjectId) {
		this.courseSubjectId = courseSubjectId;
	}
	@Override
	public String toString() {
		return "CourseRecord [courseRecordId=" + courseRecordId + ", customerId=" + customerId + ", courseSubjectId="
				+ courseSubjectId + ", courseId=" + courseId + ", recordTime=" + recordTime + ", cosrse=" + cosrse
				+ "]";
	}
	
	
}
