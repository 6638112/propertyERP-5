package com.cnfantasia.server.domainbase.room.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.room.entity.Room;

/**
 * 描述:(门牌信息) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IRoomBaseService {
	
	/**
	 * 根据条件查询(门牌信息)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<Room> getRoomByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(门牌信息)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<Room> getRoomByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(门牌信息)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<Room> getRoomByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(门牌信息)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<Room> getRoomByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(门牌信息)信息
	 * @param id
	 * @return
	 */
	public Room getRoomBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(门牌信息)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getRoomCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(门牌信息)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getRoomCountDim(Map<String,Object> paramMap);
	/**
	 * 往(门牌信息)新增一条记录
	 * @param room
	 * @return
	 */
	public int insertRoom(Room room);
	/**
	 * 批量新增(门牌信息)
	 * @param roomList
	 * @return
	 */
	public int insertRoomBatch(List<Room> roomList);
	/**
	 * 更新(门牌信息)信息
	 * @param room
	 * @return
	 */
	public int updateRoom(Room room);
	/**
	 * 批量更新(门牌信息)信息
	 * @param roomList
	 * @return
	 */
	public int updateRoomBatch(List<Room> roomList);
	/**
	 * 根据序列号删除(门牌信息)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteRoomLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(门牌信息)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteRoomLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(门牌信息)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteRoom(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(门牌信息)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteRoomBatch(List<java.math.BigInteger> idList);
	
}
