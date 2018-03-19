package com.cnfantasia.server.api.access.codec.ake.bean.request;

import com.cnfantasia.server.api.access.codec.ake.bean.base.YdtRequest;

public class QueryMonthCardReq implements YdtRequest {
    private static final long serialVersionUID = -2489476195672687716L;
    private String parkCode;
    private String cusNO;
    private String carNO;

    public String getParkCode() {
        return this.parkCode;
    }

    public void setParkCode(String parkCode) {
        this.parkCode = parkCode;
    }

    public String getCusNO() {
        return this.cusNO;
    }

    public void setCusNO(String cusNO) {
        this.cusNO = cusNO;
    }

    public String getCarNO() {
        return this.carNO;
    }

    public void setCarNO(String carNO) {
        this.carNO = carNO;
    }

    public String toString() {
        return "{parkCode:" + this.parkCode + "," + "cusNO:" + this.cusNO + "," + "carNO:" + this.carNO + "}";
    }
}

