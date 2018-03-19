package com.cnfantasia.server.ms.carReport.dto;

import java.math.BigDecimal;

public class CarReportFeeDTO {
	/** 费用（单位：元） */
	private BigDecimal fee;
	/** 费用所占比例 */
	private BigDecimal percent;
	/** 百分比可见 */
	private boolean percentVisible;

	public BigDecimal getFee() {
		return fee;
	}

	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}

	public BigDecimal getPercent() {
		return percent;
	}

	public void setPercent(BigDecimal percent) {
		this.percent = percent;
	}

	public boolean isPercentVisible() {
		return percentVisible;
	}

	public void setPercentVisible(boolean percentVisible) {
		this.percentVisible = percentVisible;
	}

}
