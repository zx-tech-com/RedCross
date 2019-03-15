package com.zx.redcross.entity;

import java.io.Serializable;

public class FavoriteNews implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer  id;
	private String   favoriteTime;
	private Customer customer;
	private News     news;
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
	public News getNews() {
		return news;
	}
	public void setNews(News news) {
		this.news = news;
	}
	@Override
	public String toString() {
		return "FavoriteNews [id=" + id + ", favoriteTime=" + favoriteTime + ", customer=" + customer + ", news=" + news
				+ "]";
	}
	

}
