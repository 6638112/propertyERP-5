package com.cnfantasia.server.ms.flashDealActivity.entity;

import com.cnfantasia.server.domainbase.flashDealActivity.entity.FlashDealActivity;

import java.math.BigInteger;

/**
 * @ClassName: FlashDealActivityCfgParam
 * @Date: 2016-11-23 18:14
 * @Auther: yanghua
 * @Description:(幸运购活动参数)
 */
public class FlashDealActivityCfgParam extends FlashDealActivity{
    /**供货商ID*/
    private BigInteger ebuySupplyMerchantId;
    /**供应商名称*/
    private String merchantName;
    /**商品名称*/
    private String productName;
    /**更新人*/
    private String updateMan;
    /**新增人*/
    private String addMan;
    /**活动状态--开始状态*/
    private Integer startStatus;
    /**活动状态--结束状态*/
    private Integer endStatus;
    /***/
    private Integer status;
    /**价格*/
    private Long price;

    public BigInteger getEbuySupplyMerchantId() {
        return ebuySupplyMerchantId;
    }

    public void setEbuySupplyMerchantId(BigInteger ebuySupplyMerchantId) {
        this.ebuySupplyMerchantId = ebuySupplyMerchantId;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getUpdateMan() {
        return updateMan;
    }

    public void setUpdateMan(String updateMan) {
        this.updateMan = updateMan;
    }

    public String getAddMan() {
        return addMan;
    }

    public void setAddMan(String addMan) {
        this.addMan = addMan;
    }

    public Integer getStartStatus() {
        return startStatus;
    }

    public void setStartStatus(Integer startStatus) {
        this.startStatus = startStatus;
    }

    public Integer getEndStatus() {
        return endStatus;
    }

    public void setEndStatus(Integer endStatus) {
        this.endStatus = endStatus;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }
}
