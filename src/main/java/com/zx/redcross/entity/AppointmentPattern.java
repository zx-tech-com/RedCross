package com.zx.redcross.entity;

import java.util.List;

public class AppointmentPattern {

	private Integer id;
	private boolean onUse;
	private String pattern;
	private String description;
	private List<AppointmentDate> appointmentDate;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public boolean isOnUse() {
		return onUse;
	}
	public void setOnUse(boolean onUse) {
		this.onUse = onUse;
	}
	public String getPattern() {
		return pattern;
	}
	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<AppointmentDate> getAppointmentDate() {
		return appointmentDate;
	}
	public void setAppointmentDate(List<AppointmentDate> appointmentDate) {
		this.appointmentDate = appointmentDate;
	}
	@Override
	public String toString() {
		return "AppointmentPattern [id=" + id + ", onUse=" + onUse + ", pattern=" + pattern + ", description="
				+ description + ", appointmentDate=" + appointmentDate + "]";
	}
	
}
