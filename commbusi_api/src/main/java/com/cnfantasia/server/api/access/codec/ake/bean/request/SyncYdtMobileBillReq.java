package com.cnfantasia.server.api.access.codec.ake.bean.request;

import com.cnfantasia.server.api.access.codec.ake.bean.base.YdtRequest;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class SyncYdtMobileBillReq implements YdtRequest {
    private static final long serialVersionUID = 3367667976886836760L;
    private String parkCode;
    private String parkingCode;
    private String ydtBillCode;
    private Date payDate;
    private BigDecimal actPayCharge;
    private BigDecimal reliefCharge;
    private Integer payWay;
    private String desc;
    private List<UseTraderCoupon> traderCouponList;

    public List<UseTraderCoupon> getTraderCouponList() {
        return this.traderCouponList;
    }

    public void setTraderCouponList(List<UseTraderCoupon> traderCouponList) {
        this.traderCouponList = traderCouponList;
    }

    public Date getPayDate() {
        return this.payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    public BigDecimal getActPayCharge() {
        return this.actPayCharge;
    }

    public void setActPayCharge(BigDecimal actPayCharge) {
        this.actPayCharge = actPayCharge;
    }

    public BigDecimal getReliefCharge() {
        return this.reliefCharge;
    }

    public void setReliefCharge(BigDecimal reliefCharge) {
        this.reliefCharge = reliefCharge;
    }

    public Integer getPayWay() {
        return this.payWay;
    }

    public void setPayWay(Integer payWay) {
        this.payWay = payWay;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getParkCode() {
        return this.parkCode;
    }

    public void setParkCode(String parkCode) {
        this.parkCode = parkCode;
    }

    public String getParkingCode() {
        return this.parkingCode;
    }

    public void setParkingCode(String parkingCode) {
        this.parkingCode = parkingCode;
    }

    public String getYdtBillCode() {
        return this.ydtBillCode;
    }

    public void setYdtBillCode(String ydtBillCode) {
        this.ydtBillCode = ydtBillCode;
    }

    public String toString() {
        return "SyncYdtMobileBillReq [parkCode=" + this.parkCode + ", parkingCode=" + this.parkingCode + ", ydtBillCode=" + this.ydtBillCode
                + ", payDate=" + this.payDate + ", actPayCharge=" + this.actPayCharge + ", reliefCharge=" + this.reliefCharge + ", payWay="
                + this.payWay + ", desc=" + this.desc + "]";
    }
}

