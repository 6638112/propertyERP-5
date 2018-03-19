package com.cnfantasia.server.domainbase.prizeRuleGenerateUsercount.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.prizeRuleGenerateUsercount.entity.PrizeRuleGenerateUsercount;

/**
 * 描述:(折扣生成规则-用户数量因素) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IPrizeRuleGenerateUsercountBaseService {
	
	/**
	 * 根据条件查询(折扣生成规则-用户数量因素)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<PrizeRuleGenerateUsercount> getPrizeRuleGenerateUsercountByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(折扣生成规则-用户数量因素)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<PrizeRuleGenerateUsercount> getPrizeRuleGenerateUsercountByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(折扣生成规则-用户数量因素)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<PrizeRuleGenerateUsercount> getPrizeRuleGenerateUsercountByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(折扣生成规则-用户数量因素)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<PrizeRuleGenerateUsercount> getPrizeRuleGenerateUsercountByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(折扣生成规则-用户数量因素)信息
	 * @param id
	 * @return
	 */
	public PrizeRuleGenerateUsercount getPrizeRuleGenerateUsercountBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(折扣生成规则-用户数量因素)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getPrizeRuleGenerateUsercountCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(折扣生成规则-用户数量因素)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getPrizeRuleGenerateUsercountCountDim(Map<String,Object> paramMap);
	/**
	 * 往(折扣生成规则-用户数量因素)新增一条记录
	 * @param prizeRuleGenerateUsercount
	 * @return
	 */
	public int insertPrizeRuleGenerateUsercount(PrizeRuleGenerateUsercount prizeRuleGenerateUsercount);
	/**
	 * 批量新增(折扣生成规则-用户数量因素)
	 * @param prizeRuleGenerateUsercountList
	 * @return
	 */
	public int insertPrizeRuleGenerateUsercountBatch(List<PrizeRuleGenerateUsercount> prizeRuleGenerateUsercountList);
	/**
	 * 更新(折扣生成规则-用户数量因素)信息
	 * @param prizeRuleGenerateUsercount
	 * @return
	 */
	public int updatePrizeRuleGenerateUsercount(PrizeRuleGenerateUsercount prizeRuleGenerateUsercount);
	/**
	 * 批量更新(折扣生成规则-用户数量因素)信息
	 * @param prizeRuleGenerateUsercountList
	 * @return
	 */
	public int updatePrizeRuleGenerateUsercountBatch(List<PrizeRuleGenerateUsercount> prizeRuleGenerateUsercountList);
	/**
	 * 根据序列号删除(折扣生成规则-用户数量因素)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deletePrizeRuleGenerateUsercountLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(折扣生成规则-用户数量因素)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deletePrizeRuleGenerateUsercountLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(折扣生成规则-用户数量因素)信息
//	 * @param id
//	 * @return
//	 */
//	public int deletePrizeRuleGenerateUsercount(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(折扣生成规则-用户数量因素)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deletePrizeRuleGenerateUsercountBatch(List<java.math.BigInteger> idList);
	
}
