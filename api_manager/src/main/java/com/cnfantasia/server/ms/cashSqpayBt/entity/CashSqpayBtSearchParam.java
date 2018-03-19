package com.cnfantasia.server.ms.cashSqpayBt.entity;

/**
 * 双乾支付补贴搜索参数
 * 
 * @author liyulin
 * @version 1.0 2016年9月9日 下午8:15:42
 */
public class CashSqpayBtSearchParam {

	/** 物业对象名称 */
	private String goalName;
	/** 业务类型 */
	private String orderType;
	/** 支付时间起始时间 */
	private String payTimeStart;
	/** 支付时间结束时间 */
	private String payTimeEnd;
	/** 订单号 */
	private String orderNo;

	public String getGoalName() {
		return goalName;
	}

	public void setGoalName(String goalName) {
		this.goalName = goalName;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getPayTimeStart() {
		return payTimeStart;
	}

	public void setPayTimeStart(String payTimeStart) {
		this.payTimeStart = payTimeStart;
	}

	public String getPayTimeEnd() {
		return payTimeEnd;
	}

	public void setPayTimeEnd(String payTimeEnd) {
		this.payTimeEnd = payTimeEnd;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

}
