package com.cnfantasia.server.api.access.codec.ake.bean.request;

import com.cnfantasia.server.api.access.codec.ake.bean.base.ParkRequest;
import java.util.Date;

public class CorrectParkCarCodeReq implements ParkRequest {
    private static final long serialVersionUID = 7199280760542393927L;
    private Date inDate;
    private String modifyBY;
    private Date modifyDate;
    private String newCarCode;
    private String newCardCode;
    private String parkCode;
    private String parkingCode;

    public Date getInDate() {
        return this.inDate;
    }

    public String getModifyBY() {
        return this.modifyBY;
    }

    public Date getModifyDate() {
        return this.modifyDate;
    }

    public String getNewCarCode() {
        return this.newCarCode;
    }

    public String getNewCardCode() {
        return this.newCardCode;
    }

    public String getParkCode() {
        return this.parkCode;
    }

    public String getParkingCode() {
        return this.parkingCode;
    }

    public void setInDate(Date inDate) {
        this.inDate = inDate;
    }

    public void setModifyBY(String modifyBY) {
        this.modifyBY = modifyBY;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public void setNewCarCode(String newCarCode) {
        this.newCarCode = newCarCode;
    }

    public void setNewCardCode(String newCardCode) {
        this.newCardCode = newCardCode;
    }

    public void setParkCode(String parkCode) {
        this.parkCode = parkCode;
    }

    public void setParkingCode(String parkingCode) {
        this.parkingCode = parkingCode;
    }

    public String toString() {
        return "CorrectParkCarCodeReq [parkCode=" + this.parkCode + ", parkingCode=" + this.parkingCode + ", newCarCode=" + this.newCarCode
                + ", newCardCode=" + this.newCardCode + ", inDate=" + this.inDate + ", modifyBY=" + this.modifyBY + ", modifyDate=" + this.modifyDate
                + "]";
    }
}

