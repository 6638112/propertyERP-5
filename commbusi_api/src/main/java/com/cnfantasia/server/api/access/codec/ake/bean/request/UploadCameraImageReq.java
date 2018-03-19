package com.cnfantasia.server.api.access.codec.ake.bean.request;

import com.cnfantasia.server.api.access.codec.ake.bean.base.ParkRequest;

public class UploadCameraImageReq implements ParkRequest {
    private static final long serialVersionUID = 213069409559978835L;
    private String cameraCode;
    private Integer cameraType;
    private String carCode;
    private byte[] cardImage;
    private byte[] carImage;
    private String finalCarCode;
    private Float finalReliability;
    private String parkingCode;
    private Float reliability;

    public String getCameraCode() {
        return this.cameraCode;
    }

    public Integer getCameraType() {
        return this.cameraType;
    }

    public String getCarCode() {
        return this.carCode;
    }

    public byte[] getCardImage() {
        return this.cardImage;
    }

    public byte[] getCarImage() {
        return this.carImage;
    }

    public String getFinalCarCode() {
        return this.finalCarCode;
    }

    public Float getFinalReliability() {
        return this.finalReliability;
    }

    public String getParkingCode() {
        return this.parkingCode;
    }

    public Float getReliability() {
        return this.reliability;
    }

    public void setCameraCode(String cameraCode) {
        this.cameraCode = cameraCode;
    }

    public void setCameraType(Integer cameraType) {
        this.cameraType = cameraType;
    }

    public void setCarCode(String carCode) {
        this.carCode = carCode;
    }

    public void setCardImage(byte[] cardImage) {
        this.cardImage = cardImage;
    }

    public void setCarImage(byte[] carImage) {
        this.carImage = carImage;
    }

    public void setFinalCarCode(String finalCarCode) {
        this.finalCarCode = finalCarCode;
    }

    public void setFinalReliability(Float finalReliability) {
        this.finalReliability = finalReliability;
    }

    public void setParkingCode(String parkingCode) {
        this.parkingCode = parkingCode;
    }

    public void setReliability(Float reliability) {
        this.reliability = reliability;
    }

    public String toString() {
        return "UpdateCameraImageReq [parkingCode=" + this.parkingCode + ", finalReliability=" + this.finalReliability + ", finalCarCode="
                + this.finalCarCode + ", cameraType=" + this.cameraType + ", cardImage="
                + (this.cardImage == null ? null : Integer.valueOf(this.cardImage.length)) + ", carImage="
                + (this.carImage == null ? null : Integer.valueOf(this.carImage.length)) + ", reliability=" + this.reliability + ", cameraCode="
                + this.cameraCode + ", carCode=" + this.carCode + "]";
    }
}

