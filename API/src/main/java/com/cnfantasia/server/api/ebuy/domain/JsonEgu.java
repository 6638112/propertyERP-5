package com.cnfantasia.server.api.ebuy.domain;

public class JsonEgu {
	private String status;//状态
	private String eguOrder;//订单号
	private String errorMsg;//错误信息
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getEguOrder() {
		return eguOrder;
	}
	public void setEguOrder(String eguOrder) {
		this.eguOrder = eguOrder;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
}
