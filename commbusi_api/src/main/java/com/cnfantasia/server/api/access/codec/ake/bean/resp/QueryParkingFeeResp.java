package com.cnfantasia.server.api.access.codec.ake.bean.resp;

import com.cnfantasia.server.api.access.codec.ake.bean.base.ResponseBaseData;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class QueryParkingFeeResp implements ResponseBaseData {
    private static final long serialVersionUID = -2025989446389581381L;
    private Date enterDate; // 入场时间
    private BigDecimal payValue; // 需缴费金额
    private BigDecimal paidValue; // 已缴金额
    private Date paidDate; // 上次缴费时间
    private Integer payType; // 0:正常缴费，1：补缴
    private Long parkTime; // 停车时长
    private List<ParkingFeeTraderCoupon> traderCouponList; // 商家券优惠金额
    private BigDecimal parkPaidValue; // 线下已交金额

    public BigDecimal getParkPaidValue() {
        return this.parkPaidValue;
    }

    public void setParkPaidValue(BigDecimal parkPaidValue) {
        this.parkPaidValue = parkPaidValue;
    }

    public List<ParkingFeeTraderCoupon> getTraderCouponList() {
        return this.traderCouponList;
    }

    public void setTraderCouponList(List<ParkingFeeTraderCoupon> traderCouponList) {
        this.traderCouponList = traderCouponList;
    }

    public Long getParkTime() {
        return this.parkTime;
    }

    public void setParkTime(Long parkTime) {
        this.parkTime = parkTime;
    }

    public Date getEnterDate() {
        return this.enterDate;
    }

    public void setEnterDate(Date enterDate) {
        this.enterDate = enterDate;
    }

    public BigDecimal getPayValue() {
        return this.payValue;
    }

    public void setPayValue(BigDecimal payValue) {
        this.payValue = payValue;
    }

    public BigDecimal getPaidValue() {
        return this.paidValue;
    }

    public void setPaidValue(BigDecimal paidValue) {
        this.paidValue = paidValue;
    }

    public Date getPaidDate() {
        return this.paidDate;
    }

    public void setPaidDate(Date paidDate) {
        this.paidDate = paidDate;
    }

    public Integer getPayType() {
        return this.payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public String toString() {
        return "QueryParkingFeeResp [enterDate=" + this.enterDate + ", payValue=" + this.payValue + ", paidValue=" + this.paidValue
                + ", paidDate=" + this.paidDate + ", payType=" + this.payType + ", parkTime=" + this.parkTime + ", traderCouponList="
                + this.traderCouponList + ", parkPaidValue=" + this.parkPaidValue + "]";
    }
}

