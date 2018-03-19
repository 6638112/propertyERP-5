package com.cnfantasia.server.domainbase.userHasTRoom.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.userHasTRoom.entity.UserHasTRoom;

/**
 * 描述:(用户门牌关系) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IUserHasTRoomBaseService {
	
	/**
	 * 根据条件查询(用户门牌关系)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<UserHasTRoom> getUserHasTRoomByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(用户门牌关系)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<UserHasTRoom> getUserHasTRoomByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(用户门牌关系)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<UserHasTRoom> getUserHasTRoomByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(用户门牌关系)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<UserHasTRoom> getUserHasTRoomByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(用户门牌关系)信息
	 * @param id
	 * @return
	 */
	public UserHasTRoom getUserHasTRoomBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(用户门牌关系)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getUserHasTRoomCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(用户门牌关系)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getUserHasTRoomCountDim(Map<String,Object> paramMap);
	/**
	 * 往(用户门牌关系)新增一条记录
	 * @param userHasTRoom
	 * @return
	 */
	public int insertUserHasTRoom(UserHasTRoom userHasTRoom);
	/**
	 * 批量新增(用户门牌关系)
	 * @param userHasTRoomList
	 * @return
	 */
	public int insertUserHasTRoomBatch(List<UserHasTRoom> userHasTRoomList);
	/**
	 * 更新(用户门牌关系)信息
	 * @param userHasTRoom
	 * @return
	 */
	public int updateUserHasTRoom(UserHasTRoom userHasTRoom);
	/**
	 * 批量更新(用户门牌关系)信息
	 * @param userHasTRoomList
	 * @return
	 */
	public int updateUserHasTRoomBatch(List<UserHasTRoom> userHasTRoomList);
	/**
	 * 根据序列号删除(用户门牌关系)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteUserHasTRoomLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(用户门牌关系)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteUserHasTRoomLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(用户门牌关系)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteUserHasTRoom(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(用户门牌关系)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteUserHasTRoomBatch(List<java.math.BigInteger> idList);
	
}
