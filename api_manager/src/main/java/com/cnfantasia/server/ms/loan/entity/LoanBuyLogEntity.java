package com.cnfantasia.server.ms.loan.entity;

public class LoanBuyLogEntity extends LoanBuyLogReq {
	
	/** 订单号 */
	private String orderNo;
	/** 借贷时间 */
	private String loanDate;
	/** 借贷金额（单位 */
	private Double loanAmount;

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getLoanDate() {
		return loanDate;
	}

	public void setLoanDate(String loanDate) {
		this.loanDate = loanDate;
	}

	public Double getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(Double loanAmount) {
		this.loanAmount = loanAmount;
	}

}
