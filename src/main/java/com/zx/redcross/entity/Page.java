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
	
	private String query = null;
	@SuppressWarnings("unused")
	private String finalQuery = null;
	
	private Integer pageCount;
	private Integer totalPage;
	
	public Integer getTotalPage() {
		if(pageCount%pageSize==0){
            totalPage = pageCount/pageSize;
		}else{
			totalPage = pageCount/pageSize+1;
		}
		return totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	public Integer getPageCount() {
		return pageCount;
	}
	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}
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
	
	public String getQuery() {
		return query;
	}
	
	public void setQuery(String query) {
		this.query = query;
	}
	
	public String getFinalQuery() {
		if(this.query == null || this.query.length() == 0)
			return null;
		return "%" + this.query + "%";
	}
	
	
}
