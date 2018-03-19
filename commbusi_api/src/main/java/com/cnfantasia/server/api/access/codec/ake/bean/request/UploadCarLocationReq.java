package com.cnfantasia.server.api.access.codec.ake.bean.request;

import com.cnfantasia.server.api.access.codec.ake.bean.base.ParkRequest;
import java.util.Date;

public class UploadCarLocationReq implements ParkRequest {
    private static final long serialVersionUID = -7120539559408035787L;
    private String parkCode;
    private String carCode;
    private String parkingSpaceFloor;
    private String parkingSpaceArea;
    private String parkingSpaceNumber;
    private Date parkingTime;
    private byte[] carImage;

    public String getParkCode() {
        return this.parkCode;
    }

    public void setParkCode(String parkCode) {
        this.parkCode = parkCode;
    }

    public String getCarCode() {
        return this.carCode;
    }

    public void setCarCode(String carCode) {
        this.carCode = carCode;
    }

    public String getParkingSpaceFloor() {
        return this.parkingSpaceFloor;
    }

    public void setParkingSpaceFloor(String parkingSpaceFloor) {
        this.parkingSpaceFloor = parkingSpaceFloor;
    }

    public String getParkingSpaceArea() {
        return this.parkingSpaceArea;
    }

    public void setParkingSpaceArea(String parkingSpaceArea) {
        this.parkingSpaceArea = parkingSpaceArea;
    }

    public String getParkingSpaceNumber() {
        return this.parkingSpaceNumber;
    }

    public void setParkingSpaceNumber(String parkingSpaceNumber) {
        this.parkingSpaceNumber = parkingSpaceNumber;
    }

    public Date getParkingTime() {
        return this.parkingTime;
    }

    public void setParkingTime(Date parkingTime) {
        this.parkingTime = parkingTime;
    }

    public byte[] getCarImage() {
        return this.carImage;
    }

    public void setCarImage(byte[] carImage) {
        this.carImage = carImage;
    }

    public String toString() {
        return "UploadCarLocationReq [parkCode=" + this.parkCode + ", carCode=" + this.carCode + ", parkingSpaceFloor=" + this.parkingSpaceFloor
                + ", parkingSpaceArea=" + this.parkingSpaceArea + ", parkingSpaceNumber=" + this.parkingSpaceNumber + ", parkingTime="
                + this.parkingTime + ", carImage=" + (this.carImage == null ? null : Integer.valueOf(this.carImage.length)) + "]";
    }
}

