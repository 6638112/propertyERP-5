package com.cnfantasia.server.domainbase.prizeRuleConfig.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.prizeRuleConfig.dao.IPrizeRuleConfigBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.prizeRuleConfig.entity.PrizeRuleConfig;

/**
 * 描述:(抽奖规则配置) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class PrizeRuleConfigBaseService implements IPrizeRuleConfigBaseService{
	
	private IPrizeRuleConfigBaseDao prizeRuleConfigBaseDao;
	public void setPrizeRuleConfigBaseDao(IPrizeRuleConfigBaseDao prizeRuleConfigBaseDao) {
		this.prizeRuleConfigBaseDao = prizeRuleConfigBaseDao;
	}
	/**
	 * 根据条件查询(抽奖规则配置)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<PrizeRuleConfig> getPrizeRuleConfigByCondition(Map<String,Object> paramMap){
		return prizeRuleConfigBaseDao.selectPrizeRuleConfigByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(抽奖规则配置)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<PrizeRuleConfig> getPrizeRuleConfigByConditionDim(Map<String,Object> paramMap){
		return prizeRuleConfigBaseDao.selectPrizeRuleConfigByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(抽奖规则配置)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<PrizeRuleConfig> getPrizeRuleConfigByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return prizeRuleConfigBaseDao.selectPrizeRuleConfigByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(抽奖规则配置)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<PrizeRuleConfig> getPrizeRuleConfigByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return prizeRuleConfigBaseDao.selectPrizeRuleConfigByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(抽奖规则配置)信息
	 * @param id
	 * @return
	 */
	@Override
	public PrizeRuleConfig getPrizeRuleConfigBySeqId(java.math.BigInteger id){
		return prizeRuleConfigBaseDao.selectPrizeRuleConfigBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(抽奖规则配置)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getPrizeRuleConfigCount(Map<String,Object> paramMap){
		return prizeRuleConfigBaseDao.selectPrizeRuleConfigCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(抽奖规则配置)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getPrizeRuleConfigCountDim(Map<String,Object> paramMap){
		return prizeRuleConfigBaseDao.selectPrizeRuleConfigCount(paramMap,true);
	}
	/**
	 * 往(抽奖规则配置)新增一条记录
	 * @param prizeRuleConfig
	 * @return
	 */
	@Override
	public int insertPrizeRuleConfig(PrizeRuleConfig prizeRuleConfig){
		return prizeRuleConfigBaseDao.insertPrizeRuleConfig(prizeRuleConfig);
	}
	/**
	 * 批量新增(抽奖规则配置)
	 * @param prizeRuleConfigList
	 * @return
	 */
	@Override
	public int insertPrizeRuleConfigBatch(List<PrizeRuleConfig> prizeRuleConfigList){
		return prizeRuleConfigBaseDao.insertPrizeRuleConfigBatch(prizeRuleConfigList);
	}
	/**
	 * 更新(抽奖规则配置)信息
	 * @param prizeRuleConfig
	 * @return
	 */
	@Override
	public int updatePrizeRuleConfig(PrizeRuleConfig prizeRuleConfig){
		return prizeRuleConfigBaseDao.updatePrizeRuleConfig(prizeRuleConfig);
	}
	/**
	 * 批量更新(抽奖规则配置)信息
	 * @param prizeRuleConfigList
	 * @return
	 */
	@Override
	public int updatePrizeRuleConfigBatch(List<PrizeRuleConfig> prizeRuleConfigList){
		return prizeRuleConfigBaseDao.updatePrizeRuleConfigBatch(prizeRuleConfigList);
	}
	/**
	 * 根据序列号删除(抽奖规则配置)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deletePrizeRuleConfigLogic(java.math.BigInteger id){
		return prizeRuleConfigBaseDao.deletePrizeRuleConfigLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(抽奖规则配置)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deletePrizeRuleConfigLogicBatch(List<java.math.BigInteger> idList){
		return prizeRuleConfigBaseDao.deletePrizeRuleConfigLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(抽奖规则配置)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deletePrizeRuleConfig(java.math.BigInteger id){
//		return prizeRuleConfigBaseDao.deletePrizeRuleConfig(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(抽奖规则配置)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deletePrizeRuleConfigBatch(List<java.math.BigInteger> idList){
//		return prizeRuleConfigBaseDao.deletePrizeRuleConfigBatch(idList);
//	}
	
}
