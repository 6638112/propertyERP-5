package com.cnfantasia.server.domainbase.userHasPropertyCard.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.userHasPropertyCard.entity.UserHasPropertyCard;

/**
 * 描述:(用户购买物业代扣卡) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IUserHasPropertyCardBaseService {
	
	/**
	 * 根据条件查询(用户购买物业代扣卡)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<UserHasPropertyCard> getUserHasPropertyCardByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(用户购买物业代扣卡)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<UserHasPropertyCard> getUserHasPropertyCardByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(用户购买物业代扣卡)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<UserHasPropertyCard> getUserHasPropertyCardByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(用户购买物业代扣卡)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<UserHasPropertyCard> getUserHasPropertyCardByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(用户购买物业代扣卡)信息
	 * @param id
	 * @return
	 */
	public UserHasPropertyCard getUserHasPropertyCardBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(用户购买物业代扣卡)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getUserHasPropertyCardCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(用户购买物业代扣卡)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getUserHasPropertyCardCountDim(Map<String,Object> paramMap);
	/**
	 * 往(用户购买物业代扣卡)新增一条记录
	 * @param userHasPropertyCard
	 * @return
	 */
	public int insertUserHasPropertyCard(UserHasPropertyCard userHasPropertyCard);
	/**
	 * 批量新增(用户购买物业代扣卡)
	 * @param userHasPropertyCardList
	 * @return
	 */
	public int insertUserHasPropertyCardBatch(List<UserHasPropertyCard> userHasPropertyCardList);
	/**
	 * 更新(用户购买物业代扣卡)信息
	 * @param userHasPropertyCard
	 * @return
	 */
	public int updateUserHasPropertyCard(UserHasPropertyCard userHasPropertyCard);
	/**
	 * 批量更新(用户购买物业代扣卡)信息
	 * @param userHasPropertyCardList
	 * @return
	 */
	public int updateUserHasPropertyCardBatch(List<UserHasPropertyCard> userHasPropertyCardList);
	/**
	 * 根据序列号删除(用户购买物业代扣卡)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteUserHasPropertyCardLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(用户购买物业代扣卡)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteUserHasPropertyCardLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(用户购买物业代扣卡)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteUserHasPropertyCard(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(用户购买物业代扣卡)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteUserHasPropertyCardBatch(List<java.math.BigInteger> idList);
	
}
