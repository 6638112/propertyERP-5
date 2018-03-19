package com.cnfantasia.server.api.access.entity;

import java.math.BigDecimal;

/**
 * 临停车支付详情
 * 
 * @author liyulin
 * @version 1.0 2016年8月26日 下午4:47:33
 */
public class TmpCarPayDetail {
	/** 支付金额 */
	private BigDecimal payMoney;
	/** 优惠金额 */
	private BigDecimal couponAmount;
	/** 车禁券抵扣金额 */
	private BigDecimal ucAmount;
	/** 支付方式 */
	private Integer payMethod;
	/** 支付时间 */
	private String payTime;
	/** 订单号 */
	private String orderNo;
	/** 支付状态 */
	private Integer payStatus;

	public TmpCarPayDetail() {
		super();
	}

	public TmpCarPayDetail(BigDecimal payMoney, BigDecimal couponAmount,
			BigDecimal ucAmount, Integer payMethod, String payTime,
			String orderNo, Integer payStatus) {
		super();
		this.payMoney = payMoney;
		this.couponAmount = couponAmount;
		this.ucAmount = ucAmount;
		this.payMethod = payMethod;
		this.payTime = payTime;
		this.orderNo = orderNo;
		this.payStatus = payStatus;
	}

	public BigDecimal getUcAmount() {
		return ucAmount;
	}

	public void setUcAmount(BigDecimal ucAmount) {
		this.ucAmount = ucAmount;
	}

	public BigDecimal getPayMoney() {
		return payMoney;
	}

	public void setPayMoney(BigDecimal payMoney) {
		this.payMoney = payMoney;
	}

	public BigDecimal getCouponAmount() {
		return couponAmount;
	}

	public void setCouponAmount(BigDecimal couponAmount) {
		this.couponAmount = couponAmount;
	}

	public Integer getPayMethod() {
		return payMethod;
	}

	public void setPayMethod(Integer payMethod) {
		this.payMethod = payMethod;
	}

	public String getPayTime() {
		return payTime;
	}

	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Integer getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(Integer payStatus) {
		this.payStatus = payStatus;
	}

}
