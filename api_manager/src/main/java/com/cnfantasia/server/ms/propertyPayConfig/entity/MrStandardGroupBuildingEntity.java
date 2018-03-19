package com.cnfantasia.server.ms.propertyPayConfig.entity;

import java.math.BigInteger;
import java.util.List;

/**
 * @ClassName: MrStandardGroupBuildingEntity
 * @Date: 2017-09-26 9:29
 * @Auther: yanghua
 * @Description:(小区收费标准)
 */
public class MrStandardGroupBuildingEntity {
    private BigInteger id;//t_mr_standard_group_building id
    private BigInteger gbId;
    private String gbName;
    private List<MrFeeItemForStandar> mrFeeItemForStandars;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

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

    public List<MrFeeItemForStandar> getMrFeeItemForStandars() {
        return mrFeeItemForStandars;
    }

    public void setMrFeeItemForStandars(List<MrFeeItemForStandar> mrFeeItemForStandars) {
        this.mrFeeItemForStandars = mrFeeItemForStandars;
    }
}
