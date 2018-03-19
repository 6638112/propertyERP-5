package com.cnfantasia.server.domainbase.roomHasCarNum.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.roomHasCarNum.entity.RoomHasCarNum;

/**
 * 描述:(房间车牌对应表) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IRoomHasCarNumBaseService {
	
	/**
	 * 根据条件查询(房间车牌对应表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<RoomHasCarNum> getRoomHasCarNumByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(房间车牌对应表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<RoomHasCarNum> getRoomHasCarNumByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(房间车牌对应表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<RoomHasCarNum> getRoomHasCarNumByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(房间车牌对应表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<RoomHasCarNum> getRoomHasCarNumByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(房间车牌对应表)信息
	 * @param id
	 * @return
	 */
	public RoomHasCarNum getRoomHasCarNumBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(房间车牌对应表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getRoomHasCarNumCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(房间车牌对应表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getRoomHasCarNumCountDim(Map<String,Object> paramMap);
	/**
	 * 往(房间车牌对应表)新增一条记录
	 * @param roomHasCarNum
	 * @return
	 */
	public int insertRoomHasCarNum(RoomHasCarNum roomHasCarNum);
	/**
	 * 批量新增(房间车牌对应表)
	 * @param roomHasCarNumList
	 * @return
	 */
	public int insertRoomHasCarNumBatch(List<RoomHasCarNum> roomHasCarNumList);
	/**
	 * 更新(房间车牌对应表)信息
	 * @param roomHasCarNum
	 * @return
	 */
	public int updateRoomHasCarNum(RoomHasCarNum roomHasCarNum);
	/**
	 * 批量更新(房间车牌对应表)信息
	 * @param roomHasCarNumList
	 * @return
	 */
	public int updateRoomHasCarNumBatch(List<RoomHasCarNum> roomHasCarNumList);
	/**
	 * 根据序列号删除(房间车牌对应表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteRoomHasCarNumLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(房间车牌对应表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteRoomHasCarNumLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(房间车牌对应表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteRoomHasCarNum(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(房间车牌对应表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteRoomHasCarNumBatch(List<java.math.BigInteger> idList);
	
}
