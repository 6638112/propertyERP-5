package com.cnfantasia.server.ms.fixedFeeCfg.entity;

import java.math.BigInteger;

/**
 * @ClassName: FixedFeeDetailQryParam
 * @Date: 2017-01-19 9:26
 * @Auther: yanghau
 * @Description:(收费项房间管理列表查询参数类)
 */
public class FixedFeeDetailQryParam {
    private BigInteger gbId;
    private String bName;
    private String unitName;
    private String roomNumTail;

    public BigInteger getGbId() {
        return gbId;
    }

    public void setGbId(BigInteger gbId) {
        this.gbId = gbId;
    }

    public String getbName() {
        return bName;
    }

    public void setbName(String bName) {
        this.bName = bName;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getRoomNumTail() {
        return roomNumTail;
    }

    public void setRoomNumTail(String roomNumTail) {
        this.roomNumTail = roomNumTail;
    }
}
