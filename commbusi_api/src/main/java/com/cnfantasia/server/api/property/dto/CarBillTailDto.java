package com.cnfantasia.server.api.property.dto;

/**
 * 车禁账单尾部信息
 * 
 * @author liyulin
 * @version 1.0 2017年3月10日 下午1:56:28
 */
public class CarBillTailDto {
	/** 车牌 */
	private String carNum;
	/** 月卡费用 */
	private String carFee;
	/** 缴费月数 */
	private String month;
	/** 停放地址 */
	private String parking;
	/** 到期时间 */
	private String expire;

	public String getCarNum() {
		return carNum;
	}

	public void setCarNum(String carNum) {
		this.carNum = carNum;
	}

	public String getCarFee() {
		return carFee;
	}

	public void setCarFee(String carFee) {
		this.carFee = carFee;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getParking() {
		return parking;
	}

	public void setParking(String parking) {
		this.parking = parking;
	}

	public String getExpire() {
		return expire;
	}

	public void setExpire(String expire) {
		this.expire = expire;
	}

}
