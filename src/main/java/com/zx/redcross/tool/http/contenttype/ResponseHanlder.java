package com.zx.redcross.tool.http.contenttype;

import java.io.IOException;
import java.net.HttpURLConnection;

import com.zx.redcross.tool.BusinessExceptionUtils;

public abstract class ResponseHanlder {

	protected HttpURLConnection connection;
	
	private Integer code = -1;
	private Object content = null;
	private String contentType = null;
	
	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public void fetchStatusCode() {
		Integer code = -1;
		try {
			code = this.connection.getResponseCode();
		} catch (IOException e) {
			BusinessExceptionUtils.throwNewBusinessException("获取statusCode失败"+e.getMessage());
		}
		this.setCode(code);
	}
	
	public void fetchContentType() {
		String conentType = null;
		try {
			conentType = this.connection.getContentType();
		}catch(Exception e) {
			BusinessExceptionUtils.throwNewBusinessException("获取contentType失败"+e.getMessage());
		}
		this.setContentType(conentType);
	}
	
	
	public abstract void fetchContent();
	
	public void fetchInfo() {
		this.fetchStatusCode();
		this.fetchContentType();
		this.fetchContent();
	}
	
	
	public Integer getCode() {
		return code;
	}
	public Object getContent() {
		return content;
	}
	protected void setCode(Integer code) {
		this.code = code;
	}
	protected void setContent(Object content) {
		this.content = content;
	}
	
	public void setConnection(HttpURLConnection connection) {
		this.connection = connection;
	}
	
}
