package com.cnfantasia.server.ms.flashDealActivity.service;

import com.cnfantasia.server.domainbase.flashDealActivity.service.IFlashDealActivityBaseService;
import com.cnfantasia.server.ms.flashDealActivity.entity.FlashDealActivityCfgParam;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: IFlashDealActivityCfgService
 * @Date: 2016-11-23 16:56
 * @Auther: yanghua
 * @Description:(幸运购活动配置)
 */
public interface IFlashDealActivityCfgService {
    /**
     * 保存幸运购配置信息
     * @param flashDealActivityCfgParam
     * @return
     */
    void saveFlashDealActivityCfg(FlashDealActivityCfgParam flashDealActivityCfgParam);

    /**
     * 活动列表
     * @param paramMap
     * @return
     */
    List<FlashDealActivityCfgParam> getFlashDealActivityList(Map<String, Object> paramMap);

    /**
     * 活动记录总数
     * @param paramMap
     * @return
     */
    int getFlashDealActivityListCount(Map<String, Object> paramMap);

    /**
     * 详情查询
     * @param flashDealActivityId
     * @return
     */
    FlashDealActivityCfgParam getFlashDealActivityDetail(BigInteger flashDealActivityId);
}
