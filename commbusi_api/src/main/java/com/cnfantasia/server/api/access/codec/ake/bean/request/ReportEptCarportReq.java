package com.cnfantasia.server.api.access.codec.ake.bean.request;

import com.cnfantasia.server.api.access.codec.ake.bean.base.ParkRequest;

public class ReportEptCarportReq implements ParkRequest {
    private static final long serialVersionUID = -1335941835070578801L;
    private String parkCode;
    private Integer eptCarports;

    public String getParkCode() {
        return this.parkCode;
    }

    public void setParkCode(String parkCode) {
        this.parkCode = parkCode;
    }

    public Integer getEptCarports() {
        return this.eptCarports;
    }

    public void setEptCarports(Integer eptCarports) {
        this.eptCarports = eptCarports;
    }

    public String toString() {
        return "ReportEptCarportReq [parkCode=" + this.parkCode + ", eptCarports=" + this.eptCarports + "]";
    }
}

