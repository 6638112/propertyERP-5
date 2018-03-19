package com.cnfantasia.server.domainbase.fixedFeeItem.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.fixedFeeItem.entity.FixedFeeItem;

/**
 * 描述:(固定收费项) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IFixedFeeItemBaseService {
	
	/**
	 * 根据条件查询(固定收费项)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	List<FixedFeeItem> getFixedFeeItemByCondition(Map<String, Object> paramMap);
	/**
	 * 根据条件查询(固定收费项)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	List<FixedFeeItem> getFixedFeeItemByConditionDim(Map<String, Object> paramMap);
	/**
	 * 按条件分页查询(固定收费项)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	List<FixedFeeItem> getFixedFeeItemByCondition(Map<String, Object> paramMap, PageModel pageModel);
	/**
	 * 按条件分页查询(固定收费项)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	List<FixedFeeItem> getFixedFeeItemByConditionDim(Map<String, Object> paramMap, PageModel pageModel);
	/**
	 * 根据序列号查询(固定收费项)信息
	 * @param id
	 * @return
	 */
	FixedFeeItem getFixedFeeItemBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(固定收费项)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	int getFixedFeeItemCount(Map<String, Object> paramMap);
	/**
	 * 根据条件查询满足条件的(固定收费项)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	int getFixedFeeItemCountDim(Map<String, Object> paramMap);
	/**
	 * 往(固定收费项)新增一条记录
	 * @param fixedFeeItem
	 * @return
	 */
	int insertFixedFeeItem(FixedFeeItem fixedFeeItem);
	/**
	 * 批量新增(固定收费项)
	 * @param fixedFeeItemList
	 * @return
	 */
	int insertFixedFeeItemBatch(List<FixedFeeItem> fixedFeeItemList);
	/**
	 * 更新(固定收费项)信息
	 * @param fixedFeeItem
	 * @return
	 */
	int updateFixedFeeItem(FixedFeeItem fixedFeeItem);
	/**
	 * 批量更新(固定收费项)信息
	 * @param fixedFeeItemList
	 * @return
	 */
	int updateFixedFeeItemBatch(List<FixedFeeItem> fixedFeeItemList);
	/**
	 * 根据序列号删除(固定收费项)信息_逻辑删除
	 * @param id
	 * @return
	 */

	int deleteFixedFeeItemLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(固定收费项)信息_逻辑删除
	 * @param idList
	 * @return
	 */

	int deleteFixedFeeItemLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(固定收费项)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteFixedFeeItem(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(固定收费项)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteFixedFeeItemBatch(List<java.math.BigInteger> idList);
	
}
