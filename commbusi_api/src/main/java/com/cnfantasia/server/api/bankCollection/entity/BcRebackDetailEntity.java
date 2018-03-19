package com.cnfantasia.server.api.bankCollection.entity;

import java.math.BigInteger;

/**
 * @ClassName: BcRebackDetailEntity
 * @Date: 2017-04-16 11:25
 * @Auther: yanghua
 * @Description:(银行回盘明细)
 */
public class BcRebackDetailEntity {
    /** 账单id */
    private BigInteger paybillId;
    /** 回盘信息 */
    private String rebackContent;
    /** 回盘结果={Y成功; E/O失败} */
    private String status;
    private Long amount;

    public BigInteger getPaybillId() {
        return paybillId;
    }

    public void setPaybillId(BigInteger paybillId) {
        this.paybillId = paybillId;
    }

    public String getRebackContent() {
        return rebackContent;
    }

    public void setRebackContent(String rebackContent) {
        this.rebackContent = rebackContent;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }
}
