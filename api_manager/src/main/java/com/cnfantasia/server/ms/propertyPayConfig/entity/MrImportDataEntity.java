package com.cnfantasia.server.ms.propertyPayConfig.entity;

import java.math.BigInteger;

/**
 * @ClassName: MrImportDataEntity
 * @Date: 2017-10-17 18:04
 * @Auther: yanghua
 * @Description:(抄表导入excel行数据)
 */
public class MrImportDataEntity {
    private BigInteger realRoomId;
    private BigInteger feeItemId;
    private String mrName;
    private Double multiplierTimes;
    private double mrNameValue;

    public BigInteger getFeeItemId() {
        return feeItemId;
    }

    public void setFeeItemId(BigInteger feeItemId) {
        this.feeItemId = feeItemId;
    }

    public String getMrName() {
        return mrName;
    }

    public void setMrName(String mrName) {
        this.mrName = mrName;
    }

    public Double getMultiplierTimes() {
        return multiplierTimes;
    }

    public void setMultiplierTimes(Double multiplierTimes) {
        this.multiplierTimes = multiplierTimes;
    }

    public BigInteger getRealRoomId() {
        return realRoomId;
    }

    public void setRealRoomId(BigInteger realRoomId) {
        this.realRoomId = realRoomId;
    }

    public double getMrNameValue() {
        return mrNameValue;
    }

    public void setMrNameValue(double mrNameValue) {
        this.mrNameValue = mrNameValue;
    }
}
