package com.zx.redcross.tool.http;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Map;
import java.util.Map.Entry;

import com.zx.redcross.tool.BusinessExceptionUtils;
import com.zx.redcross.tool.MapUtils;
import com.zx.redcross.tool.http.contenttype.RequestContentType;
import com.zx.redcross.tool.http.contenttype.ResponseHanlder;
import com.zx.redcross.tool.http.responsehandler.DefaultResponseHandler;

/**
 * 1.实例化对象(实例化过程中会自动openConnection,打开连接)<br>
 * 2.设置请求头,请求方法<br>
 * 3.发起连接<br>
 * 4.获取返回数据<br>
 * @author Daryl<br>
 */
public class HttpRequest {
	
	private URL url;
	private HttpURLConnection connection;
	private HttpRequestMethod method = HttpRequestMethod.GET;
	private Map<String,Object> headers = MapUtils.getHashMapInstance();
	//这里使用了策略模式 ContentType的对象存储Content-Type类型和需要发送的请求参数params
	private RequestContentType contentTypeStrategy;
	private ResponseHanlder responseHandler;
	
	//==================添加请求头信息====================
	public void addHeader(String key,Object value) {
		this.headers.put(key, value);
	}
	
	public void addHeader(String key,String value) {
		addHeader(key,(Object)value);
	}
	
	public void addAllHeaders(Map<String,Object> headers) {
		if(null != headers) {
			for(Entry<String,Object> entry : headers.entrySet()) {
				this.addHeader(entry.getKey(), entry.getValue());
			}
		}
	}
	
	private void setRequestMethod(){
		try {
			this.connection.setRequestMethod(this.method.getValue());
		} catch (ProtocolException e) {
			BusinessExceptionUtils.throwNewBusinessException("设置RequestMethod参数失败" + e.getMessage());
		}
	}
	
	private void setRequestHeaders() {
		if(!this.headers.isEmpty()) {
			for(Entry<String,Object> entry : this.headers.entrySet()) {
				this.connection.setRequestProperty(entry.getKey(), entry.getValue().toString());
			}
		}
		if(null != this.contentTypeStrategy) {
			this.connection.setRequestProperty("Content-Type", this.contentTypeStrategy.getContentType());
		}
	}
	
	private void connect(){
		try {
			this.connection.connect();
		} catch (IOException e) {
			BusinessExceptionUtils.throwNewBusinessException("connect连接失败" + e.getMessage());
		}
	}
	
	
	private void writeParams() {//看一下是在connect之后调用,还是connect之前调用   结果:在connect之前写入参数
		if(null == this.contentTypeStrategy)//没有参数需要传递
			return;
		this.connection.setDoOutput(true);
		try(OutputStream outStream = this.connection.getOutputStream();){//try with resouce会自动关闭流
			String params = this.contentTypeStrategy.assembleParams();
			System.err.println(params);
			outStream.write(params.getBytes());
		}catch (IOException e) {
			BusinessExceptionUtils.throwNewBusinessException("写入参数失败" + e.getMessage());
		}
	}
	
	private void handleResponse() {
		if(null == this.responseHandler) {//
			this.responseHandler = new DefaultResponseHandler();
		}
		this.responseHandler.setConnection(this.connection);
		this.responseHandler.fetchInfo();
	}
	
	/**
	 * 所有的准备工作必须在connect之前.<br>
	 * 方法,请求头,请求参数都是一起发送的.<br>
	 * 因此所有参数在真正发送(即connect建立连接)前设置完毕.
	 */
	public void connectAndFetchResult() {
		this.setRequestMethod();
		this.setRequestHeaders();
		this.writeParams();
		this.connect();//所有的准备工作必须在connect之前
		this.handleResponse();
	}
	
	
	
	//==============Constructor here===================
	public HttpRequest(URL url){
		this.url = url;
		try {
			this.connection = (HttpURLConnection) this.url.openConnection();
		} catch (IOException e) {
			BusinessExceptionUtils.throwNewBusinessException("openConnection 出现异常" + e.getMessage());
		}
	}
	/**
	 *  MalformedURLException  Malformed-->畸形的,难看的
	 */
	public HttpRequest(String urlStr){
		URL url = null;
		try {
			url = new URL(urlStr);
			this.url = url;
			this.connection = (HttpURLConnection) this.url.openConnection();
		} catch (MalformedURLException e) {
			BusinessExceptionUtils.throwNewBusinessException("url 不合法" + e.getMessage());
		} catch (IOException e) {
			BusinessExceptionUtils.throwNewBusinessException("openConnection 出现异常" + e.getMessage());
		}
	}
	public HttpRequest(String url,HttpRequestMethod method, RequestContentType contentType){
		this(url);
		this.method = method;
		this.contentTypeStrategy = contentType;
	}
	public HttpRequest(URL url,HttpRequestMethod method, RequestContentType contentType){
		this(url);
		this.method = method;
		this.contentTypeStrategy = contentType;
	}

	//=================getters and setters=======================
	public URL getUrl() {
		return url;
	}

	public void setUrl(URL url) {
		this.url = url;
	}

	public HttpURLConnection getConnection() {
		return connection;
	}

	public void setConnection(HttpURLConnection connection) {
		this.connection = connection;
	}

	public HttpRequestMethod getMethod() {
		return method;
	}

	public void setMethod(HttpRequestMethod method) {
		this.method = method;
	}

	public Map<String, Object> getHeaders() {
		return headers;
	}

	public void setHeaders(Map<String, Object> headers) {
		this.headers = headers;
	}

	public RequestContentType getContentType() {
		return contentTypeStrategy;
	}

	public void setContentType(RequestContentType contentType) {
		this.contentTypeStrategy = contentType;
	}
	
	public ResponseHanlder getResponseHandler() {
		return responseHandler;
	}

	public void setResponseHandler(ResponseHanlder responseHandler) {
		this.responseHandler = responseHandler;
	}
	
	
	public RequestContentType getContentTypeStrategy() {
		return contentTypeStrategy;
	}


	//======================获取响应处理的结果========================
	public Integer getResponseStatusCode() {
		return this.responseHandler.getCode();
	}
	
	public Object getResponseContent() {
		return this.responseHandler.getContent();
	}
}
