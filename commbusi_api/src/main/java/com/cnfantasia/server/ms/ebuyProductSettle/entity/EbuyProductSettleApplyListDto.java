package com.cnfantasia.server.ms.ebuyProductSettle.entity;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * 结算申请页面列表
 * 
 * @author liyulin
 * @version 1.0 2016年6月8日 下午2:59:46
 */
public class EbuyProductSettleApplyListDto {

	/** t_ebuy_delivery_order表f_id */
	private BigInteger delieveOrderId;
	/** 订单号 */
	private String orderNo;
	/** 支付时间 */
	private String payTime;
	/** 商品总额 */
	private BigDecimal productAmount;
	/** 运费 */
	private BigDecimal deliveryFee;
	/** 退款金额 */
	private BigDecimal refundFee;
	/** 订单总金额 */
	private BigDecimal orderTotalAmount;
	/** 订单结算金额 */
	private BigDecimal revenueAmount;
	/** 供应商 */
	private String merchant;
	/** 实际支付 */
	private BigDecimal realPay;

	public EbuyProductSettleApplyListDto() {
		super();
	}

	public EbuyProductSettleApplyListDto(BigInteger delieveOrderId, String orderNo, String payTime, BigDecimal productAmount, BigDecimal deliveryFee,
			BigDecimal refundFee, BigDecimal orderTotalAmount, BigDecimal revenueAmount, String merchant, BigDecimal realPay) {
		super();
		this.delieveOrderId = delieveOrderId;
		this.orderNo = orderNo;
		this.payTime = payTime;
		this.productAmount = productAmount;
		this.deliveryFee = deliveryFee;
		this.refundFee = refundFee;
		this.orderTotalAmount = orderTotalAmount;
		this.revenueAmount = revenueAmount;
		this.merchant = merchant;
		this.realPay = realPay;
	}

	public BigInteger getDelieveOrderId() {
		return delieveOrderId;
	}

	public void setDelieveOrderId(BigInteger delieveOrderId) {
		this.delieveOrderId = delieveOrderId;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getPayTime() {
		return payTime;
	}

	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}

	public BigDecimal getProductAmount() {
		return productAmount;
	}

	public void setProductAmount(BigDecimal productAmount) {
		this.productAmount = productAmount;
	}

	public BigDecimal getDeliveryFee() {
		return deliveryFee;
	}

	public void setDeliveryFee(BigDecimal deliveryFee) {
		this.deliveryFee = deliveryFee;
	}

	public BigDecimal getRefundFee() {
		return refundFee;
	}

	public void setRefundFee(BigDecimal refundFee) {
		this.refundFee = refundFee;
	}

	public BigDecimal getOrderTotalAmount() {
		return orderTotalAmount;
	}

	public void setOrderTotalAmount(BigDecimal orderTotalAmount) {
		this.orderTotalAmount = orderTotalAmount;
	}

	public BigDecimal getRevenueAmount() {
		return revenueAmount;
	}

	public void setRevenueAmount(BigDecimal revenueAmount) {
		this.revenueAmount = revenueAmount;
	}

	public String getMerchant() {
		return merchant;
	}

	public void setMerchant(String merchant) {
		this.merchant = merchant;
	}

	public BigDecimal getRealPay() {
		return realPay;
	}

	public void setRealPay(BigDecimal realPay) {
		this.realPay = realPay;
	}

}
