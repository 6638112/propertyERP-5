package com.cnfantasia.server.api.ebuyorder.entity;

import com.cnfantasia.server.common.utils.PriceUtil;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.List;

/**
 * @ClassName: DeliveryOrderDetailEntity
 * @Date: 2016-09-18 11:19
 * @Auther: kangduo
 * @Description:(运单详情)
 */
public class DeliveryOrderDetailEntity implements Serializable{

    private static final long serialVersionUID = -8556857030062760371L;

    private BigInteger id;
    private BigInteger orderId;
    private String orderNo;
    private String delivAddress;
    private BigDecimal totalDeliveryOrderFee;
    private BigDecimal totalProductFee;
    private Long amount;
    private Long totalCoupon;
    private Long deliveryRealFee;
    private String delivPeopleName;
    private String delivPhone;
    private Integer refundType;
    private BigDecimal deliveryOrderRefundFee;
    private Integer payMethod;
    private String payTime;
    private int totalProductQty;
    private String refundReason;
    private String refundApplyTime;

    private List<OrderProduct> orderProductList;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public BigInteger getOrderId() {
        return orderId;
    }

    public void setOrderId(BigInteger orderId) {
        this.orderId = orderId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getDelivAddress() {
        return delivAddress;
    }

    public void setDelivAddress(String delivAddress) {
        this.delivAddress = delivAddress;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public BigDecimal getTotalCoupon() {
        return PriceUtil.div100(totalCoupon).setScale(2, RoundingMode.HALF_UP);
    }

    public void setTotalCoupon(Long totalCoupon) {
        this.totalCoupon = totalCoupon;
    }

    public BigDecimal getDeliveryRealFee() {
        return PriceUtil.div100(deliveryRealFee).setScale(2, RoundingMode.HALF_UP);
    }

    public void setDeliveryRealFee(Long deliveryRealFee) {
        this.deliveryRealFee = deliveryRealFee;
    }

    public String getDelivPeopleName() {
        return delivPeopleName;
    }

    public void setDelivPeopleName(String delivPeopleName) {
        this.delivPeopleName = delivPeopleName;
    }

    public String getDelivPhone() {
        return delivPhone;
    }

    public void setDelivPhone(String delivPhone) {
        this.delivPhone = delivPhone;
    }

    public Integer getRefundType() {
        return refundType;
    }

    public void setRefundType(Integer refundType) {
        this.refundType = refundType;
    }

    public BigDecimal getDeliveryOrderRefundFee() {
        return deliveryOrderRefundFee.setScale(2, RoundingMode.HALF_UP);
    }

    public void setDeliveryOrderRefundFee(BigDecimal deliveryOrderRefundFee) {
        this.deliveryOrderRefundFee = deliveryOrderRefundFee;
    }

    public Integer getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(Integer payMethod) {
        this.payMethod = payMethod;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public List<OrderProduct> getOrderProductList() {
        return orderProductList;
    }

    public void setOrderProductList(List<OrderProduct> orderProductList) {
        this.orderProductList = orderProductList;
    }

    public BigDecimal getTotalDeliveryOrderFee() {
        return PriceUtil.div100(amount + totalCoupon).setScale(2, RoundingMode.HALF_UP);
    }

    public void setTotalDeliveryOrderFee(BigDecimal totalDeliveryOrderFee) {
        this.totalDeliveryOrderFee = totalDeliveryOrderFee;
    }

    public BigDecimal getTotalProductFee() {
        return PriceUtil.div100(amount + totalCoupon - deliveryRealFee).setScale(2, RoundingMode.HALF_UP);
    }

    public void setTotalProductFee(BigDecimal totalProductFee) {
        this.totalProductFee = totalProductFee;
    }

    public int getTotalProductQty() {
        int total = 0;
        for (OrderProduct orderProduct : orderProductList) {
            total += orderProduct.getQty();
        }
        return total;
    }

    public void setTotalProductQty(int totalProductQty) {
        this.totalProductQty = totalProductQty;
    }

    public String getRefundReason() {
        return refundReason;
    }

    public void setRefundReason(String refundReason) {
        this.refundReason = refundReason;
    }

    public String getRefundApplyTime() {
        return refundApplyTime;
    }

    public void setRefundApplyTime(String refundApplyTime) {
        this.refundApplyTime = refundApplyTime;
    }
}
