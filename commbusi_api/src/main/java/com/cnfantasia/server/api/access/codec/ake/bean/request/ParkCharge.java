package com.cnfantasia.server.api.access.codec.ake.bean.request;

import java.util.List;

import com.cnfantasia.server.api.access.codec.ake.bean.base.DataDetailObj;

public class ParkCharge implements DataDetailObj {
    private static final long serialVersionUID = 3513703594983545571L;
    private String parkingCode;
    private List<ParkChargeDetail> chargeDetails;

    public String getParkingCode() {
        return parkingCode;
    }

    public void setParkingCode(final String parkingCode) {
        this.parkingCode = parkingCode;
    }

    public List<ParkChargeDetail> getChargeDetails() {
        return chargeDetails;
    }

    public void setChargeDetails(final List<ParkChargeDetail> chargeDetails) {
        this.chargeDetails = chargeDetails;
    }

    @Override
    public String toString() {
        return "ParkCharge [parkingCode=" + parkingCode + ", chargeDetails=" + chargeDetails + "]";
    }
}
