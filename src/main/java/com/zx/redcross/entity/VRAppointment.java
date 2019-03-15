package com.zx.redcross.entity;

public class VRAppointment {

	private Integer id;				//后台管理查询时，该字段会作为AppointmentDate id 来使用 IVRAppointmentMapper.getVRAppointmentCounts和IVRAppointmentMapper.listVRAppointment
	private String tel;
	private String realname;
	private String appointmentDate;	//后台管理查询时，该字段会作为起始时间字段来使用 IVRAppointmentMapper.getVRAppointmentCounts和IVRAppointmentMapper.listVRAppointment
	private String submitTime;		//后台管理查询时，该字段会作为结束时间字段来使用IVRAppointmentMapper.getVRAppointmentCounts和IVRAppointmentMapper.listVRAppointment
	private String coment;
	private String flag;
	
	private AppointmentDate date;
	private Customer customer;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getAppointmentDate() {
		return appointmentDate;
	}
	public void setAppointmentDate(String appointmentDate) {
		this.appointmentDate = appointmentDate;
	}
	public String getSubmitTime() {
		return submitTime;
	}
	public void setSubmitTime(String submitTime) {
		this.submitTime = submitTime;
	}
	public String getComent() {
		return coment;
	}
	public void setComent(String coment) {
		this.coment = coment;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public AppointmentDate getDate() {
		return date;
	}
	public void setDate(AppointmentDate date) {
		this.date = date;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	@Override
	public String toString() {
		return "VRAppointment [id=" + id + ", tel=" + tel + ", realname=" + realname + ", appointmentDate="
				+ appointmentDate + ", submitTime=" + submitTime + ", coment=" + coment + ", flag=" + flag + ", date="
				+ date + ", customer=" + customer + "]";
	}

}
