package com.cnfantasia.server.ms.limitBuyActivity.entity;

import java.math.BigInteger;

/**
 * 限时购详情
 * 
 * @author liyulin
 * @version 1.0 2016年12月29日 上午10:39:39
 */
public class LimitBuyActivityDetailDto extends LimitBuyActivityUpdateParam {
	
	/** 商品id */
	private BigInteger productId;
	/** 货架分类 */
	private String shelfType;
	/** 商品名 */
	private String productName;
	/** 促销库存 */
	private long leftCount;
	/** 
	 * 每人限购
	 */
	private long maxCanBuy;
	/** 供应商 */
	private String merchantName;

	public long getMaxCanBuy() {
		return maxCanBuy;
	}

	public void setMaxCanBuy(long maxCanBuy) {
		this.maxCanBuy = maxCanBuy;
	}

	public long getLeftCount() {
		return leftCount;
	}

	public void setLeftCount(long leftCount) {
		this.leftCount = leftCount;
	}

	public BigInteger getProductId() {
		return productId;
	}

	public void setProductId(BigInteger productId) {
		this.productId = productId;
	}

	public String getShelfType() {
		return shelfType;
	}

	public void setShelfType(String shelfType) {
		this.shelfType = shelfType;
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

}
