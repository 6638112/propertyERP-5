package com.cnfantasia.server.api.flashDealActivity.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.api.flashDealActivity.entity.FlashDealActivityDetailEntity;
import com.cnfantasia.server.api.flashDealActivity.entity.FlashDealActivityDetailNewEntity;
import com.cnfantasia.server.api.flashDealActivity.entity.FlashDealRemindEntity;
import com.cnfantasia.server.business.pub.entity.PageModel;

/**
 * @ClassName: IFlashDealActivityService
 * @Date: 2016-10-29 17:46
 * @Auther: kangduo
 * @Description:(秒杀活动Service层接口)
 */
public interface IFlashDealActivityService {

    /**
     * 查询指定小区可参与的活动
     * @param gbId
     * @param pageModel
     * @return
     */
    List<FlashDealActivityDetailNewEntity> getActivityList(BigInteger userId, BigInteger supplyMerchantId, BigInteger gbId, PageModel pageModel);
    
    /**
     * 我的中奖记录
     */
    List<FlashDealActivityDetailEntity> getMyRecords(Map<String, Object> param);

    /**
     * 查询指定ID的活动
     * @param activityId
     * @return
     */
    FlashDealActivityDetailEntity getActivityEntityDetail(BigInteger activityId, BigInteger userId);

    /**
     * 确认付款
     * @param activityId
     * @param userId
     * @param JFBAmount
     * @return 订单ID
     */
    Map<String, Object> confirmPay(BigInteger activityId, BigInteger userId, String userMobile, String userName, String subChannelId, Long JFBAmount);

    /**
     * 进行抽奖
     * @param flashDealActivityId
     * @return
     */
    boolean luckDrawByActivityId(BigInteger flashDealActivityId);

    /**
     * 保存用户活动提醒信息
     * @param activityId
     * @param userId
     * @return
     */
    boolean saveFlashDealRemind(BigInteger activityId, BigInteger userId);

    /**
     * 查询需要提醒活动的用户
     * @return
     */
    List<FlashDealRemindEntity> getFlashDealRemindList();
}
