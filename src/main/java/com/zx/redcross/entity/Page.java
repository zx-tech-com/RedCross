package com.zx.redcross.entity;

import com.zx.redcross.tool.Constant;

import io.swagger.annotations.ApiModelProperty;

/**
 * 分页管理工具类
 * @author Daryl
 */
public class Page {

	@ApiModelProperty(value = "每页显示个数", required = false)
	private Integer pageSize = Constant.APP_PAGE_SIZE;
	@ApiModelProperty(value = "当前页数", required = true)
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
		this.start = (pageNo-1) * pageSize;
		return this.start;
	}
	
	
}
