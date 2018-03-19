package com.cnfantasia.server.api.access.entity;

/**
 * 华安月卡缴费成功后通知参数
 * 
 * @author liyulin
 * @version 1.0 2017年8月18日 上午11:33:47
 */
public class HAMonthCarNotifyParam {

	/** 停车场编号 */
	private String plotNo;
	/** 车牌 */
	private String carNum;
	/** 费用（单位：分） */
	private long fee;
	/** 有效开始时间 */
	private String startTime;
	/** 有效截止时间 */
	private String endTime;

	public String getPlotNo() {
		return plotNo;
	}

	public void setPlotNo(String plotNo) {
		this.plotNo = plotNo;
	}

	public String getCarNum() {
		return carNum;
	}

	public void setCarNum(String carNum) {
		this.carNum = carNum;
	}

	public long getFee() {
		return fee;
	}

	public void setFee(long fee) {
		this.fee = fee;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

}
