package com.cnfantasia.server.ms.revenue.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class EbuyRevenueTotal implements Serializable {
	
	private static final long serialVersionUID = 2116461458445749731L;

	private BigDecimal totalAmountDiscount; //优惠总额
	
	private BigDecimal totalAmountReal; //实付总额
	
	private BigDecimal totalAmount; //结算总额
	
	private BigDecimal totalPlatform; //平台收益额
	
	private BigDecimal totalPlatformUse; //平台使用费
	
	private BigDecimal totalRecommender; //推荐人收益额
	
	private BigDecimal totalWy; //物业收益额
	
	private BigDecimal totalAgent; //代理收益额
	
	private BigDecimal totalSupply; //店铺收益额
	
	private BigDecimal totalOrder; //订单额
	
	private BigDecimal totalOrderDiscount; //优惠总额
	
	private BigDecimal totalOrderReal; //实付总额

	public BigDecimal getTotalAmountDiscount() {
		return totalAmountDiscount;
	}

	public void setTotalAmountDiscount(BigDecimal totalAmountDiscount) {
		this.totalAmountDiscount = totalAmountDiscount;
	}

	public BigDecimal getTotalAmountReal() {
		return totalAmountReal;
	}

	public void setTotalAmountReal(BigDecimal totalAmountReal) {
		this.totalAmountReal = totalAmountReal;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public BigDecimal getTotalPlatform() {
		return totalPlatform;
	}

	public void setTotalPlatform(BigDecimal totalPlatform) {
		this.totalPlatform = totalPlatform;
	}

	public BigDecimal getTotalRecommender() {
		return totalRecommender;
	}

	public void setTotalRecommender(BigDecimal totalRecommender) {
		this.totalRecommender = totalRecommender;
	}

	public BigDecimal getTotalOrder() {
		return totalOrder;
	}

	public void setTotalOrder(BigDecimal totalOrder) {
		this.totalOrder = totalOrder;
	}

	public BigDecimal getTotalOrderDiscount() {
		return totalOrderDiscount;
	}

	public void setTotalOrderDiscount(BigDecimal totalOrderDiscount) {
		this.totalOrderDiscount = totalOrderDiscount;
	}

	public BigDecimal getTotalOrderReal() {
		return totalOrderReal;
	}

	public void setTotalOrderReal(BigDecimal totalOrderReal) {
		this.totalOrderReal = totalOrderReal;
	}

	public BigDecimal getTotalWy() {
		return totalWy;
	}

	public void setTotalWy(BigDecimal totalWy) {
		this.totalWy = totalWy;
	}

	public BigDecimal getTotalAgent() {
		return totalAgent;
	}

	public void setTotalAgent(BigDecimal totalAgent) {
		this.totalAgent = totalAgent;
	}

	public BigDecimal getTotalSupply() {
		return totalSupply;
	}

	public void setTotalSupply(BigDecimal totalSupply) {
		this.totalSupply = totalSupply;
	}

	public BigDecimal getTotalPlatformUse() {
		return totalPlatformUse;
	}

	public void setTotalPlatformUse(BigDecimal totalPlatformUse) {
		this.totalPlatformUse = totalPlatformUse;
	}

}
