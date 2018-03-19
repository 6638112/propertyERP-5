package com.cnfantasia.server.api.carMsgTask.entity;

public class CarSendMsgEntity {
	/** 手机号 */
	private String mobile;
	/** 车牌 */
	private String carNum;
	/** 小区 */
	private String gbName;
	/** 剩余天数 */
	private String expireDay;

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getCarNum() {
		return carNum;
	}

	public void setCarNum(String carNum) {
		this.carNum = carNum;
	}

	public String getGbName() {
		return gbName;
	}

	public void setGbName(String gbName) {
		this.gbName = gbName;
	}

	public String getExpireDay() {
		return expireDay;
	}

	public void setExpireDay(String expireDay) {
		this.expireDay = expireDay;
	}

}
