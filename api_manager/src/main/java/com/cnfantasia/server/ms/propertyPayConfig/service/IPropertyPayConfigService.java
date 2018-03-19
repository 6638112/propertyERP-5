package com.cnfantasia.server.ms.propertyPayConfig.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.ms.propertyPayConfig.entity.*;

import com.cnfantasia.server.domainbase.alterPeriodFeeItem.entity.AlterPeriodFeeItem;

/**
 * 收费项配置
 * 
 * @author liyulin
 * @version 1.0 2016年10月24日 下午2:53:09
 */
public interface IPropertyPayConfigService {
	
	/**
	 * 根据条件查询(选择周期收费项)信息
	 * @param paramMap
	 * @return
	 */
	List<AlterPeriodFeeItem> selectAlterPeriodFeeItemByCondition(Map<String, Object> paramMap);

	/**
	 * 保存收费项配置
	 * 
	 * @param gbId
	 * @param alterPeriodFeeItems
	 * @param billName
	 *@param periodMonths
	 * @return
	 */
	boolean savePayConfig(BigInteger gbId, String alterPeriodFeeItems, String billName, String periodMonths);
	
	/**
	 * 保存滞纳金配置
	 * 
	 * @param lateMoneyConfigRequest
	 * @return
	 */
	boolean saveLateMoneyConfig(LateMoneyConfigRequest lateMoneyConfigRequest);

	/**
	 * 删除收费项
	 * @param itemId
	 * @param gbId
	 * @return
     */
	String deleteItem(BigInteger itemId, BigInteger gbId);

	/**
	 * 删除某小区的费用明细
	 * @param gbId
	 * @return
     */
	String deleteAllDetailByGb(BigInteger gbId);
	
	/**
     * 保存临时收费项配置
     * @param gbId
     * @param items 收费项
     * @return
     */
	boolean saveTmpFeeItem(BigInteger gbId, String items);

	/**
	 * 根据小区ID查询抄表收费项及计费规则信息
	 * @param gbId
	 * @return
     */
	List<MrFeeItemEntity> getMrFeeItemEntityByGbId(BigInteger gbId);

	/**
	 * 保存一户多表配置
	 * @param calculateRuleEntity
	 * @param gbId
	 */
	void saveMrCalculateRuleCfg(CalculateRuleEntity calculateRuleEntity, BigInteger gbId);

	/**
	 * 删除收费配置规则
	 * @param id
     */
	int delMrCalculateRuleCfg(BigInteger id);

	/**
	 * 根据小区ID查询抄表收费标准的楼栋房间数据
	 * @param gbId
	 * @return
     */
	List<GroupBuildingTreeEntity> getRoomTreeByGbId(BigInteger gbId);

	/**
	 * 查询抄表收费标准
	 * @param gbId
	 * @param buildingId
	 * @param realRoomId
     * @return
     */
	JsonResponse getMrStandardList(BigInteger gbId, BigInteger buildingId, BigInteger realRoomId);

	/**
	 * 保存抄表收费标准
	 * @param standardSaveParam
	 * @return
     */
	String saveMrStandard(StandardSaveParam standardSaveParam);

	/**
	 * 删除房间收费标准
	 * @param id
	 * @return
     */
	int delMrStandard(BigInteger id);

	/**
	 * 根据收费标准ID查询计算规则
	 * @param standardRoomId
	 * @return
     */
	List<CalculateRuleEntity> getCalculateRuleByStandardRooId(BigInteger standardRoomId);

	/**
	 * 查询计费规则：定价方式
	 * @param standardRoomId
	 * @return
     */
	Integer getCalculatePriceTypeByStandardRoomId(BigInteger standardRoomId);

	/**
	 * 检查计算规则是否被使用
	 * @param id
	 * @return
     */
	Boolean calculateRuleIsUse(BigInteger id);
}
