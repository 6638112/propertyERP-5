package com.propertySoftwareDock.jeez.entity;

import javax.xml.bind.annotation.*;

/**
 * @ClassName: JeezPayByBillItem
 * @Date: 2016-11-26 16:41
 * @Auther: kangduo
 * @Description:()
 */
public class JeezPayByBillItem {
    private String payNo;
    //欠费单号
    private String arrearNo;
    private String amount;
    //是否同时缴滞纳金,1为是，0为否
    private String isAlsoLateFee = "1";
    //无优惠
    private String discount = "0";
    //1为支付宝PC端，2为支付宝（移动），3为微信支付，4为银联在线
    private String payPlatfrom = "3";
    //1为支付宝生活缴费，2为社区网，3为社区通（app），4为微信，5为支付宝服务窗
    private String fromType = "4";

    public String getPayNo() {
        return payNo;
    }

    public void setPayNo(String payNo) {
        this.payNo = payNo;
    }

    public String getArrearNo() {
        return arrearNo;
    }

    public void setArrearNo(String arrearNo) {
        this.arrearNo = arrearNo;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getIsAlsoLateFee() {
        return isAlsoLateFee;
    }

    public void setIsAlsoLateFee(String isAlsoLateFee) {
        this.isAlsoLateFee = isAlsoLateFee;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getPayPlatfrom() {
        return payPlatfrom;
    }

    public void setPayPlatfrom(String payPlatfrom) {
        this.payPlatfrom = payPlatfrom;
    }

    public String getFromType() {
        return fromType;
    }

    public void setFromType(String fromType) {
        this.fromType = fromType;
    }

    private String convertNullToEmptyStr(String string) {
        return string == null ? "" : string;
    }

    @Override
    public String toString() {
        return convertNullToEmptyStr(payNo) +
                "," + convertNullToEmptyStr(arrearNo) +
                "," + convertNullToEmptyStr(amount) +
                "," + convertNullToEmptyStr(isAlsoLateFee) +
                "," + convertNullToEmptyStr(discount) +
                "," + convertNullToEmptyStr(payPlatfrom) +
                "," + convertNullToEmptyStr(fromType);
    }
}
