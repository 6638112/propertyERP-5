package com.cnfantasia.server.api.plotproperty.entity;

import com.cnfantasia.server.domainbase.payBill.entity.PayBill;

import java.util.List;

/**
 * @ClassName: AlterPayBillEntity
 * @Date: 2017-07-13 10:52
 * @Auther: yanghua
 * @Description:(选择周期账单类)
 */
public class AlterPayBillEntity extends PayBill{
    private List<PayBill> unpaidList;
    /** 优惠金额 */
    private Long preferAmt;
    public List<PayBill> getUnpaidList() {
        return unpaidList;
    }

    public void setUnpaidList(List<PayBill> unpaidList) {
        this.unpaidList = unpaidList;
    }

    public Long getPreferAmt() {
        return preferAmt;
    }

    public void setPreferAmt(Long preferAmt) {
        this.preferAmt = preferAmt;
    }
}
