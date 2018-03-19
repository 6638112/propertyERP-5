package com.cnfantasia.server.domainbase.prizeRuleDrawOnlinedays.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.prizeRuleDrawOnlinedays.entity.PrizeRuleDrawOnlinedays;

/**
 * 描述:(折扣抽奖规则-用户在线时间) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IPrizeRuleDrawOnlinedaysBaseDao {
	/**
	 * 根据条件查询(折扣抽奖规则-用户在线时间)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<PrizeRuleDrawOnlinedays> selectPrizeRuleDrawOnlinedaysByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(折扣抽奖规则-用户在线时间)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<PrizeRuleDrawOnlinedays> selectPrizeRuleDrawOnlinedaysByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(折扣抽奖规则-用户在线时间)信息
	 * @param id
	 * @return
	 */
	public PrizeRuleDrawOnlinedays selectPrizeRuleDrawOnlinedaysBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(折扣抽奖规则-用户在线时间)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectPrizeRuleDrawOnlinedaysCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(折扣抽奖规则-用户在线时间)新增一条记录
	 * @param prizeRuleDrawOnlinedays
	 * @return
	 */
	public int insertPrizeRuleDrawOnlinedays(PrizeRuleDrawOnlinedays prizeRuleDrawOnlinedays);
	
	/**
	 * 批量新增(折扣抽奖规则-用户在线时间)信息
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
	 * 根据Id 批量删除(折扣抽奖规则-用户在线时间)信息_逻辑删除
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
