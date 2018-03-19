package com.cnfantasia.server.api.lateFee.entity;

import com.cnfantasia.server.api.groupBuildingCycleCfg.entity.UnPaidPayBillEntity;
import com.cnfantasia.server.api.plotproperty.entity.PropertyDetail;
import com.cnfantasia.server.domainbase.groupBuildingCalculateLatefeeRule.entity.GroupBuildingCalculateLatefeeRule;
import com.cnfantasia.server.domainbase.payBill.entity.PayBill;
import com.cnfantasia.server.domainbase.propertyFeeDetail.entity.PropertyFeeDetail;

import java.util.List;

/**
 * @className: PayBillForLateFee
 * @date: 2017-11-02 16:16
 * @author: yanghua
 * @description:(需要计算滞纳金的账单信息)
 */
public class PayBillForLateFee extends PayBill{
    /**
     * 费用项明细
     */
    private List<PropertyFeeDetail> propertyFeeDetailList;

    /**
     * 欠费明细
     */
    private List<UnPaidPayBillEntity> unPaidPayBillEntities;

    /**
     * 滞纳金配置
     */
    private GroupBuildingCalculateLatefeeRule groupBuildingCalculateLatefeeRule;

    public List<UnPaidPayBillEntity> getUnPaidPayBillEntities() {
        return unPaidPayBillEntities;
    }

    public void setUnPaidPayBillEntities(List<UnPaidPayBillEntity> unPaidPayBillEntities) {
        this.unPaidPayBillEntities = unPaidPayBillEntities;
    }

    public GroupBuildingCalculateLatefeeRule getGroupBuildingCalculateLatefeeRule() {
        return groupBuildingCalculateLatefeeRule;
    }

    public void setGroupBuildingCalculateLatefeeRule(GroupBuildingCalculateLatefeeRule groupBuildingCalculateLatefeeRule) {
        this.groupBuildingCalculateLatefeeRule = groupBuildingCalculateLatefeeRule;
    }

    public List<PropertyFeeDetail> getPropertyFeeDetailList() {
        return propertyFeeDetailList;
    }

    public void setPropertyFeeDetailList(List<PropertyFeeDetail> propertyFeeDetailList) {
        this.propertyFeeDetailList = propertyFeeDetailList;
    }
}
