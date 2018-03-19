package com.cnfantasia.server.api.access.entity;

import java.math.BigInteger;

/**
 * 通知（月卡、临停车）缴费成功参数
 * 
 * @author liyulin
 * @version 1.0 2017年6月17日 下午12:30:44
 */
public class NotifyCarPaySuccessParam {
	/** 停车场 */
	private BigInteger gbId;
	/** 车牌 */
	private String carNum;
	/** 充值时间 */
	private String chargeTime;
	/** 支付月数 */
	private int payNum;
	/** 实收（单位：元） */
	private double actualFee;
	/** 应收（单位：元） */
	private double receivableFee;
	/** 有效开始时间 */
	private String starttime;
	/** 有效截至时间 */
	private String endtime;
	private String cardsn;
	private BigInteger cnplId;
	private Integer pushTimes;

	public BigInteger getGbId() {
		return gbId;
	}

	public void setGbId(BigInteger gbId) {
		this.gbId = gbId;
	}

	public String getCarNum() {
		return carNum;
	}

	public void setCarNum(String carNum) {
		this.carNum = carNum;
	}

	public String getChargeTime() {
		return chargeTime;
	}

	public void setChargeTime(String chargeTime) {
		this.chargeTime = chargeTime;
	}

	public int getPayNum() {
		return payNum;
	}

	public void setPayNum(int payNum) {
		this.payNum = payNum;
	}

	public double getActualFee() {
		return actualFee;
	}

	public void setActualFee(double actualFee) {
		this.actualFee = actualFee;
	}

	public double getReceivableFee() {
		return receivableFee;
	}

	public void setReceivableFee(double receivableFee) {
		this.receivableFee = receivableFee;
	}

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public BigInteger getCnplId() {
		return cnplId;
	}

	public void setCnplId(BigInteger cnplId) {
		this.cnplId = cnplId;
	}

	public String getCardsn() {
		return cardsn;
	}

	public void setCardsn(String cardsn) {
		this.cardsn = cardsn;
	}

	public Integer getPushTimes() {
		return pushTimes;
	}

	public void setPushTimes(Integer pushTimes) {
		this.pushTimes = pushTimes;
	}

}
