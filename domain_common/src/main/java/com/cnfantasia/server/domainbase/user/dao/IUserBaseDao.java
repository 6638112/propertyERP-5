package com.cnfantasia.server.domainbase.user.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.user.entity.User;

/**
 * 描述:(用户表) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IUserBaseDao {
	/**
	 * 根据条件查询(用户表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<User> selectUserByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(用户表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<User> selectUserByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(用户表)信息
	 * @param id
	 * @return
	 */
	public User selectUserBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(用户表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectUserCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(用户表)新增一条记录
	 * @param user
	 * @return
	 */
	public int insertUser(User user);
	
	/**
	 * 批量新增(用户表)信息
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
	 * 根据Id 批量删除(用户表)信息_逻辑删除
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
