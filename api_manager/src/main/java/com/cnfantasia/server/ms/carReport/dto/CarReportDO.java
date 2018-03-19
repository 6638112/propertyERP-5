package com.cnfantasia.server.ms.carReport.dto;

import java.math.BigDecimal;

public class CarReportDO {
	/** 管理处 */
	private String pmName;
	/** 停车场 */
	private String plotName;
	/** 月卡现金（单位：元） */
	private BigDecimal monthCash;
	/** 临停车现金（单位：元） */
	private BigDecimal tempCash;
	/** 月卡解放区缴费（单位：元） */
	private BigDecimal monthJfqFee;
	/** 临停车解放区缴费（单位：元） */
	private BigDecimal tempJfqFee;

	public CarReportDO() {
		super();
	}

	public CarReportDO(String pmName, String plotName, BigDecimal monthCash, BigDecimal tempCash, BigDecimal monthJfqFee, BigDecimal tempJfqFee) {
		super();
		this.pmName = pmName;
		this.plotName = plotName;
		this.monthCash = monthCash;
		this.tempCash = tempCash;
		this.monthJfqFee = monthJfqFee;
		this.tempJfqFee = tempJfqFee;
	}

	public String getPmName() {
		return pmName;
	}

	public void setPmName(String pmName) {
		this.pmName = pmName;
	}

	public String getPlotName() {
		return plotName;
	}

	public void setPlotName(String plotName) {
		this.plotName = plotName;
	}

	public BigDecimal getMonthCash() {
		return monthCash;
	}

	public void setMonthCash(BigDecimal monthCash) {
		this.monthCash = monthCash;
	}

	public BigDecimal getTempCash() {
		return tempCash;
	}

	public void setTempCash(BigDecimal tempCash) {
		this.tempCash = tempCash;
	}

	public BigDecimal getMonthJfqFee() {
		return monthJfqFee;
	}

	public void setMonthJfqFee(BigDecimal monthJfqFee) {
		this.monthJfqFee = monthJfqFee;
	}

	public BigDecimal getTempJfqFee() {
		return tempJfqFee;
	}

	public void setTempJfqFee(BigDecimal tempJfqFee) {
		this.tempJfqFee = tempJfqFee;
	}

}
