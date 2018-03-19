package com.cnfantasia.server.api.access.codec.ake.bean.request;

import com.cnfantasia.server.api.access.codec.ake.bean.base.YdtRequest;
import java.math.BigDecimal;
import java.util.Date;

public class RenewalFeesOfMonthCardReq implements YdtRequest {
    private static final long serialVersionUID = 91794203317838752L;
    private String parkCode; // 停车场唯一编号
    private String carNO; // 车牌号
    private Date payDate; // 缴费日期
    private Integer payMonths; // 缴费月数
    private Date payExpiryDate; // 缴费截止日期
    private Integer payStandardCode; // 收费标准编号，例如：300元/月
    private BigDecimal paySumMoney; // 缴费总金额

    public String getParkCode() {
        return this.parkCode;
    }

    public void setParkCode(String parkCode) {
        this.parkCode = parkCode;
    }

    public String getCarNO() {
        return this.carNO;
    }

    public void setCarNO(String carNO) {
        this.carNO = carNO;
    }

    public Date getPayDate() {
        return this.payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    public Integer getPayMonths() {
        return this.payMonths;
    }

    public void setPayMonths(Integer payMonths) {
        this.payMonths = payMonths;
    }

    public Date getPayExpiryDate() {
        return this.payExpiryDate;
    }

    public void setPayExpiryDate(Date payExpiryDate) {
        this.payExpiryDate = payExpiryDate;
    }

    public Integer getPayStandardCode() {
        return this.payStandardCode;
    }

    public void setPayStandardCode(Integer payStandardCode) {
        this.payStandardCode = payStandardCode;
    }

    public BigDecimal getPaySumMoney() {
        return this.paySumMoney;
    }

    public void setPaySumMoney(BigDecimal paySumMoney) {
        this.paySumMoney = paySumMoney;
    }

    public String toString() {
        return "{parkCode:" + this.parkCode + "," + "carNO:" + this.carNO + "," + "payDate:" + this.payDate + "," + "payMonths："
                + this.payMonths + "," + "payExpiryDate:" + this.payExpiryDate + "," + "payStandardCode:" + this.payStandardCode + ","
                + "paySumMoney:" + this.paySumMoney + "}";
    }
}
