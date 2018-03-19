package com.cnfantasia.server.ms.ebuyProductSettle.entity;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * 单个运单的结算申请各种金额
 * 
 * @author liyulin
 * @version 1.0 2016年9月21日 下午4:02:56
 */
public class RevenueAllTotalAmount {
	private BigInteger deliveryOrderId;
	/** 结算金额 */
	private BigDecimal totalMoney;
	/** 实付 */
	private BigDecimal realPay;
	/** 优惠金额 */
	private BigDecimal preferAmount;
	/** 退款 */
	private BigDecimal refund;
	/** 解放区运费 */
	private BigDecimal jfqDeliveryFee;
	/** 供应商运费 */
	private BigDecimal gysDeliveryFee;
	/** 商品销售总额 */
	private BigDecimal totalSellAmount;
	/** 退款商品的销售总额 */
	private BigDecimal refundTotalSellAmount;
	/** 商品采购总额 */
	private BigDecimal purchaseAmount;
	/** 退款商品的采购总额 */
	private BigDecimal refundPurchaseAmount;

	/** 实付 */
	public BigDecimal getRealPay() {
		return realPay;
	}

	public void setRealPay(BigDecimal realPay) {
		this.realPay = realPay;
	}

	/** 优惠金额 */
	public BigDecimal getPreferAmount() {
		return preferAmount;
	}

	public void setPreferAmount(BigDecimal preferAmount) {
		this.preferAmount = preferAmount;
	}

	/** 退款 */
	public BigDecimal getRefund() {
		return refund;
	}

	public void setRefund(BigDecimal refund) {
		this.refund = refund;
	}

	/** 解放区运费 */
	public BigDecimal getJfqDeliveryFee() {
		return jfqDeliveryFee;
	}

	public void setJfqDeliveryFee(BigDecimal jfqDeliveryFee) {
		this.jfqDeliveryFee = jfqDeliveryFee;
	}

	/** 供应商运费 */
	public BigDecimal getGysDeliveryFee() {
		return gysDeliveryFee;
	}

	public void setGysDeliveryFee(BigDecimal gysDeliveryFee) {
		this.gysDeliveryFee = gysDeliveryFee;
	}

	/** 商品销售总额 */
	public BigDecimal getTotalSellAmount() {
		return totalSellAmount;
	}

	public void setTotalSellAmount(BigDecimal totalSellAmount) {
		this.totalSellAmount = totalSellAmount;
	}

	/** 退款商品的销售总额 */
	public BigDecimal getRefundTotalSellAmount() {
		return refundTotalSellAmount;
	}

	public void setRefundTotalSellAmount(BigDecimal refundTotalSellAmount) {
		this.refundTotalSellAmount = refundTotalSellAmount;
	}

	/** 商品采购总额 */
	public BigDecimal getPurchaseAmount() {
		return purchaseAmount;
	}

	public void setPurchaseAmount(BigDecimal purchaseAmount) {
		this.purchaseAmount = purchaseAmount;
	}

	/** 退款商品的采购总额 */
	public BigDecimal getRefundPurchaseAmount() {
		return refundPurchaseAmount;
	}

	public void setRefundPurchaseAmount(BigDecimal refundPurchaseAmount) {
		this.refundPurchaseAmount = refundPurchaseAmount;
	}
	
	/** 结算金额 */
	public BigDecimal getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(BigDecimal totalMoney) {
		this.totalMoney = totalMoney;
	}

	public BigInteger getDeliveryOrderId() {
		return deliveryOrderId;
	}

	public void setDeliveryOrderId(BigInteger deliveryOrderId) {
		this.deliveryOrderId = deliveryOrderId;
	}

	@Override
	public String toString() {
		return "RevenueAllTotalAmount [deliveryOrderId=" + deliveryOrderId
				+ ", totalMoney=" + totalMoney + ", realPay=" + realPay
				+ ", preferAmount=" + preferAmount + ", refund=" + refund
				+ ", jfqDeliveryFee=" + jfqDeliveryFee + ", gysDeliveryFee="
				+ gysDeliveryFee + ", totalSellAmount=" + totalSellAmount
				+ ", refundTotalSellAmount=" + refundTotalSellAmount
				+ ", purchaseAmount=" + purchaseAmount
				+ ", refundPurchaseAmount=" + refundPurchaseAmount + "]";
	}

}
