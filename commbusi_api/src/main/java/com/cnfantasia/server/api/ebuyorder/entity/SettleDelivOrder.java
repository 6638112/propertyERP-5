package com.cnfantasia.server.api.ebuyorder.entity;

import com.cnfantasia.server.common.utils.PriceUtil;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

/**
 * @ClassName: SettleDelivOrder
 * @Date: 2016-09-14 15:15
 * @Auther: kangduo
 * @Description:(申请结算列表对象)
 */
public class SettleDelivOrder implements Serializable {
    private static final long serialVersionUID = -6457688276627120464L;
    private BigInteger deliveryOrderId;
    private String deliveryAddress;
    private Long deliveryOrderAmount;
    private Long deliveryOrderCoupon;
    //运单总额
    private Long deliveryOrderTotalAmount;
    private BigDecimal deliveryOrderRefundAmount;
    private String deliveryOrderAddTime;
    private boolean hasDelivRefund;
    private Integer productQty;

    public BigInteger getDeliveryOrderId() {
        return deliveryOrderId;
    }

    public void setDeliveryOrderId(BigInteger deliveryOrderId) {
        this.deliveryOrderId = deliveryOrderId;
    }

    public String getDeliveryAddress() {
    	if(deliveryAddress == null) {
    		return "维修耗材，到店自取";
    	}
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public Long getDeliveryOrderAmount() {
        return deliveryOrderAmount;
    }

    public void setDeliveryOrderAmount(Long deliveryOrderAmount) {
        this.deliveryOrderAmount = deliveryOrderAmount;
    }

    public Long getDeliveryOrderCoupon() {
        return deliveryOrderCoupon;
    }

    public void setDeliveryOrderCoupon(Long deliveryOrderCoupon) {
        this.deliveryOrderCoupon = deliveryOrderCoupon;
    }

    public BigDecimal getDeliveryOrderTotalAmount() {
        return PriceUtil.div100(deliveryOrderAmount + deliveryOrderCoupon).setScale(2, RoundingMode.HALF_UP);
    }

    public void setDeliveryOrderTotalAmount(Long deliveryOrderTotalAmount) {
        this.deliveryOrderTotalAmount = deliveryOrderTotalAmount;
    }

    public String getDeliveryOrderAddTime() {
        return deliveryOrderAddTime;
    }

    public void setDeliveryOrderAddTime(String deliveryOrderAddTime) {
        this.deliveryOrderAddTime = deliveryOrderAddTime;
    }

    public boolean isHasDelivRefund() {
        return hasDelivRefund;
    }

    public void setHasDelivRefund(boolean hasDelivRefund) {
        this.hasDelivRefund = hasDelivRefund;
    }

    public BigDecimal getDeliveryOrderRefundAmount() {
        return deliveryOrderRefundAmount;
    }

    public void setDeliveryOrderRefundAmount(BigDecimal deliveryOrderRefundAmount) {
        this.deliveryOrderRefundAmount = deliveryOrderRefundAmount;
    }

    public Integer getProductQty() {
        return productQty;
    }

    public void setProductQty(Integer productQty) {
        this.productQty = productQty;
    }
}
