package com.cnfantasia.server.api.property.dto;

/**
 * 账单支付信息
 * 
 * @author liyulin
 * @version 1.0 2017年3月10日 下午2:00:53
 */
public class BillPayDto {
	/** 优惠金额（单位：元） */
	private PreferAmtDto preferAmt;
	/** 支付状态 */
	private String result;
	/** 支付方式 */
	private String payMethod;
	/** 交易时间 */
	private String payTime;
	
	public PreferAmtDto getPreferAmt() {
		return preferAmt;
	}

	public void setPreferAmt(PreferAmtDto preferAmt) {
		this.preferAmt = preferAmt;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getPayMethod() {
		return payMethod;
	}

	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}

	public String getPayTime() {
		return payTime;
	}

	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}

}
