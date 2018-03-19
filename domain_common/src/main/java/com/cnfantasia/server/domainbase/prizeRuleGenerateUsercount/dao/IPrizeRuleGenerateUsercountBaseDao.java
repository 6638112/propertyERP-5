package com.cnfantasia.server.domainbase.prizeRuleGenerateUsercount.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.prizeRuleGenerateUsercount.entity.PrizeRuleGenerateUsercount;

/**
 * 描述:(折扣生成规则-用户数量因素) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IPrizeRuleGenerateUsercountBaseDao {
	/**
	 * 根据条件查询(折扣生成规则-用户数量因素)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<PrizeRuleGenerateUsercount> selectPrizeRuleGenerateUsercountByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(折扣生成规则-用户数量因素)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<PrizeRuleGenerateUsercount> selectPrizeRuleGenerateUsercountByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(折扣生成规则-用户数量因素)信息
	 * @param id
	 * @return
	 */
	public PrizeRuleGenerateUsercount selectPrizeRuleGenerateUsercountBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(折扣生成规则-用户数量因素)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectPrizeRuleGenerateUsercountCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(折扣生成规则-用户数量因素)新增一条记录
	 * @param prizeRuleGenerateUsercount
	 * @return
	 */
	public int insertPrizeRuleGenerateUsercount(PrizeRuleGenerateUsercount prizeRuleGenerateUsercount);
	
	/**
	 * 批量新增(折扣生成规则-用户数量因素)信息
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
	 * 根据Id 批量删除(折扣生成规则-用户数量因素)信息_逻辑删除
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
