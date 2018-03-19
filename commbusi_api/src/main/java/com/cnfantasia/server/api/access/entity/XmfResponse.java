package com.cnfantasia.server.api.access.entity;

import com.alibaba.fastjson.JSONArray;

/**
 * 小蜜蜂车禁响应结果
 * 
 * @author liyulin
 * @version 1.0 2017年12月11日 上午11:02:42
 */
public class XmfResponse {
	private String state;
	private String message;
	private JSONArray outList;

	public XmfResponse() {
		super();
	}

	public XmfResponse(String state, String message, JSONArray outList) {
		super();
		this.state = state;
		this.message = message;
		this.outList = outList;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public JSONArray getOutList() {
		return outList;
	}

	public void setOutList(JSONArray outList) {
		this.outList = outList;
	}

}
