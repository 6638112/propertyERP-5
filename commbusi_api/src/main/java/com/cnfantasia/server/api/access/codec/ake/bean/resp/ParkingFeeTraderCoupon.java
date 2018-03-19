package com.cnfantasia.server.api.access.codec.ake.bean.resp;

import java.math.BigDecimal;

import com.cnfantasia.server.api.access.codec.ake.bean.base.DataDetailObj;

public class ParkingFeeTraderCoupon implements DataDetailObj {
    private static final long serialVersionUID = -8963283252868630913L;
    private String traderCouponGrantSn;
    private BigDecimal traderCouponValue;
    private Boolean isValid;

    public String getTraderCouponGrantSn() {
        return this.traderCouponGrantSn;
    }

    public void setTraderCouponGrantSn(String traderCouponGrantSn) {
        this.traderCouponGrantSn = traderCouponGrantSn;
    }

    public BigDecimal getTraderCouponValue() {
        return this.traderCouponValue;
    }

    public void setTraderCouponValue(BigDecimal traderCouponValue) {
        this.traderCouponValue = traderCouponValue;
    }

    public Boolean getIsValid() {
        return this.isValid;
    }

    public void setIsValid(Boolean isValid) {
        this.isValid = isValid;
    }

    public String toString() {
        return "ParkingFeeTraderCoupon [traderCouponGrantSn=" + this.traderCouponGrantSn + ", traderCouponValue=" + this.traderCouponValue
                + ", isValid=" + this.isValid + "]";
    }
}

