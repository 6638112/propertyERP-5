package com.cnfantasia.server.api.bankCollection.entity;

public class ResultMsg {
	private String msg;
	private boolean isSuccess;

	public ResultMsg() {
		super();
	}

	public ResultMsg(String msg, boolean isSuccess) {
		super();
		this.msg = msg;
		this.isSuccess = isSuccess;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

}
