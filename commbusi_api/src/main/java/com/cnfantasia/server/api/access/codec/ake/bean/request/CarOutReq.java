package com.cnfantasia.server.api.access.codec.ake.bean.request;

import com.cnfantasia.server.api.access.codec.ake.bean.base.ParkRequest;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class CarOutReq implements ParkRequest {
    private static final long serialVersionUID = -587961211986955757L;
    private Integer chargeState; // 0：（未缴费，由平台扣费）；1：（在停车场端已缴费）2：（在平台端已缴费）；3:(免费)

    private Float finalReliability;
    private Date inDate;
    private String leaveCarCode;
    private Float leaveReliability;
    private Date outDate;
    private String outerAddress;
    private Integer outerType; // 出场类型 0未确认，1自动放行，2确认放行，3异常放行
    private String parkingCode; // 停车流水号
    private Long parkTime; // 停车时长
    private BigDecimal platformShouldPay;
    private BigDecimal realCharge;
    private BigDecimal reliefCharge;
    private String security;
    private BigDecimal shouldCharge;
    private List<UseTraderCoupon> traderCouponList;

    public Integer getChargeState() {
        return this.chargeState;
    }

    public Float getFinalReliability() {
        return this.finalReliability;
    }

    public Date getInDate() {
        return this.inDate;
    }

    public String getLeaveCarCode() {
        return this.leaveCarCode;
    }

    public Float getLeaveReliability() {
        return this.leaveReliability;
    }

    public Date getOutDate() {
        return this.outDate;
    }

    public String getOuterAddress() {
        return this.outerAddress;
    }

    public Integer getOuterType() {
        return this.outerType;
    }

    public String getParkingCode() {
        return this.parkingCode;
    }

    public Long getParkTime() {
        return this.parkTime;
    }

    public BigDecimal getPlatformShouldPay() {
        return this.platformShouldPay;
    }

    public BigDecimal getRealCharge() {
        return this.realCharge;
    }

    public BigDecimal getReliefCharge() {
        return this.reliefCharge;
    }

    public String getSecurity() {
        return this.security;
    }

    public BigDecimal getShouldCharge() {
        return this.shouldCharge;
    }

    public List<UseTraderCoupon> getTraderCouponList() {
        return this.traderCouponList;
    }

    public void setChargeState(Integer chargeState) {
        this.chargeState = chargeState;
    }

    public void setFinalReliability(Float finalReliability) {
        this.finalReliability = finalReliability;
    }

    public void setInDate(Date inDate) {
        this.inDate = inDate;
    }

    public void setLeaveCarCode(String leaveCarCode) {
        this.leaveCarCode = leaveCarCode;
    }

    public void setLeaveReliability(Float leaveReliability) {
        this.leaveReliability = leaveReliability;
    }

    public void setOutDate(Date outDate) {
        this.outDate = outDate;
    }

    public void setOuterAddress(String outerAddress) {
        this.outerAddress = outerAddress;
    }

    public void setOuterType(Integer outerType) {
        this.outerType = outerType;
    }

    public void setParkingCode(String parkingCode) {
        this.parkingCode = parkingCode;
    }

    public void setParkTime(Long parkTime) {
        this.parkTime = parkTime;
    }

    public void setPlatformShouldPay(BigDecimal platformShouldPay) {
        this.platformShouldPay = platformShouldPay;
    }

    public void setRealCharge(BigDecimal realCharge) {
        this.realCharge = realCharge;
    }

    public void setReliefCharge(BigDecimal reliefCharge) {
        this.reliefCharge = reliefCharge;
    }

    public void setSecurity(String security) {
        this.security = security;
    }

    public void setShouldCharge(BigDecimal shouldCharge) {
        this.shouldCharge = shouldCharge;
    }

    public void setTraderCouponList(List<UseTraderCoupon> traderCouponList) {
        this.traderCouponList = traderCouponList;
    }

    public String toString() {
        return "CarOutReq [parkingCode=" + this.parkingCode + ", inDate=" + this.inDate + ", outDate=" + this.outDate + ", chargeState="
                + this.chargeState + ", platformShouldPay=" + this.platformShouldPay + ", outerAddress=" + this.outerAddress + ", parkTime="
                + this.parkTime + ", security=" + this.security + ", finalReliability=" + this.finalReliability + ", leaveReliability="
                + this.leaveReliability + ", leaveCarCode=" + this.leaveCarCode + ", outerType=" + this.outerType + ", traderCouponList="
                + this.traderCouponList + ", shouldCharge=" + this.shouldCharge + ", realCharge=" + this.realCharge + ", reliefCharge="
                + this.reliefCharge + "]";
    }
}

