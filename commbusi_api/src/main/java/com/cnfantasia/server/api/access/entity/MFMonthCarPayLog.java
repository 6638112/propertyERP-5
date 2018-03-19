package com.cnfantasia.server.api.access.entity;

/**
 * 小蜜蜂月卡支付记录
 * 
 * @author liyulin
 * @version 1.0 2017年8月15日 上午11:29:12
 */
public class MFMonthCarPayLog {

	/** 车牌 */
	private String carNum;
	/** 续费月数 */
	private long payNum;
	/** 开始有效期 */
	private String startDate;
	/** 截止有效期 */
	private String endDate;
	/** 支付时间 */
	private String payTime;
	/** 支付总金额 */
	private long totalPrice;

	public String getCarNum() {
		return carNum;
	}

	public void setCarNum(String carNum) {
		this.carNum = carNum;
	}

	public long getPayNum() {
		return payNum;
	}

	public void setPayNum(long payNum) {
		this.payNum = payNum;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getPayTime() {
		return payTime;
	}

	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}

	public long getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(long totalPrice) {
		this.totalPrice = totalPrice;
	}

}
