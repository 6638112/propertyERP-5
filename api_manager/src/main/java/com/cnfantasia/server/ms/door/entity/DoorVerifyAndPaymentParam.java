package com.cnfantasia.server.ms.door.entity;

/**
 * 门牌验证缴费情况查询参数
 * 
 * @author liyulin
 * @version 1.0 2016年7月14日 上午11:12:57
 */
public class DoorVerifyAndPaymentParam extends DoorVerifyAndPaymentDto {
	/** 认证开始时间 */
	private String verifyStartTime;
	/** 认证结束时间 */
	private String verifyEndTime;
	/** 缴费开始时间 */
	private String payStartTime;
	/** 缴费结束时间 */
	private String payEndTime;

	public DoorVerifyAndPaymentParam() {
		super();
	}

	public DoorVerifyAndPaymentParam(String verifyStartTime, String verifyEndTime, String payStartTime, String payEndTime) {
		super();
		this.verifyStartTime = verifyStartTime;
		this.verifyEndTime = verifyEndTime;
		this.payStartTime = payStartTime;
		this.payEndTime = payEndTime;
	}

	public String getVerifyStartTime() {
		return verifyStartTime;
	}

	public void setVerifyStartTime(String verifyStartTime) {
		this.verifyStartTime = verifyStartTime;
	}

	public String getVerifyEndTime() {
		return verifyEndTime;
	}

	public void setVerifyEndTime(String verifyEndTime) {
		this.verifyEndTime = verifyEndTime;
	}

	public String getPayStartTime() {
		return payStartTime;
	}

	public void setPayStartTime(String payStartTime) {
		this.payStartTime = payStartTime;
	}

	public String getPayEndTime() {
		return payEndTime;
	}

	public void setPayEndTime(String payEndTime) {
		this.payEndTime = payEndTime;
	}

}
