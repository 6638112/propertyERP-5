package com.cnfantasia.server.domainbase.tmpFeeItem.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.tmpFeeItem.entity.TmpFeeItem;

/**
 * 描述:(临时收费项) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ITmpFeeItemBaseDao {
	/**
	 * 根据条件查询(临时收费项)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	List<TmpFeeItem> selectTmpFeeItemByCondition(Map<String, Object> paramMap, boolean isDim);
	/**
	 * 按条件分页查询(临时收费项)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	List<TmpFeeItem> selectTmpFeeItemByCondition(Map<String, Object> paramMap, PageModel pageModel, boolean isDim);
	/**
	 * 根据序列号查询(临时收费项)信息
	 * @param id
	 * @return
	 */
	TmpFeeItem selectTmpFeeItemBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(临时收费项)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	int selectTmpFeeItemCount(Map<String, Object> paramMap, boolean isDim);
	/**
	 * 往(临时收费项)新增一条记录
	 * @param tmpFeeItem
	 * @return
	 */
	int insertTmpFeeItem(TmpFeeItem tmpFeeItem);
	
	/**
	 * 批量新增(临时收费项)信息
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
	 * 根据Id 批量删除(临时收费项)信息_逻辑删除
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
