package com.cnfantasia.server.commbusi.plotproperty.entity;

import com.cnfantasia.server.common.utils.StringUtils;

import java.math.BigInteger;

/**
 * Created by yangh on 2016/10/19.
 */
public class EditPropPayBillTypeEntity {
    private BigInteger gbId;
    private Integer paytimeType;
    private Integer propertyPeriodType;
    private String[] periodMonthsArr;
    private String periodMonths;
    private Integer activeStatus;
    private Integer propertyMonthChange;
    private Integer payPeriodStart;
    private Integer payPeriodEnd;
    private Integer verificationStatus;
    private Integer isPrefer;

    public BigInteger getGbId() {
        return gbId;
    }

    public void setGbId(BigInteger gbId) {
        this.gbId = gbId;
    }

    public Integer getPaytimeType() {
        return paytimeType;
    }

    public void setPaytimeType(Integer paytimeType) {
        this.paytimeType = paytimeType;
    }

    public Integer getPropertyPeriodType() {
        return propertyPeriodType;
    }

    public void setPropertyPeriodType(Integer propertyPeriodType) {
        this.propertyPeriodType = propertyPeriodType;
    }

    public Integer getActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(Integer activeStatus) {
        this.activeStatus = activeStatus;
    }

    public Integer getPropertyMonthChange() {//给一个默认值，兼容以前的问题
        if(paytimeType != null && paytimeType.equals(1)) {//月缴
            propertyMonthChange = 0;//给默认值为0本月缴当月
        }
        return propertyMonthChange;
    }

    public void setPropertyMonthChange(Integer propertyMonthChange) {
        this.propertyMonthChange = propertyMonthChange;
    }

    public Integer getPayPeriodStart() {
        if(paytimeType != null && paytimeType.equals(1)) {//月缴
            payPeriodStart = 1;//给默认值为1
        }
        return payPeriodStart;
    }

    public void setPayPeriodStart(Integer payPeriodStart) {
        this.payPeriodStart = payPeriodStart;
    }

    public Integer getPayPeriodEnd() {
        if(paytimeType != null && paytimeType.equals(1)) {//月缴
            payPeriodEnd = 2;//给默认值为2
        }
        return payPeriodEnd;
    }

    public void setPayPeriodEnd(Integer payPeriodEnd) {
        this.payPeriodEnd = payPeriodEnd;
    }

    public Integer getVerificationStatus() {
        return verificationStatus;
    }

    public void setVerificationStatus(Integer verificationStatus) {
        this.verificationStatus = verificationStatus;
    }

    public Integer getIsPrefer() {
        return isPrefer;
    }

    public void setIsPrefer(Integer isPrefer) {
        this.isPrefer = isPrefer;
    }

    public String[] getPeriodMonthsArr() {
        if (!StringUtils.isEmpty(this.periodMonths)) {
            return periodMonthsArr = this.periodMonths.split(",");
        }
        return periodMonthsArr;
    }

    public void setPeriodMonthsArr(String[] periodMonthsArr) {
        this.periodMonthsArr = periodMonthsArr;
    }

    public String getPeriodMonths() {
        if(this.periodMonthsArr != null && this.periodMonthsArr.length > 0) {
            StringBuilder str = new StringBuilder();
            for (int i = 0; i < this.periodMonthsArr.length;i++) {
                str.append(this.periodMonthsArr[i]);
                if (i != this.periodMonthsArr.length - 1) {
                    str.append(",");
                }
            }
            periodMonths = str.toString();
        }
        return periodMonths;
    }

    public void setPeriodMonths(String periodMonths) {
        this.periodMonths = periodMonths;
    }
}
