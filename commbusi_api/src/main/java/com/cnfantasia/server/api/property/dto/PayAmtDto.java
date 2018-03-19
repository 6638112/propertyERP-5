package com.cnfantasia.server.api.property.dto;

/**
 * 跳转支付确认页面时的支付金额
 * 
 * @author liyulin
 * @version 1.0 2017年3月10日 下午2:50:30
 */
public class PayAmtDto {

	/** 账单总金额 */
	private String totalAmt;
	/** 实际需要支付的金额 */
	private String realAmt;
	/** 优惠金额 */
	private String amtBt;
	/** 优惠描述 */
	private String couponDesc;
	/** 红包比例 */
	private Double hbPercent;

	public String getTotalAmt() {
		return totalAmt;
	}

	public void setTotalAmt(String totalAmt) {
		this.totalAmt = totalAmt;
	}

	public String getRealAmt() {
		return realAmt;
	}

	public void setRealAmt(String realAmt) {
		this.realAmt = realAmt;
	}

	public String getAmtBt() {
		return amtBt;
	}

	public void setAmtBt(String amtBt) {
		this.amtBt = amtBt;
	}

	public String getCouponDesc() {
		return couponDesc;
	}

	public void setCouponDesc(String couponDesc) {
		this.couponDesc = couponDesc;
	}

	public Double getHbPercent() {
		return hbPercent;
	}

	public void setHbPercent(Double hbPercent) {
		this.hbPercent = hbPercent;
	}

}
