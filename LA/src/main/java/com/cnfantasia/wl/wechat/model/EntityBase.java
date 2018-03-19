package com.cnfantasia.wl.wechat.model;


public class EntityBase {
	String status;
	String message;
	String errcode;
	String transNo;
	DataValue dataValue;

	public DataValue getDataValue() {
		return dataValue;
	}

	public void setDataValue(DataValue dataValue) {
		this.dataValue = dataValue;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getErrcode() {
		return errcode;
	}

	public void setErrcode(String errcode) {
		this.errcode = errcode;
	}

	public String getTransNo() {
		return transNo;
	}

	public void setTransNo(String transNo) {
		this.transNo = transNo;
	}

}
