package com.cnfantasia.server.api.ebuyorder.entity;

import java.io.Serializable;

/**
 * 类说明：
 * 
 * @author hunter
 * @since 2014年6月7日下午2:30:24
 */
public class OrderDetailBean implements Serializable {
	private static final long serialVersionUID = 3804603870669797586L;

	private String id;
	private String orderNo;
	private String huaId;
	private String realName;
	private String buyTime;
	private String amount;
	private String factAmount;
	private String payMethod;
	private String status;
	private String payStatus;
	

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the orderNo
	 */
	public String getOrderNo() {
		return orderNo;
	}

	/**
	 * @param orderNo
	 *            the orderNo to set
	 */
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	/**
	 * @return the huaId
	 */
	public String getHuaId() {
		return huaId;
	}

	/**
	 * @param huaId
	 *            the huaId to set
	 */
	public void setHuaId(String huaId) {
		this.huaId = huaId;
	}

	/**
	 * @return the realName
	 */
	public String getRealName() {
		return realName;
	}

	/**
	 * @param realName
	 *            the realName to set
	 */
	public void setRealName(String realName) {
		this.realName = realName;
	}

	/**
	 * @return the buyTime
	 */
	public String getBuyTime() {
		return buyTime;
	}

	/**
	 * @param buyTime
	 *            the buyTime to set
	 */
	public void setBuyTime(String buyTime) {
		this.buyTime = buyTime;
	}

	/**
	 * @return the amount
	 */
	public String getAmount() {
		return amount;
	}

	/**
	 * @param amount
	 *            the amount to set
	 */
	public void setAmount(String amount) {
		this.amount = amount;
	}

	/**
	 * @return the factAmount
	 */
	public String getFactAmount() {
		return factAmount;
	}

	/**
	 * @param factAmount
	 *            the factAmount to set
	 */
	public void setFactAmount(String factAmount) {
		this.factAmount = factAmount;
	}

	/**
	 * @return the payMethod
	 */
	public String getPayMethod() {
		return payMethod;
	}

	/**
	 * @param payMethod
	 *            the payMethod to set
	 */
	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the payStatus
	 */
	public String getPayStatus() {
		return payStatus;
	}

	/**
	 * @param payStatus
	 *            the payStatus to set
	 */
	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}
}
