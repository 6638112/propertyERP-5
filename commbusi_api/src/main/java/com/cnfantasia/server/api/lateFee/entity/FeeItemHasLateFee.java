package com.cnfantasia.server.api.lateFee.entity;

import java.math.BigInteger;
import java.util.Set;

/**
 * @className: FeeItemHasLateFee
 * @date: 2017-11-07 11:08
 * @author: yanghua
 * @description:(需要计算滞纳金的费用项)
 */
public class FeeItemHasLateFee {
    /**
     * 费用类型：1：抄表收费
     */
    private Set<String> mrFeeItemNames;
    /**
     * 费用类型：2：固定收费
     */
    private Set<String> fixedFeeItemNames;
    /**
     * 费用类型：3：临时收费
     */
    private Set<String> tmpFeeItemNames;

    public Set<String> getFixedFeeItemNames() {
        return fixedFeeItemNames;
    }

    public void setFixedFeeItemNames(Set<String> fixedFeeItemNames) {
        this.fixedFeeItemNames = fixedFeeItemNames;
    }

    public Set<String> getMrFeeItemNames() {
        return mrFeeItemNames;
    }

    public void setMrFeeItemNames(Set<String> mrFeeItemNames) {
        this.mrFeeItemNames = mrFeeItemNames;
    }

    public Set<String> getTmpFeeItemNames() {
        return tmpFeeItemNames;
    }

    public void setTmpFeeItemNames(Set<String> tmpFeeItemNames) {
        this.tmpFeeItemNames = tmpFeeItemNames;
    }
}
