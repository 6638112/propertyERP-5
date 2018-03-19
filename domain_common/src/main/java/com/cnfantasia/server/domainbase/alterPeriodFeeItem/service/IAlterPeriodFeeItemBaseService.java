package com.cnfantasia.server.domainbase.alterPeriodFeeItem.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.alterPeriodFeeItem.entity.AlterPeriodFeeItem;

/**
 * 描述:(选择周期收费项) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IAlterPeriodFeeItemBaseService {
	
	/**
	 * 根据条件查询(选择周期收费项)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	List<AlterPeriodFeeItem> getAlterPeriodFeeItemByCondition(Map<String, Object> paramMap);
	/**
	 * 根据条件查询(选择周期收费项)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	List<AlterPeriodFeeItem> getAlterPeriodFeeItemByConditionDim(Map<String, Object> paramMap);
	/**
	 * 按条件分页查询(选择周期收费项)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	List<AlterPeriodFeeItem> getAlterPeriodFeeItemByCondition(Map<String, Object> paramMap, PageModel pageModel);
	/**
	 * 按条件分页查询(选择周期收费项)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	List<AlterPeriodFeeItem> getAlterPeriodFeeItemByConditionDim(Map<String, Object> paramMap, PageModel pageModel);
	/**
	 * 根据序列号查询(选择周期收费项)信息
	 * @param id
	 * @return
	 */
	AlterPeriodFeeItem getAlterPeriodFeeItemBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(选择周期收费项)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	int getAlterPeriodFeeItemCount(Map<String, Object> paramMap);
	/**
	 * 根据条件查询满足条件的(选择周期收费项)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	int getAlterPeriodFeeItemCountDim(Map<String, Object> paramMap);
	/**
	 * 往(选择周期收费项)新增一条记录
	 * @param alterPeriodFeeItem
	 * @return
	 */
	int insertAlterPeriodFeeItem(AlterPeriodFeeItem alterPeriodFeeItem);
	/**
	 * 批量新增(选择周期收费项)
	 * @param alterPeriodFeeItemList
	 * @return
	 */
	int insertAlterPeriodFeeItemBatch(List<AlterPeriodFeeItem> alterPeriodFeeItemList);
	/**
	 * 更新(选择周期收费项)信息
	 * @param alterPeriodFeeItem
	 * @return
	 */
	int updateAlterPeriodFeeItem(AlterPeriodFeeItem alterPeriodFeeItem);
	/**
	 * 批量更新(选择周期收费项)信息
	 * @param alterPeriodFeeItemList
	 * @return
	 */
	int updateAlterPeriodFeeItemBatch(List<AlterPeriodFeeItem> alterPeriodFeeItemList);
	/**
	 * 根据序列号删除(选择周期收费项)信息_逻辑删除
	 * @param id
	 * @return
	 */

	int deleteAlterPeriodFeeItemLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(选择周期收费项)信息_逻辑删除
	 * @param idList
	 * @return
	 */

	int deleteAlterPeriodFeeItemLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(选择周期收费项)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteAlterPeriodFeeItem(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(选择周期收费项)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteAlterPeriodFeeItemBatch(List<java.math.BigInteger> idList);
	
}
