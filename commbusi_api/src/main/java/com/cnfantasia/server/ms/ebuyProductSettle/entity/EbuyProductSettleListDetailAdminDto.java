package com.cnfantasia.server.ms.ebuyProductSettle.entity;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * 管理员结算详情导出
 * 
 * @author liyulin
 * @version 1.0 2016年6月17日 下午5:54:18
 */
public class EbuyProductSettleListDetailAdminDto {

	/** t_ebuy_delivery_order表f_id */
	private BigInteger delieveOrderId;
	/** 订单号 */
	private String orderNo;
	/** 解放号 */
	private String buyerId;
	/** 支付时间 */
	private String payTime;
	/** 供应商名称 */
	private String merchant;
	/** 商品名称 */
	private String productName;
	/** 购买数量 */
	private String buyNum;
	/** 商品销售单价 */
	private BigDecimal price;
	/** 采购单价 */
	private BigDecimal purchasePrice;
	/** 销售金额小计 */
	private BigDecimal totalSellAmount;
	/** 运单销售小计 */
	private BigDecimal deliveryProductAmount;
	/** 解放区运费 */
	private BigDecimal deliveryRealFee;
	/** 销售退款金额 */
	private BigDecimal refundAmount;
	/** 运单实际销售额 */
	private BigDecimal deliverySellAmount;
	/** 采购小计 */
	private BigDecimal purchaseAmount;
	/** 运单采购小计 */
	private BigDecimal deliveryPurchaseAmount;
	/** 运费 */
	private BigDecimal deliverySettlementFee;
	/** 采购退款金额 */
	private BigDecimal purchaseRefund;
	/** 运单采购金额 */
	private BigDecimal deliveryTotalPurchaseAmount;
	/** 运单结算金额 */
	private BigDecimal revenueAmount;
	/** 最终实际支付 */
	private BigDecimal realPay;
	/** 交易平台 */
	private String payMethod;
	/** 交易流水号 */
	private String flowNo;
	/** 收货人姓名 */
	private String receiver;
	/** 收货人联系方式 */
	private String receiverMobile;
	/** 收货地址 */
	private String address;

	public EbuyProductSettleListDetailAdminDto() {
		super();
	}

	public EbuyProductSettleListDetailAdminDto(BigInteger delieveOrderId, String orderNo, String buyerId, String payTime, String merchant, String productName,
			String buyNum, BigDecimal price, BigDecimal purchasePrice, BigDecimal totalSellAmount, BigDecimal deliveryProductAmount,
			BigDecimal deliveryRealFee, BigDecimal refundAmount, BigDecimal deliverySellAmount, BigDecimal purchaseAmount, BigDecimal deliveryPurchaseAmount,
			BigDecimal deliverySettlementFee, BigDecimal purchaseRefund, BigDecimal deliveryTotalPurchaseAmount, BigDecimal revenueAmount, BigDecimal realPay,
			String payMethod, String flowNo, String receiver, String receiverMobile, String address) {
		super();
		this.delieveOrderId = delieveOrderId;
		this.orderNo = orderNo;
		this.buyerId = buyerId;
		this.payTime = payTime;
		this.merchant = merchant;
		this.productName = productName;
		this.buyNum = buyNum;
		this.price = price;
		this.purchasePrice = purchasePrice;
		this.totalSellAmount = totalSellAmount;
		this.deliveryProductAmount = deliveryProductAmount;
		this.deliveryRealFee = deliveryRealFee;
		this.refundAmount = refundAmount;
		this.deliverySellAmount = deliverySellAmount;
		this.purchaseAmount = purchaseAmount;
		this.deliveryPurchaseAmount = deliveryPurchaseAmount;
		this.deliverySettlementFee = deliverySettlementFee;
		this.purchaseRefund = purchaseRefund;
		this.deliveryTotalPurchaseAmount = deliveryTotalPurchaseAmount;
		this.revenueAmount = revenueAmount;
		this.realPay = realPay;
		this.payMethod = payMethod;
		this.flowNo = flowNo;
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

	public String getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
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

	public String getBuyNum() {
		return buyNum;
	}

	public void setBuyNum(String buyNum) {
		this.buyNum = buyNum;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(BigDecimal purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public BigDecimal getTotalSellAmount() {
		return totalSellAmount;
	}

	public void setTotalSellAmount(BigDecimal totalSellAmount) {
		this.totalSellAmount = totalSellAmount;
	}

	public BigDecimal getDeliveryProductAmount() {
		return deliveryProductAmount;
	}

	public void setDeliveryProductAmount(BigDecimal deliveryProductAmount) {
		this.deliveryProductAmount = deliveryProductAmount;
	}

	public BigDecimal getDeliveryRealFee() {
		return deliveryRealFee;
	}

	public void setDeliveryRealFee(BigDecimal deliveryRealFee) {
		this.deliveryRealFee = deliveryRealFee;
	}

	public BigDecimal getRefundAmount() {
		return refundAmount;
	}

	public void setRefundAmount(BigDecimal refundAmount) {
		this.refundAmount = refundAmount;
	}

	public BigDecimal getDeliverySellAmount() {
		return deliverySellAmount;
	}

	public void setDeliverySellAmount(BigDecimal deliverySellAmount) {
		this.deliverySellAmount = deliverySellAmount;
	}

	public BigDecimal getPurchaseAmount() {
		return purchaseAmount;
	}

	public void setPurchaseAmount(BigDecimal purchaseAmount) {
		this.purchaseAmount = purchaseAmount;
	}

	public BigDecimal getDeliveryPurchaseAmount() {
		return deliveryPurchaseAmount;
	}

	public void setDeliveryPurchaseAmount(BigDecimal deliveryPurchaseAmount) {
		this.deliveryPurchaseAmount = deliveryPurchaseAmount;
	}

	public BigDecimal getDeliverySettlementFee() {
		return deliverySettlementFee;
	}

	public void setDeliverySettlementFee(BigDecimal deliverySettlementFee) {
		this.deliverySettlementFee = deliverySettlementFee;
	}

	public BigDecimal getPurchaseRefund() {
		return purchaseRefund;
	}

	public void setPurchaseRefund(BigDecimal purchaseRefund) {
		this.purchaseRefund = purchaseRefund;
	}

	public BigDecimal getDeliveryTotalPurchaseAmount() {
		return deliveryTotalPurchaseAmount;
	}

	public void setDeliveryTotalPurchaseAmount(BigDecimal deliveryTotalPurchaseAmount) {
		this.deliveryTotalPurchaseAmount = deliveryTotalPurchaseAmount;
	}

	public BigDecimal getRevenueAmount() {
		return revenueAmount;
	}

	public void setRevenueAmount(BigDecimal revenueAmount) {
		this.revenueAmount = revenueAmount;
	}

	public BigDecimal getRealPay() {
		return realPay;
	}

	public void setRealPay(BigDecimal realPay) {
		this.realPay = realPay;
	}

	public String getPayMethod() {
		return payMethod;
	}

	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}

	public String getFlowNo() {
		return flowNo;
	}

	public void setFlowNo(String flowNo) {
		this.flowNo = flowNo;
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
