package com.cnfantasia.server.ms.ebuyMerchant.entity;

import java.io.Serializable;

/**
 * Created by shiyl on 2016/6/13.
 */
public class SupplyMerchantSearchParam implements Serializable {
    private static final long serialVersionUID = 1627692831361227513L;

    private String cityName;
    private String linkName;
    private Integer revenueType;
    private String supplierName;
    private String addTime;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getLinkName() {
        return linkName;
    }

    public void setLinkName(String linkName) {
        this.linkName = linkName;
    }

    public Integer getRevenueType() {
        return revenueType;
    }

    public void setRevenueType(Integer revenueType) {
        this.revenueType = revenueType;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }
}
