package com.cnfantasia.server.api.access.codec.ake.bean.resp;

import com.cnfantasia.server.api.access.codec.ake.bean.base.ResponseBaseData;

public class SyncYdtBillResp implements ResponseBaseData {
    private static final long serialVersionUID = -488284406102159484L;
    private String parkBillCode;

    public String getParkBillCode() {
        return this.parkBillCode;
    }

    public void setParkBillCode(String parkBillCode) {
        this.parkBillCode = parkBillCode;
    }

    public String toString() {
        return "SyncYdtBillResp [parkBillCode=" + this.parkBillCode + "]";
    }
}

