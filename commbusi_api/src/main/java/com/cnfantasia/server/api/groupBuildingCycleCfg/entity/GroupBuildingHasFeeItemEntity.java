package com.cnfantasia.server.api.groupBuildingCycleCfg.entity;

import com.cnfantasia.server.domainbase.fixedFeeItem.entity.FixedFeeItem;
import com.cnfantasia.server.domainbase.groupBuildingBillCycleConfig.entity.GroupBuildingBillCycleConfig;
import com.cnfantasia.server.domainbase.mrFeeItem.entity.MrFeeItem;
import com.cnfantasia.server.domainbase.tmpFeeItem.entity.TmpFeeItem;

import java.math.BigInteger;
import java.util.List;

/**
 * @ClassName: GroupBuildingBillCycleConfigEntity
 * @Date: 2017-07-07 15:47
 * @Auther: yanghua
 * @Description:(小区包含的费用项)
 */
public class GroupBuildingHasFeeItemEntity {
    /**包含常规收费项*/
    private List<FixedFeeItem> fixedFeeItems;
    /**包含抄表收费项*/
    private List<MrFeeItem> mrFeeItems;
    /**包含临时收费项*/
    private List<TmpFeeItem> tmpFeeItems;

    public List<FixedFeeItem> getFixedFeeItems() {
        return fixedFeeItems;
    }

    public void setFixedFeeItems(List<FixedFeeItem> fixedFeeItems) {
        this.fixedFeeItems = fixedFeeItems;
    }

    public List<MrFeeItem> getMrFeeItems() {
        return mrFeeItems;
    }

    public void setMrFeeItems(List<MrFeeItem> mrFeeItems) {
        this.mrFeeItems = mrFeeItems;
    }

    public List<TmpFeeItem> getTmpFeeItems() {
        return tmpFeeItems;
    }

    public void setTmpFeeItems(List<TmpFeeItem> tmpFeeItems) {
        this.tmpFeeItems = tmpFeeItems;
    }
}
