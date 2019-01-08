package com.zx.redcross.entity;

import java.io.Serializable;

public class Employee implements Serializable{
	
	private static final long serialVersionUID = 4701491435392495023L;
	
	private int id;
	private String name;
	private float pay;
	private int dept;
	
	public Employee() {
		
	}
	
	public Employee(int id, String name, float pay,int dept) {
		super();
		this.id = id;
		this.name = name;
		this.pay = pay;
		this.dept = dept;
	}
	
	@Override
	public String toString() {
		return "[id=" + id + ", name=" + name + ", pay=" + pay + ", dept=" + dept + "]";
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getPay() {
		return pay;
	}
	public void setPay(float pay) {
		this.pay = pay;
	}
	public int getDept() {
		return dept;
	}
	public void setDept(int dept) {
		this.dept = dept;
	}
	
}
