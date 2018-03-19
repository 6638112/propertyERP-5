package com.cnfantasia.server.ms.propertyPayConfig.entity;

import com.cnfantasia.server.domainbase.mrCalculateRuleCfg.entity.MrCalculateRuleCfg;
import com.cnfantasia.server.domainbase.mrFeeItemFormula.entity.MrFeeItemFormula;

import java.math.BigInteger;
import java.util.List;

/**
 * @ClassName: CalculateRuleEntity
 * @Date: 2017-09-22 16:56
 * @Auther: yanghua
 * @Description:(计费规则实体)
 */
public class CalculateRuleEntity extends MrCalculateRuleCfg{
    private BigInteger mrFeeItemId;
    private List<MrFeeItemFormula> mrFeeItemFormulas;

    public List<MrFeeItemFormula> getMrFeeItemFormulas() {
        return mrFeeItemFormulas;
    }

    public void setMrFeeItemFormulas(List<MrFeeItemFormula> mrFeeItemFormulas) {
        this.mrFeeItemFormulas = mrFeeItemFormulas;
    }

    public BigInteger getMrFeeItemId() {
        return mrFeeItemId;
    }

    public void setMrFeeItemId(BigInteger mrFeeItemId) {
        this.mrFeeItemId = mrFeeItemId;
    }
}
