package com.cnfantasia.server.ms.propertyRepair.entity;

import com.cnfantasia.server.domainbase.dredgeType.entity.DredgeType;

import java.math.BigInteger;

/**
 * @ClassName: DredgeTypeEntity
 * @Date: 2017-04-12 10:01
 * @Auther: yanghua
 * @Description:()
 */
public class DredgeTypeEntity extends DredgeType {
    private BigInteger priceCfgId;//维修价格配置id

    public BigInteger getPriceCfgId() {
        return priceCfgId;
    }

    public void setPriceCfgId(BigInteger priceCfgId) {
        this.priceCfgId = priceCfgId;
    }
}
