package com.cnfantasia.server.api.payment.entity;

/**
 * 双乾支付补贴返回参数
 * 
 * @author liyulin
 * @version 1.0 2016年9月8日 下午7:41:55
 */
public class SqPayBtResponse {
	private String userId;
	private String merNo;
	private String billNo;
	private String amount;
	private String payAmount;
	private String discountAmount;
	private String orderState;
	private String stateExplain;
	private String remark;
	private String sign;

	public SqPayBtResponse() {
		super();
	}

	public SqPayBtResponse(String userId, String merNo, String billNo,
			String amount, String payAmount, String discountAmount,
			String orderState, String stateExplain, String remark, String sign) {
		super();
		this.userId = userId;
		this.merNo = merNo;
		this.billNo = billNo;
		this.amount = amount;
		this.payAmount = payAmount;
		this.discountAmount = discountAmount;
		this.orderState = orderState;
		this.stateExplain = stateExplain;
		this.remark = remark;
		this.sign = sign;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getMerNo() {
		return merNo;
	}

	public void setMerNo(String merNo) {
		this.merNo = merNo;
	}

	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(String payAmount) {
		this.payAmount = payAmount;
	}

	public String getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(String discountAmount) {
		this.discountAmount = discountAmount;
	}

	public String getOrderState() {
		return orderState;
	}

	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}

	public String getStateExplain() {
		return stateExplain;
	}

	public void setStateExplain(String stateExplain) {
		this.stateExplain = stateExplain;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	@Override
	public String toString() {
		return "SqPayBtResponse [userId=" + userId + ", merNo=" + merNo
				+ ", billNo=" + billNo + ", amount=" + amount + ", payAmount="
				+ payAmount + ", discountAmount=" + discountAmount
				+ ", orderState=" + orderState + ", stateExplain="
				+ stateExplain + ", remark=" + remark + ", sign=" + sign + "]";
	}

}
