package com.cnfantasia.server.ms.payBill.entity;

import com.cnfantasia.server.domainbase.propertyFeeDetail.entity.PropertyFeeDetail;

import java.math.BigDecimal;

/**
 * @ClassName: PropertyFeeDetail4Export
 * @Date: 2017-02-17 10:11
 * @Auther: yanghua
 * @Description:(账单导出使用的费用明细实体)
 */
public class PropertyFeeDetail4Export extends PropertyFeeDetail{
    /**抄表收费项的本期读数 不是抄表则为空*/
    private String startValue;
    /**抄表收费项的上期读数 不是抄表则为空*/
    private String endValue;
    /**用量*/
    private BigDecimal dosageValue;

    public String getStartValue() {
        return startValue;
    }

    public void setStartValue(String startValue) {
        this.startValue = startValue;
    }

    public String getEndValue() {
        return endValue;
    }

    public void setEndValue(String endValue) {
        this.endValue = endValue;
    }

    public BigDecimal getDosageValue() {
        return dosageValue;
    }

    public void setDosageValue(BigDecimal dosageValue) {
        this.dosageValue = dosageValue;
    }
}
