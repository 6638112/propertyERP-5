package com.cnfantasia.server.ms.propertyPayConfig.dao;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.domainbase.alterPeriodCfg.entity.AlterPeriodCfg;
import com.cnfantasia.server.domainbase.alterPeriodFeeItem.entity.AlterPeriodFeeItem;
import com.cnfantasia.server.domainbase.mrStandardBuilding.entity.MrStandardBuilding;
import com.cnfantasia.server.domainbase.mrStandardGroupBuilding.entity.MrStandardGroupBuilding;
import com.cnfantasia.server.ms.propertyPayConfig.entity.*;

/**
 * 收费项配置
 * 
 * @author liyulin
 * @version 1.0 2016年10月24日 下午2:53:09
 */
public interface IPropertyPayConfigDao {
	
	/**
	 * 根据条件查询(选择周期收费项)信息
	 * @param paramMap
	 * @return
	 */
	List<AlterPeriodFeeItem> selectAlterPeriodFeeItemByCondition(Map<String, Object> paramMap);
	
	/**
	 * 查询配置信息（加锁，避免并发）
	 * 
	 * @param gbId
	 * @return
	 */
	AlterPeriodCfg getAlterPeriodCfgWithLock(BigInteger gbId);
	
	/**
	 * 更新(选择周期配置)信息
	 * @param alterPeriodCfg
	 * @return
	 */
	int updateAlterPeriodCfg(AlterPeriodCfg alterPeriodCfg);

	/**
	 * 删除明细项
	 * @param paramMap
	 * @return
     */
	int deleteAlterRoomHasFeeItemByItemId(Map<String,Object> paramMap);

	/**
	 * 根据小区id清除选择周期数据
	 * @param paraMap
	 * @return
     */
	int deleteAllDetailByGb(Map paraMap);

	/**
	 * 根据小区id查询抄表收费项数据信息
	 * @param gbId
	 * @return
     */
	List<MrFeeItemEntity> getMrFeeItemEntityByGbId(BigInteger gbId);

	/**
	 * 删除收费规则配置项
	 *
	 * @param userId
	 * @param id
	 * @return
     */
	int delMrCalculateRuleCfg(BigInteger userId, BigInteger id);

	/**
	 * 根据小区ID查询抄表收费标准的楼栋房间数据
	 * @param gbId
	 * @return
     */
	List<GroupBuildingTreeEntity> getRoomTreeByGbId(BigInteger gbId);

	/**
	 * 根据房间ID查询收费标准数据
	 * @param realRoomId
	 * @return
     */
	List<MrStandardRoomEntity> getMrStandardRoomByRealRoomId(BigInteger realRoomId);

	/**
	 * 根据楼栋ID查询收费标准数据
	 * @param buildingId
	 * @return
     */
	List<MrStandardBuildingEntity> getMrStandardBuildingByBuildingId(BigInteger buildingId);

	/**
	 * 根据小区ID查询收费标准数据
	 * @param gbId
	 * @return
     */
	List<MrStandardGroupBuildingEntity> getMrStandardBuildingBygbId(BigInteger gbId);

	/**
	 * 查询房间号对应的费用项对应的计算规则
	 * @param realRoomId
	 * @return
     */
	List<CalculateRuleForStandar> getItemFeesHascalculateRules(BigInteger realRoomId);

	/**
	 * 根据收费标准ID查询计算规则
	 * @param standardRoomId
	 * @return
	 */
	List<CalculateRuleEntity> getCalculateRuleByStandardRooId(BigInteger standardRoomId);

	/**
	 * 查询计算规则的定价方式
	 * @param standardRoomId
	 * @return
     */
	Integer getCalculatePriceTypeByStandardRoomId(BigInteger standardRoomId);

	/**
	 * 检查计算规则使用情况
	 * @param id
	 * @return
     */
	Integer getCalculateRuleIsUseList(BigInteger id);
}
