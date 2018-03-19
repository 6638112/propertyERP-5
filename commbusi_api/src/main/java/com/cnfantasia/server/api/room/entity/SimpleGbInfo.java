package com.cnfantasia.server.api.room.entity;

import java.math.BigInteger;

/**
 * @ClassName: SimpleGbInfo.
 * @Date: 2017-08-01 17:52
 * @Auther: kangduo
 * @Description: ()
 */
public class SimpleGbInfo {

    private BigInteger gbId;
    private String gbName;
    private BigInteger blockId;
    private String blockName;
    private BigInteger cityId;
    private String cityName;
    private BigInteger provinceId;
    private String provinceName;
    private Integer signStatus;

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

    public BigInteger getBlockId() {
        return blockId;
    }

    public void setBlockId(BigInteger blockId) {
        this.blockId = blockId;
    }

    public String getBlockName() {
        return blockName;
    }

    public void setBlockName(String blockName) {
        this.blockName = blockName;
    }

    public BigInteger getCityId() {
        return cityId;
    }

    public void setCityId(BigInteger cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public BigInteger getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(BigInteger provinceId) {
        this.provinceId = provinceId;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public Integer getSignStatus() {
        return signStatus;
    }

    public void setSignStatus(Integer signStatus) {
        this.signStatus = signStatus;
    }
}
