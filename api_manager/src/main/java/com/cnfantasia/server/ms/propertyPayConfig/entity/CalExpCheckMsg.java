package com.cnfantasia.server.ms.propertyPayConfig.entity;

/**
 * 公式校验bean
 * 
 * @author liyulin
 * @version 1.0 2016年10月25日 上午11:39:07
 */
public class CalExpCheckMsg {

	private boolean isValid;
	private String msg;

	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
