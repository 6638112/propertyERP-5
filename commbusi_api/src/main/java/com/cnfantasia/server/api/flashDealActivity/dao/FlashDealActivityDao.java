package com.cnfantasia.server.api.flashDealActivity.dao;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.api.flashDealActivity.entity.FlashDealActivityDetailEntity;
import com.cnfantasia.server.api.flashDealActivity.entity.FlashDealActivityDetailNewEntity;
import com.cnfantasia.server.api.flashDealActivity.entity.FlashDealRemindEntity;
import com.cnfantasia.server.api.pub.dao.AbstractBaseDao;

/**
 * @ClassName: FlashDealActivityDao
 * @Date: 2016-10-29 17:45
 * @Auther: kangduo
 * @Description:(秒杀活动Dao层接口)
 */
public class FlashDealActivityDao extends AbstractBaseDao implements IFlashDealActivityDao{

    @Override
    public List<FlashDealActivityDetailNewEntity> getActivityList(Map<String, Object> param) {
        return sqlSession.selectList("flashDealActivity.getActivityList", param);
    }
    
    /**
     * 我的中奖记录
     */
    @Override
    public List<FlashDealActivityDetailEntity> getMyRecords(Map<String, Object> param){
    	return sqlSession.selectList("flashDealActivity.select_my_records", param);
    }

    @Override
    public FlashDealActivityDetailEntity getActivityEntityDetail(Map<String, Object> param) {
        return sqlSession.selectOne("flashDealActivity.getActivityEntityDetail", param);
    }

    @Override
    public boolean luckDrawByActivityId(BigInteger flashDealActivityId) {
        return sqlSession.selectOne("flashDealActivity.luckDrawByActivityId", flashDealActivityId);
    }

    @Override
    public List<String> getEbuyProductPicList(BigInteger activityId) {
        return sqlSession.selectList("flashDealActivity.getEbuyProductPicList", activityId);
    }

    @Override
    public List<FlashDealRemindEntity> getFlashDealRemindList() {
        return sqlSession.selectList("flashDealActivity.getFlashDealRemindList");
    }
}
