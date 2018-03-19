package com.cnfantasia.server.api.access.codec.ake.bean.request;

import com.cnfantasia.server.api.access.codec.ake.bean.base.YdtRequest;
import java.math.BigDecimal;

public class PrepayParkingFeeReq implements YdtRequest {
    private static final long serialVersionUID = -5680941763728415140L;
    private String parkCode;
    private String parkingCode;
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

    public BigDecimal getMoney() {
        return this.money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String toString() {
        return "PrepayParkingFeeReq [parkCode=" + this.parkCode + ", parkingCode=" + this.parkingCode + ", money=" + this.money + "]";
    }
}

