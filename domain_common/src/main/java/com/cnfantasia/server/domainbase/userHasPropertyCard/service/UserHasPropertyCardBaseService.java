package com.cnfantasia.server.domainbase.userHasPropertyCard.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.userHasPropertyCard.dao.IUserHasPropertyCardBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.userHasPropertyCard.entity.UserHasPropertyCard;

/**
 * 描述:(用户购买物业代扣卡) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class UserHasPropertyCardBaseService implements IUserHasPropertyCardBaseService{
	
	private IUserHasPropertyCardBaseDao userHasPropertyCardBaseDao;
	public void setUserHasPropertyCardBaseDao(IUserHasPropertyCardBaseDao userHasPropertyCardBaseDao) {
		this.userHasPropertyCardBaseDao = userHasPropertyCardBaseDao;
	}
	/**
	 * 根据条件查询(用户购买物业代扣卡)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<UserHasPropertyCard> getUserHasPropertyCardByCondition(Map<String,Object> paramMap){
		return userHasPropertyCardBaseDao.selectUserHasPropertyCardByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(用户购买物业代扣卡)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<UserHasPropertyCard> getUserHasPropertyCardByConditionDim(Map<String,Object> paramMap){
		return userHasPropertyCardBaseDao.selectUserHasPropertyCardByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(用户购买物业代扣卡)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<UserHasPropertyCard> getUserHasPropertyCardByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return userHasPropertyCardBaseDao.selectUserHasPropertyCardByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(用户购买物业代扣卡)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<UserHasPropertyCard> getUserHasPropertyCardByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return userHasPropertyCardBaseDao.selectUserHasPropertyCardByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(用户购买物业代扣卡)信息
	 * @param id
	 * @return
	 */
	@Override
	public UserHasPropertyCard getUserHasPropertyCardBySeqId(java.math.BigInteger id){
		return userHasPropertyCardBaseDao.selectUserHasPropertyCardBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(用户购买物业代扣卡)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getUserHasPropertyCardCount(Map<String,Object> paramMap){
		return userHasPropertyCardBaseDao.selectUserHasPropertyCardCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(用户购买物业代扣卡)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getUserHasPropertyCardCountDim(Map<String,Object> paramMap){
		return userHasPropertyCardBaseDao.selectUserHasPropertyCardCount(paramMap,true);
	}
	/**
	 * 往(用户购买物业代扣卡)新增一条记录
	 * @param userHasPropertyCard
	 * @return
	 */
	@Override
	public int insertUserHasPropertyCard(UserHasPropertyCard userHasPropertyCard){
		return userHasPropertyCardBaseDao.insertUserHasPropertyCard(userHasPropertyCard);
	}
	/**
	 * 批量新增(用户购买物业代扣卡)
	 * @param userHasPropertyCardList
	 * @return
	 */
	@Override
	public int insertUserHasPropertyCardBatch(List<UserHasPropertyCard> userHasPropertyCardList){
		return userHasPropertyCardBaseDao.insertUserHasPropertyCardBatch(userHasPropertyCardList);
	}
	/**
	 * 更新(用户购买物业代扣卡)信息
	 * @param userHasPropertyCard
	 * @return
	 */
	@Override
	public int updateUserHasPropertyCard(UserHasPropertyCard userHasPropertyCard){
		return userHasPropertyCardBaseDao.updateUserHasPropertyCard(userHasPropertyCard);
	}
	/**
	 * 批量更新(用户购买物业代扣卡)信息
	 * @param userHasPropertyCardList
	 * @return
	 */
	@Override
	public int updateUserHasPropertyCardBatch(List<UserHasPropertyCard> userHasPropertyCardList){
		return userHasPropertyCardBaseDao.updateUserHasPropertyCardBatch(userHasPropertyCardList);
	}
	/**
	 * 根据序列号删除(用户购买物业代扣卡)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteUserHasPropertyCardLogic(java.math.BigInteger id){
		return userHasPropertyCardBaseDao.deleteUserHasPropertyCardLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(用户购买物业代扣卡)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteUserHasPropertyCardLogicBatch(List<java.math.BigInteger> idList){
		return userHasPropertyCardBaseDao.deleteUserHasPropertyCardLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(用户购买物业代扣卡)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteUserHasPropertyCard(java.math.BigInteger id){
//		return userHasPropertyCardBaseDao.deleteUserHasPropertyCard(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(用户购买物业代扣卡)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteUserHasPropertyCardBatch(List<java.math.BigInteger> idList){
//		return userHasPropertyCardBaseDao.deleteUserHasPropertyCardBatch(idList);
//	}
	
}
