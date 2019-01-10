package com.zx.redcross.entity;

import com.zx.redcross.tool.Constant;

/**
 * 分页管理工具类
 * @author Daryl
 */
public class Page {

	private Integer pageSize = Constant.APP_PAGE_SIZE;
	private Integer pageNo=1;
	private Integer start;
	
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getPageNo() {
		return pageNo;
	}
	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;	
	}
	public Integer getStart() {
		return this.start = (pageNo-1) * pageSize;
	}
	
	
}
