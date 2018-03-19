package com.cnfantasia.server.api.access.codec.ake.bean.request;

import com.cnfantasia.server.api.access.codec.ake.bean.base.ParkRequest;
import java.util.List;

public class SyncParkBillReq implements ParkRequest {
    private static final long serialVersionUID = -4469434484094659693L;
    private String parkCode;
    private List<ParkCharge> charges;

    public String getParkCode() {
        return this.parkCode;
    }

    public void setParkCode(String parkCode) {
        this.parkCode = parkCode;
    }

    public List<ParkCharge> getCharges() {
        return this.charges;
    }

    public void setCharges(List<ParkCharge> charges) {
        this.charges = charges;
    }

    public String toString() {
        return "SyncParkBillReq [parkCode=" + this.parkCode + ", charges=" + this.charges + "]";
    }
}

