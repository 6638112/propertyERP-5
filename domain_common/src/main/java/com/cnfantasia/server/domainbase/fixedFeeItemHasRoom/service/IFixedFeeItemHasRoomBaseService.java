package com.cnfantasia.server.domainbase.fixedFeeItemHasRoom.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.fixedFeeItemHasRoom.entity.FixedFeeItemHasRoom;

/**
 * 描述:(固定收费项和房间关联信息) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IFixedFeeItemHasRoomBaseService {
	
	/**
	 * 根据条件查询(固定收费项和房间关联信息)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	List<FixedFeeItemHasRoom> getFixedFeeItemHasRoomByCondition(Map<String, Object> paramMap);
	/**
	 * 根据条件查询(固定收费项和房间关联信息)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	List<FixedFeeItemHasRoom> getFixedFeeItemHasRoomByConditionDim(Map<String, Object> paramMap);
	/**
	 * 按条件分页查询(固定收费项和房间关联信息)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	List<FixedFeeItemHasRoom> getFixedFeeItemHasRoomByCondition(Map<String, Object> paramMap, PageModel pageModel);
	/**
	 * 按条件分页查询(固定收费项和房间关联信息)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	List<FixedFeeItemHasRoom> getFixedFeeItemHasRoomByConditionDim(Map<String, Object> paramMap, PageModel pageModel);
	/**
	 * 根据序列号查询(固定收费项和房间关联信息)信息
	 * @param id
	 * @return
	 */
	FixedFeeItemHasRoom getFixedFeeItemHasRoomBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(固定收费项和房间关联信息)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	int getFixedFeeItemHasRoomCount(Map<String, Object> paramMap);
	/**
	 * 根据条件查询满足条件的(固定收费项和房间关联信息)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	int getFixedFeeItemHasRoomCountDim(Map<String, Object> paramMap);
	/**
	 * 往(固定收费项和房间关联信息)新增一条记录
	 * @param fixedFeeItemHasRoom
	 * @return
	 */
	int insertFixedFeeItemHasRoom(FixedFeeItemHasRoom fixedFeeItemHasRoom);
	/**
	 * 批量新增(固定收费项和房间关联信息)
	 * @param fixedFeeItemHasRoomList
	 * @return
	 */
	int insertFixedFeeItemHasRoomBatch(List<FixedFeeItemHasRoom> fixedFeeItemHasRoomList);
	/**
	 * 更新(固定收费项和房间关联信息)信息
	 * @param fixedFeeItemHasRoom
	 * @return
	 */
	int updateFixedFeeItemHasRoom(FixedFeeItemHasRoom fixedFeeItemHasRoom);
	/**
	 * 批量更新(固定收费项和房间关联信息)信息
	 * @param fixedFeeItemHasRoomList
	 * @return
	 */
	int updateFixedFeeItemHasRoomBatch(List<FixedFeeItemHasRoom> fixedFeeItemHasRoomList);
	/**
	 * 根据序列号删除(固定收费项和房间关联信息)信息_逻辑删除
	 * @param id
	 * @return
	 */

	int deleteFixedFeeItemHasRoomLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(固定收费项和房间关联信息)信息_逻辑删除
	 * @param idList
	 * @return
	 */

	int deleteFixedFeeItemHasRoomLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(固定收费项和房间关联信息)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteFixedFeeItemHasRoom(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(固定收费项和房间关联信息)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteFixedFeeItemHasRoomBatch(List<java.math.BigInteger> idList);
	
}
