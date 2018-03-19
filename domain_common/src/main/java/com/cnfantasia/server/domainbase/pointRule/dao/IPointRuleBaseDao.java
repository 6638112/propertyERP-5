package com.cnfantasia.server.domainbase.pointRule.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.pointRule.entity.PointRule;

/**
 * 描述:(积分规则) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IPointRuleBaseDao {
	/**
	 * 根据条件查询(积分规则)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<PointRule> selectPointRuleByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(积分规则)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<PointRule> selectPointRuleByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(积分规则)信息
	 * @param id
	 * @return
	 */
	public PointRule selectPointRuleBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(积分规则)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectPointRuleCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(积分规则)新增一条记录
	 * @param pointRule
	 * @return
	 */
	public int insertPointRule(PointRule pointRule);
	
	/**
	 * 批量新增(积分规则)信息
	 * @param pointRuleList
	 * @return
	 */
	public int insertPointRuleBatch(List<PointRule> pointRuleList);
	
	/**
	 * 更新(积分规则)信息
	 * @param pointRule
	 * @return
	 */
	public int updatePointRule(PointRule pointRule);
	
	/**
	 * 批量更新(积分规则)信息
	 * @param pointRuleList
	 * @return
	 */
	public int updatePointRuleBatch(List<PointRule> pointRuleList);
	
	/**
	 * 根据序列号删除(积分规则)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deletePointRuleLogic(java.math.BigInteger id);
	
	/**
	 * 根据Id 批量删除(积分规则)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deletePointRuleLogicBatch(List<java.math.BigInteger> idList);
	
	
//	/**
//	 * 根据序列号删除(积分规则)信息
//	 * @param id
//	 * @return
//	 */
//	public int deletePointRule(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(积分规则)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deletePointRuleBatch(List<java.math.BigInteger> idList);
	
	
}
