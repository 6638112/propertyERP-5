package com.cnfantasia.server.domainbase.limitBuyActivity.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.limitBuyActivity.dao.ILimitBuyActivityBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.limitBuyActivity.entity.LimitBuyActivity;

/**
 * 描述:(限时促销) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class LimitBuyActivityBaseService implements ILimitBuyActivityBaseService{
	
	private ILimitBuyActivityBaseDao limitBuyActivityBaseDao;
	public void setLimitBuyActivityBaseDao(ILimitBuyActivityBaseDao limitBuyActivityBaseDao) {
		this.limitBuyActivityBaseDao = limitBuyActivityBaseDao;
	}
	/**
	 * 根据条件查询(限时促销)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<LimitBuyActivity> getLimitBuyActivityByCondition(Map<String,Object> paramMap){
		return limitBuyActivityBaseDao.selectLimitBuyActivityByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(限时促销)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<LimitBuyActivity> getLimitBuyActivityByConditionDim(Map<String,Object> paramMap){
		return limitBuyActivityBaseDao.selectLimitBuyActivityByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(限时促销)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<LimitBuyActivity> getLimitBuyActivityByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return limitBuyActivityBaseDao.selectLimitBuyActivityByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(限时促销)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<LimitBuyActivity> getLimitBuyActivityByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return limitBuyActivityBaseDao.selectLimitBuyActivityByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(限时促销)信息
	 * @param id
	 * @return
	 */
	@Override
	public LimitBuyActivity getLimitBuyActivityBySeqId(java.math.BigInteger id){
		return limitBuyActivityBaseDao.selectLimitBuyActivityBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(限时促销)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getLimitBuyActivityCount(Map<String,Object> paramMap){
		return limitBuyActivityBaseDao.selectLimitBuyActivityCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(限时促销)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getLimitBuyActivityCountDim(Map<String,Object> paramMap){
		return limitBuyActivityBaseDao.selectLimitBuyActivityCount(paramMap,true);
	}
	/**
	 * 往(限时促销)新增一条记录
	 * @param limitBuyActivity
	 * @return
	 */
	@Override
	public int insertLimitBuyActivity(LimitBuyActivity limitBuyActivity){
		return limitBuyActivityBaseDao.insertLimitBuyActivity(limitBuyActivity);
	}
	/**
	 * 批量新增(限时促销)
	 * @param limitBuyActivityList
	 * @return
	 */
	@Override
	public int insertLimitBuyActivityBatch(List<LimitBuyActivity> limitBuyActivityList){
		return limitBuyActivityBaseDao.insertLimitBuyActivityBatch(limitBuyActivityList);
	}
	/**
	 * 更新(限时促销)信息
	 * @param limitBuyActivity
	 * @return
	 */
	@Override
	public int updateLimitBuyActivity(LimitBuyActivity limitBuyActivity){
		return limitBuyActivityBaseDao.updateLimitBuyActivity(limitBuyActivity);
	}
	/**
	 * 批量更新(限时促销)信息
	 * @param limitBuyActivityList
	 * @return
	 */
	@Override
	public int updateLimitBuyActivityBatch(List<LimitBuyActivity> limitBuyActivityList){
		return limitBuyActivityBaseDao.updateLimitBuyActivityBatch(limitBuyActivityList);
	}
	/**
	 * 根据序列号删除(限时促销)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteLimitBuyActivityLogic(java.math.BigInteger id){
		return limitBuyActivityBaseDao.deleteLimitBuyActivityLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(限时促销)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteLimitBuyActivityLogicBatch(List<java.math.BigInteger> idList){
		return limitBuyActivityBaseDao.deleteLimitBuyActivityLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(限时促销)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteLimitBuyActivity(java.math.BigInteger id){
//		return limitBuyActivityBaseDao.deleteLimitBuyActivity(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(限时促销)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteLimitBuyActivityBatch(List<java.math.BigInteger> idList){
//		return limitBuyActivityBaseDao.deleteLimitBuyActivityBatch(idList);
//	}
	
}
