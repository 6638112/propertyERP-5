package com.cnfantasia.server.api.access.entity;

import com.cnfantasia.server.domainbase.carNumList.entity.CarNumList;

/**
 * 物业缴费处车辆缴费卡
 * 
 * @author liyulin
 * @version 1.0 2016年11月22日 下午4:47:52
 */
public class CarBill extends CarNumList{

	/** 小区 */
	private String gbName;
	/** 是否支付确认中 */
	private boolean confirmPay;

	public String getGbName() {
		return gbName;
	}

	public void setGbName(String gbName) {
		this.gbName = gbName;
	}

	public boolean isConfirmPay() {
		return confirmPay;
	}

	public void setConfirmPay(boolean confirmPay) {
		this.confirmPay = confirmPay;
	}

}
