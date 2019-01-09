package com.zx.redcross.entity;

import java.io.Serializable;


public class Favorite implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String favoriteTime;
	private Customer customer;
	private Topic topic;

	public Favorite() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFavoriteTime() {
		return this.favoriteTime;
	}

	public void setFavoriteTime(String favoriteTime) {
		this.favoriteTime = favoriteTime;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Topic getTopic() {
		return this.topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}

	@Override
	public String toString() {
		return "Favorite [id=" + id + ", favoriteTime=" + favoriteTime + ", customer=" + customer + ", topic=" + topic
				+ "]";
	}

	
}