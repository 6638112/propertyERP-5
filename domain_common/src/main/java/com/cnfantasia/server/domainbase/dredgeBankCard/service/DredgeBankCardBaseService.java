package com.cnfantasia.server.domainbase.dredgeBankCard.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.dredgeBankCard.dao.IDredgeBankCardBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.dredgeBankCard.entity.DredgeBankCard;

/**
 * 描述:(用户绑定的银行卡信息) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class DredgeBankCardBaseService implements IDredgeBankCardBaseService{
	
	private IDredgeBankCardBaseDao dredgeBankCardBaseDao;
	public void setDredgeBankCardBaseDao(IDredgeBankCardBaseDao dredgeBankCardBaseDao) {
		this.dredgeBankCardBaseDao = dredgeBankCardBaseDao;
	}
	/**
	 * 根据条件查询(用户绑定的银行卡信息)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<DredgeBankCard> getDredgeBankCardByCondition(Map<String,Object> paramMap){
		return dredgeBankCardBaseDao.selectDredgeBankCardByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(用户绑定的银行卡信息)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<DredgeBankCard> getDredgeBankCardByConditionDim(Map<String,Object> paramMap){
		return dredgeBankCardBaseDao.selectDredgeBankCardByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(用户绑定的银行卡信息)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<DredgeBankCard> getDredgeBankCardByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return dredgeBankCardBaseDao.selectDredgeBankCardByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(用户绑定的银行卡信息)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<DredgeBankCard> getDredgeBankCardByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return dredgeBankCardBaseDao.selectDredgeBankCardByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(用户绑定的银行卡信息)信息
	 * @param id
	 * @return
	 */
	@Override
	public DredgeBankCard getDredgeBankCardBySeqId(java.math.BigInteger id){
		return dredgeBankCardBaseDao.selectDredgeBankCardBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(用户绑定的银行卡信息)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getDredgeBankCardCount(Map<String,Object> paramMap){
		return dredgeBankCardBaseDao.selectDredgeBankCardCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(用户绑定的银行卡信息)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getDredgeBankCardCountDim(Map<String,Object> paramMap){
		return dredgeBankCardBaseDao.selectDredgeBankCardCount(paramMap,true);
	}
	/**
	 * 往(用户绑定的银行卡信息)新增一条记录
	 * @param dredgeBankCard
	 * @return
	 */
	@Override
	public int insertDredgeBankCard(DredgeBankCard dredgeBankCard){
		return dredgeBankCardBaseDao.insertDredgeBankCard(dredgeBankCard);
	}
	/**
	 * 批量新增(用户绑定的银行卡信息)
	 * @param dredgeBankCardList
	 * @return
	 */
	@Override
	public int insertDredgeBankCardBatch(List<DredgeBankCard> dredgeBankCardList){
		return dredgeBankCardBaseDao.insertDredgeBankCardBatch(dredgeBankCardList);
	}
	/**
	 * 更新(用户绑定的银行卡信息)信息
	 * @param dredgeBankCard
	 * @return
	 */
	@Override
	public int updateDredgeBankCard(DredgeBankCard dredgeBankCard){
		return dredgeBankCardBaseDao.updateDredgeBankCard(dredgeBankCard);
	}
	/**
	 * 批量更新(用户绑定的银行卡信息)信息
	 * @param dredgeBankCardList
	 * @return
	 */
	@Override
	public int updateDredgeBankCardBatch(List<DredgeBankCard> dredgeBankCardList){
		return dredgeBankCardBaseDao.updateDredgeBankCardBatch(dredgeBankCardList);
	}
	/**
	 * 根据序列号删除(用户绑定的银行卡信息)信息_逻辑删除
	 * @param id
	 * @return
	 */
	/* 
	@Override
	public int deleteDredgeBankCardLogic(java.math.BigInteger id){
		return dredgeBankCardBaseDao.deleteDredgeBankCardLogic(id);
	}
	 */
	/**
	 * 根据序列号批量删除(用户绑定的银行卡信息)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	/* 
	@Override
	public int deleteDredgeBankCardLogicBatch(List<java.math.BigInteger> idList){
		return dredgeBankCardBaseDao.deleteDredgeBankCardLogicBatch(idList);
	}
	 */
//	/**
//	 * 根据序列号删除(用户绑定的银行卡信息)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteDredgeBankCard(java.math.BigInteger id){
//		return dredgeBankCardBaseDao.deleteDredgeBankCard(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(用户绑定的银行卡信息)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteDredgeBankCardBatch(List<java.math.BigInteger> idList){
//		return dredgeBankCardBaseDao.deleteDredgeBankCardBatch(idList);
//	}
	
}
