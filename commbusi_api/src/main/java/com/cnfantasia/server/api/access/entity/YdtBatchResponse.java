package com.cnfantasia.server.api.access.entity;

import com.alibaba.fastjson.JSONArray;

/**
 * 一道通车禁响应结果
 * 
 * @author liyulin
 * @version 1.0 2017年12月7日 下午1:39:59
 */
public class YdtBatchResponse {
	private String errorCode;
	private String errorMsg;
	private JSONArray result;

	public YdtBatchResponse() {
		super();
	}

	public YdtBatchResponse(String errorCode, String errorMsg, JSONArray result) {
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

	public JSONArray getResult() {
		return result;
	}

	public void setResult(JSONArray result) {
		this.result = result;
	}

}
