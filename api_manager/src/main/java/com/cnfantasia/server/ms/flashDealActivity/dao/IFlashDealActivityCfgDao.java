package com.cnfantasia.server.ms.flashDealActivity.dao;

import com.cnfantasia.server.ms.flashDealActivity.entity.FlashDealActivityCfgParam;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: IFlashDealActivityDao
 * @Date: 2016-11-23 16:59
 * @Auther: yanghua
 * @Description:(幸运购活动配置)
 */
public interface IFlashDealActivityCfgDao {
    /**
     * 获取幸运购活动列表
     * @param paramMap
     * @return
     */
    List<FlashDealActivityCfgParam> getFlashDealActivityList(Map<String, Object> paramMap);

    /**
     * 活动总数
     * @param paramMap
     * @return
     */
    int getFlashDealActivityListCount(Map<String, Object> paramMap);

    /**
     * 查询活动详情
     * @param flashDealActivityId
     * @return
     */
    FlashDealActivityCfgParam getFlashDealActivityDetail(BigInteger flashDealActivityId);
}
