package com.propertySoftwareDock.jeez.entity;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.domainbase.propertyFeeDetail.entity.PropertyFeeDetail;

import javax.xml.bind.annotation.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * @ClassName: JeezFeeEntity
 * @Date: 2016-11-25 15:24
 * @Auther: kangduo
 * @Description:(极致拿到的费用)
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Result")
@XmlType(propOrder = {})
public class JeezFeeEntity {

    @XmlElementWrapper(name = "List")
    @XmlElement(name="FeeItem")
    private List<JeezSingleFeeItem> feeItems;

    @XmlElement(name="TotalAmount")
    private BigDecimal totalAmount;

    public List<JeezSingleFeeItem> getFeeItems() {
        return feeItems;
    }

    public void setFeeItems(List<JeezSingleFeeItem> feeItems) {
        this.feeItems = feeItems;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount == null ? null : totalAmount.setScale(2, RoundingMode.HALF_UP);
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

}
