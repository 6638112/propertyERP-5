package com.cnfantasia.server.api.flashDealActivity.dao;

import com.cnfantasia.server.api.flashDealActivity.entity.FlashDealActivityDetailEntity;
import com.cnfantasia.server.api.flashDealActivity.entity.FlashDealActivityDetailNewEntity;
import com.cnfantasia.server.api.flashDealActivity.entity.FlashDealRemindEntity;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: IFlashDealActivityDao
 * @Date: 2016-10-29 17:45
 * @Auther: kangduo
 * @Description:(秒杀活动Dao层接口)
 */
public interface IFlashDealActivityDao {
    List<FlashDealActivityDetailNewEntity> getActivityList(Map<String, Object> param);
    
    List<FlashDealActivityDetailEntity> getMyRecords(Map<String, Object> param);

    FlashDealActivityDetailEntity getActivityEntityDetail(Map<String, Object> param);

    boolean luckDrawByActivityId(BigInteger flashDealActivityId);

    /**
     * 查询幸运购商品的图片
     * @param activityId
     * @return
     */
    List<String> getEbuyProductPicList(BigInteger activityId);

    /***
     * 查询需要推送提醒的信息
     * @return
     */
    List<FlashDealRemindEntity> getFlashDealRemindList();
}
