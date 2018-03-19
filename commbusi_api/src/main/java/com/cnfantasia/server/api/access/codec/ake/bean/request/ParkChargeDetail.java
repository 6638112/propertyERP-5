package com.cnfantasia.server.api.access.codec.ake.bean.request;

import com.cnfantasia.server.api.access.codec.ake.bean.base.DataDetailObj;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class ParkChargeDetail implements DataDetailObj {
    private static final long serialVersionUID = 1280823025340820718L;
    private BigDecimal actPayCharge;
    private String billCode;
    private String desc;
    private Date payDate;
    private Integer payWay;
    private BigDecimal platformShouldPay;
    private BigDecimal reliefCharge;
    private List<UseTraderCoupon> traderCouponList;

    public BigDecimal getActPayCharge() {
        return this.actPayCharge;
    }

    public String getBillCode() {
        return this.billCode;
    }

    public String getDesc() {
        return this.desc;
    }

    public Date getPayDate() {
        return this.payDate;
    }

    public Integer getPayWay() {
        return this.payWay;
    }

    public BigDecimal getPlatformShouldPay() {
        return this.platformShouldPay;
    }

    public BigDecimal getReliefCharge() {
        return this.reliefCharge;
    }

    public List<UseTraderCoupon> getTraderCouponList() {
        return this.traderCouponList;
    }

    public void setActPayCharge(BigDecimal actPayCharge) {
        this.actPayCharge = actPayCharge;
    }

    public void setBillCode(String billCode) {
        this.billCode = billCode;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    public void setPayWay(Integer payWay) {
        this.payWay = payWay;
    }

    public void setPlatformShouldPay(BigDecimal platformShouldPay) {
        this.platformShouldPay = platformShouldPay;
    }

    public void setReliefCharge(BigDecimal reliefCharge) {
        this.reliefCharge = reliefCharge;
    }

    public void setTraderCouponList(List<UseTraderCoupon> traderCouponList) {
        this.traderCouponList = traderCouponList;
    }

    public String toString() {
        return "ParkChargeDetail [billCode=" + this.billCode + ", payDate=" + this.payDate + ", actPayCharge=" + this.actPayCharge + ", reliefCharge="
                + this.reliefCharge + ", payWay=" + this.payWay + ", desc=" + this.desc + ", traderCouponList=" + this.traderCouponList + "]";
    }
}

