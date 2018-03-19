package com.cnfantasia.server.api.ebuy.entity;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * @ClassName: DredgeProductSpecEntity
 * @Date: 2017-05-09 15:36
 * @Auther: kangduo
 * @Description: ()
 */
public class DredgeProductSpecEntity implements Serializable {

    private static final long serialVersionUID = -1581303691999167193L;
    private BigInteger specId;
    private BigInteger dredgeProductId;
    private String specName;
    private Integer specNum;
    private Integer specPrice;
    private String specUnit;

    public BigInteger getSpecId() {
        return specId;
    }

    public void setSpecId(BigInteger specId) {
        this.specId = specId;
    }

    public BigInteger getDredgeProductId() {
        return dredgeProductId;
    }

    public void setDredgeProductId(BigInteger dredgeProductId) {
        this.dredgeProductId = dredgeProductId;
    }

    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
    }

    public Integer getSpecNum() {
        return specNum;
    }

    public void setSpecNum(Integer specNum) {
        this.specNum = specNum;
    }

    public Integer getSpecPrice() {
        return specPrice;
    }

    public void setSpecPrice(Integer specPrice) {
        this.specPrice = specPrice;
    }

    public String getSpecUnit() {
        return specUnit;
    }

    public void setSpecUnit(String specUnit) {
        this.specUnit = specUnit;
    }
}
