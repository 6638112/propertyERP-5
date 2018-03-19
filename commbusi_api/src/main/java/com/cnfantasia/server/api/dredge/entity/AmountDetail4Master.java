package com.cnfantasia.server.api.dredge.entity;

public class AmountDetail4Master {
	private long id;
	private int feeType;
	private String feeName;
	private double feeAmount;
	private int isWithdrawed;
	private int canWithdrawDays;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getFeeType() {
		return feeType;
	}
	public void setFeeType(int feeType) {
		this.feeType = feeType;
	}
	public String getFeeName() {
		return feeName;
	}
	public void setFeeName(String feeName) {
		this.feeName = feeName;
	}
	public double getFeeAmount() {
		return feeAmount;
	}
	public void setFeeAmount(double feeAmount) {
		this.feeAmount = feeAmount;
	}
	public int getIsWithdrawed() {
		return isWithdrawed;
	}
	public void setIsWithdrawed(int isWithdrawed) {
		this.isWithdrawed = isWithdrawed;
	}
	public int getCanWithdrawDays() {
		return canWithdrawDays;
	}
	public void setCanWithdrawDays(int canWithdrawDays) {
		this.canWithdrawDays = canWithdrawDays;
	}
	
	
}
