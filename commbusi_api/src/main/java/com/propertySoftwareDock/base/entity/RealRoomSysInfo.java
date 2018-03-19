package com.propertySoftwareDock.base.entity;

import com.cnfantasia.server.domainbase.building.entity.Building;
import com.cnfantasia.server.domainbase.groupBuilding.entity.GroupBuilding;
import com.cnfantasia.server.domainbase.propertyProprietor.entity.PropertyProprietor;
import com.cnfantasia.server.domainbase.realRoom.entity.RealRoom;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * @ClassName: RealRoomSysInfo
 * @Date: 2016-12-06 17:42
 * @Auther: kangduo
 * @Description: (房间在系统的信息)
 */
public class RealRoomSysInfo implements Serializable {
    private static final long serialVersionUID = -5400226566430659422L;
    private BigInteger realRoomId;
    private BigInteger realRoomMapperId;
    private String softwareRoomId;
    private String softwareCustomerId;
    private RealRoom realRoom;
    private Building building;
    private GroupBuilding groupBuilding;
    private PropertyProprietor propertyProprietor;

    public BigInteger getRealRoomId() {
        return realRoomId;
    }

    public void setRealRoomId(BigInteger realRoomId) {
        this.realRoomId = realRoomId;
    }

    public BigInteger getRealRoomMapperId() {
        return realRoomMapperId;
    }

    public void setRealRoomMapperId(BigInteger realRoomMapperId) {
        this.realRoomMapperId = realRoomMapperId;
    }

    public String getSoftwareRoomId() {
        return softwareRoomId;
    }

    public void setSoftwareRoomId(String softwareRoomId) {
        this.softwareRoomId = softwareRoomId;
    }

    public String getSoftwareCustomerId() {
        return softwareCustomerId;
    }

    public void setSoftwareCustomerId(String softwareCustomerId) {
        this.softwareCustomerId = softwareCustomerId;
    }

    public RealRoom getRealRoom() {
        return realRoom;
    }

    public void setRealRoom(RealRoom realRoom) {
        this.realRoom = realRoom;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public GroupBuilding getGroupBuilding() {
        return groupBuilding;
    }

    public void setGroupBuilding(GroupBuilding groupBuilding) {
        this.groupBuilding = groupBuilding;
    }

    public PropertyProprietor getPropertyProprietor() {
        return propertyProprietor;
    }

    public void setPropertyProprietor(PropertyProprietor propertyProprietor) {
        this.propertyProprietor = propertyProprietor;
    }
}
