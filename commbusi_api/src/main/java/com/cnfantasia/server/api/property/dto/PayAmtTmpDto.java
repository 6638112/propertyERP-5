package com.cnfantasia.server.api.property.dto;

import java.math.BigDecimal;

/**
 * 跳转支付确认页面时的支付金额
 * 
 * @author liyulin
 * @version 1.0 2017年3月10日 下午2:50:30
 */
public class PayAmtTmpDto {
	/** 账单总金额（单位：分） */
	private BigDecimal totalAmt;
	/** 实际需要支付的金额 （单位：分） */
	private BigDecimal realAmt;
	/** 优惠金额 （单位：分） */
	private BigDecimal amtBt;
	/** 物业宝抵扣金额（单位：分） */
	private BigDecimal deduAmt;

	public BigDecimal getTotalAmt() {
		return totalAmt;
	}

	public void setTotalAmt(BigDecimal totalAmt) {
		this.totalAmt = totalAmt;
	}

	public BigDecimal getRealAmt() {
		return realAmt;
	}

	public void setRealAmt(BigDecimal realAmt) {
		this.realAmt = realAmt;
	}

	public BigDecimal getAmtBt() {
		return amtBt;
	}

	public void setAmtBt(BigDecimal amtBt) {
		this.amtBt = amtBt;
	}

	public BigDecimal getDeduAmt() {
		return deduAmt;
	}

	public void setDeduAmt(BigDecimal deduAmt) {
		this.deduAmt = deduAmt;
	}

}
