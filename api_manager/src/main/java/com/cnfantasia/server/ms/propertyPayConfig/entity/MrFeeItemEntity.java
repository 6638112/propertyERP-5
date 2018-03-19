package com.cnfantasia.server.ms.propertyPayConfig.entity;

import com.cnfantasia.server.domainbase.mrCalculateRuleCfg.entity.MrCalculateRuleCfg;
import com.cnfantasia.server.domainbase.mrFeeItem.entity.MrFeeItem;

import java.util.List;

/**
 * @ClassName: MrFeeItemEntity
 * @Date: 2017-09-22 10:04
 * @Auther: yanghua
 * @Description:(抄表收费项扩展，增加一户多表的计费规则信息)
 */
public class MrFeeItemEntity extends MrFeeItem{
    private List<MrCalculateRuleCfg> calculateRuleCfgList;

    public List<MrCalculateRuleCfg> getCalculateRuleCfgList() {
        return calculateRuleCfgList;
    }

    public void setCalculateRuleCfgList(List<MrCalculateRuleCfg> calculateRuleCfgList) {
        this.calculateRuleCfgList = calculateRuleCfgList;
    }
}
