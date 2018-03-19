package com.cnfantasia.server.api.payment.entity;

import java.math.BigInteger;

/**
 * 双乾支付请求参数
 * 
 * @author liyulin
 * @version 1.0 2016年9月8日 下午8:27:19
 */
public class SqPayRequest {
	private BigInteger userId;
	private String phone;
	private String merNo;
	private String billNo;
	private String amount;
	private String payType;
	private String remark;
	private String products;
	private String sign;

	public SqPayRequest() {
		super();
	}

	public SqPayRequest(BigInteger userId, String phone, String merNo,
			String billNo, String amount, String payType, String remark,
			String products, String sign) {
		super();
		this.userId = userId;
		this.phone = phone;
		this.merNo = merNo;
		this.billNo = billNo;
		this.amount = amount;
		this.payType = payType;
		this.remark = remark;
		this.products = products;
		this.sign = sign;
	}

	public BigInteger getUserId() {
		return userId;
	}

	public void setUserId(BigInteger userId) {
		this.userId = userId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getProducts() {
		return products;
	}

	public void setProducts(String products) {
		this.products = products;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	@Override
	public String toString() {
		return "SqPayRequest [userId=" + userId + ", phone=" + phone
				+ ", merNo=" + merNo + ", billNo=" + billNo + ", amount="
				+ amount + ", payType=" + payType + ", remark=" + remark
				+ ", products=" + products + ", sign=" + sign + "]";
	}

}
