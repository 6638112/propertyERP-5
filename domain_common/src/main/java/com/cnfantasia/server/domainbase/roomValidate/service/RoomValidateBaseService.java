package com.cnfantasia.server.domainbase.roomValidate.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.roomValidate.dao.IRoomValidateBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.roomValidate.entity.RoomValidate;

/**
 * 描述:(门牌校验信息) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class RoomValidateBaseService implements IRoomValidateBaseService{
	
	private IRoomValidateBaseDao roomValidateBaseDao;
	public void setRoomValidateBaseDao(IRoomValidateBaseDao roomValidateBaseDao) {
		this.roomValidateBaseDao = roomValidateBaseDao;
	}
	/**
	 * 根据条件查询(门牌校验信息)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<RoomValidate> getRoomValidateByCondition(Map<String,Object> paramMap){
		return roomValidateBaseDao.selectRoomValidateByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(门牌校验信息)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<RoomValidate> getRoomValidateByConditionDim(Map<String,Object> paramMap){
		return roomValidateBaseDao.selectRoomValidateByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(门牌校验信息)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<RoomValidate> getRoomValidateByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return roomValidateBaseDao.selectRoomValidateByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(门牌校验信息)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<RoomValidate> getRoomValidateByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return roomValidateBaseDao.selectRoomValidateByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(门牌校验信息)信息
	 * @param id
	 * @return
	 */
	@Override
	public RoomValidate getRoomValidateBySeqId(java.math.BigInteger id){
		return roomValidateBaseDao.selectRoomValidateBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(门牌校验信息)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getRoomValidateCount(Map<String,Object> paramMap){
		return roomValidateBaseDao.selectRoomValidateCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(门牌校验信息)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getRoomValidateCountDim(Map<String,Object> paramMap){
		return roomValidateBaseDao.selectRoomValidateCount(paramMap,true);
	}
	/**
	 * 往(门牌校验信息)新增一条记录
	 * @param roomValidate
	 * @return
	 */
	@Override
	public int insertRoomValidate(RoomValidate roomValidate){
		return roomValidateBaseDao.insertRoomValidate(roomValidate);
	}
	/**
	 * 批量新增(门牌校验信息)
	 * @param roomValidateList
	 * @return
	 */
	@Override
	public int insertRoomValidateBatch(List<RoomValidate> roomValidateList){
		return roomValidateBaseDao.insertRoomValidateBatch(roomValidateList);
	}
	/**
	 * 更新(门牌校验信息)信息
	 * @param roomValidate
	 * @return
	 */
	@Override
	public int updateRoomValidate(RoomValidate roomValidate){
		return roomValidateBaseDao.updateRoomValidate(roomValidate);
	}
	/**
	 * 批量更新(门牌校验信息)信息
	 * @param roomValidateList
	 * @return
	 */
	@Override
	public int updateRoomValidateBatch(List<RoomValidate> roomValidateList){
		return roomValidateBaseDao.updateRoomValidateBatch(roomValidateList);
	}
	/**
	 * 根据序列号删除(门牌校验信息)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteRoomValidateLogic(java.math.BigInteger id){
		return roomValidateBaseDao.deleteRoomValidateLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(门牌校验信息)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteRoomValidateLogicBatch(List<java.math.BigInteger> idList){
		return roomValidateBaseDao.deleteRoomValidateLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(门牌校验信息)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteRoomValidate(java.math.BigInteger id){
//		return roomValidateBaseDao.deleteRoomValidate(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(门牌校验信息)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteRoomValidateBatch(List<java.math.BigInteger> idList){
//		return roomValidateBaseDao.deleteRoomValidateBatch(idList);
//	}
	
}
