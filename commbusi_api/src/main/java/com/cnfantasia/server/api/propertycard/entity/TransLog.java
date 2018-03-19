package com.cnfantasia.server.api.propertycard.entity;

import com.cnfantasia.server.common.utils.PriceUtil;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * 物业代扣交易流水
 * @author wenfq 2016-04-27
 *
 */
public class TransLog {
	BigInteger id;
	/**
	 * 交易详情描述
	 */
	String transDescription;
	/**
	 * 交易金额
	 */
	BigDecimal transAmt;
	
	/**
	 * 交易类型
	 */
	int transType;
	
	/**
	 * 交易时间 
	 */
	String transTime;
	
	/**
	 * 交易状态描述
	 */
	String transState;
	
	/**
	 * 支付方式 
	 */
	String payMethod;
	
	/**
	 * 交易单号
	 */
	String transNo;
	
	public String getTransNo() {
		return transNo;
	}
	public void setTransNo(String transNo) {
		this.transNo = transNo;
	}
	public String getPayMethod() {
		return payMethod;
	}
	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}
	public BigInteger getId() {
		return id;
	}
	public void setId(BigInteger id) {
		this.id = id;
	}
	public String getTransDescription() {
		return transDescription;
	}
	public void setTransDescription(String transDescription) {
		this.transDescription = transDescription;
	}
	public BigDecimal getTransAmt() {
		return transAmt;
	}
	public void setTransAmt(BigDecimal transAmt) {
		this.transAmt = PriceUtil.div100(transAmt);
	}
	public int getTransType() {
		return transType;
	}
	public void setTransType(int transType) {
		this.transType = transType;
	}
	public String getTransTime() {
		return transTime;
	}
	public void setTransTime(String transTime) {
		this.transTime = transTime;
	}
	public String getTransState() {
		return transState;
	}
	public void setTransState(String transState) {
		this.transState = transState;
	}
	
}
