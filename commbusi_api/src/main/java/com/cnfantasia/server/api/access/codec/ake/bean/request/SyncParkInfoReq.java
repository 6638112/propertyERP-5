package com.cnfantasia.server.api.access.codec.ake.bean.request;

import com.cnfantasia.server.api.access.codec.ake.bean.base.ParkRequest;

public class SyncParkInfoReq implements ParkRequest {
    private static final long serialVersionUID = -8847594789288785385L;
    private String parkCode;
    private String name;
    private String address;
    private String gpsX;
    private String gpsY;
    private String desc;
    private Integer carports;
    private String conTel;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGpsX() {
        return this.gpsX;
    }

    public void setGpsX(String gpsX) {
        this.gpsX = gpsX;
    }

    public String getGpsY() {
        return this.gpsY;
    }

    public void setGpsY(String gpsY) {
        this.gpsY = gpsY;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getCarports() {
        return this.carports;
    }

    public void setCarports(Integer carports) {
        this.carports = carports;
    }

    public String getConTel() {
        return this.conTel;
    }

    public void setConTel(String conTel) {
        this.conTel = conTel;
    }

    public String getParkCode() {
        return this.parkCode;
    }

    public void setParkCode(String parkCode) {
        this.parkCode = parkCode;
    }

    public String toString() {
        return "SyncParkInfoReq [parkCode=" + this.parkCode + ", name=" + this.name + ", address=" + this.address + ", gpsX=" + this.gpsX + ", gpsY="
                + this.gpsY + ", desc=" + this.desc + ", carports=" + this.carports + ", conTel=" + this.conTel + "]";
    }
}

