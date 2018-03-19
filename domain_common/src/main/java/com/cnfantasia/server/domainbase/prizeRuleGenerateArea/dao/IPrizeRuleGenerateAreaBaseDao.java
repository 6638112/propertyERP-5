package com.cnfantasia.server.domainbase.prizeRuleGenerateArea.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.prizeRuleGenerateArea.entity.PrizeRuleGenerateArea;

/**
 * 描述:(折扣生成规则-折扣区间分配因素) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IPrizeRuleGenerateAreaBaseDao {
	/**
	 * 根据条件查询(折扣生成规则-折扣区间分配因素)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<PrizeRuleGenerateArea> selectPrizeRuleGenerateAreaByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(折扣生成规则-折扣区间分配因素)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<PrizeRuleGenerateArea> selectPrizeRuleGenerateAreaByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(折扣生成规则-折扣区间分配因素)信息
	 * @param id
	 * @return
	 */
	public PrizeRuleGenerateArea selectPrizeRuleGenerateAreaBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(折扣生成规则-折扣区间分配因素)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectPrizeRuleGenerateAreaCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(折扣生成规则-折扣区间分配因素)新增一条记录
	 * @param prizeRuleGenerateArea
	 * @return
	 */
	public int insertPrizeRuleGenerateArea(PrizeRuleGenerateArea prizeRuleGenerateArea);
	
	/**
	 * 批量新增(折扣生成规则-折扣区间分配因素)信息
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
	 * 根据Id 批量删除(折扣生成规则-折扣区间分配因素)信息_逻辑删除
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
