package com.cnfantasia.server.ms.revenue.entity;

import com.cnfantasia.server.domainbase.propertyFeeDetailTemp.entity.PropertyFeeDetailTemp;

/**
 * @ClassName: PropertyFeeDetailTempEntity
 * @Date: 2017-07-14 15:02
 * @Auther: yanghua
 * @Description:(费用项临时类:自动生成账单使用)
 */
public class PropertyFeeDetailTempEntity extends PropertyFeeDetailTemp {
    /** 费用账单已经生成到的月份（截止月份） */
    private String createBillMonth;

    public String getCreateBillMonth() {
        return createBillMonth;
    }

    public void setCreateBillMonth(String createBillMonth) {
        this.createBillMonth = createBillMonth;
    }
}
