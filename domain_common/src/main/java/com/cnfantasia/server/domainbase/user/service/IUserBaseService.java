package com.cnfantasia.server.domainbase.user.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.user.entity.User;

/**
 * 描述:(用户表) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IUserBaseService {
	
	/**
	 * 根据条件查询(用户表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<User> getUserByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(用户表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<User> getUserByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(用户表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<User> getUserByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(用户表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<User> getUserByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(用户表)信息
	 * @param id
	 * @return
	 */
	public User getUserBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(用户表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getUserCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(用户表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getUserCountDim(Map<String,Object> paramMap);
	/**
	 * 往(用户表)新增一条记录
	 * @param user
	 * @return
	 */
	public int insertUser(User user);
	/**
	 * 批量新增(用户表)
	 * @param userList
	 * @return
	 */
	public int insertUserBatch(List<User> userList);
	/**
	 * 更新(用户表)信息
	 * @param user
	 * @return
	 */
	public int updateUser(User user);
	/**
	 * 批量更新(用户表)信息
	 * @param userList
	 * @return
	 */
	public int updateUserBatch(List<User> userList);
	/**
	 * 根据序列号删除(用户表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteUserLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(用户表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteUserLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(用户表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteUser(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(用户表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteUserBatch(List<java.math.BigInteger> idList);
	
}
