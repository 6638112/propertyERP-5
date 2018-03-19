package com.cnfantasia.server.domainbase.fixedFeeItem.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.fixedFeeItem.entity.FixedFeeItem;

/**
 * 描述:(固定收费项) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IFixedFeeItemBaseDao {
	/**
	 * 根据条件查询(固定收费项)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	List<FixedFeeItem> selectFixedFeeItemByCondition(Map<String, Object> paramMap, boolean isDim);
	/**
	 * 按条件分页查询(固定收费项)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	List<FixedFeeItem> selectFixedFeeItemByCondition(Map<String, Object> paramMap, PageModel pageModel, boolean isDim);
	/**
	 * 根据序列号查询(固定收费项)信息
	 * @param id
	 * @return
	 */
	FixedFeeItem selectFixedFeeItemBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(固定收费项)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	int selectFixedFeeItemCount(Map<String, Object> paramMap, boolean isDim);
	/**
	 * 往(固定收费项)新增一条记录
	 * @param fixedFeeItem
	 * @return
	 */
	int insertFixedFeeItem(FixedFeeItem fixedFeeItem);
	
	/**
	 * 批量新增(固定收费项)信息
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
	 * 根据Id 批量删除(固定收费项)信息_逻辑删除
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
