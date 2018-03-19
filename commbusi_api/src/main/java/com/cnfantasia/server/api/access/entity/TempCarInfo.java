package com.cnfantasia.server.api.access.entity;

import java.math.BigInteger;

/**
 * 临停车信息
 * 
 * @author liyulin
 * @version 1.0 2017年12月7日 下午2:40:10
 */
public class TempCarInfo extends BaseCarResult {
	/** 当前停车费总金额 */
	private long amt;
	/** 当前停车费实付金额 */
	private long realAmt;
	/** 当前停车费优惠金额 */
	private long discountFee;
	/** 已缴费用 */
	private long paidFee;
	/** 入场时间（毫秒） */
	private long enterTime;
	/** 停车场对应的解放区gbId */
	private BigInteger gbId;
	/** 第三方订单（小蜜蜂车禁使用） */
	private String orderId;
	/** 停车场名字（小蜜蜂车禁使用） */
	private String plotName;

	public TempCarInfo() {
		super();
	}

	public TempCarInfo(boolean state) {
		super();
		super.setState(state);
	}

	public TempCarInfo(long amt, long realAmt, long discountFee, long paidFee, long enterTime, BigInteger gbId,
			String orderId, String plotName) {
		super();
		this.amt = amt;
		this.realAmt = realAmt;
		this.discountFee = discountFee;
		this.paidFee = paidFee;
		this.enterTime = enterTime;
		this.gbId = gbId;
		this.orderId = orderId;
		this.plotName = plotName;
	}

	public long getAmt() {
		return amt;
	}

	public void setAmt(long amt) {
		this.amt = amt;
	}

	public long getRealAmt() {
		return realAmt;
	}

	public void setRealAmt(long realAmt) {
		this.realAmt = realAmt;
	}

	public long getDiscountFee() {
		return discountFee;
	}

	public void setDiscountFee(long discountFee) {
		this.discountFee = discountFee;
	}

	public long getPaidFee() {
		return paidFee;
	}

	public void setPaidFee(long paidFee) {
		this.paidFee = paidFee;
	}

	public long getEnterTime() {
		return enterTime;
	}

	public void setEnterTime(long enterTime) {
		this.enterTime = enterTime;
	}

	public BigInteger getGbId() {
		return gbId;
	}

	public void setGbId(BigInteger gbId) {
		this.gbId = gbId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getPlotName() {
		return plotName;
	}

	public void setPlotName(String plotName) {
		this.plotName = plotName;
	}

}
