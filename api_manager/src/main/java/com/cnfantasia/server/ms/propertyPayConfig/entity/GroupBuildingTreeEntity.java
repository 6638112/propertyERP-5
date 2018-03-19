package com.cnfantasia.server.ms.propertyPayConfig.entity;

import java.math.BigInteger;
import java.util.List;

/**
 * @ClassName: GroupBuildingTreeEntity
 * @Date: 2017-09-25 16:00
 * @Auther: yanghua
 * @Description:(抄表收费标准)
 */
public class GroupBuildingTreeEntity {
    private BigInteger gbId;
    private String gbName;
    private List<RealRoomTreeEntity> buildingRooms;

    public BigInteger getGbId() {
        return gbId;
    }

    public void setGbId(BigInteger gbId) {
        this.gbId = gbId;
    }

    public String getGbName() {
        return gbName;
    }

    public void setGbName(String gbName) {
        this.gbName = gbName;
    }

    public List<RealRoomTreeEntity> getBuildingRooms() {
        return buildingRooms;
    }

    public void setBuildingRooms(List<RealRoomTreeEntity> buildingRooms) {
        this.buildingRooms = buildingRooms;
    }
}
