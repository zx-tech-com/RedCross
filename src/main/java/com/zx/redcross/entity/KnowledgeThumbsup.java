package com.zx.redcross.entity;

import java.io.Serializable;

public class KnowledgeThumbsup implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private Knowledge knowledge;
	private Customer customer;
	
	public KnowledgeThumbsup() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Knowledge getKnowledge() {
		return this.knowledge;
	}

	public void setKnowledge(Knowledge knowledge) {
		this.knowledge = knowledge;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "KnowledgeThumbsup [id=" + id + ", knowledge=" + knowledge + ", customer=" + customer + "]";
	}

}