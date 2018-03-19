package com.cnfantasia.server.api.property.dto;

import java.util.List;

/**
 * 支付详情
 * 
 * @author liyulin
 * @version 1.0 2017年3月10日 上午10:39:59
 */
public class PayDetailDto {
	/** 支付状态 */
	private Integer payStatus;
	/** 支付结果 */
	private String result;
	/** 实际支付金额（单位：元） */
	private String realAmount;
	/** 账单总金额（单位：元） */
	private String totalAmount;
	/** 是否有优惠 */
	private boolean isPrefer;
	/** 优惠金额（单位：元） */
	private PreferAmtDto preferAmt;
	/** 交易时间 */
	private String payTime;
	/** 支付方式 */
	private String payMethod;
	/** 交易单号 */
	private String transNo;
	/** 账单明细 */
	private List<PayBillDetailDto> payBillDetails;

	public Integer getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(Integer payStatus) {
		this.payStatus = payStatus;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getRealAmount() {
		return realAmount;
	}

	public void setRealAmount(String realAmount) {
		this.realAmount = realAmount;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	public boolean isPrefer() {
		return isPrefer;
	}

	public void setPrefer(boolean isPrefer) {
		this.isPrefer = isPrefer;
	}

	public PreferAmtDto getPreferAmt() {
		return preferAmt;
	}

	public void setPreferAmt(PreferAmtDto preferAmt) {
		this.preferAmt = preferAmt;
	}

	public String getPayTime() {
		return payTime;
	}

	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}

	public String getPayMethod() {
		return payMethod;
	}

	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}

	public String getTransNo() {
		return transNo;
	}

	public void setTransNo(String transNo) {
		this.transNo = transNo;
	}

	public List<PayBillDetailDto> getPayBillDetails() {
		return payBillDetails;
	}

	public void setPayBillDetails(List<PayBillDetailDto> payBillDetails) {
		this.payBillDetails = payBillDetails;
	}

}
