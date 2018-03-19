package com.cnfantasia.server.api.access.codec.ake.bean.resp;

import com.cnfantasia.server.api.access.codec.ake.bean.base.ResponseBaseData;
import java.util.List;

public class SyncParkBillResp implements ResponseBaseData {
    private static final long serialVersionUID = -6089950104423259564L;
    private String parkCode;
    private List<FailCharge> failCharges;

    public String getParkCode() {
        return this.parkCode;
    }

    public void setParkCode(String parkCode) {
        this.parkCode = parkCode;
    }

    public List<FailCharge> getFailCharges() {
        return this.failCharges;
    }

    public void setFailCharges(List<FailCharge> failCharges) {
        this.failCharges = failCharges;
    }

    public String toString() {
        return "SyncParkBillResp [parkCode=" + this.parkCode + ", failCharges=" + this.failCharges + "]";
    }
}

