package com.zx.redcross.entity;

public class AppointmentDate {
	
	private Integer id ;
	private String dvalue;
	private AppointmentPattern pattern;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDvalue() {
		return dvalue;
	}
	public void setDvalue(String dvalue) {
		this.dvalue = dvalue;
	}
	public AppointmentPattern getPattern() {
		return pattern;
	}
	public void setPattern(AppointmentPattern pattern) {
		this.pattern = pattern;
	}
	@Override
	public String toString() {
		return "AppointmentDate [id=" + id + ", dvalue=" + dvalue + ", pattern=" + pattern + "]";
	}
	
	
	
}
