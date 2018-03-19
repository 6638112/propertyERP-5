package com.cnfantasia.server.ms.ebuyProductSettle.entity;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * 供应商结算申请记录导出Dto
 * 
 * @author liyulin
 * @version 1.0 2016年6月14日 下午1:26:49
 */
public class EbuyProductSettleApplyListExportDto {

	/** t_ebuy_delivery_order表f_id */
	private BigInteger delieveOrderId;
	/** 订单号 */
	private String orderNo;
	/** 支付时间 */
	private String payTime;
	/** 供应商 */
	private String merchant;
	/** 商品名称 */
	private String productName;
	/** 购买数量 */
	private Integer buyNum;
	/** 商品结算单价 */
	private BigDecimal purchasePrice;
	/** 商品结算金额 */
	private BigDecimal productAmount;
	/** 商品金额小计 */
	private BigDecimal deliveryProductAmount;
	/** 运费 */
	private BigDecimal deliveryFee;
	/** 退款金额 */
	private BigDecimal refundAmount;
	/** 订单总金额 */
	private BigDecimal deliveryAmount;
	/** 结算金额 */
	private BigDecimal revenueAmount;
	/** 收货人 */
	private String receiver;
	/** 收货人联系方式 */
	private String receiverMobile;
	/** 收货地址 */
	private String address;

	public EbuyProductSettleApplyListExportDto() {
		super();
	}

	public EbuyProductSettleApplyListExportDto(BigInteger delieveOrderId, String orderNo, String payTime, String merchant, String productName, Integer buyNum,
			BigDecimal purchasePrice, BigDecimal productAmount, BigDecimal deliveryProductAmount, BigDecimal deliveryFee, BigDecimal refundAmount,
			BigDecimal deliveryAmount, BigDecimal revenueAmount, String receiver, String receiverMobile, String address) {
		super();
		this.delieveOrderId = delieveOrderId;
		this.orderNo = orderNo;
		this.payTime = payTime;
		this.merchant = merchant;
		this.productName = productName;
		this.buyNum = buyNum;
		this.purchasePrice = purchasePrice;
		this.productAmount = productAmount;
		this.deliveryProductAmount = deliveryProductAmount;
		this.deliveryFee = deliveryFee;
		this.refundAmount = refundAmount;
		this.deliveryAmount = deliveryAmount;
		this.revenueAmount = revenueAmount;
		this.receiver = receiver;
		this.receiverMobile = receiverMobile;
		this.address = address;
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

	public String getMerchant() {
		return merchant;
	}

	public void setMerchant(String merchant) {
		this.merchant = merchant;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getBuyNum() {
		return buyNum;
	}

	public void setBuyNum(Integer buyNum) {
		this.buyNum = buyNum;
	}

	public BigDecimal getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(BigDecimal purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public BigDecimal getProductAmount() {
		return productAmount;
	}

	public void setProductAmount(BigDecimal productAmount) {
		this.productAmount = productAmount;
	}

	public BigDecimal getDeliveryProductAmount() {
		return deliveryProductAmount;
	}

	public void setDeliveryProductAmount(BigDecimal deliveryProductAmount) {
		this.deliveryProductAmount = deliveryProductAmount;
	}

	public BigDecimal getDeliveryFee() {
		return deliveryFee;
	}

	public void setDeliveryFee(BigDecimal deliveryFee) {
		this.deliveryFee = deliveryFee;
	}

	public BigDecimal getRefundAmount() {
		return refundAmount;
	}

	public void setRefundAmount(BigDecimal refundAmount) {
		this.refundAmount = refundAmount;
	}

	public BigDecimal getDeliveryAmount() {
		return deliveryAmount;
	}

	public void setDeliveryAmount(BigDecimal deliveryAmount) {
		this.deliveryAmount = deliveryAmount;
	}

	public BigDecimal getRevenueAmount() {
		return revenueAmount;
	}

	public void setRevenueAmount(BigDecimal revenueAmount) {
		this.revenueAmount = revenueAmount;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getReceiverMobile() {
		return receiverMobile;
	}

	public void setReceiverMobile(String receiverMobile) {
		this.receiverMobile = receiverMobile;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
