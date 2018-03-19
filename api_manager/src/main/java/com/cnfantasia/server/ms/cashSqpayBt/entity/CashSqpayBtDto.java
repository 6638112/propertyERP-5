package com.cnfantasia.server.ms.cashSqpayBt.entity;

/**
 * 双乾支付补贴明细Dto
 * 
 * @author liyulin
 * @version 1.0 2016年9月9日 下午8:13:51
 */
public class CashSqpayBtDto {
	
	/** 业务对象名称 */
	private String goalName;
	/** 业务类型 */
	private Integer orderType;
	/** 实付金额 */
	private Long amount;
	/** 补贴金额 */
	private Long amountBt;
	/** 支付时间 */
	private String payTime;
	/** 流水号 */
	private String orderNo;

	public String getGoalName() {
		return goalName;
	}

	public void setGoalName(String goalName) {
		this.goalName = goalName;
	}

	public Integer getOrderType() {
		return orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public Long getAmountBt() {
		return amountBt;
	}

	public void setAmountBt(Long amountBt) {
		this.amountBt = amountBt;
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

}
