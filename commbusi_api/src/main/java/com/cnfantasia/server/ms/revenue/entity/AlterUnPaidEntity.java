package com.cnfantasia.server.ms.revenue.entity;

import com.cnfantasia.server.domainbase.groupBuildingBillCycle.entity.GroupBuildingBillCycle;
import com.cnfantasia.server.domainbase.payBill.entity.PayBill;

import java.math.BigInteger;
import java.util.List;

/**
 * @ClassName: AlterUnPaidEntity
 * @Date: 2017-07-13 10:17
 * @Auther: yanghua
 * @Description:(选择周期欠费配置类)
 */
public class AlterUnPaidEntity extends GroupBuildingBillCycle{
    private String billName;
    private List<PayBill> payBills;

    public String getBillName() {
        return billName;
    }

    public void setBillName(String billName) {
        this.billName = billName;
    }

    public List<PayBill> getPayBills() {
        return payBills;
    }

    public void setPayBills(List<PayBill> payBills) {
        this.payBills = payBills;
    }
}
