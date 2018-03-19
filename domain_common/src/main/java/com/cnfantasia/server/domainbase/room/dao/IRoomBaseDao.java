package com.cnfantasia.server.domainbase.room.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.room.entity.Room;

/**
 * 描述:(门牌信息) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IRoomBaseDao {
	/**
	 * 根据条件查询(门牌信息)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<Room> selectRoomByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(门牌信息)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<Room> selectRoomByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(门牌信息)信息
	 * @param id
	 * @return
	 */
	public Room selectRoomBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(门牌信息)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectRoomCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(门牌信息)新增一条记录
	 * @param room
	 * @return
	 */
	public int insertRoom(Room room);
	
	/**
	 * 批量新增(门牌信息)信息
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
	 * 根据Id 批量删除(门牌信息)信息_逻辑删除
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
