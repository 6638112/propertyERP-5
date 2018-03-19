package com.cnfantasia.server.ms.carReport.dto;

import java.math.BigDecimal;

/**
 * 支付渠道
 * 
 * @author liyulin
 * @version 1.0 2017年11月7日 上午9:59:56
 */
public class PayChannelDTO {
	/** 解放区费用 */
	private CarReportFeeDTO jfqFee;
	/** 现金 */
	private CarReportFeeDTO cash;
	/** 合计 */
	private BigDecimal totalFee;

	public CarReportFeeDTO getJfqFee() {
		return jfqFee;
	}

	public void setJfqFee(CarReportFeeDTO jfqFee) {
		this.jfqFee = jfqFee;
	}

	public CarReportFeeDTO getCash() {
		return cash;
	}

	public void setCash(CarReportFeeDTO cash) {
		this.cash = cash;
	}

	public BigDecimal getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(BigDecimal totalFee) {
		this.totalFee = totalFee;
	}

}
