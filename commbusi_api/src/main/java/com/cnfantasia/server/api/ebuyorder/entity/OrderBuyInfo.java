package com.cnfantasia.server.api.ebuyorder.entity;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * @ClassName: OrderBuyInfo
 * @Date: 2016-07-14 10:17
 * @Auther: kangduo
 * @Description:()
 */
public class OrderBuyInfo implements Serializable {
    private static final long serialVersionUID = 579575942206899775L;

    private BigInteger deliveryOrderId;
    private BigInteger userId;
    private String userMobile;
    private BigInteger supplyMerchantId;
    private String supplyMerchantName;
    private String supplyMerchantTel;
    private Integer productCount;
    private String orderNo;//订单号
    
    private String ziTiAddress;//自提点地址

    public String getZiTiAddress() {
		return ziTiAddress;
	}

	public void setZiTiAddress(String ziTiAddress) {
		this.ziTiAddress = ziTiAddress;
	}

	public BigInteger getDeliveryOrderId() {
        return deliveryOrderId;
    }

    public void setDeliveryOrderId(BigInteger deliveryOrderId) {
        this.deliveryOrderId = deliveryOrderId;
    }

    public BigInteger getUserId() {
        return userId;
    }

    public void setUserId(BigInteger userId) {
        this.userId = userId;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public BigInteger getSupplyMerchantId() {
        return supplyMerchantId;
    }

    public void setSupplyMerchantId(BigInteger supplyMerchantId) {
        this.supplyMerchantId = supplyMerchantId;
    }

    public String getSupplyMerchantName() {
        return supplyMerchantName;
    }

    public void setSupplyMerchantName(String supplyMerchantName) {
        this.supplyMerchantName = supplyMerchantName;
    }

    public String getSupplyMerchantTel() {
        return supplyMerchantTel;
    }

    public void setSupplyMerchantTel(String supplyMerchantTel) {
        this.supplyMerchantTel = supplyMerchantTel;
    }

    public Integer getProductCount() {
        return productCount;
    }

    public void setProductCount(Integer productCount) {
        this.productCount = productCount;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
}
