package com.cnfantasia.server.domainbase.prizeRuleGenerateArea.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.prizeRuleGenerateArea.entity.PrizeRuleGenerateArea;

/**
 * 描述:(折扣生成规则-折扣区间分配因素) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IPrizeRuleGenerateAreaBaseService {
	
	/**
	 * 根据条件查询(折扣生成规则-折扣区间分配因素)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<PrizeRuleGenerateArea> getPrizeRuleGenerateAreaByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(折扣生成规则-折扣区间分配因素)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<PrizeRuleGenerateArea> getPrizeRuleGenerateAreaByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(折扣生成规则-折扣区间分配因素)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<PrizeRuleGenerateArea> getPrizeRuleGenerateAreaByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(折扣生成规则-折扣区间分配因素)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<PrizeRuleGenerateArea> getPrizeRuleGenerateAreaByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(折扣生成规则-折扣区间分配因素)信息
	 * @param id
	 * @return
	 */
	public PrizeRuleGenerateArea getPrizeRuleGenerateAreaBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(折扣生成规则-折扣区间分配因素)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getPrizeRuleGenerateAreaCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(折扣生成规则-折扣区间分配因素)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getPrizeRuleGenerateAreaCountDim(Map<String,Object> paramMap);
	/**
	 * 往(折扣生成规则-折扣区间分配因素)新增一条记录
	 * @param prizeRuleGenerateArea
	 * @return
	 */
	public int insertPrizeRuleGenerateArea(PrizeRuleGenerateArea prizeRuleGenerateArea);
	/**
	 * 批量新增(折扣生成规则-折扣区间分配因素)
	 * @param prizeRuleGenerateAreaList
	 * @return
	 */
	public int insertPrizeRuleGenerateAreaBatch(List<PrizeRuleGenerateArea> prizeRuleGenerateAreaList);
	/**
	 * 更新(折扣生成规则-折扣区间分配因素)信息
	 * @param prizeRuleGenerateArea
	 * @return
	 */
	public int updatePrizeRuleGenerateArea(PrizeRuleGenerateArea prizeRuleGenerateArea);
	/**
	 * 批量更新(折扣生成规则-折扣区间分配因素)信息
	 * @param prizeRuleGenerateAreaList
	 * @return
	 */
	public int updatePrizeRuleGenerateAreaBatch(List<PrizeRuleGenerateArea> prizeRuleGenerateAreaList);
	/**
	 * 根据序列号删除(折扣生成规则-折扣区间分配因素)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deletePrizeRuleGenerateAreaLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(折扣生成规则-折扣区间分配因素)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deletePrizeRuleGenerateAreaLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(折扣生成规则-折扣区间分配因素)信息
//	 * @param id
//	 * @return
//	 */
//	public int deletePrizeRuleGenerateArea(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(折扣生成规则-折扣区间分配因素)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deletePrizeRuleGenerateAreaBatch(List<java.math.BigInteger> idList);
	
}
