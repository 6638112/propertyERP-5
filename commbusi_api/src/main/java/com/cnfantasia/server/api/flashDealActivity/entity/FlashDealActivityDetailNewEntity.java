package com.cnfantasia.server.api.flashDealActivity.entity;

import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.business.pub.sysParam.ISysParamManager;
import com.cnfantasia.server.common.utils.PriceUtil;
import com.cnfantasia.server.domainbase.flashDealBuyRecord.entity.FlashDealBuyRecord;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

/**
 * @ClassName: FlashDealActivityDetailNewEntity
 * @Date: 2016-12-08 10:06
 * @Auther: yanghau
 * @Description:()
 */
public class FlashDealActivityDetailNewEntity implements Serializable{
    /**开始日*/
    private String startDay;
    /**幸运购详情*/
    private List<FlashDealActivityDetailEntity> flashDealActivityDetailEntities;
    /**是否是今天的活动 1 是 0 不是*/
    private int todayStatus;

    public String getStartDay() {
        return startDay;
    }

    public void setStartDay(String startDay) {
        this.startDay = startDay;
    }

    public List<FlashDealActivityDetailEntity> getFlashDealActivityDetailEntities() {
        return flashDealActivityDetailEntities;
    }

    public void setFlashDealActivityDetailEntities(List<FlashDealActivityDetailEntity> flashDealActivityDetailEntities) {
        this.flashDealActivityDetailEntities = flashDealActivityDetailEntities;
    }

    public int getTodayStatus() {
        if(startDay != null) {
            String day = startDay.substring(startDay.indexOf('月')+1,startDay.indexOf('日'));
            int day1 = Integer.valueOf(day);
            Calendar c = Calendar.getInstance();
            int day2 = c.get(Calendar.DAY_OF_MONTH);
            if(day1 == day2) {
                this.todayStatus = 1;
            }
        }
        return todayStatus;
    }

    public void setTodayStatus(int todayStatus) {
        this.todayStatus = todayStatus;
    }
}
