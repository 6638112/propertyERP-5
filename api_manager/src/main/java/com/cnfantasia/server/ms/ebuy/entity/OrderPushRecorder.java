package com.cnfantasia.server.ms.ebuy.entity;

public class OrderPushRecorder {
	int isPush_success; //是否推送成功
	String push_result; //推送结果

	public int getIsPush_success() {
		return isPush_success;
	}

	public void setIsPush_success(int isPush_success) {
		this.isPush_success = isPush_success;
	}

	public String getPush_result() {
		return push_result;
	}

	public void setPush_result(String push_result) {
		this.push_result = push_result;
	}

}
