package com.cnfantasia.server.domainbase.roomValidate.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.roomValidate.entity.RoomValidate;

/**
 * 描述:(门牌校验信息) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IRoomValidateBaseService {
	
	/**
	 * 根据条件查询(门牌校验信息)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<RoomValidate> getRoomValidateByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(门牌校验信息)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<RoomValidate> getRoomValidateByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(门牌校验信息)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<RoomValidate> getRoomValidateByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(门牌校验信息)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<RoomValidate> getRoomValidateByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(门牌校验信息)信息
	 * @param id
	 * @return
	 */
	public RoomValidate getRoomValidateBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(门牌校验信息)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getRoomValidateCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(门牌校验信息)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getRoomValidateCountDim(Map<String,Object> paramMap);
	/**
	 * 往(门牌校验信息)新增一条记录
	 * @param roomValidate
	 * @return
	 */
	public int insertRoomValidate(RoomValidate roomValidate);
	/**
	 * 批量新增(门牌校验信息)
	 * @param roomValidateList
	 * @return
	 */
	public int insertRoomValidateBatch(List<RoomValidate> roomValidateList);
	/**
	 * 更新(门牌校验信息)信息
	 * @param roomValidate
	 * @return
	 */
	public int updateRoomValidate(RoomValidate roomValidate);
	/**
	 * 批量更新(门牌校验信息)信息
	 * @param roomValidateList
	 * @return
	 */
	public int updateRoomValidateBatch(List<RoomValidate> roomValidateList);
	/**
	 * 根据序列号删除(门牌校验信息)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteRoomValidateLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(门牌校验信息)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteRoomValidateLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(门牌校验信息)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteRoomValidate(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(门牌校验信息)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteRoomValidateBatch(List<java.math.BigInteger> idList);
	
}
