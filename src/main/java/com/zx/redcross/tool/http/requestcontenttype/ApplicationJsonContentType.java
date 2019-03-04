package com.zx.redcross.tool.http.requestcontenttype;

import com.alibaba.fastjson.JSON;
import com.zx.redcross.tool.http.contenttype.RequestContentType;

public class ApplicationJsonContentType implements RequestContentType{

	private Object params;
	public ApplicationJsonContentType(Object params) {
		this.params = params;
	}

	public ApplicationJsonContentType() {

	}
	
	@Override
	public String assembleParams() {
		return JSON.toJSONString(params);
	}

	@Override
	public String getContentType() {
		return "application/json";
	}
}
