package com.cnfantasia.server.common.json;

import java.util.HashMap;
import java.util.Map;


/**
 * 类名：JsonRequest  <br />
 *
 * 功能：json结果对象
 *
 */
public class JsonRequest {
	
	// 头信息
	private Map<String, String> header;
	
	// 请求体
	private Request request;
	
	/**
	 * 构造方法
	 */
	public JsonRequest() {
		this.request = new Request();
	}
	
	public void addHeader(String name, String value) {
		if (null == header) {
			header = new HashMap<String, String>();
		}
		
		header.put(name, value);
	}

	public Map<String, String> getHeader() {
		return header;
	}

	public void setHeader(Map<String, String> header) {
		this.header = header;
	}

	public Request getRequest() {
		return request;
	}

	public void setRequest(Request request) {
		this.request = request;
	}

	public static class Request {
		
		// id
		private String id;
		
		// 交易id
		private String transaction;
		
		// 参数
		private Map<String, Object> params;
		
		/**
		 * 构造方法
		 */
		public Request() {
			this.params = new HashMap<String, Object>();
		}
		
		public void addParameter(String name, Object value) {
			params.put(name, value);
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public Map<String, Object> getParams() {
			return params;
		}

		public void setParams(Map<String, Object> params) {
			this.params = params;
		}

		public String getTransaction() {
			return transaction;
		}

		public void setTransaction(String transaction) {
			this.transaction = transaction;
		}
	}
}
