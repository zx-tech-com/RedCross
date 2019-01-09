package com.zx.redcross.entity;

import java.io.Serializable;

public class Concern implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String concernTime;
	private Customer aCustomer;//关注人
	private Customer pCustomer;//被关注者

	public Concern() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getConcernTime() {
		return this.concernTime;
	}

	public void setConcernTime(String concernTime) {
		this.concernTime = concernTime;
	}

	public Customer getaCustomer() {
		return aCustomer;
	}

	public void setaCustomer(Customer aCustomer) {
		this.aCustomer = aCustomer;
	}

	public Customer getpCustomer() {
		return pCustomer;
	}

	public void setpCustomer(Customer pCustomer) {
		this.pCustomer = pCustomer;
	}

	@Override
	public String toString() {
		return "Concern [id=" + id + ", concernTime=" + concernTime + ", aCustomer=" + aCustomer + ", pCustomer="
				+ pCustomer + "]";
	}
	
}