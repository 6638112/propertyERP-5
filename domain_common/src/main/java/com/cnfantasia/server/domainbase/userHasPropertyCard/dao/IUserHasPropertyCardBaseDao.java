package com.cnfantasia.server.domainbase.userHasPropertyCard.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.userHasPropertyCard.entity.UserHasPropertyCard;

/**
 * 描述:(用户购买物业代扣卡) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IUserHasPropertyCardBaseDao {
	/**
	 * 根据条件查询(用户购买物业代扣卡)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<UserHasPropertyCard> selectUserHasPropertyCardByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(用户购买物业代扣卡)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<UserHasPropertyCard> selectUserHasPropertyCardByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(用户购买物业代扣卡)信息
	 * @param id
	 * @return
	 */
	public UserHasPropertyCard selectUserHasPropertyCardBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(用户购买物业代扣卡)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectUserHasPropertyCardCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(用户购买物业代扣卡)新增一条记录
	 * @param userHasPropertyCard
	 * @return
	 */
	public int insertUserHasPropertyCard(UserHasPropertyCard userHasPropertyCard);
	
	/**
	 * 批量新增(用户购买物业代扣卡)信息
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
	 * 根据Id 批量删除(用户购买物业代扣卡)信息_逻辑删除
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
