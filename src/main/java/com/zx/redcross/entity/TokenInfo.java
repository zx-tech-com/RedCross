package com.zx.redcross.entity;

import java.util.Calendar;
import java.util.Map;

import com.zx.redcross.tool.Constant;
import com.zx.redcross.tool.MapUtils;

/**
 * token的实体类
 * @author Daryl
 *
 */
public class TokenInfo {
	
	private long generateTime;//生成时间
	private long validTime;//有效期
	private Integer customerId;//token对应的用户id

	public TokenInfo() {
		generateTime = Calendar.getInstance().getTimeInMillis();
		this.refreshValidTime();
	}
	
	
	
	public TokenInfo(Integer customerId) {
		super();
		this.generateTime = Calendar.getInstance().getTimeInMillis();
		this.customerId = customerId;
		this.refreshValidTime();
	}



	public long getGenerateTime() {
		return generateTime;
	}
	public long getValidTime() {
		return validTime;
	}
	public void setValidTime(long validTime) {
		this.validTime = validTime;
	}
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	
	/**
	 * 每当用户访问时,均会调用该函数,重置token的有效期为NUMBER_8
	 */
	public void refreshValidTime() {
		Calendar date = Calendar.getInstance();
		date.add(Constant.HOUR_FIELD, Constant.NUMBER_8);
		this.setValidTime(date.getTime().getTime());
	}
	
	/**
	 * token是否已经过期
	 * @return true 过期，false未过期
	 */
	public boolean isExpired() {
		return this.validTime < Calendar.getInstance().getTime().getTime();
	}
	
	@Override
	public String toString() {
		return "TokenInfo [generateTime=" + generateTime + ", validTime=" + validTime
				+ ", customerId=" + customerId + "]";
	}
	
	public  Map<String,Object> toMap(){
		Map<String, Object> map = MapUtils.getHashMapInstance();
		
		map.put(Constant.CUSTOMERID, customerId);
		map.put(Constant.CUSTOMERID, generateTime);
		
		return map;
	}
	
}
