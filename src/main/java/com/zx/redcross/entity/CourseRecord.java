package com.zx.redcross.entity;

public class CourseRecord {
	
	private Integer courseRecordId;
	private Integer customerId;
	private Integer courseSubjectId;
	private Integer courseId; 
	private String  recordTime;
	private String 	currentMinute;//当前记录观看到那一分钟，哪一秒
	private Course course;
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
	public Integer getCourseSubjectId() {
		return courseSubjectId;
	}
	public void setCourseSubjectId(Integer courseSubjectId) {
		this.courseSubjectId = courseSubjectId;
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
	public String getCurrentMinute() {
		return currentMinute;
	}
	public void setCurrentMinute(String currentMinute) {
		this.currentMinute = currentMinute;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	@Override
	public String toString() {
		return "CourseRecord [courseRecordId=" + courseRecordId + ", customerId=" + customerId + ", courseSubjectId="
				+ courseSubjectId + ", courseId=" + courseId + ", recordTime=" + recordTime + ", currentMinute="
				+ currentMinute + ", course=" + course + "]";
	}

	
	
	
}
