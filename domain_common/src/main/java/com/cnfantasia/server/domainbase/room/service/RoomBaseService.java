package com.cnfantasia.server.domainbase.room.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.room.dao.IRoomBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.room.entity.Room;

/**
 * 描述:(门牌信息) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class RoomBaseService implements IRoomBaseService{
	
	private IRoomBaseDao roomBaseDao;
	public void setRoomBaseDao(IRoomBaseDao roomBaseDao) {
		this.roomBaseDao = roomBaseDao;
	}
	/**
	 * 根据条件查询(门牌信息)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<Room> getRoomByCondition(Map<String,Object> paramMap){
		return roomBaseDao.selectRoomByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(门牌信息)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<Room> getRoomByConditionDim(Map<String,Object> paramMap){
		return roomBaseDao.selectRoomByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(门牌信息)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<Room> getRoomByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return roomBaseDao.selectRoomByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(门牌信息)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<Room> getRoomByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return roomBaseDao.selectRoomByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(门牌信息)信息
	 * @param id
	 * @return
	 */
	@Override
	public Room getRoomBySeqId(java.math.BigInteger id){
		return roomBaseDao.selectRoomBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(门牌信息)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getRoomCount(Map<String,Object> paramMap){
		return roomBaseDao.selectRoomCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(门牌信息)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getRoomCountDim(Map<String,Object> paramMap){
		return roomBaseDao.selectRoomCount(paramMap,true);
	}
	/**
	 * 往(门牌信息)新增一条记录
	 * @param room
	 * @return
	 */
	@Override
	public int insertRoom(Room room){
		return roomBaseDao.insertRoom(room);
	}
	/**
	 * 批量新增(门牌信息)
	 * @param roomList
	 * @return
	 */
	@Override
	public int insertRoomBatch(List<Room> roomList){
		return roomBaseDao.insertRoomBatch(roomList);
	}
	/**
	 * 更新(门牌信息)信息
	 * @param room
	 * @return
	 */
	@Override
	public int updateRoom(Room room){
		return roomBaseDao.updateRoom(room);
	}
	/**
	 * 批量更新(门牌信息)信息
	 * @param roomList
	 * @return
	 */
	@Override
	public int updateRoomBatch(List<Room> roomList){
		return roomBaseDao.updateRoomBatch(roomList);
	}
	/**
	 * 根据序列号删除(门牌信息)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteRoomLogic(java.math.BigInteger id){
		return roomBaseDao.deleteRoomLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(门牌信息)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteRoomLogicBatch(List<java.math.BigInteger> idList){
		return roomBaseDao.deleteRoomLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(门牌信息)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteRoom(java.math.BigInteger id){
//		return roomBaseDao.deleteRoom(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(门牌信息)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteRoomBatch(List<java.math.BigInteger> idList){
//		return roomBaseDao.deleteRoomBatch(idList);
//	}
	
}
