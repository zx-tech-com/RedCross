package com.zx.redcross.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class GroupPayRecord implements Serializable{

	
	private static final long serialVersionUID = 1L;
	private Integer id;
	private BigDecimal amount;
	private String payMethod;
	private String orderNumber;
	private byte status;
	private GroupOrder groupOrder;
	private Customer customer;

	public GroupPayRecord() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getPayMethod() {
		return payMethod;
	}

	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public byte getStatus() {
		return status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	public GroupOrder getGroupOrder() {
		return groupOrder;
	}

	public void setGroupOrder(GroupOrder groupOrder) {
		this.groupOrder = groupOrder;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "GroupPayRecord [id=" + id + ", amount=" + amount + ", payMethod=" + payMethod + ", orderNumber="
				+ orderNumber + ", status=" + status + ", groupOrder=" + groupOrder + ", customer=" + customer + "]";
	}

}
