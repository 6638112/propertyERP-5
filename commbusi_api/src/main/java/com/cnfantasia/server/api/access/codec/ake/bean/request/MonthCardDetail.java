package com.cnfantasia.server.api.access.codec.ake.bean.request;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class MonthCardDetail implements Serializable {
    private static final long serialVersionUID = -4513592717313378625L;
    private String carNO;
    private Date startDate;
    private Date endDate;
    private BigDecimal chargeStandard;

    public String getCarNO() {
        return this.carNO;
    }

    public void setCarNO(String carNO) {
        this.carNO = carNO;
    }

    public Date getStartDate() {
        return this.startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return this.endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public BigDecimal getChargeStandard() {
        return this.chargeStandard;
    }

    public void setChargeStandard(BigDecimal chargeStandard) {
        this.chargeStandard = chargeStandard;
    }

    public String toString() {
        return "{carNO:" + this.carNO + "," + "startDate:" + this.startDate + "," + "endDate:" + this.endDate + "," + "chargeStandard:"
                + this.chargeStandard + "}";
    }
}

