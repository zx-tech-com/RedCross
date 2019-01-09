package com.zx.redcross.entity;

import java.io.Serializable;
import java.util.List;


public class OsDistrict implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private byte level;
	private String name;
	private int upid;
	private List<Customer> customers;
	private List<ExamOrder> examOrders;

	public OsDistrict() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public byte getLevel() {
		return this.level;
	}

	public void setLevel(byte level) {
		this.level = level;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getUpid() {
		return this.upid;
	}

	public void setUpid(int upid) {
		this.upid = upid;
	}

	public List<Customer> getCustomers() {
		return this.customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	public Customer addCustomer(Customer customer) {
		getCustomers().add(customer);
		customer.setOsDistrict(this);

		return customer;
	}

	public Customer removeCustomer(Customer customer) {
		getCustomers().remove(customer);
		customer.setOsDistrict(null);

		return customer;
	}

	public List<ExamOrder> getExamOrders() {
		return this.examOrders;
	}

	public void setExamOrders(List<ExamOrder> examOrders) {
		this.examOrders = examOrders;
	}

	public ExamOrder addExamOrder(ExamOrder examOrder) {
		getExamOrders().add(examOrder);
		examOrder.setOsDistrict(this);

		return examOrder;
	}

	public ExamOrder removeExamOrder(ExamOrder examOrder) {
		getExamOrders().remove(examOrder);
		examOrder.setOsDistrict(null);

		return examOrder;
	}

	@Override
	public String toString() {
		return "OsDistrict [id=" + id + ", level=" + level + ", name=" + name + ", upid=" + upid + ", customers="
				+ customers + ", examOrders=" + examOrders + "]";
	}

}