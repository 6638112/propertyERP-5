package com.cnfantasia.server.domainbase.prizeRuleDrawOnlinedays.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.prizeRuleDrawOnlinedays.entity.PrizeRuleDrawOnlinedays;

/**
 * 描述:(折扣抽奖规则-用户在线时间) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IPrizeRuleDrawOnlinedaysBaseService {
	
	/**
	 * 根据条件查询(折扣抽奖规则-用户在线时间)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<PrizeRuleDrawOnlinedays> getPrizeRuleDrawOnlinedaysByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(折扣抽奖规则-用户在线时间)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<PrizeRuleDrawOnlinedays> getPrizeRuleDrawOnlinedaysByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(折扣抽奖规则-用户在线时间)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<PrizeRuleDrawOnlinedays> getPrizeRuleDrawOnlinedaysByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(折扣抽奖规则-用户在线时间)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<PrizeRuleDrawOnlinedays> getPrizeRuleDrawOnlinedaysByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(折扣抽奖规则-用户在线时间)信息
	 * @param id
	 * @return
	 */
	public PrizeRuleDrawOnlinedays getPrizeRuleDrawOnlinedaysBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(折扣抽奖规则-用户在线时间)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getPrizeRuleDrawOnlinedaysCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(折扣抽奖规则-用户在线时间)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getPrizeRuleDrawOnlinedaysCountDim(Map<String,Object> paramMap);
	/**
	 * 往(折扣抽奖规则-用户在线时间)新增一条记录
	 * @param prizeRuleDrawOnlinedays
	 * @return
	 */
	public int insertPrizeRuleDrawOnlinedays(PrizeRuleDrawOnlinedays prizeRuleDrawOnlinedays);
	/**
	 * 批量新增(折扣抽奖规则-用户在线时间)
	 * @param prizeRuleDrawOnlinedaysList
	 * @return
	 */
	public int insertPrizeRuleDrawOnlinedaysBatch(List<PrizeRuleDrawOnlinedays> prizeRuleDrawOnlinedaysList);
	/**
	 * 更新(折扣抽奖规则-用户在线时间)信息
	 * @param prizeRuleDrawOnlinedays
	 * @return
	 */
	public int updatePrizeRuleDrawOnlinedays(PrizeRuleDrawOnlinedays prizeRuleDrawOnlinedays);
	/**
	 * 批量更新(折扣抽奖规则-用户在线时间)信息
	 * @param prizeRuleDrawOnlinedaysList
	 * @return
	 */
	public int updatePrizeRuleDrawOnlinedaysBatch(List<PrizeRuleDrawOnlinedays> prizeRuleDrawOnlinedaysList);
	/**
	 * 根据序列号删除(折扣抽奖规则-用户在线时间)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deletePrizeRuleDrawOnlinedaysLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(折扣抽奖规则-用户在线时间)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deletePrizeRuleDrawOnlinedaysLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(折扣抽奖规则-用户在线时间)信息
//	 * @param id
//	 * @return
//	 */
//	public int deletePrizeRuleDrawOnlinedays(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(折扣抽奖规则-用户在线时间)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deletePrizeRuleDrawOnlinedaysBatch(List<java.math.BigInteger> idList);
	
}
