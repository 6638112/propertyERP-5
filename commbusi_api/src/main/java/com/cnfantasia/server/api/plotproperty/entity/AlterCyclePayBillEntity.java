package com.cnfantasia.server.api.plotproperty.entity;

import com.cnfantasia.server.domainbase.groupBuildingBillCycle.entity.GroupBuildingBillCycle;

import java.util.List;

/**
 * @ClassName: AlterCyclePayBillEntity
 * @Date: 2017-07-13 16:44
 * @Auther: yanghua
 * @Description:(选择周期账期对应的账单)
 */
public class AlterCyclePayBillEntity extends GroupBuildingBillCycle{
    /**账单明细*/
    private String billName;
    /**该账期对应的账单信息*/
    private List<AlterPayBillEntity> alterPayBillEntities;

    public String getBillName() {
        return billName;
    }

    public void setBillName(String billName) {
        this.billName = billName;
    }

    public List<AlterPayBillEntity> getAlterPayBillEntities() {
        return alterPayBillEntities;
    }

    public void setAlterPayBillEntities(List<AlterPayBillEntity> alterPayBillEntities) {
        this.alterPayBillEntities = alterPayBillEntities;
    }
}
