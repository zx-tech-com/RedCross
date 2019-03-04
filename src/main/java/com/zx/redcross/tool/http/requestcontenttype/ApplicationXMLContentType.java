package com.zx.redcross.tool.http.requestcontenttype;

import com.zx.redcross.tool.http.contenttype.RequestContentType;

/**
 * xml格式的字符串
 * @author Daryl
 */
public class ApplicationXMLContentType implements RequestContentType{

	private String params;
	
	/**
	 * @param params xml格式的字符串
	 */
	public ApplicationXMLContentType(String params) {
		this.params = params;
	}

	public ApplicationXMLContentType() {

	}
	
	@Override
	public String assembleParams() {
		return params;
	}

	@Override
	public String getContentType() {
		return "application/xml";
	}
}
