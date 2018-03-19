package com.cnfantasia.server.domainbase.roomVerifyPayment.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.roomVerifyPayment.dao.IRoomVerifyPaymentBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.roomVerifyPayment.entity.RoomVerifyPayment;

/**
 * 描述:(门牌验证缴费情况查询表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class RoomVerifyPaymentBaseService implements IRoomVerifyPaymentBaseService{
	
	private IRoomVerifyPaymentBaseDao roomVerifyPaymentBaseDao;
	public void setRoomVerifyPaymentBaseDao(IRoomVerifyPaymentBaseDao roomVerifyPaymentBaseDao) {
		this.roomVerifyPaymentBaseDao = roomVerifyPaymentBaseDao;
	}
	/**
	 * 根据条件查询(门牌验证缴费情况查询表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<RoomVerifyPayment> getRoomVerifyPaymentByCondition(Map<String,Object> paramMap){
		return roomVerifyPaymentBaseDao.selectRoomVerifyPaymentByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(门牌验证缴费情况查询表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<RoomVerifyPayment> getRoomVerifyPaymentByConditionDim(Map<String,Object> paramMap){
		return roomVerifyPaymentBaseDao.selectRoomVerifyPaymentByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(门牌验证缴费情况查询表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<RoomVerifyPayment> getRoomVerifyPaymentByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return roomVerifyPaymentBaseDao.selectRoomVerifyPaymentByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(门牌验证缴费情况查询表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<RoomVerifyPayment> getRoomVerifyPaymentByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return roomVerifyPaymentBaseDao.selectRoomVerifyPaymentByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(门牌验证缴费情况查询表)信息
	 * @param id
	 * @return
	 */
	@Override
	public RoomVerifyPayment getRoomVerifyPaymentBySeqId(java.math.BigInteger id){
		return roomVerifyPaymentBaseDao.selectRoomVerifyPaymentBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(门牌验证缴费情况查询表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getRoomVerifyPaymentCount(Map<String,Object> paramMap){
		return roomVerifyPaymentBaseDao.selectRoomVerifyPaymentCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(门牌验证缴费情况查询表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getRoomVerifyPaymentCountDim(Map<String,Object> paramMap){
		return roomVerifyPaymentBaseDao.selectRoomVerifyPaymentCount(paramMap,true);
	}
	/**
	 * 往(门牌验证缴费情况查询表)新增一条记录
	 * @param roomVerifyPayment
	 * @return
	 */
	@Override
	public int insertRoomVerifyPayment(RoomVerifyPayment roomVerifyPayment){
		return roomVerifyPaymentBaseDao.insertRoomVerifyPayment(roomVerifyPayment);
	}
	/**
	 * 批量新增(门牌验证缴费情况查询表)
	 * @param roomVerifyPaymentList
	 * @return
	 */
	@Override
	public int insertRoomVerifyPaymentBatch(List<RoomVerifyPayment> roomVerifyPaymentList){
		return roomVerifyPaymentBaseDao.insertRoomVerifyPaymentBatch(roomVerifyPaymentList);
	}
	/**
	 * 更新(门牌验证缴费情况查询表)信息
	 * @param roomVerifyPayment
	 * @return
	 */
	@Override
	public int updateRoomVerifyPayment(RoomVerifyPayment roomVerifyPayment){
		return roomVerifyPaymentBaseDao.updateRoomVerifyPayment(roomVerifyPayment);
	}
	/**
	 * 批量更新(门牌验证缴费情况查询表)信息
	 * @param roomVerifyPaymentList
	 * @return
	 */
	@Override
	public int updateRoomVerifyPaymentBatch(List<RoomVerifyPayment> roomVerifyPaymentList){
		return roomVerifyPaymentBaseDao.updateRoomVerifyPaymentBatch(roomVerifyPaymentList);
	}
	/**
	 * 根据序列号删除(门牌验证缴费情况查询表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	/* 
	@Override
	public int deleteRoomVerifyPaymentLogic(java.math.BigInteger id){
		return roomVerifyPaymentBaseDao.deleteRoomVerifyPaymentLogic(id);
	}
	 */
	/**
	 * 根据序列号批量删除(门牌验证缴费情况查询表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	/* 
	@Override
	public int deleteRoomVerifyPaymentLogicBatch(List<java.math.BigInteger> idList){
		return roomVerifyPaymentBaseDao.deleteRoomVerifyPaymentLogicBatch(idList);
	}
	 */
//	/**
//	 * 根据序列号删除(门牌验证缴费情况查询表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteRoomVerifyPayment(java.math.BigInteger id){
//		return roomVerifyPaymentBaseDao.deleteRoomVerifyPayment(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(门牌验证缴费情况查询表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteRoomVerifyPaymentBatch(List<java.math.BigInteger> idList){
//		return roomVerifyPaymentBaseDao.deleteRoomVerifyPaymentBatch(idList);
//	}
	
}
