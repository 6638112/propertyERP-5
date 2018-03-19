package com.cnfantasia.server.api.access.codec.ake.bean.request;

import com.cnfantasia.server.api.access.codec.ake.bean.base.YdtRequest;
import java.util.Arrays;

public class QueryParkingFeeReq implements YdtRequest {
    private static final long serialVersionUID = -7949678441338197892L;
    private String parkCode;
    private String parkingCode;
    private String[] traderCouponGrantSnArray;

    public String[] getTraderCouponGrantSnArray() {
        return this.traderCouponGrantSnArray;
    }

    public void setTraderCouponGrantSnArray(String[] traderCouponGrantSnArray) {
        this.traderCouponGrantSnArray = traderCouponGrantSnArray;
    }

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

    public String toString() {
        return "QueryParkingFeeReq [parkCode=" + this.parkCode + ", parkingCode=" + this.parkingCode + ", traderCouponGrantSnArray="
                + Arrays.toString(this.traderCouponGrantSnArray) + "]";
    }
}

