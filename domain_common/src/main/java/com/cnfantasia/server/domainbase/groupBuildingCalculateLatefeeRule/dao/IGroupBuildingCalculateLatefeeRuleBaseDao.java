package com.cnfantasia.server.domainbase.groupBuildingCalculateLatefeeRule.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.groupBuildingCalculateLatefeeRule.entity.GroupBuildingCalculateLatefeeRule;

/**
 * 描述:(小区滞纳金配置表) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IGroupBuildingCalculateLatefeeRuleBaseDao {
	/**
	 * 根据条件查询(小区滞纳金配置表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	List<GroupBuildingCalculateLatefeeRule> selectGroupBuildingCalculateLatefeeRuleByCondition(Map<String, Object> paramMap, boolean isDim);
	/**
	 * 按条件分页查询(小区滞纳金配置表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	List<GroupBuildingCalculateLatefeeRule> selectGroupBuildingCalculateLatefeeRuleByCondition(Map<String, Object> paramMap, PageModel pageModel, boolean isDim);
	/**
	 * 根据序列号查询(小区滞纳金配置表)信息
	 * @param id
	 * @return
	 */
	GroupBuildingCalculateLatefeeRule selectGroupBuildingCalculateLatefeeRuleBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(小区滞纳金配置表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	int selectGroupBuildingCalculateLatefeeRuleCount(Map<String, Object> paramMap, boolean isDim);
	/**
	 * 往(小区滞纳金配置表)新增一条记录
	 * @param groupBuildingCalculateLatefeeRule
	 * @return
	 */
	int insertGroupBuildingCalculateLatefeeRule(GroupBuildingCalculateLatefeeRule groupBuildingCalculateLatefeeRule);
	
	/**
	 * 批量新增(小区滞纳金配置表)信息
	 * @param groupBuildingCalculateLatefeeRuleList
	 * @return
	 */
	int insertGroupBuildingCalculateLatefeeRuleBatch(List<GroupBuildingCalculateLatefeeRule> groupBuildingCalculateLatefeeRuleList);
	
	/**
	 * 更新(小区滞纳金配置表)信息
	 * @param groupBuildingCalculateLatefeeRule
	 * @return
	 */
	int updateGroupBuildingCalculateLatefeeRule(GroupBuildingCalculateLatefeeRule groupBuildingCalculateLatefeeRule);
	
	/**
	 * 批量更新(小区滞纳金配置表)信息
	 * @param groupBuildingCalculateLatefeeRuleList
	 * @return
	 */
	int updateGroupBuildingCalculateLatefeeRuleBatch(List<GroupBuildingCalculateLatefeeRule> groupBuildingCalculateLatefeeRuleList);
	
	/**
	 * 根据序列号删除(小区滞纳金配置表)信息_逻辑删除
	 * @param id
	 * @return
	 */

	int deleteGroupBuildingCalculateLatefeeRuleLogic(java.math.BigInteger id);
	
	/**
	 * 根据Id 批量删除(小区滞纳金配置表)信息_逻辑删除
	 * @param idList
	 * @return
	 */

	int deleteGroupBuildingCalculateLatefeeRuleLogicBatch(List<java.math.BigInteger> idList);
	
	
//	/**
//	 * 根据序列号删除(小区滞纳金配置表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteGroupBuildingCalculateLatefeeRule(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(小区滞纳金配置表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteGroupBuildingCalculateLatefeeRuleBatch(List<java.math.BigInteger> idList);
	
	
}
