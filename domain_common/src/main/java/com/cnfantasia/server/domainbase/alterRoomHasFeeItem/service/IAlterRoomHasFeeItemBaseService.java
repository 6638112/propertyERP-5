package com.cnfantasia.server.domainbase.alterRoomHasFeeItem.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.alterRoomHasFeeItem.entity.AlterRoomHasFeeItem;

/**
 * 描述:(房间收费项表) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IAlterRoomHasFeeItemBaseService {
	
	/**
	 * 根据条件查询(房间收费项表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	List<AlterRoomHasFeeItem> getAlterRoomHasFeeItemByCondition(Map<String, Object> paramMap);
	/**
	 * 根据条件查询(房间收费项表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	List<AlterRoomHasFeeItem> getAlterRoomHasFeeItemByConditionDim(Map<String, Object> paramMap);
	/**
	 * 按条件分页查询(房间收费项表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	List<AlterRoomHasFeeItem> getAlterRoomHasFeeItemByCondition(Map<String, Object> paramMap, PageModel pageModel);
	/**
	 * 按条件分页查询(房间收费项表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	List<AlterRoomHasFeeItem> getAlterRoomHasFeeItemByConditionDim(Map<String, Object> paramMap, PageModel pageModel);
	/**
	 * 根据序列号查询(房间收费项表)信息
	 * @param id
	 * @return
	 */
	AlterRoomHasFeeItem getAlterRoomHasFeeItemBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(房间收费项表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	int getAlterRoomHasFeeItemCount(Map<String, Object> paramMap);
	/**
	 * 根据条件查询满足条件的(房间收费项表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	int getAlterRoomHasFeeItemCountDim(Map<String, Object> paramMap);
	/**
	 * 往(房间收费项表)新增一条记录
	 * @param alterRoomHasFeeItem
	 * @return
	 */
	int insertAlterRoomHasFeeItem(AlterRoomHasFeeItem alterRoomHasFeeItem);
	/**
	 * 批量新增(房间收费项表)
	 * @param alterRoomHasFeeItemList
	 * @return
	 */
	int insertAlterRoomHasFeeItemBatch(List<AlterRoomHasFeeItem> alterRoomHasFeeItemList);
	/**
	 * 更新(房间收费项表)信息
	 * @param alterRoomHasFeeItem
	 * @return
	 */
	int updateAlterRoomHasFeeItem(AlterRoomHasFeeItem alterRoomHasFeeItem);
	/**
	 * 批量更新(房间收费项表)信息
	 * @param alterRoomHasFeeItemList
	 * @return
	 */
	int updateAlterRoomHasFeeItemBatch(List<AlterRoomHasFeeItem> alterRoomHasFeeItemList);
	/**
	 * 根据序列号删除(房间收费项表)信息_逻辑删除
	 * @param id
	 * @return
	 */

	int deleteAlterRoomHasFeeItemLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(房间收费项表)信息_逻辑删除
	 * @param idList
	 * @return
	 */

	int deleteAlterRoomHasFeeItemLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(房间收费项表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteAlterRoomHasFeeItem(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(房间收费项表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteAlterRoomHasFeeItemBatch(List<java.math.BigInteger> idList);
	
}
