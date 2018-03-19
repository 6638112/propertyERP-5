package com.cnfantasia.server.domainbase.roomVerifyPayment.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.roomVerifyPayment.entity.RoomVerifyPayment;

/**
 * 描述:(门牌验证缴费情况查询表) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IRoomVerifyPaymentBaseService {
	
	/**
	 * 根据条件查询(门牌验证缴费情况查询表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<RoomVerifyPayment> getRoomVerifyPaymentByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(门牌验证缴费情况查询表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<RoomVerifyPayment> getRoomVerifyPaymentByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(门牌验证缴费情况查询表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<RoomVerifyPayment> getRoomVerifyPaymentByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(门牌验证缴费情况查询表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<RoomVerifyPayment> getRoomVerifyPaymentByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(门牌验证缴费情况查询表)信息
	 * @param id
	 * @return
	 */
	public RoomVerifyPayment getRoomVerifyPaymentBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(门牌验证缴费情况查询表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getRoomVerifyPaymentCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(门牌验证缴费情况查询表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getRoomVerifyPaymentCountDim(Map<String,Object> paramMap);
	/**
	 * 往(门牌验证缴费情况查询表)新增一条记录
	 * @param roomVerifyPayment
	 * @return
	 */
	public int insertRoomVerifyPayment(RoomVerifyPayment roomVerifyPayment);
	/**
	 * 批量新增(门牌验证缴费情况查询表)
	 * @param roomVerifyPaymentList
	 * @return
	 */
	public int insertRoomVerifyPaymentBatch(List<RoomVerifyPayment> roomVerifyPaymentList);
	/**
	 * 更新(门牌验证缴费情况查询表)信息
	 * @param roomVerifyPayment
	 * @return
	 */
	public int updateRoomVerifyPayment(RoomVerifyPayment roomVerifyPayment);
	/**
	 * 批量更新(门牌验证缴费情况查询表)信息
	 * @param roomVerifyPaymentList
	 * @return
	 */
	public int updateRoomVerifyPaymentBatch(List<RoomVerifyPayment> roomVerifyPaymentList);
	/**
	 * 根据序列号删除(门牌验证缴费情况查询表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	/* 
	public int deleteRoomVerifyPaymentLogic(java.math.BigInteger id);
	 */
	/**
	 * 根据序列号批量删除(门牌验证缴费情况查询表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	/* 
	public int deleteRoomVerifyPaymentLogicBatch(List<java.math.BigInteger> idList);
	 */
//	/**
//	 * 根据序列号删除(门牌验证缴费情况查询表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteRoomVerifyPayment(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(门牌验证缴费情况查询表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteRoomVerifyPaymentBatch(List<java.math.BigInteger> idList);
	
}
