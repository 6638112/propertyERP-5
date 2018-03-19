package com.cnfantasia.server.ms.propertyPayConfig.entity;

import java.math.BigInteger;

/**
 * @ClassName: CalculateRuleForStandar
 * @Date: 2017-09-25 17:15
 * @Auther: yanghua
 * @Description:(收费规则--收费标准)
 */
public class CalculateRuleForStandar {
    private BigInteger calculateRuleId;
    private String calculateRuleName;

    public BigInteger getCalculateRuleId() {
        return calculateRuleId;
    }

    public void setCalculateRuleId(BigInteger calculateRuleId) {
        this.calculateRuleId = calculateRuleId;
    }

    public String getCalculateRuleName() {
        return calculateRuleName;
    }

    public void setCalculateRuleName(String calculateRuleName) {
        this.calculateRuleName = calculateRuleName;
    }
}
