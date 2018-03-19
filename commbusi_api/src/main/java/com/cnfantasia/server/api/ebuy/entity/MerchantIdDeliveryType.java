package com.cnfantasia.server.api.ebuy.entity;

import java.math.BigInteger;

/**
 * @Description: 供应商id-配送方式
 * @Auther: wenfq
 * @Date: 2017-08-03 14:58
 */
public class MerchantIdDeliveryType {
    /**
     * 供应商id
     */
    private BigInteger merchantId;
    /**
     * 配送方式：1快递; 2到店自提
     */
    private int deliveryType = 1;

    public BigInteger getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(BigInteger merchantId) {
        this.merchantId = merchantId;
    }

    public int getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(int deliveryType) {
        this.deliveryType = deliveryType;
    }
}
