package com.cnfantasia.server.ms.limitBuyActivity.entity;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * 限时抢购
 * 
 * @author liyulin
 * @version 1.0 2016年12月29日 上午9:44:48
 */
public class LimitBuyActivityListDto {
	
	private BigInteger lbaId;
	private BigInteger productId;
	/** 限时购标题 */
	private String title;
	/** 商品名 */
	private String productName;
	/** 供应商 */
	private String merchantName;
	/** 原价 */
	private BigDecimal originalPrice;
	/** 抢购价 */
	private BigDecimal robPrice;
	/**
	 * 库存
	 */
	private int leftCount;
	/**
	 * 每人限购
	 */
	private int maxCanBuy;
	/** 开始时间 */
	private String startTime;
	/** 结束时间 */
	private String endTime;
	/** 开始状态（startTime < now()） */
	private String startStatus;
	/** 结束状态 （endTime > now()） */
	private String endStatus;
	/** 显示位置 */
	private Integer positionType;

	public int getMaxCanBuy() {
		return maxCanBuy;
	}

	public void setMaxCanBuy(int maxCanBuy) {
		this.maxCanBuy = maxCanBuy;
	}

	public int getLeftCount() {
		return leftCount;
	}

	public void setLeftCount(int leftCount) {
		this.leftCount = leftCount;
	}

	public BigInteger getLbaId() {
		return lbaId;
	}

	public void setLbaId(BigInteger lbaId) {
		this.lbaId = lbaId;
	}

	public BigInteger getProductId() {
		return productId;
	}

	public void setProductId(BigInteger productId) {
		this.productId = productId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public BigDecimal getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(BigDecimal originalPrice) {
		this.originalPrice = originalPrice;
	}

	public BigDecimal getRobPrice() {
		return robPrice;
	}

	public void setRobPrice(BigDecimal robPrice) {
		this.robPrice = robPrice;
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

	public String getStartStatus() {
		return startStatus;
	}

	public void setStartStatus(String startStatus) {
		this.startStatus = startStatus;
	}

	public String getEndStatus() {
		return endStatus;
	}

	public void setEndStatus(String endStatus) {
		this.endStatus = endStatus;
	}

	public Integer getPositionType() {
		return positionType;
	}

	public void setPositionType(Integer positionType) {
		this.positionType = positionType;
	}
}
