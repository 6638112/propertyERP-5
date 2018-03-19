package com.cnfantasia.server.ms.propertyPayConfig.entity;

import java.math.BigInteger;
import java.util.List;

/**
 * @ClassName: MrFeeItemForStandar
 * @Date: 2017-09-25 17:14
 * @Auther: yanghua
 * @Description:(收费标准--费用项)
 */
public class MrFeeItemForStandar {
    /**费用项ID*/
    private BigInteger feeItemId;
    /**费用项名称*/
    private String feeItemName;
    /**每个小区或者楼栋的每个费用项对应的选中的收费规则ID*/
    private BigInteger mrCalculateRuleCfgId;
    /**费用项 和 计费规则关系*/
    private List<CalculateRuleForStandar> calculateRuleForStandars;

    public BigInteger getFeeItemId() {
        return feeItemId;
    }

    public void setFeeItemId(BigInteger feeItemId) {
        this.feeItemId = feeItemId;
    }

    public String getFeeItemName() {
        return feeItemName;
    }

    public void setFeeItemName(String feeItemName) {
        this.feeItemName = feeItemName;
    }

    public List<CalculateRuleForStandar> getCalculateRuleForStandars() {
        return calculateRuleForStandars;
    }

    public void setCalculateRuleForStandars(List<CalculateRuleForStandar> calculateRuleForStandars) {
        this.calculateRuleForStandars = calculateRuleForStandars;
    }

    public BigInteger getMrCalculateRuleCfgId() {
        return mrCalculateRuleCfgId;
    }

    public void setMrCalculateRuleCfgId(BigInteger mrCalculateRuleCfgId) {
        this.mrCalculateRuleCfgId = mrCalculateRuleCfgId;
    }
}
