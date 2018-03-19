package com.cnfantasia.server.ms.flashDealActivity.dao;

import com.cnfantasia.server.ms.flashDealActivity.entity.FlashDealActivityCfgParam;
import com.cnfantasia.server.ms.pub.dao.AbstractBaseDao;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: FlashDealActivityCfgDao
 * @Date: 2016-11-23 17:00
 * @Auther: yanghua
 * @Description:(幸运购活动配置)
 */
public class FlashDealActivityCfgDao extends AbstractBaseDao implements IFlashDealActivityCfgDao{
    @Override
    public List<FlashDealActivityCfgParam> getFlashDealActivityList(Map<String, Object> paramMap) {
        return sqlSession.selectList("flashDealActivityCfg.getFlashDealActivityList",paramMap);
    }

    @Override
    public int getFlashDealActivityListCount(Map<String, Object> paramMap) {
        return sqlSession.selectOne("flashDealActivityCfg.getFlashDealActivityListCount",paramMap);
    }

    @Override
    public FlashDealActivityCfgParam getFlashDealActivityDetail(BigInteger flashDealActivityId) {
        return sqlSession.selectOne("flashDealActivityCfg.getFlashDealActivityDetail",flashDealActivityId);
    }
}
