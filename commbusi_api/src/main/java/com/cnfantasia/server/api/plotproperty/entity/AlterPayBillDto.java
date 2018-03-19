package com.cnfantasia.server.api.plotproperty.entity;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: AlterPayBillDto
 * @Date: 2017-07-13 16:29
 * @Auther: yanghua
 * @Description:(选择周期账单列表)
 */
public class AlterPayBillDto {
    /**账单名称*/
    private String billTypeName;
    /** 账单类型图标 */
    private String billTypeImg;
    /** 账单类型灰色图标 */
    private String billTypeGrayImg;
    /**已缴至月份*/
    private String payEnd;
    /**开始月份*/
    private long monthStart;
    /**月份*/
    private List<AlterMonth2Bills> monthAndPayBill;
    /**收费类型：1 定期  2选择周期*/
    private Integer cycleType;
    /**账单时间标题*/
    private String billTitle;

    public String getBillTypeName() {
        return billTypeName;
    }

    public void setBillTypeName(String billTypeName) {
        this.billTypeName = billTypeName;
    }

    public List<AlterMonth2Bills> getMonthAndPayBill() {
        return monthAndPayBill;
    }

    public void setMonthAndPayBill(List<AlterMonth2Bills> monthAndPayBill) {
        this.monthAndPayBill = monthAndPayBill;
    }

    public String getBillTypeImg() {
        return billTypeImg;
    }

    public void setBillTypeImg(String billTypeImg) {
        this.billTypeImg = billTypeImg;
    }

    public String getBillTypeGrayImg() {
        return billTypeGrayImg;
    }

    public void setBillTypeGrayImg(String billTypeGrayImg) {
        this.billTypeGrayImg = billTypeGrayImg;
    }

    public String getPayEnd() {
        return payEnd;
    }

    public void setPayEnd(String payEnd) {
        this.payEnd = payEnd;
    }

    public long getMonthStart() {
        return monthStart;
    }

    public void setMonthStart(long monthStart) {
        this.monthStart = monthStart;
    }

    public Integer getCycleType() {
        return cycleType;
    }

    public void setCycleType(Integer cycleType) {
        this.cycleType = cycleType;
    }

    public String getBillTitle() {
        return billTitle;
    }

    public void setBillTitle(String billTitle) {
        this.billTitle = billTitle;
    }
}
