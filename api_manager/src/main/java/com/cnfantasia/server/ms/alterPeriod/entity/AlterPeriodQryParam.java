package com.cnfantasia.server.ms.alterPeriod.entity;

import java.math.BigInteger;

/**
 * @ClassName: AlterPeriodQryParam
 * @Date: 2016-10-25 9:26
 * @Auther: kangduo
 * @Description:()
 */
public class AlterPeriodQryParam {
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
