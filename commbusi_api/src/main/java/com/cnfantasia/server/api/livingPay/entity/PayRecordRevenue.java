package com.cnfantasia.server.api.livingPay.entity;

import java.math.BigDecimal;

/**
 * 生活缴费收益明细
 * @Author: wenfq
 * @Date: 2017-11-13 14:48
 */
public class PayRecordRevenue {
    private long id;
    private String typeName;
    private String chargeObject;
    private String address;
    private String linkTel;
    private String payTime;
    private BigDecimal amount;
    private BigDecimal amtBalance;
    private String statusStr;//订单状态
    private String revenueStatusStr;//结算状态

    public String getRevenueStatusStr() {
        return revenueStatusStr;
    }

    public void setRevenueStatusStr(String revenueStatusStr) {
        this.revenueStatusStr = revenueStatusStr;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getChargeObject() {
        return chargeObject;
    }

    public void setChargeObject(String chargeObject) {
        this.chargeObject = chargeObject;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getAmtBalance() {
        return amtBalance;
    }

    public void setAmtBalance(BigDecimal amtBalance) {
        this.amtBalance = amtBalance;
    }

    public String getStatusStr() {
        return statusStr;
    }

    public void setStatusStr(String statusStr) {
        this.statusStr = statusStr;
    }

    public String getLinkTel() {
        return linkTel;
    }

    public void setLinkTel(String linkTel) {
        this.linkTel = linkTel;
    }
}
