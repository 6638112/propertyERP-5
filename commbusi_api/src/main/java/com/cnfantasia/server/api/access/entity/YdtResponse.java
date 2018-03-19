package com.cnfantasia.server.api.access.entity;

import com.alibaba.fastjson.JSONObject;

/**
 * 一道通车禁响应结果
 * 
 * @author liyulin
 * @version 1.0 2017年12月7日 下午1:37:30
 */
public class YdtResponse {
	private String errorCode;
	private String errorMsg;
	private JSONObject result;

	public YdtResponse() {
		super();
	}

	public YdtResponse(String errorCode, String errorMsg, JSONObject result) {
		super();
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
		this.result = result;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public JSONObject getResult() {
		return result;
	}

	public void setResult(JSONObject result) {
		this.result = result;
	}

}
