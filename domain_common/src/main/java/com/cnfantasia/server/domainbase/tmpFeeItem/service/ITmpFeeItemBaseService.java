package com.cnfantasia.server.domainbase.tmpFeeItem.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.tmpFeeItem.entity.TmpFeeItem;

/**
 * 描述:(临时收费项) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ITmpFeeItemBaseService {
	
	/**
	 * 根据条件查询(临时收费项)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	List<TmpFeeItem> getTmpFeeItemByCondition(Map<String, Object> paramMap);
	/**
	 * 根据条件查询(临时收费项)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	List<TmpFeeItem> getTmpFeeItemByConditionDim(Map<String, Object> paramMap);
	/**
	 * 按条件分页查询(临时收费项)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	List<TmpFeeItem> getTmpFeeItemByCondition(Map<String, Object> paramMap, PageModel pageModel);
	/**
	 * 按条件分页查询(临时收费项)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	List<TmpFeeItem> getTmpFeeItemByConditionDim(Map<String, Object> paramMap, PageModel pageModel);
	/**
	 * 根据序列号查询(临时收费项)信息
	 * @param id
	 * @return
	 */
	TmpFeeItem getTmpFeeItemBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(临时收费项)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	int getTmpFeeItemCount(Map<String, Object> paramMap);
	/**
	 * 根据条件查询满足条件的(临时收费项)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	int getTmpFeeItemCountDim(Map<String, Object> paramMap);
	/**
	 * 往(临时收费项)新增一条记录
	 * @param tmpFeeItem
	 * @return
	 */
	int insertTmpFeeItem(TmpFeeItem tmpFeeItem);
	/**
	 * 批量新增(临时收费项)
	 * @param tmpFeeItemList
	 * @return
	 */
	int insertTmpFeeItemBatch(List<TmpFeeItem> tmpFeeItemList);
	/**
	 * 更新(临时收费项)信息
	 * @param tmpFeeItem
	 * @return
	 */
	int updateTmpFeeItem(TmpFeeItem tmpFeeItem);
	/**
	 * 批量更新(临时收费项)信息
	 * @param tmpFeeItemList
	 * @return
	 */
	int updateTmpFeeItemBatch(List<TmpFeeItem> tmpFeeItemList);
	/**
	 * 根据序列号删除(临时收费项)信息_逻辑删除
	 * @param id
	 * @return
	 */

	int deleteTmpFeeItemLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(临时收费项)信息_逻辑删除
	 * @param idList
	 * @return
	 */

	int deleteTmpFeeItemLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(临时收费项)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteTmpFeeItem(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(临时收费项)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteTmpFeeItemBatch(List<java.math.BigInteger> idList);
	
}
