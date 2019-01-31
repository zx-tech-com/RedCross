package com.zx.redcross.entity;

public class Admin {

	private Integer id; 
	private String username;
	private String passwd;
	private String ctime;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String password) {
		this.passwd = password;
	}
	public String getCtime() {
		return ctime;
	}
	public void setCtime(String ctime) {
		this.ctime = ctime;
	}
	
	@Override
	public String toString() {
		return "Admin [id=" + id + ", username=" + username + ", passwd=" + passwd + ", ctime=" + ctime + "]";
	}
	
}
