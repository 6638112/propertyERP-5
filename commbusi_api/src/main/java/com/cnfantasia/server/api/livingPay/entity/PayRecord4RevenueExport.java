package com.cnfantasia.server.api.livingPay.entity;

import java.math.BigDecimal;

/**
 * @Author: wenfq
 * @Date: 2017-11-21 11:41
 */
public class PayRecord4RevenueExport {
    private long livingPayRecordId;
    private int livingPayRecordStatus;//记录状态
    private String orderNo;
    private String chargeObject;//户号
    private String feeTypeName;
    private String roomNo;
    private String address;
    private BigDecimal amount;
    private String payFlowNo;// 支付流水号
    private String payTime;//支付时间
    private String payMethod;//支付方式
    private String chargeStatusString;
    private String settleStatusString;

    public String getChargeObject() {
        return chargeObject;
    }

    public void setChargeObject(String chargeObject) {
        this.chargeObject = chargeObject;
    }

    public int getLivingPayRecordStatus() {
        return livingPayRecordStatus;
    }

    public void setLivingPayRecordStatus(int livingPayRecordStatus) {
        this.livingPayRecordStatus = livingPayRecordStatus;
    }

    public long getLivingPayRecordId() {
        return livingPayRecordId;
    }

    public void setLivingPayRecordId(long livingPayRecordId) {
        this.livingPayRecordId = livingPayRecordId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getFeeTypeName() {
        return feeTypeName;
    }

    public void setFeeTypeName(String feeTypeName) {
        this.feeTypeName = feeTypeName;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getPayFlowNo() {
        return payFlowNo;
    }

    public void setPayFlowNo(String payFlowNo) {
        this.payFlowNo = payFlowNo;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public String getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

    public String getChargeStatusString() {
        return chargeStatusString;
    }

    public void setChargeStatusString(String chargeStatusString) {
        this.chargeStatusString = chargeStatusString;
    }

    public String getSettleStatusString() {
        return settleStatusString;
    }

    public void setSettleStatusString(String settleStatusString) {
        this.settleStatusString = settleStatusString;
    }
}
