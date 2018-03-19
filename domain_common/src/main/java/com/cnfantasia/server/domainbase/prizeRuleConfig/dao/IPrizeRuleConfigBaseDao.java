package com.cnfantasia.server.domainbase.prizeRuleConfig.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.prizeRuleConfig.entity.PrizeRuleConfig;

/**
 * 描述:(抽奖规则配置) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IPrizeRuleConfigBaseDao {
	/**
	 * 根据条件查询(抽奖规则配置)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<PrizeRuleConfig> selectPrizeRuleConfigByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(抽奖规则配置)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<PrizeRuleConfig> selectPrizeRuleConfigByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(抽奖规则配置)信息
	 * @param id
	 * @return
	 */
	public PrizeRuleConfig selectPrizeRuleConfigBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(抽奖规则配置)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectPrizeRuleConfigCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(抽奖规则配置)新增一条记录
	 * @param prizeRuleConfig
	 * @return
	 */
	public int insertPrizeRuleConfig(PrizeRuleConfig prizeRuleConfig);
	
	/**
	 * 批量新增(抽奖规则配置)信息
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
	 * 根据Id 批量删除(抽奖规则配置)信息_逻辑删除
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
