package com.cnfantasia.server.ms.propertyPayConfig.entity;

import java.math.BigInteger;

/**
 * @ClassName: StandardSaveParam
 * @Date: 2017-09-26 13:10
 * @Auther: yanghua
 * @Description:(收费标准保存)
 */
public class StandardSaveParam {
    //保存记录的ID--t_mr_standard_group_building,t_mr_standard_building,t_mr_standard_room之一
    private BigInteger id;
    //1小区标准，2楼栋标准，房间标准
    private Integer standardType;
    //费用项ID
    private BigInteger mrFeeItemId;
    //计算规则ID
    private BigInteger mrCalculateRuleCfgId;
    //计费表名称
    private String mrName;
    //计量倍率
    private Double multiplierTimes;
    //小区ID
    private BigInteger gbId;
    //楼栋ID
    private BigInteger buildingId;
    //房间ID
    private BigInteger realRoomId;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public Integer getStandardType() {
        return standardType;
    }

    public void setStandardType(Integer standardType) {
        this.standardType = standardType;
    }

    public BigInteger getMrFeeItemId() {
        return mrFeeItemId;
    }

    public void setMrFeeItemId(BigInteger mrFeeItemId) {
        this.mrFeeItemId = mrFeeItemId;
    }

    public BigInteger getMrCalculateRuleCfgId() {
        return mrCalculateRuleCfgId;
    }

    public void setMrCalculateRuleCfgId(BigInteger mrCalculateRuleCfgId) {
        this.mrCalculateRuleCfgId = mrCalculateRuleCfgId;
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

    public BigInteger getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(BigInteger buildingId) {
        this.buildingId = buildingId;
    }

    public BigInteger getGbId() {
        return gbId;
    }

    public void setGbId(BigInteger gbId) {
        this.gbId = gbId;
    }
}
