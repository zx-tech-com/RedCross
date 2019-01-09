package com.zx.redcross.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class ExamPayRecord implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private BigDecimal amount;
	private String payMethod;
	private byte status;
	private ExamOrder examOrder;
	private Customer customer;

	public ExamPayRecord() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getAmount() {
		return this.amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getPayMethod() {
		return this.payMethod;
	}

	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}

	public byte getStatus() {
		return this.status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	public ExamOrder getExamOrder() {
		return this.examOrder;
	}

	public void setExamOrder(ExamOrder examOrder) {
		this.examOrder = examOrder;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "ExamPayRecord [id=" + id + ", amount=" + amount + ", payMethod=" + payMethod + ", status=" + status
				+ ", examOrder=" + examOrder + ", customer=" + customer + "]";
	}

	
}