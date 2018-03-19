package com.cnfantasia.server.api.homeMessage.entity;

import com.cnfantasia.server.common.utils.PriceUtil;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

/**
 * @ClassName: PayBillMessageEntity
 * @Date: 2017-03-01 11:06
 * @Auther: kangduo
 * @Description: ()
 */
public class PayBillMessageEntity {
    private BigInteger userId;
    private BigInteger roomId;
    private BigInteger payBillId;
    private Long payBillAmount;
    private Integer feeDetailCount;
    private String payStart;
    private String payEnd;
    private String maxBillTime;

    public BigInteger getUserId() {
        return userId;
    }

    public void setUserId(BigInteger userId) {
        this.userId = userId;
    }

    public BigInteger getRoomId() {
        return roomId;
    }

    public void setRoomId(BigInteger roomId) {
        this.roomId = roomId;
    }

    public BigInteger getPayBillId() {
        return payBillId;
    }

    public void setPayBillId(BigInteger payBillId) {
        this.payBillId = payBillId;
    }

    public BigDecimal getPayBillAmount() {
        return PriceUtil.div100(payBillAmount);
    }

    public void setPayBillAmount(Long payBillAmount) {
        this.payBillAmount = payBillAmount;
    }

    public Integer getFeeDetailCount() {
        return feeDetailCount;
    }

    public void setFeeDetailCount(Integer feeDetailCount) {
        this.feeDetailCount = feeDetailCount;
    }

    public String getPayStart() {
        return payStart;
    }

    public void setPayStart(String payStart) {
        this.payStart = payStart;
    }

    public String getPayEnd() {
        return payEnd;
    }

    public void setPayEnd(String payEnd) {
        this.payEnd = payEnd;
    }

    public String getMaxBillTime() {
        return maxBillTime;
    }

    public void setMaxBillTime(String maxBillTime) {
        this.maxBillTime = maxBillTime;
    }
}
