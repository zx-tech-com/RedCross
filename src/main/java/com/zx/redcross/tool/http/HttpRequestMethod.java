package com.zx.redcross.tool.http;

public enum HttpRequestMethod {
	
	GET("GET"),POST("POST");
	
	private final String value;
	
	private HttpRequestMethod(String value) {
		this.value = value;
	}
	
	public String value() {
		return this.value;
	}
	
	public String getValue(){
		return this.value;
	}
}
