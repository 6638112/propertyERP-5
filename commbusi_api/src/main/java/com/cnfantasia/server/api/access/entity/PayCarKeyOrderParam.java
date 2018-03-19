package com.cnfantasia.server.api.access.entity;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * 车禁缴费支付参数
 * 
 * @author liyulin
 * @version 1.0 2016年8月24日 下午1:50:58
 */
public class PayCarKeyOrderParam {

	/** 用户id */
	private BigInteger userId;
	/** 车牌id */
	private BigInteger carId;
	/** 临停车缴费小区id */
	private BigInteger gbId;
	/** 月卡缴费月数 */
	private long payNum;
	/** 临停车缴费金额（单位：元） */
	private double payFee;
	/** 支付渠道 */
	private Integer subChannelId;
	/** 是否需要发票 */
	private Integer needBill;
	/** 红包金额（单位：元） */
	private BigDecimal hbAmt;
	/** 是否来自一键缴费 */
	private boolean isFromTotalOrder;
	/** 华鹏飞车禁需要 */
	private double receivableFee;
	private String sessionId;
	/** 优惠金额（小蜜蜂车禁，单位：分） */
	private long discountamount;
	/** 停车订单编号（小蜜蜂） */
	private String orderid;
	/**券id：t_user_coupon*/
	private BigInteger ucId;

	public PayCarKeyOrderParam() {
		super();
	}

	public PayCarKeyOrderParam(BigInteger userId, BigInteger carId,
			BigInteger gbId, long payNum, double payFee, Integer subChannelId,
			Integer needBill, BigDecimal hbAmt, boolean isFromTotalOrder,
			double receivableFee, String sessionId, long discountamount,
			String orderid) {
		super();
		this.userId = userId;
		this.carId = carId;
		this.gbId = gbId;
		this.payNum = payNum;
		this.payFee = payFee;
		this.subChannelId = subChannelId;
		this.needBill = needBill;
		this.hbAmt = hbAmt;
		this.isFromTotalOrder = isFromTotalOrder;
		this.receivableFee = receivableFee;
		this.sessionId = sessionId;
		this.discountamount = discountamount;
		this.orderid = orderid;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public double getReceivableFee() {
		return receivableFee;
	}

	public void setReceivableFee(double receivableFee) {
		this.receivableFee = receivableFee;
	}

	public BigInteger getUserId() {
		return userId;
	}

	public void setUserId(BigInteger userId) {
		this.userId = userId;
	}

	public BigInteger getCarId() {
		return carId;
	}

	public void setCarId(BigInteger carId) {
		this.carId = carId;
	}

	public BigInteger getGbId() {
		return gbId;
	}

	public void setGbId(BigInteger gbId) {
		this.gbId = gbId;
	}

	public long getPayNum() {
		return payNum;
	}

	public void setPayNum(long payNum) {
		this.payNum = payNum;
	}

	public double getPayFee() {
		return payFee;
	}

	public void setPayFee(double payFee) {
		this.payFee = payFee;
	}

	public Integer getSubChannelId() {
		return subChannelId;
	}

	public void setSubChannelId(Integer subChannelId) {
		this.subChannelId = subChannelId;
	}

	public Integer getNeedBill() {
		return needBill;
	}

	public void setNeedBill(Integer needBill) {
		this.needBill = needBill;
	}

	public BigDecimal getHbAmt() {
		return hbAmt;
	}

	public void setHbAmt(BigDecimal hbAmt) {
		this.hbAmt = hbAmt;
	}

	public boolean isFromTotalOrder() {
		return isFromTotalOrder;
	}

	public void setFromTotalOrder(boolean isFromTotalOrder) {
		this.isFromTotalOrder = isFromTotalOrder;
	}

	public long getDiscountamount() {
		return discountamount;
	}

	public void setDiscountamount(long discountamount) {
		this.discountamount = discountamount;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public BigInteger getUcId() {
		return ucId;
	}

	public void setUcId(BigInteger ucId) {
		this.ucId = ucId;
	}

}
