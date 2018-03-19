package com.cnfantasia.server.api.groupBuildingCycleCfg.entity;

import com.cnfantasia.server.domainbase.payBill.entity.PayBill;
import com.cnfantasia.server.domainbase.propertyFeeDetail.entity.PropertyFeeDetail;

import java.util.List;

/**
 * @ClassName: UpdatePayBillEntity
 * @Date: 2017-08-31 15:09
 * @Auther: yanghua
 * @Description:(需要进行更新的账单)
 */
public class UpdatePayBillEntity extends PayBill{
    private List<PropertyFeeDetail> propertyFeeDetailList;

    public List<PropertyFeeDetail> getPropertyFeeDetailList() {
        return propertyFeeDetailList;
    }

    public void setPropertyFeeDetailList(List<PropertyFeeDetail> propertyFeeDetailList) {
        this.propertyFeeDetailList = propertyFeeDetailList;
    }
}
