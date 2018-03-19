package com.cnfantasia.server.api.groupBuildingCycleCfg.entity;

import com.cnfantasia.server.domainbase.payBill.entity.PayBill;
import com.cnfantasia.server.domainbase.propertyFeeDetail.entity.PropertyFeeDetail;

import java.util.List;

/**
 * @ClassName: UnPaidPayBillEntity
 * @Date: 2017-05-09 10:22
 * @Auther: yanghua
 * @Description:(欠费账单信息)
 */
public class UnPaidPayBillEntity extends PayBill{
    private List<PropertyFeeDetail> propertyFeeDetailList;

    public List<PropertyFeeDetail> getPropertyFeeDetailList() {
        return propertyFeeDetailList;
    }

    public void setPropertyFeeDetailList(List<PropertyFeeDetail> propertyFeeDetailList) {
        this.propertyFeeDetailList = propertyFeeDetailList;
    }
}
