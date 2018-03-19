package com.cnfantasia.server.ms.limitBuyActivity.entity;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * 限时抢购更新
 * 
 * @author liyulin
 * @version 1.0 2016年12月29日 上午11:35:00
 */
public class LimitBuyActivityUpdateParam {
	private BigInteger id;
	/** 抢购价 */
	private BigDecimal robPrice;
	/** 标题 */
	private String title;
	/** 开始时间 */
	private String startTime;
	/** 结束时间 */
	private String endTime;
	
	/** 原价  单位：分*/
	private BigDecimal originalPrice;

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public BigDecimal getRobPrice() {
		return robPrice;
	}

	public void setRobPrice(BigDecimal robPrice) {
		this.robPrice = robPrice;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public BigDecimal getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(BigDecimal originalPrice) {
		this.originalPrice = originalPrice;
	}
}
