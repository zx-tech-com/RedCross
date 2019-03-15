package com.zx.redcross.entity;

import java.math.BigDecimal;

public class CourseSubjectPrice {

	private Integer id;
	private Integer minThresHold;
	private Integer maxThresHold;
	private BigDecimal price;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getMinThresHold() {
		return minThresHold;
	}
	public void setMinThresHold(Integer minThresHold) {
		this.minThresHold = minThresHold;
	}
	public Integer getMaxThresHold() {
		return maxThresHold;
	}
	public void setMaxThresHold(Integer maxThresHold) {
		this.maxThresHold = maxThresHold;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	@Override
	public String toString() {
		return "CourseSubjectPrice [id=" + id + ", minThresHold=" + minThresHold + ", maxThresHold=" + maxThresHold
				+ ", price=" + price + "]";
	}
	
	
}
