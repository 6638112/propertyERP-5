package com.propertySoftwareDock.jeez.entity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.domainbase.propertyFeeDetail.entity.PropertyFeeDetail;

/**
 * @ClassName: JeezSingleFeeItem
 * @Date: 2016-11-25 15:28
 * @Auther: kangduo
 * @Description:(极致单项费用)
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "FeeItem")
@XmlType(propOrder = {})
public class JeezSingleFeeItem {
    @XmlElement(name = "BillNo")
    private String billNo;
    @XmlElement(name = "tollItemNo")
    private String tollItemNo;
    @XmlElement(name = "TollItem")
    private String feeName;
    @XmlElement(name = "CalcStartDate")
    private String calcStartDate;
    @XmlElement(name = "CalcEndDate")
    private String calcEndDate;
    @XmlElement(name = "Amount")
    private BigDecimal amount;
    @XmlElement(name = "Latefee")
    private BigDecimal lateFee;
    @XmlElement(name = "BaseAmount")
    private BigDecimal baseAmount;

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public String getFeeName() {
        return feeName;
    }

    public void setFeeName(String feeName) {
        this.feeName = feeName;
    }

    public String getCalcStartDate() {
        if (calcStartDate == null)
            return null;

        if (calcStartDate.contains("-")) {
            return calcStartDate;
        }
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date(calcStartDate));
    }

    public void setCalcStartDate(String calcStartDate) {
        this.calcStartDate = calcStartDate;
    }

    public String getCalcEndDate() {
        if (calcEndDate == null)
            return null;

        if (calcEndDate.contains("-")) {
            return calcEndDate;
        }

        return new SimpleDateFormat("yyyy-MM-dd").format(new Date(calcEndDate));
    }

    public void setCalcEndDate(String calcEndDate) {
        this.calcEndDate = calcEndDate;
    }

    public BigDecimal getAmount() {
        return amount == null ? null : amount.setScale(2, RoundingMode.HALF_UP);
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getLateFee() {
        return lateFee == null ? null : lateFee.setScale(2, RoundingMode.HALF_UP);
    }

    public void setLateFee(BigDecimal lateFee) {
        this.lateFee = lateFee;
    }

    public BigDecimal getBaseAmount() {
        return baseAmount == null ? null : baseAmount.setScale(2, RoundingMode.HALF_UP);
    }

    public void setBaseAmount(BigDecimal baseAmount) {
        this.baseAmount = baseAmount;
    }

    public String getTollItemNo() {
        return tollItemNo;
    }

    public void setTollItemNo(String tollItemNo) {
        this.tollItemNo = tollItemNo;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

    public PropertyFeeDetail turnToPropertyFeeDetail() {
        PropertyFeeDetail propertyFeeDetail = new PropertyFeeDetail();
        propertyFeeDetail.setName(this.getFeeName());
        propertyFeeDetail.setTotalPrice(getAmount().multiply(BigDecimal.valueOf(100)).doubleValue());
        propertyFeeDetail.setAllowancePrice(0L);
        return propertyFeeDetail;
    }
}
