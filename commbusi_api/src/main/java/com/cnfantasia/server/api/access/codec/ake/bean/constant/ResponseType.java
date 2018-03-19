package com.cnfantasia.server.api.access.codec.ake.bean.constant;

import com.cnfantasia.server.api.access.codec.ake.bean.base.EnumInterface;

public enum ResponseType implements EnumInterface {
    LOGIN,
    SEARCH_CAR,
    SYNC_PARKINFO,
    REPORT_EPT_CARPORTS,
    CAR_IN,
    CAR_OUT,
    PREPAY_FEE,
    QUERY_FEE,
    SYNC_PARK_BILL,
    SYNC_YDT_BILL,
    SYNC_SERVERI_TIME,
    QUERY_COUPON,
    CORRECT_PARK_CAR_CODE,
    CORRECT_YDT_CAR_CODE,
    UPLOAD_CAMERA_IMAGE,
    QUERY_MONTH_CARD,
    RENEWALFEES_OF_MONTH_CARD,
    OPEN_GATE_REQ,
    UPLOAD_CAR_LOCATION,
    SEND_TRADER_COUPON,
    UPDATE_TRADER_COUPON_STATUS;

    private ResponseType() {
    }
}