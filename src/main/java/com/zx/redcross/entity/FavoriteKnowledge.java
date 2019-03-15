package com.zx.redcross.entity;

import java.io.Serializable;

public class FavoriteKnowledge implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String favoriteTime;
	private Customer customer;
	private Knowledge knowledge;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFavoriteTime() {
		return favoriteTime;
	}
	public void setFavoriteTime(String favoriteTime) {
		this.favoriteTime = favoriteTime;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Knowledge getKnowledge() {
		return knowledge;
	}
	public void setKnowledge(Knowledge knowledge) {
		this.knowledge = knowledge;
	}
	
	@Override
	public String toString() {
		return "FavoriteKnowledge [id=" + id + ", favoriteTime=" + favoriteTime + ", customer=" + customer
				+ ", knowledge=" + knowledge + "]";
	}
	
	
}
