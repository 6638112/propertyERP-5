package com.cnfantasia.server.ms.ebuyProductSettle.entity;

import com.cnfantasia.server.common.utils.PriceUtil;
import com.cnfantasia.server.common.utils.excel.annotation.Excel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @ClassName: EbuyProductSettleDetailAdminDto
 * @Date: 2016-08-11 11:25
 * @Auther: kangduo
 * @Description:(结算申请详情导出表)
 */
public class EbuyProductSettleDetailAdminDto implements Serializable {
    private static final long serialVersionUID = -8779425736541612669L;

    @Excel(title = "订单号")
    private String orderNo;

    @Excel(title = "解放号")
    private BigInteger buyerId;

    @Excel(title = "支付时间")
    private String payTime;

    @Excel(title = "供应商名称")
    private String supplyMerchantName;

    @Excel(title = "商品名称")
    private String productName;

    @Excel(title = "购买数量")
    private Integer productQty;

    @Excel(title = "商品销售单价(元)")
    private Integer productSellPrice;

    @Excel(title = "销售金额小计")
    private BigDecimal productTotalAmount;

    @Excel(title = "运单销售小计")
    private BigDecimal deliveryOrderTotalAmount;

    @Excel(title = "销售退款金额")
    private BigDecimal refundFee;

    @Excel(title = "运单销售额")
    private BigDecimal deliveryOrderSellAmount;

    @Excel(title = "运单结算金额")
    private BigDecimal deliveryOrderSettleAmount;

    @Excel(title = "运费")
    private BigDecimal deliveryRealFee;

    @Excel(title = "运单结算总额")
    private BigDecimal deliveryOrderTotalSettleAmount;

    @Excel(title = "最终实际支付")
    private BigDecimal deliveryOrderRealPay;

    @Excel(title = "补贴")
    private BigDecimal butie;

    @Excel(title = "交易平台")
    private String payMethodDesc;

    @Excel(title = "支付流水号")
    private String payFlowNo;

    @Excel(title = "收货人姓名")
    private String receiver;

    @Excel(title = "收货人联系方式")
    private String receiverMobile;

    @Excel(title = "收货地址")
    private String delivAddress;

    private BigInteger deliveryOrderId;
    private Integer payMethod;
    private Integer deliveryOrderAmount;
    private Integer deliveryOrderCoupon;
    private BigDecimal deliverySettleFee;
    private BigDecimal refundMoney;
    private Integer refundCoupon;
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

    public BigInteger getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(BigInteger buyerId) {
        this.buyerId = buyerId;
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

    public BigDecimal getProductTotalAmount() {
        return PriceUtil.removeTailZero(productTotalAmount);
    }

    public void setProductTotalAmount(BigDecimal productTotalAmount) {
        this.productTotalAmount = productTotalAmount;
    }

    public BigDecimal getDeliveryOrderTotalAmount() {
        return PriceUtil.removeTailZero(deliveryOrderTotalAmount);
    }

    public void setDeliveryOrderTotalAmount(BigDecimal deliveryOrderTotalAmount) {
        this.deliveryOrderTotalAmount = deliveryOrderTotalAmount;
    }

    public BigDecimal getRefundFee() {
        return PriceUtil.removeTailZero(refundFee);
    }

    public void setRefundFee(BigDecimal refundFee) {
        this.refundFee = refundFee;
    }

    public BigDecimal getDeliveryOrderSellAmount() {
        return PriceUtil.removeTailZero(deliveryOrderSellAmount);
    }

    public void setDeliveryOrderSellAmount(BigDecimal deliveryOrderSellAmount) {
        this.deliveryOrderSellAmount = deliveryOrderSellAmount;
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

    public BigDecimal getDeliveryOrderRealPay() {
        return PriceUtil.removeTailZero(deliveryOrderRealPay);
    }

    public void setDeliveryOrderRealPay(BigDecimal deliveryOrderRealPay) {
        this.deliveryOrderRealPay = deliveryOrderRealPay;
    }

    public BigDecimal getButie() {
        return PriceUtil.removeTailZero(butie);
    }

    public void setButie(BigDecimal butie) {
        this.butie = butie;
    }

    public String getPayMethodDesc() {
        if(payMethod == null){
            return "";
        } else if (payMethod.equals(1)) {
            return "微信支付";
        } else if (payMethod.equals(2)) {
            return "支付宝";
        } else if (payMethod.equals(3)) {
            return "银联支付";
        } else if (payMethod.equals(4)) {
            return "纯粮票支付";
        } else if (payMethod.equals(5)) {
            return "纯积分支付";
        } else if (payMethod.equals(6)) {
            return "微信轻应用支付";
        } else if (payMethod.equals(7)) {
            return "纯优惠券支付";
        } else if (payMethod.equals(9)) {
            return "双乾支付";
        } else {
            return "";
        }
    }

    public void setPayMethodDesc(String payMethodDesc) {
        this.payMethodDesc = payMethodDesc;
    }

    public String getPayFlowNo() {
        return payFlowNo;
    }

    public void setPayFlowNo(String payFlowNo) {
        this.payFlowNo = payFlowNo;
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

    public Integer getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(Integer payMethod) {
        this.payMethod = payMethod;
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

    public BigDecimal getRefundMoney() {
        return refundMoney;
    }

    public void setRefundMoney(BigDecimal refundMoney) {
        this.refundMoney = refundMoney;
    }

    public Integer getRefundCoupon() {
        return refundCoupon;
    }

    public void setRefundCoupon(Integer refundCoupon) {
        this.refundCoupon = refundCoupon;
    }
}
