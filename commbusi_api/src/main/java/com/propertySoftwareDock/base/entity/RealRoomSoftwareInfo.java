package com.propertySoftwareDock.base.entity;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * @ClassName: RealRoomSoftwareInfo
 * @Date: 2016-11-26 17:47
 * @Auther: kangduo
 * @Description:(房间号对接需要的信息)
 */
public class RealRoomSoftwareInfo implements Serializable {
    private static final long serialVersionUID = -6287750903423571266L;

    private BigInteger realRoomId;
    private String softwareHouseId;
    private BigInteger gbId;
    private String softwareGbId;
    private String softwareCustomerId;
    private String dbCode;
    private String rsaPublicKey;
    private String rsaPrivateKey;
    private String serverAddress;
    private String serviceClassName;
    private Integer cannotConnectStartDate;
    private Integer cannotConnectEndDate;
    private BigInteger defaultRepairerId;

    private BigInteger payBillId;

    public BigInteger getRealRoomId() {
        return realRoomId;
    }

    public void setRealRoomId(BigInteger realRoomId) {
        this.realRoomId = realRoomId;
    }

    public String getSoftwareHouseId() {
        return softwareHouseId;
    }

    public void setSoftwareHouseId(String softwareHouseId) {
        this.softwareHouseId = softwareHouseId;
    }

    public BigInteger getGbId() {
        return gbId;
    }

    public void setGbId(BigInteger gbId) {
        this.gbId = gbId;
    }

    public String getSoftwareGbId() {
        return softwareGbId;
    }

    public void setSoftwareGbId(String softwareGbId) {
        this.softwareGbId = softwareGbId;
    }

    public String getSoftwareCustomerId() {
        return softwareCustomerId;
    }

    public void setSoftwareCustomerId(String softwareCustomerId) {
        this.softwareCustomerId = softwareCustomerId;
    }

    public String getDbCode() {
        return dbCode;
    }

    public void setDbCode(String dbCode) {
        this.dbCode = dbCode;
    }

    public String getRsaPublicKey() {
        return rsaPublicKey;
    }

    public void setRsaPublicKey(String rsaPublicKey) {
        this.rsaPublicKey = rsaPublicKey;
    }

    public String getRsaPrivateKey() {
        return rsaPrivateKey;
    }

    public void setRsaPrivateKey(String rsaPrivateKey) {
        this.rsaPrivateKey = rsaPrivateKey;
    }

    public String getServerAddress() {
        return serverAddress;
    }

    public void setServerAddress(String serverAddress) {
        this.serverAddress = serverAddress;
    }

    public String getServiceClassName() {
        return serviceClassName;
    }

    public void setServiceClassName(String serviceClassName) {
        this.serviceClassName = serviceClassName;
    }

    public BigInteger getPayBillId() {
        return payBillId;
    }

    public void setPayBillId(BigInteger payBillId) {
        this.payBillId = payBillId;
    }

    public Integer getCannotConnectStartDate() {
        return cannotConnectStartDate;
    }

    public void setCannotConnectStartDate(Integer cannotConnectStartDate) {
        this.cannotConnectStartDate = cannotConnectStartDate;
    }

    public Integer getCannotConnectEndDate() {
        return cannotConnectEndDate;
    }

    public void setCannotConnectEndDate(Integer cannotConnectEndDate) {
        this.cannotConnectEndDate = cannotConnectEndDate;
    }

    public BigInteger getDefaultRepairerId() {
        return defaultRepairerId;
    }

    public void setDefaultRepairerId(BigInteger defaultRepairerId) {
        this.defaultRepairerId = defaultRepairerId;
    }
}
