package com.cnfantasia.server.api.livingPay.entity;

import com.cnfantasia.server.business.pub.utils.BigDecimalUtil;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @Author: wenfq
 * @Date: 2017-11-17 16:15
 */
public class Info4AppleRevenue {
    BigInteger recordId;//缴款记录id
    BigInteger rsaId;//收益明细对应id
    BigDecimal revenueAmt; //收益金额

    public BigInteger getRecordId() {
        return recordId;
    }

    public void setRecordId(BigInteger recordId) {
        this.recordId = recordId;
    }

    public BigInteger getRsaId() {
        return rsaId;
    }

    public void setRsaId(BigInteger rsaId) {
        this.rsaId = rsaId;
    }

    public BigDecimal getRevenueAmt() {
        return revenueAmt;
    }

    public void setRevenueAmt(BigDecimal revenueAmt) {
        this.revenueAmt = revenueAmt;
    }
}
