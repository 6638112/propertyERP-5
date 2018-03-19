package com.cnfantasia.server.api.access.codec.ake.bean.request;

import com.cnfantasia.server.api.access.codec.ake.bean.base.YdtRequest;
import java.math.BigDecimal;
import java.util.Date;

public class CorrectYdtCarCodeReq implements YdtRequest {
    private static final long serialVersionUID = -1393277892650455805L;
    private String parkCode;
    private String parkingCode;
    private String newCarCode;
    private String newCardCode;
    private Date inDate;
    private String modifyBY;
    private Date modifyDate;
    private BigDecimal money;

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

    public String getNewCarCode() {
        return this.newCarCode;
    }

    public void setNewCarCode(String newCarCode) {
        this.newCarCode = newCarCode;
    }

    public String getNewCardCode() {
        return this.newCardCode;
    }

    public void setNewCardCode(String newCardCode) {
        this.newCardCode = newCardCode;
    }

    public Date getInDate() {
        return this.inDate;
    }

    public void setInDate(Date inDate) {
        this.inDate = inDate;
    }

    public String getModifyBY() {
        return this.modifyBY;
    }

    public void setModifyBY(String modifyBY) {
        this.modifyBY = modifyBY;
    }

    public Date getModifyDate() {
        return this.modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public BigDecimal getMoney() {
        return this.money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String toString() {
        return "CorrectYdtCarCodeReq [parkCode=" + this.parkCode + ", parkingCode=" + this.parkingCode + ", newCarCode=" + this.newCarCode
                + ", newCardCode=" + this.newCardCode + ", inDate=" + this.inDate + ", modifyBY=" + this.modifyBY + ", modifyDate=" + this.modifyDate
                + ", money=" + this.money + "]";
    }
}

