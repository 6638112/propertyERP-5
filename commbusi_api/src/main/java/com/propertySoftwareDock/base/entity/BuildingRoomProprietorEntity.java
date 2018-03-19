package com.propertySoftwareDock.base.entity;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * @ClassName: BuildingRoomProprietorEntity
 * @Date: 2016-12-07 16:25
 * @Auther: kangduo
 * @Description: (楼栋房间业主信息, 指插入房间信息用)
 */
public class BuildingRoomProprietorEntity implements Serializable {
    private static final long serialVersionUID = 2531619914320915747L;

    private BigInteger buildingId;
    private String buildingName;
    private BigInteger realRoomId;
    private String roomUnit; //单元号
    private String roomNumber; //房间号
    private String proprietorName;
    private String proprietorPhone;
    private Double roomSize = 0d; //房屋面积
    private Double roomManagerPrice = 0d; //管理费单价
    private String proprietorIdNumber; //业主身份证号

    private String softwareCustomerId;
    private String softwareRoomId;

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

    public BigInteger getRealRoomId() {
        return realRoomId;
    }

    public void setRealRoomId(BigInteger realRoomId) {
        this.realRoomId = realRoomId;
    }

    public String getRoomUnit() {
        return roomUnit;
    }

    public void setRoomUnit(String roomUnit) {
        this.roomUnit = roomUnit;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getProprietorName() {
        return proprietorName;
    }

    public void setProprietorName(String proprietorName) {
        this.proprietorName = proprietorName;
    }

    public String getProprietorPhone() {
        return proprietorPhone;
    }

    public void setProprietorPhone(String proprietorPhone) {
        this.proprietorPhone = proprietorPhone;
    }

    public Double getRoomSize() {
        return roomSize;
    }

    public void setRoomSize(Double roomSize) {
        this.roomSize = roomSize;
    }

    public Double getRoomManagerPrice() {
        return roomManagerPrice;
    }

    public void setRoomManagerPrice(Double roomManagerPrice) {
        this.roomManagerPrice = roomManagerPrice;
    }

    public String getProprietorIdNumber() {
        return proprietorIdNumber;
    }

    public void setProprietorIdNumber(String proprietorIdNumber) {
        this.proprietorIdNumber = proprietorIdNumber;
    }

    public String getSoftwareCustomerId() {
        return softwareCustomerId;
    }

    public void setSoftwareCustomerId(String softwareCustomerId) {
        this.softwareCustomerId = softwareCustomerId;
    }

    public String getSoftwareRoomId() {
        return softwareRoomId;
    }

    public void setSoftwareRoomId(String softwareRoomId) {
        this.softwareRoomId = softwareRoomId;
    }
}
