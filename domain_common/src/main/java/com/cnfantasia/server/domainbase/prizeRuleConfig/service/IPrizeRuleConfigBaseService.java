package com.cnfantasia.server.domainbase.prizeRuleConfig.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.prizeRuleConfig.entity.PrizeRuleConfig;

/**
 * 描述:(抽奖规则配置) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IPrizeRuleConfigBaseService {
	
	/**
	 * 根据条件查询(抽奖规则配置)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<PrizeRuleConfig> getPrizeRuleConfigByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(抽奖规则配置)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<PrizeRuleConfig> getPrizeRuleConfigByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(抽奖规则配置)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<PrizeRuleConfig> getPrizeRuleConfigByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(抽奖规则配置)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<PrizeRuleConfig> getPrizeRuleConfigByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(抽奖规则配置)信息
	 * @param id
	 * @return
	 */
	public PrizeRuleConfig getPrizeRuleConfigBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(抽奖规则配置)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getPrizeRuleConfigCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(抽奖规则配置)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getPrizeRuleConfigCountDim(Map<String,Object> paramMap);
	/**
	 * 往(抽奖规则配置)新增一条记录
	 * @param prizeRuleConfig
	 * @return
	 */
	public int insertPrizeRuleConfig(PrizeRuleConfig prizeRuleConfig);
	/**
	 * 批量新增(抽奖规则配置)
	 * @param prizeRuleConfigList
	 * @return
	 */
	public int insertPrizeRuleConfigBatch(List<PrizeRuleConfig> prizeRuleConfigList);
	/**
	 * 更新(抽奖规则配置)信息
	 * @param prizeRuleConfig
	 * @return
	 */
	public int updatePrizeRuleConfig(PrizeRuleConfig prizeRuleConfig);
	/**
	 * 批量更新(抽奖规则配置)信息
	 * @param prizeRuleConfigList
	 * @return
	 */
	public int updatePrizeRuleConfigBatch(List<PrizeRuleConfig> prizeRuleConfigList);
	/**
	 * 根据序列号删除(抽奖规则配置)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deletePrizeRuleConfigLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(抽奖规则配置)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deletePrizeRuleConfigLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(抽奖规则配置)信息
//	 * @param id
//	 * @return
//	 */
//	public int deletePrizeRuleConfig(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(抽奖规则配置)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deletePrizeRuleConfigBatch(List<java.math.BigInteger> idList);
	
}
