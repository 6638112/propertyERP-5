package com.cnfantasia.server.api.access.codec.ake.bean.resp;

import com.cnfantasia.server.api.access.codec.ake.bean.base.ResponseBaseData;
import java.math.BigDecimal;

public class CorrectParkCarCodeResp implements ResponseBaseData {
    private static final long serialVersionUID = 6301374680922596258L;
    private BigDecimal money;
    private String parkingCode;

    public BigDecimal getMoney() {
        if (this.money != null) {
            return this.money.setScale(2);
        }
        return null;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getParkingCode() {
        return this.parkingCode;
    }

    public void setParkingCode(String parkingCode) {
        this.parkingCode = parkingCode;
    }

    public String toString() {
        return "CorrectParkCarCodeResp [money=" + this.money + ", parkingCode=" + this.parkingCode + "]";
    }
}

