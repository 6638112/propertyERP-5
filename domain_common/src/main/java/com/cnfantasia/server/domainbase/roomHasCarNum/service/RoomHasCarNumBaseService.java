package com.cnfantasia.server.domainbase.roomHasCarNum.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.roomHasCarNum.dao.IRoomHasCarNumBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.roomHasCarNum.entity.RoomHasCarNum;

/**
 * 描述:(房间车牌对应表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class RoomHasCarNumBaseService implements IRoomHasCarNumBaseService{
	
	private IRoomHasCarNumBaseDao roomHasCarNumBaseDao;
	public void setRoomHasCarNumBaseDao(IRoomHasCarNumBaseDao roomHasCarNumBaseDao) {
		this.roomHasCarNumBaseDao = roomHasCarNumBaseDao;
	}
	/**
	 * 根据条件查询(房间车牌对应表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<RoomHasCarNum> getRoomHasCarNumByCondition(Map<String,Object> paramMap){
		return roomHasCarNumBaseDao.selectRoomHasCarNumByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(房间车牌对应表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<RoomHasCarNum> getRoomHasCarNumByConditionDim(Map<String,Object> paramMap){
		return roomHasCarNumBaseDao.selectRoomHasCarNumByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(房间车牌对应表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<RoomHasCarNum> getRoomHasCarNumByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return roomHasCarNumBaseDao.selectRoomHasCarNumByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(房间车牌对应表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<RoomHasCarNum> getRoomHasCarNumByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return roomHasCarNumBaseDao.selectRoomHasCarNumByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(房间车牌对应表)信息
	 * @param id
	 * @return
	 */
	@Override
	public RoomHasCarNum getRoomHasCarNumBySeqId(java.math.BigInteger id){
		return roomHasCarNumBaseDao.selectRoomHasCarNumBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(房间车牌对应表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getRoomHasCarNumCount(Map<String,Object> paramMap){
		return roomHasCarNumBaseDao.selectRoomHasCarNumCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(房间车牌对应表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getRoomHasCarNumCountDim(Map<String,Object> paramMap){
		return roomHasCarNumBaseDao.selectRoomHasCarNumCount(paramMap,true);
	}
	/**
	 * 往(房间车牌对应表)新增一条记录
	 * @param roomHasCarNum
	 * @return
	 */
	@Override
	public int insertRoomHasCarNum(RoomHasCarNum roomHasCarNum){
		return roomHasCarNumBaseDao.insertRoomHasCarNum(roomHasCarNum);
	}
	/**
	 * 批量新增(房间车牌对应表)
	 * @param roomHasCarNumList
	 * @return
	 */
	@Override
	public int insertRoomHasCarNumBatch(List<RoomHasCarNum> roomHasCarNumList){
		return roomHasCarNumBaseDao.insertRoomHasCarNumBatch(roomHasCarNumList);
	}
	/**
	 * 更新(房间车牌对应表)信息
	 * @param roomHasCarNum
	 * @return
	 */
	@Override
	public int updateRoomHasCarNum(RoomHasCarNum roomHasCarNum){
		return roomHasCarNumBaseDao.updateRoomHasCarNum(roomHasCarNum);
	}
	/**
	 * 批量更新(房间车牌对应表)信息
	 * @param roomHasCarNumList
	 * @return
	 */
	@Override
	public int updateRoomHasCarNumBatch(List<RoomHasCarNum> roomHasCarNumList){
		return roomHasCarNumBaseDao.updateRoomHasCarNumBatch(roomHasCarNumList);
	}
	/**
	 * 根据序列号删除(房间车牌对应表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteRoomHasCarNumLogic(java.math.BigInteger id){
		return roomHasCarNumBaseDao.deleteRoomHasCarNumLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(房间车牌对应表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteRoomHasCarNumLogicBatch(List<java.math.BigInteger> idList){
		return roomHasCarNumBaseDao.deleteRoomHasCarNumLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(房间车牌对应表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteRoomHasCarNum(java.math.BigInteger id){
//		return roomHasCarNumBaseDao.deleteRoomHasCarNum(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(房间车牌对应表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteRoomHasCarNumBatch(List<java.math.BigInteger> idList){
//		return roomHasCarNumBaseDao.deleteRoomHasCarNumBatch(idList);
//	}
	
}
