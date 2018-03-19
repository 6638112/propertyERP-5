package com.cnfantasia.server.domainbase.userHasTLimitBuyActivity.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.userHasTLimitBuyActivity.entity.UserHasTLimitBuyActivity;

/**
 * 描述:(用户在限时促销购买情况表) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IUserHasTLimitBuyActivityBaseDao {
	/**
	 * 根据条件查询(用户在限时促销购买情况表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<UserHasTLimitBuyActivity> selectUserHasTLimitBuyActivityByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(用户在限时促销购买情况表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<UserHasTLimitBuyActivity> selectUserHasTLimitBuyActivityByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(用户在限时促销购买情况表)信息
	 * @param id
	 * @return
	 */
	public UserHasTLimitBuyActivity selectUserHasTLimitBuyActivityBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(用户在限时促销购买情况表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectUserHasTLimitBuyActivityCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(用户在限时促销购买情况表)新增一条记录
	 * @param userHasTLimitBuyActivity
	 * @return
	 */
	public int insertUserHasTLimitBuyActivity(UserHasTLimitBuyActivity userHasTLimitBuyActivity);
	
	/**
	 * 批量新增(用户在限时促销购买情况表)信息
	 * @param userHasTLimitBuyActivityList
	 * @return
	 */
	public int insertUserHasTLimitBuyActivityBatch(List<UserHasTLimitBuyActivity> userHasTLimitBuyActivityList);
	
	/**
	 * 更新(用户在限时促销购买情况表)信息
	 * @param userHasTLimitBuyActivity
	 * @return
	 */
	public int updateUserHasTLimitBuyActivity(UserHasTLimitBuyActivity userHasTLimitBuyActivity);
	
	/**
	 * 批量更新(用户在限时促销购买情况表)信息
	 * @param userHasTLimitBuyActivityList
	 * @return
	 */
	public int updateUserHasTLimitBuyActivityBatch(List<UserHasTLimitBuyActivity> userHasTLimitBuyActivityList);
	
	/**
	 * 根据序列号删除(用户在限时促销购买情况表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteUserHasTLimitBuyActivityLogic(java.math.BigInteger id);
	
	/**
	 * 根据Id 批量删除(用户在限时促销购买情况表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteUserHasTLimitBuyActivityLogicBatch(List<java.math.BigInteger> idList);
	
	
//	/**
//	 * 根据序列号删除(用户在限时促销购买情况表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteUserHasTLimitBuyActivity(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(用户在限时促销购买情况表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteUserHasTLimitBuyActivityBatch(List<java.math.BigInteger> idList);
	
	
}
