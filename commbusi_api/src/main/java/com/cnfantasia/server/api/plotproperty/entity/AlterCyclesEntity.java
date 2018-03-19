package com.cnfantasia.server.api.plotproperty.entity;

import java.util.List;

/**
 * @ClassName: AlterCyclesEntity
 * @Date: 2017-07-13 18:55
 * @Auther: yanghua
 * @Description:(选择周期账期)
 */
public class AlterCyclesEntity {
    /**账单明细*/
    private String billName;
    /**账单图标最后更新时间*/
    private String lastUpdTime;
    private List<AlterCyclePayBillEntity> alterCyclePayBillEntities;

    public String getBillName() {
        return billName;
    }

    public void setBillName(String billName) {
        this.billName = billName;
    }

    public List<AlterCyclePayBillEntity> getAlterCyclePayBillEntities() {
        return alterCyclePayBillEntities;
    }

    public void setAlterCyclePayBillEntities(List<AlterCyclePayBillEntity> alterCyclePayBillEntities) {
        this.alterCyclePayBillEntities = alterCyclePayBillEntities;
    }

    public String getLastUpdTime() {
        return lastUpdTime;
    }

    public void setLastUpdTime(String lastUpdTime) {
        this.lastUpdTime = lastUpdTime;
    }
}
