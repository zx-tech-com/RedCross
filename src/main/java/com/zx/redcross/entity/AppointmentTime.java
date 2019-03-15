package com.zx.redcross.entity;

public class AppointmentTime {
	
	private Integer id;
	private String period;
	private AppointmentPattern pattern;
	private AppointmentDate date;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	public AppointmentPattern getPattern() {
		return pattern;
	}
	public void setPattern(AppointmentPattern pattern) {
		this.pattern = pattern;
	}
	public AppointmentDate getDate() {
		return date;
	}
	public void setDate(AppointmentDate date) {
		this.date = date;
	}
	
	@Override
	public String toString() {
		return "AppointmentTime [id=" + id + ", period=" + period + ", pattern=" + pattern + ", date=" + date + "]";
	}
	
}
