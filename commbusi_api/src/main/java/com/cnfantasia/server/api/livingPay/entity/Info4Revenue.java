package com.cnfantasia.server.api.livingPay.entity;

import java.math.BigInteger;

/**
 * @Author: wenfq
 * @Date: 2017-11-16 9:45
 */
public class Info4Revenue {
    private BigInteger goalId;//缴费订单id
    private long payAmount;//缴费金额
    private String orderNo;//订单编号
    private String flowNo;//支付流水号

    public BigInteger getGoalId() {
        return goalId;
    }

    public void setGoalId(BigInteger goalId) {
        this.goalId = goalId;
    }

    public long getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(long payAmount) {
        this.payAmount = payAmount;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getFlowNo() {
        return flowNo;
    }

    public void setFlowNo(String flowNo) {
        this.flowNo = flowNo;
    }
}
