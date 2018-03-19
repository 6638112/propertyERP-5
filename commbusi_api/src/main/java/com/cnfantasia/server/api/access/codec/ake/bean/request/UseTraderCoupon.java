package com.cnfantasia.server.api.access.codec.ake.bean.request;

import com.cnfantasia.server.api.access.codec.ake.bean.base.DataDetailObj;
import java.math.BigDecimal;

public class UseTraderCoupon implements DataDetailObj {
    private static final long serialVersionUID = -6848035346462902727L;
    private String traderCouponGrantSn;
    private BigDecimal traderCouponValue;

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

    public String toString() {
        return "UseTraderCoupon [traderCouponGrantSn=" + this.traderCouponGrantSn + ", traderCouponValue=" + this.traderCouponValue + "]";
    }
}

