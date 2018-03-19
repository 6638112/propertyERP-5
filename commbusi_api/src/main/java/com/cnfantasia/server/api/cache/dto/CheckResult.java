package com.cnfantasia.server.api.cache.dto;

/**
 * 校验
 * 
 * @author liyulin
 * @version 1.0 2017年1月9日 上午11:14:51
 */
public class CheckResult {

	private boolean isSuccess;
	private String msg;

	public CheckResult() {
		super();
	}

	public CheckResult(boolean isSuccess) {
		super();
		this.isSuccess = isSuccess;
	}

	public CheckResult(boolean isSuccess, String msg) {
		super();
		this.isSuccess = isSuccess;
		this.msg = msg;
	}

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
