package com.cnfantasia.server.api.plotproperty.entity;

import java.math.BigDecimal;

/**
 * @ClassName: AlterMonth2Bills
 * @Date: 2017-07-13 14:20
 * @Auther: yanghua
 * @Description:(选择周期月份对应的账单信息)
 */
public class AlterMonth2Bills {
    /**月份*/
    private String month;
    /**账单IDS*/
    private String ids;
    /**金额*/
    private String amt;
    /** 优惠金额 */
    private String preferAmt;

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public String getAmt() {
        return amt;
    }

    public void setAmt(String amt) {
        this.amt = amt;
    }

    public String getPreferAmt() {
        return preferAmt;
    }

    public void setPreferAmt(String preferAmt) {
        this.preferAmt = preferAmt;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }
}
