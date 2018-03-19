package com.cnfantasia.server.ms.propertyPayConfig.entity;

import com.cnfantasia.server.domainbase.building.entity.Building;
import com.cnfantasia.server.domainbase.realRoom.entity.RealRoom;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: RealRoomTreeEntity
 * @Date: 2017-09-22 11:25
 * @Auther: yanghua
 * @Description:(房间楼栋树形结构)
 */
public class RealRoomTreeEntity {
    private BigInteger buildingId;
    private String buildingName;
    private List<RealRoomTree> realRooms;


    public BigInteger getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(BigInteger buildingId) {
        this.buildingId = buildingId;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public List<RealRoomTree> getRealRooms() {
        return realRooms;
    }

    public void setRealRooms(List<RealRoomTree> realRooms) {
        this.realRooms = realRooms;
    }
}
