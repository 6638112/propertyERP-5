package com.cnfantasia.server.ms.ebuyProductSettle.entity;

import com.cnfantasia.server.common.utils.PriceUtil;
import com.cnfantasia.server.common.utils.excel.annotation.Excel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @ClassName: EbuyProductSettleApplyExportDto
 * @Date: 2016-08-10 14:47
 * @Auther: kangduo
 * @Description:(代销导出表excel)
 */
public class EbuyProductSettleApplyExportDto implements Serializable {
    private static final long serialVersionUID = 3899286701067368635L;


    private BigInteger deliveryOrderId;

    @Excel(title = "订单号")
    private String orderNo;

    @Excel(title = "支付时间")
    private String payTime;

    @Excel(title = "供应商名称")
    private String supplyMerchantName;

    @Excel(title = "商品名称")
    private String productName;

    @Excel(title = "购买数量")
    private Integer productQty;

    @Excel(title = "商品销售单价")
    private Integer productSellPrice;

    @Excel(title = "商品金额小计")
    private BigDecimal deliveryOrderProductTotalAmount;

    @Excel(title = "退款金额")
    private BigDecimal deliveryRefundFee;

    @Excel(title = "订单总金额")
    private BigDecimal deliveryOrderTotalAmount;

    @Excel(title = "订单结算金额")
    private BigDecimal deliveryOrderSettleAmount;

    @Excel(title = "运费")
    private BigDecimal deliveryRealFee;

    @Excel(title = "订单结算总额")
    private BigDecimal deliveryOrderTotalSettleAmount;

    @Excel(title = "收货人姓名")
    private String receiver;

    @Excel(title = "收货人联系方式")
    private String receiverMobile;

    @Excel(title = "收货地址")
    private String delivAddress;

    private Integer deliveryOrderAmount;
    private Integer deliveryOrderCoupon;
    private BigDecimal deliverySettleFee;
    private BigDecimal revenueRate;
    private Integer revenueType;


    public BigInteger getDeliveryOrderId() {
        return deliveryOrderId;
    }

    public void setDeliveryOrderId(BigInteger deliveryOrderId) {
        this.deliveryOrderId = deliveryOrderId;
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

    public String getSupplyMerchantName() {
        return supplyMerchantName;
    }

    public void setSupplyMerchantName(String supplyMerchantName) {
        this.supplyMerchantName = supplyMerchantName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getProductQty() {
        return productQty;
    }

    public void setProductQty(Integer productQty) {
        this.productQty = productQty;
    }

    public BigDecimal getProductSellPrice() {
        if (this.productSellPrice == null) {
            return null;
        }
        return PriceUtil.removeTailZero(PriceUtil.div100(this.productSellPrice.longValue()));
    }

    public void setProductSellPrice(Integer productSellPrice) {
        this.productSellPrice = productSellPrice;
    }

    public Integer getDeliveryOrderAmount() {
        return deliveryOrderAmount;
    }

    public void setDeliveryOrderAmount(Integer deliveryOrderAmount) {
        this.deliveryOrderAmount = deliveryOrderAmount;
    }

    public Integer getDeliveryOrderCoupon() {
        return deliveryOrderCoupon;
    }

    public void setDeliveryOrderCoupon(Integer deliveryOrderCoupon) {
        this.deliveryOrderCoupon = deliveryOrderCoupon;
    }

    public BigDecimal getDeliveryRealFee() {
        return deliveryRealFee;
    }

    public void setDeliveryRealFee(BigDecimal deliveryRealFee) {
        this.deliveryRealFee = deliveryRealFee;
    }

    public BigDecimal getDeliveryRefundFee() {
        return PriceUtil.removeTailZero(deliveryRefundFee);
    }

    public void setDeliveryRefundFee(BigDecimal deliveryRefundFee) {
        this.deliveryRefundFee = deliveryRefundFee;
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

    public String getDelivAddress() {
        return delivAddress;
    }

    public void setDelivAddress(String delivAddress) {
        this.delivAddress = delivAddress;
    }

    public BigDecimal getDeliveryOrderProductTotalAmount() {
        return PriceUtil.removeTailZero(deliveryOrderProductTotalAmount);
    }

    public void setDeliveryOrderProductTotalAmount(BigDecimal deliveryOrderProductTotalAmount) {
        this.deliveryOrderProductTotalAmount = deliveryOrderProductTotalAmount;
    }

    public BigDecimal getDeliveryOrderTotalAmount() {
        return deliveryOrderTotalAmount;
    }

    public void setDeliveryOrderTotalAmount(BigDecimal deliveryOrderTotalAmount) {
        this.deliveryOrderTotalAmount = deliveryOrderTotalAmount;
    }

    public BigDecimal getDeliveryOrderSettleAmount() {
        return PriceUtil.removeTailZero(deliveryOrderSettleAmount);
    }

    public void setDeliveryOrderSettleAmount(BigDecimal deliveryOrderSettleAmount) {
        this.deliveryOrderSettleAmount = deliveryOrderSettleAmount;
    }

    public BigDecimal getDeliverySettleFee() {
        return PriceUtil.removeTailZero(deliverySettleFee);
    }

    public void setDeliverySettleFee(BigDecimal deliverySettleFee) {
        this.deliverySettleFee = deliverySettleFee;
    }

    public BigDecimal getDeliveryOrderTotalSettleAmount() {
        return PriceUtil.removeTailZero(deliveryOrderTotalSettleAmount);
    }

    public void setDeliveryOrderTotalSettleAmount(BigDecimal deliveryOrderTotalSettleAmount) {
        this.deliveryOrderTotalSettleAmount = deliveryOrderTotalSettleAmount;
    }

    public BigDecimal getRevenueRate() {
        return revenueRate;
    }

    public void setRevenueRate(BigDecimal revenueRate) {
        this.revenueRate = revenueRate;
    }

    public Integer getRevenueType() {
        return revenueType;
    }

    public void setRevenueType(Integer revenueType) {
        this.revenueType = revenueType;
    }
}
