package com.cnfantasia.server.domainbase.prizeRuleDrawOnlinedays.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.prizeRuleDrawOnlinedays.dao.IPrizeRuleDrawOnlinedaysBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.prizeRuleDrawOnlinedays.entity.PrizeRuleDrawOnlinedays;

/**
 * 描述:(折扣抽奖规则-用户在线时间) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class PrizeRuleDrawOnlinedaysBaseService implements IPrizeRuleDrawOnlinedaysBaseService{
	
	private IPrizeRuleDrawOnlinedaysBaseDao prizeRuleDrawOnlinedaysBaseDao;
	public void setPrizeRuleDrawOnlinedaysBaseDao(IPrizeRuleDrawOnlinedaysBaseDao prizeRuleDrawOnlinedaysBaseDao) {
		this.prizeRuleDrawOnlinedaysBaseDao = prizeRuleDrawOnlinedaysBaseDao;
	}
	/**
	 * 根据条件查询(折扣抽奖规则-用户在线时间)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<PrizeRuleDrawOnlinedays> getPrizeRuleDrawOnlinedaysByCondition(Map<String,Object> paramMap){
		return prizeRuleDrawOnlinedaysBaseDao.selectPrizeRuleDrawOnlinedaysByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(折扣抽奖规则-用户在线时间)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<PrizeRuleDrawOnlinedays> getPrizeRuleDrawOnlinedaysByConditionDim(Map<String,Object> paramMap){
		return prizeRuleDrawOnlinedaysBaseDao.selectPrizeRuleDrawOnlinedaysByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(折扣抽奖规则-用户在线时间)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<PrizeRuleDrawOnlinedays> getPrizeRuleDrawOnlinedaysByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return prizeRuleDrawOnlinedaysBaseDao.selectPrizeRuleDrawOnlinedaysByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(折扣抽奖规则-用户在线时间)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<PrizeRuleDrawOnlinedays> getPrizeRuleDrawOnlinedaysByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return prizeRuleDrawOnlinedaysBaseDao.selectPrizeRuleDrawOnlinedaysByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(折扣抽奖规则-用户在线时间)信息
	 * @param id
	 * @return
	 */
	@Override
	public PrizeRuleDrawOnlinedays getPrizeRuleDrawOnlinedaysBySeqId(java.math.BigInteger id){
		return prizeRuleDrawOnlinedaysBaseDao.selectPrizeRuleDrawOnlinedaysBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(折扣抽奖规则-用户在线时间)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getPrizeRuleDrawOnlinedaysCount(Map<String,Object> paramMap){
		return prizeRuleDrawOnlinedaysBaseDao.selectPrizeRuleDrawOnlinedaysCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(折扣抽奖规则-用户在线时间)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getPrizeRuleDrawOnlinedaysCountDim(Map<String,Object> paramMap){
		return prizeRuleDrawOnlinedaysBaseDao.selectPrizeRuleDrawOnlinedaysCount(paramMap,true);
	}
	/**
	 * 往(折扣抽奖规则-用户在线时间)新增一条记录
	 * @param prizeRuleDrawOnlinedays
	 * @return
	 */
	@Override
	public int insertPrizeRuleDrawOnlinedays(PrizeRuleDrawOnlinedays prizeRuleDrawOnlinedays){
		return prizeRuleDrawOnlinedaysBaseDao.insertPrizeRuleDrawOnlinedays(prizeRuleDrawOnlinedays);
	}
	/**
	 * 批量新增(折扣抽奖规则-用户在线时间)
	 * @param prizeRuleDrawOnlinedaysList
	 * @return
	 */
	@Override
	public int insertPrizeRuleDrawOnlinedaysBatch(List<PrizeRuleDrawOnlinedays> prizeRuleDrawOnlinedaysList){
		return prizeRuleDrawOnlinedaysBaseDao.insertPrizeRuleDrawOnlinedaysBatch(prizeRuleDrawOnlinedaysList);
	}
	/**
	 * 更新(折扣抽奖规则-用户在线时间)信息
	 * @param prizeRuleDrawOnlinedays
	 * @return
	 */
	@Override
	public int updatePrizeRuleDrawOnlinedays(PrizeRuleDrawOnlinedays prizeRuleDrawOnlinedays){
		return prizeRuleDrawOnlinedaysBaseDao.updatePrizeRuleDrawOnlinedays(prizeRuleDrawOnlinedays);
	}
	/**
	 * 批量更新(折扣抽奖规则-用户在线时间)信息
	 * @param prizeRuleDrawOnlinedaysList
	 * @return
	 */
	@Override
	public int updatePrizeRuleDrawOnlinedaysBatch(List<PrizeRuleDrawOnlinedays> prizeRuleDrawOnlinedaysList){
		return prizeRuleDrawOnlinedaysBaseDao.updatePrizeRuleDrawOnlinedaysBatch(prizeRuleDrawOnlinedaysList);
	}
	/**
	 * 根据序列号删除(折扣抽奖规则-用户在线时间)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deletePrizeRuleDrawOnlinedaysLogic(java.math.BigInteger id){
		return prizeRuleDrawOnlinedaysBaseDao.deletePrizeRuleDrawOnlinedaysLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(折扣抽奖规则-用户在线时间)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deletePrizeRuleDrawOnlinedaysLogicBatch(List<java.math.BigInteger> idList){
		return prizeRuleDrawOnlinedaysBaseDao.deletePrizeRuleDrawOnlinedaysLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(折扣抽奖规则-用户在线时间)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deletePrizeRuleDrawOnlinedays(java.math.BigInteger id){
//		return prizeRuleDrawOnlinedaysBaseDao.deletePrizeRuleDrawOnlinedays(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(折扣抽奖规则-用户在线时间)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deletePrizeRuleDrawOnlinedaysBatch(List<java.math.BigInteger> idList){
//		return prizeRuleDrawOnlinedaysBaseDao.deletePrizeRuleDrawOnlinedaysBatch(idList);
//	}
	
}
