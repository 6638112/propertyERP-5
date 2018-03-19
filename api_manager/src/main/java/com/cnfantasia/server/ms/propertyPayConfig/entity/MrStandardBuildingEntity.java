package com.cnfantasia.server.ms.propertyPayConfig.entity;

import java.math.BigInteger;
import java.util.List;

/**
 * @ClassName: MrStandardBuildingEntity
 * @Date: 2017-09-26 9:35
 * @Auther: yanghua
 * @Description:(楼栋收费标准)
 */
public class MrStandardBuildingEntity {
    private BigInteger id;//t_mr_standard_building id
    private BigInteger buildingId;
    private String buildingName;
    private BigInteger gbId;
    private List<MrFeeItemForStandar> mrFeeItemForStandars;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

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

    public BigInteger getGbId() {
        return gbId;
    }

    public void setGbId(BigInteger gbId) {
        this.gbId = gbId;
    }

    public List<MrFeeItemForStandar> getMrFeeItemForStandars() {
        return mrFeeItemForStandars;
    }

    public void setMrFeeItemForStandars(List<MrFeeItemForStandar> mrFeeItemForStandars) {
        this.mrFeeItemForStandars = mrFeeItemForStandars;
    }
}
