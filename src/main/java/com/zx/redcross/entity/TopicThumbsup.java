package com.zx.redcross.entity;

import java.io.Serializable;

public class TopicThumbsup implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Topic topic;
	private Customer customer;
	
	public TopicThumbsup() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "TopicThumbsup [id=" + id + ", topic=" + topic + ", customer=" + customer + "]";
	}

}