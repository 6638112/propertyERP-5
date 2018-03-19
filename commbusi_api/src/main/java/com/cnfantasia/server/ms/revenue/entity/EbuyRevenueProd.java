package com.cnfantasia.server.ms.revenue.entity;

import java.io.Serializable;

import com.cnfantasia.server.business.pub.utils.BigDecimalUtil;

public class EbuyRevenueProd implements Serializable {
	
	private static final long serialVersionUID = 2919562646137428022L;
	
	private Long productId;

	private String productName;
	
	private Integer qty;
	
	private Double salePrice; //出售价
	
	private Double settlementPrice; //结算价格
	
//	private Double settlePriceAll; //结算价小计
	
	private Double deliveFee; //结算运费
	
	private Double jfqFee; //解放区运费

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

	public Double getSettlementPrice() {
		if(settlementPrice != null && settlementPrice > 0) {
			return settlementPrice;
		} else {
			return salePrice;
		}
	}

	public void setSettlementPrice(Double settlementPrice) {
		this.settlementPrice = settlementPrice;
	}

	public Double getSettlePriceAll() {
		return BigDecimalUtil.mul(qty, getSettlementPrice());
	}

//	public void setSettlePriceAll(Double settlePriceAll) {
//		this.settlePriceAll = settlePriceAll;
//	}

	public Double getDeliveFee() {
		if(deliveFee != null && deliveFee > 0) {
			return deliveFee;
		} else {
			return jfqFee;
		}
	}

	public void setDeliveFee(Double deliveFee) {
		this.deliveFee = deliveFee;
	}

	public Double getJfqFee() {
		return jfqFee;
	}

	public void setJfqFee(Double jfqFee) {
		this.jfqFee = jfqFee;
	}

	public Double getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(Double salePrice) {
		this.salePrice = salePrice;
	}
	
}
