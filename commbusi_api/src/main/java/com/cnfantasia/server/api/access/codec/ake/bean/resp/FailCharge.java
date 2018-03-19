package com.cnfantasia.server.api.access.codec.ake.bean.resp;

import com.cnfantasia.server.api.access.codec.ake.bean.base.DataDetailObj;

public class FailCharge implements DataDetailObj {
    private static final long serialVersionUID = 5455208073888689064L;
    private String parkingCode;
    private Integer failChargeStatus;
    private String failChargesDetail;

    public String getParkingCode() {
        return this.parkingCode;
    }

    public void setParkingCode(String parkingCode) {
        this.parkingCode = parkingCode;
    }

    public Integer getFailChargeStatus() {
        return this.failChargeStatus;
    }

    public void setFailChargeStatus(Integer failChargeStatus) {
        this.failChargeStatus = failChargeStatus;
    }

    public String getFailChargesDetail() {
        return this.failChargesDetail;
    }

    public void setFailChargesDetail(String failChargesDetail) {
        this.failChargesDetail = failChargesDetail;
    }

    public String toString() {
        return "FailCharge [parkingCode=" + this.parkingCode + ", failChargeStatus=" + this.failChargeStatus + ", failChargesDetail="
                + this.failChargesDetail + "]";
    }
}

