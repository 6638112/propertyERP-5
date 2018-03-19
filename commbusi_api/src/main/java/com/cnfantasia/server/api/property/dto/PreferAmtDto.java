package com.cnfantasia.server.api.property.dto;

/**
 * 优惠信息
 * 
 * @author liyulin
 * @version 1.0 2017年3月15日 下午1:21:45
 */
public class PreferAmtDto {
	/** 解放区优惠金额（单位：元） */
	private String jfqDiscount;
	/** 银行卡优惠金额（单位：元） */
	private String bankDiscount;
	/** 红包（粮票）优惠 */
	private String hbAmt;
	/** 物业宝抵扣（单位：元） */
	private String deduAmt;

	public String getJfqDiscount() {
		return jfqDiscount;
	}

	public void setJfqDiscount(String jfqDiscount) {
		this.jfqDiscount = jfqDiscount;
	}

	public String getBankDiscount() {
		return bankDiscount;
	}

	public void setBankDiscount(String bankDiscount) {
		this.bankDiscount = bankDiscount;
	}

	public String getHbAmt() {
		return hbAmt;
	}

	public void setHbAmt(String hbAmt) {
		this.hbAmt = hbAmt;
	}

	public String getDeduAmt() {
		return deduAmt;
	}

	public void setDeduAmt(String deduAmt) {
		this.deduAmt = deduAmt;
	}

}
