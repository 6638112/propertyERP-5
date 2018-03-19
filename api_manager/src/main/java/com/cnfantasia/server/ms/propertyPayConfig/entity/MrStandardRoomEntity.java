package com.cnfantasia.server.ms.propertyPayConfig.entity;

import com.cnfantasia.server.domainbase.mrStandardRoom.entity.MrStandardRoom;

import java.util.List;

/**
 * @ClassName: MrStandardRoomEntity
 * @Date: 2017-09-25 17:12
 * @Auther: yanghua
 * @Description:(房间收费标准)
 */
public class MrStandardRoomEntity extends MrStandardRoom {
    /**楼栋号*/
    private String buildingName;
    /**单元号*/
    private String unitName;
    /**房间号*/
    private String roomNo;
    /**业主姓名*/
    private String ppName;

    public String getBuildingName() {
        return buildingName == null ? "" : buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getUnitName() {
        return unitName == null ? "" : unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getRoomNo() {
        return roomNo == null ? "" : roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public String getPpName() {
        return ppName == null ? "" : ppName;
    }

    public void setPpName(String ppName) {
        this.ppName = ppName;
    }
}
