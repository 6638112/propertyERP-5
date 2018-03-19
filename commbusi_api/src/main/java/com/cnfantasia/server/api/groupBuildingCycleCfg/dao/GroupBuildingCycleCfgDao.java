package com.cnfantasia.server.api.groupBuildingCycleCfg.dao;

import com.cnfantasia.server.api.groupBuildingCycleCfg.entity.FixedFeeItemInitEntity;
import com.cnfantasia.server.api.groupBuildingCycleCfg.entity.GroupBuildingBillCycleConfigEntity;
import com.cnfantasia.server.api.groupBuildingCycleCfg.entity.UnPaidPayBillEntity;
import com.cnfantasia.server.api.groupBuildingCycleCfg.entity.UpdatePayBillEntity;
import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;
import com.cnfantasia.server.domainbase.fixedFeeItem.entity.FixedFeeItem;
import com.cnfantasia.server.domainbase.groupBuildingBillCycle.entity.GroupBuildingBillCycle;
import com.cnfantasia.server.domainbase.groupBuildingBillCycleConfig.entity.GroupBuildingBillCycleConfig;
import com.cnfantasia.server.domainbase.mrFeeItem.entity.MrFeeItem;
import com.cnfantasia.server.domainbase.realRoom.entity.RealRoom;
import com.cnfantasia.server.domainbase.tmpFeeItem.entity.TmpFeeItem;
import com.cnfantasia.server.ms.revenue.entity.AlterUnPaidEntity;
import com.cnfantasia.server.ms.revenue.entity.PropertyFeeDetailTempEntity;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: GroupBuildingCycleCfgDao
 * @Date: 2017-05-08 11:30
 * @Auther: yanghua
 * @Description:(账期配置)
 */
public class GroupBuildingCycleCfgDao  extends AbstractBaseDao {

    public List<GroupBuildingBillCycleConfig> getGroupBuildingBillCycleConfigsForAuto() {
        return sqlSession.selectList("groupBuildingCycleCfg.getGroupBuildingBillCycleConfigsForAuto");
    }

    public List<PropertyFeeDetailTempEntity> getNeedSynchroData(BigInteger gbId, BigInteger id) {
        Map<String, Object> paraMap = new HashMap<String, Object>();
        paraMap.put("cfgId", id);
        paraMap.put("gbId", gbId);
        return sqlSession.selectList("fixedFeeCfg.getNeedSynchroData", paraMap);
    }

    public List<RealRoom> getNeedCreateBillRealRoom(BigInteger cycleId, BigInteger gbId) {
        Map<String, Object> paraMap = new HashMap<String, Object>();
        paraMap.put("cycleId", cycleId);
        paraMap.put("gbId", gbId);
        return sqlSession.selectList("groupBuildingCycleCfg.getNeedCreateBillRealRoom", paraMap);
    }

    public List<GroupBuildingBillCycle> getSameCycleByLastTime(BigInteger cycleId, BigInteger gbId, String payBillTypeName) {
        Map<String, Object> paraMap = new HashMap<String, Object>();
        paraMap.put("cycleId", cycleId);
        paraMap.put("gbId", gbId);
        paraMap.put("payBillTypeName", payBillTypeName);
        return sqlSession.selectOne("groupBuildingCycleCfg.getSameCycleByLastTime", paraMap);
    }

    public List<UnPaidPayBillEntity> getUnPaidPayBillList(BigInteger gbId, String payBillTypeName, Integer chargingMode) {
        Map<String, Object> paraMap = new HashMap<String, Object>();
        paraMap.put("gbId", gbId);
        paraMap.put("payBillTypeName", payBillTypeName);
        paraMap.put("chargingMode", chargingMode);
        return sqlSession.selectList("groupBuildingCycleCfg.getUnPaidPayBillList", paraMap);
    }

    /**
     * 注：任何地方不能再次引用该方法
     * 防止自动生成失败时间过了，所以使用手动生成
     * @param cycleCfgId
     * @return
     */
    public List<GroupBuildingBillCycleConfig> getGroupBuildingBillCycleConfigsForAutoById(BigInteger cycleCfgId) {
        return sqlSession.selectList("groupBuildingCycleCfg.getGroupBuildingBillCycleConfigsForAutoById", cycleCfgId);
    }

    public int isHashSameBillCycle(Map<String, Object> paraMap) {
        return sqlSession.selectOne("groupBuildingCycleCfg.isHashSameBillCycle", paraMap);
    }

    public List<GroupBuildingBillCycleConfigEntity> getGroupBuildingBillCycleConfigByCondition(Map<String, Object> paramMap) {
        return sqlSession.selectList("groupBuildingCycleCfg.getGroupBuildingBillCycleConfigByCondition", paramMap);
    }

    public List<FixedFeeItem> getBuildingHasFixedFeeItemList(BigInteger gbId) {
        return sqlSession.selectList("groupBuildingCycleCfg.getBuildingHasFixedFeeItemList", gbId);
    }

    public List<MrFeeItem> getBuildingHasMrFeeItemList(BigInteger gbId) {
        return sqlSession.selectList("groupBuildingCycleCfg.getBuildingHasMrFeeItemList", gbId);
    }
    
    public Integer setMaxRechargeNull(BigInteger gbccId) {
        return sqlSession.update("groupBuildingCycleCfg.setMaxRechargeNull", gbccId);
    }

    public List<TmpFeeItem> getBuildingHasTmpFeeItemList(BigInteger gbId) {
        return sqlSession.selectList("groupBuildingCycleCfg.getBuildingHasTmpFeeItemList", gbId);
    }

    /**
     * @param itemList
     * @param gbCycleCfgId 配置
     * @param gbId 小区
     */
    public void saveGroupBuildingBillCycleConfigHasFeeItem(List<Map<String, Object>> itemList, BigInteger gbCycleCfgId, BigInteger gbId) {
        Map<String, Object> paraMap = new HashMap<String, Object>();
        paraMap.put("itemList", itemList);
        paraMap.put("gbCycleCfgId", gbCycleCfgId);
        paraMap.put("gbId", gbId);
        sqlSession.update("groupBuildingCycleCfg.saveGroupBuildingBillCycleConfigHasFeeItem", paraMap);
    }

    public List<String> getHasFeeItems(GroupBuildingBillCycleConfigEntity groupBuildingBillCycleConfig) {
        Map<String, Object> paraMap = new HashMap<String, Object>();
        paraMap.put("fixedFeeItems", groupBuildingBillCycleConfig.getFixedFeeItemsIds());
        paraMap.put("mrFeeItems", groupBuildingBillCycleConfig.getMrFeeItemsIds());
        paraMap.put("tmpFeeItems", groupBuildingBillCycleConfig.getTmpFeeItemsIds());
        paraMap.put("gbId", groupBuildingBillCycleConfig.gettGbId());
        paraMap.put("gbCycleCfgId", groupBuildingBillCycleConfig.getId());
        return sqlSession.selectList("groupBuildingCycleCfg.getHasFeeItemsForCheck", paraMap);
    }

    public int delCollectFeesCfg(BigInteger id, BigInteger userId) {
        Map<String, Object> paraMap = new HashMap<String, Object>();
        paraMap.put("id", id);
        paraMap.put("userId", userId);
        return sqlSession.update("groupBuildingCycleCfg.delCollectFeesCfg", paraMap);
    }

    public List<FixedFeeItemInitEntity> getNeedInitFixedData(List<BigInteger> fixedFeeItemsIds) {
        Map<String, Object> paraMap = new HashMap<String, Object>();
        paraMap.put("fixedFeeItemsIds", fixedFeeItemsIds);
        return sqlSession.selectList("groupBuildingCycleCfg.getNeedInitFixedData", paraMap);
    }

    public String getNeedInitFixedDataMinDate(List<BigInteger> fixedFeeItemsIds) {
        Map<String, Object> paraMap = new HashMap<String, Object>();
        paraMap.put("fixedFeeItemsIds", fixedFeeItemsIds);
        return sqlSession.selectOne("groupBuildingCycleCfg.getNeedInitFixedDataMinDate", paraMap);
    }

    public List<AlterUnPaidEntity> getNeedUnpaidPayBillAndCycle() {
        return sqlSession.selectList("groupBuildingCycleCfg.getNeedUnpaidPayBillAndCycle");
    }

    public String getMaxBillEndMonthByFixedItem(String feeName, BigInteger realRoomId) {
        Map<String, Object> paraMap = new HashMap<String, Object>();
        paraMap.put("realRoomId", realRoomId);
        paraMap.put("feeName", feeName);
        return sqlSession.selectOne("groupBuildingCycleCfg.getMaxBillEndMonthByFixedItem", paraMap);
    }

    public List<UpdatePayBillEntity> getNeedUpdatedPayBills(List<BigInteger> itemHasRoomIds) {
        Map<String, Object> paraMap = new HashMap<String, Object>();
        paraMap.put("itemHasRoomIds", itemHasRoomIds);
        return sqlSession.selectList("groupBuildingCycleCfg.getNeedUpdatedPayBills", paraMap);
    }

    public List<GroupBuildingBillCycle> getGroupBuildingBillCycleByItemId(BigInteger itemId) {
        return sqlSession.selectList("groupBuildingCycleCfg.getGroupBuildingBillCycleByItemId", itemId);
    }

    public List<UpdatePayBillEntity> getNeedUpdatedPayBillsByRoom(BigInteger realRoomId, List<BigInteger> itemIds) {
        Map<String, Object> paraMap = new HashMap<String, Object>();
        paraMap.put("itemIds", itemIds);
        paraMap.put("realRoomId", realRoomId);
        return sqlSession.selectList("groupBuildingCycleCfg.getNeedUpdatedPayBillsByRoom", paraMap);
    }

    public List<AlterUnPaidEntity> getNeedUnpaidPayBillAndCycle02(BigInteger cycleCfgId) {
        return sqlSession.selectList("groupBuildingCycleCfg.getNeedUnpaidPayBillAndCycle02", cycleCfgId);
    }

    public List<GroupBuildingBillCycleConfig> getBuildingBillCycleConfigsByItemIds(List<BigInteger> itemIds) {
        Map<String, Object> paraMap = new HashMap<String, Object>();
        paraMap.put("itemIds", itemIds);
        return sqlSession.selectList("groupBuildingCycleCfg.getBuildingBillCycleConfigsByItemIds", paraMap);
    }

    public int delNoPayBillsGroupBuildingCycles(List<BigInteger> cycleIds) {
        Map<String, Object> paraMap = new HashMap<String, Object>();
        paraMap.put("cycleIds", cycleIds);
        return sqlSession.update("groupBuildingCycleCfg.delNoPayBillsGroupBuildingCycles", paraMap);
    }

    public List<MrFeeItem> getMrFeeItemByCondition(Map<String, Object> mriQryMap) {
        return sqlSession.selectList("groupBuildingCycleCfg.getMrFeeItemByCondition", mriQryMap);
    }

    public List<TmpFeeItem> getTmpFeeItemByCondition(Map<String, Object> mriQryMap) {
        return sqlSession.selectList("groupBuildingCycleCfg.getTmpFeeItemByCondition", mriQryMap);
    }
}
