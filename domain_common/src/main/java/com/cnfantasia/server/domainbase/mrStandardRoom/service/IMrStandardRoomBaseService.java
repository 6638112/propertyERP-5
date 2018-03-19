package com.cnfantasia.server.domainbase.mrStandardRoom.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.mrStandardRoom.entity.MrStandardRoom;

/**
 * 描述:(抄表收费标准(房间)) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IMrStandardRoomBaseService {
	
	/**
	 * 根据条件查询(抄表收费标准(房间))信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	List<MrStandardRoom> getMrStandardRoomByCondition(Map<String, Object> paramMap);
	/**
	 * 根据条件查询(抄表收费标准(房间))信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	List<MrStandardRoom> getMrStandardRoomByConditionDim(Map<String, Object> paramMap);
	/**
	 * 按条件分页查询(抄表收费标准(房间))信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	List<MrStandardRoom> getMrStandardRoomByCondition(Map<String, Object> paramMap, PageModel pageModel);
	/**
	 * 按条件分页查询(抄表收费标准(房间))信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	List<MrStandardRoom> getMrStandardRoomByConditionDim(Map<String, Object> paramMap, PageModel pageModel);
	/**
	 * 根据序列号查询(抄表收费标准(房间))信息
	 * @param id
	 * @return
	 */
	MrStandardRoom getMrStandardRoomBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(抄表收费标准(房间))记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	int getMrStandardRoomCount(Map<String, Object> paramMap);
	/**
	 * 根据条件查询满足条件的(抄表收费标准(房间))记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	int getMrStandardRoomCountDim(Map<String, Object> paramMap);
	/**
	 * 往(抄表收费标准(房间))新增一条记录
	 * @param mrStandardRoom
	 * @return
	 */
	int insertMrStandardRoom(MrStandardRoom mrStandardRoom);
	/**
	 * 批量新增(抄表收费标准(房间))
	 * @param mrStandardRoomList
	 * @return
	 */
	int insertMrStandardRoomBatch(List<MrStandardRoom> mrStandardRoomList);
	/**
	 * 更新(抄表收费标准(房间))信息
	 * @param mrStandardRoom
	 * @return
	 */
	int updateMrStandardRoom(MrStandardRoom mrStandardRoom);
	/**
	 * 批量更新(抄表收费标准(房间))信息
	 * @param mrStandardRoomList
	 * @return
	 */
	int updateMrStandardRoomBatch(List<MrStandardRoom> mrStandardRoomList);
	/**
	 * 根据序列号删除(抄表收费标准(房间))信息_逻辑删除
	 * @param id
	 * @return
	 */

	int deleteMrStandardRoomLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(抄表收费标准(房间))信息_逻辑删除
	 * @param idList
	 * @return
	 */

	int deleteMrStandardRoomLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(抄表收费标准(房间))信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteMrStandardRoom(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(抄表收费标准(房间))信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteMrStandardRoomBatch(List<java.math.BigInteger> idList);
	
}
