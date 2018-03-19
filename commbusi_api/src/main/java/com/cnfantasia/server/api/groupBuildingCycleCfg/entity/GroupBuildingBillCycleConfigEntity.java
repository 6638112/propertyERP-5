package com.cnfantasia.server.api.groupBuildingCycleCfg.entity;

import com.cnfantasia.server.common.utils.DataUtil;
import com.cnfantasia.server.common.utils.DateUtils;
import com.cnfantasia.server.domainbase.fixedFeeItem.entity.FixedFeeItem;
import com.cnfantasia.server.domainbase.groupBuildingBillCycleConfig.entity.GroupBuildingBillCycleConfig;
import com.cnfantasia.server.domainbase.mrFeeItem.entity.MrFeeItem;
import com.cnfantasia.server.domainbase.tmpFeeItem.entity.TmpFeeItem;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

/**
 * @ClassName: GroupBuildingBillCycleConfigEntity
 * @Date: 2017-07-07 15:47
 * @Auther: yanghua
 * @Description:(账期配置实体)
 */
public class GroupBuildingBillCycleConfigEntity extends GroupBuildingBillCycleConfig {
    /**包含常规收费项*/
    private List<FixedFeeItem> fixedFeeItems;
    private List<BigInteger> fixedFeeItemsIds;
    /**包含抄表收费项*/
    private List<MrFeeItem> mrFeeItems;
    private List<BigInteger> mrFeeItemsIds;
    /**包含临时收费项*/
    private List<TmpFeeItem> tmpFeeItems;
    private List<BigInteger> tmpFeeItemsIds;
    /**选中月份数组*/
    private String[] months;
    /**缴费时长*/
    private Integer billMonthSize;

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

    public List<BigInteger> getFixedFeeItemsIds() {
        return fixedFeeItemsIds;
    }

    public void setFixedFeeItemsIds(List<BigInteger> fixedFeeItemsIds) {
        this.fixedFeeItemsIds = fixedFeeItemsIds;
    }

    public List<BigInteger> getMrFeeItemsIds() {
        return mrFeeItemsIds;
    }

    public void setMrFeeItemsIds(List<BigInteger> mrFeeItemsIds) {
        this.mrFeeItemsIds = mrFeeItemsIds;
    }

    public List<BigInteger> getTmpFeeItemsIds() {
        return tmpFeeItemsIds;
    }

    public void setTmpFeeItemsIds(List<BigInteger> tmpFeeItemsIds) {
        this.tmpFeeItemsIds = tmpFeeItemsIds;
    }

    public String[] getMonths() {
        if(!DataUtil.isEmpty(getPeriodMonths())) {
            months = getPeriodMonths().split(",");
        }
        return months;
    }

    public void setMonths(String[] months) {
        this.months = months;
    }

    public Integer getBillMonthSize() {
        Date billMonthStart = DateUtils.convertStrToDate(getBillMonthStart());
        Date billMonthEnd = DateUtils.convertStrToDate(getBillMonthEnd());
        billMonthSize = DateUtils.getDiffMonths(billMonthStart, billMonthEnd);
        /*2017-07 - 2017-08 = 2*/
        return billMonthSize + 1;
    }

    public void setBillMonthSize(Integer billMonthSize) {
        this.billMonthSize = billMonthSize;
    }

}
