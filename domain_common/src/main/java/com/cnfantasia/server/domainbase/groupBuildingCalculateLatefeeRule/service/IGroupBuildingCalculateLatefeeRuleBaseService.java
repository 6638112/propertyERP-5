package com.cnfantasia.server.domainbase.groupBuildingCalculateLatefeeRule.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.groupBuildingCalculateLatefeeRule.entity.GroupBuildingCalculateLatefeeRule;

/**
 * 描述:(小区滞纳金配置表) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IGroupBuildingCalculateLatefeeRuleBaseService {
	
	/**
	 * 根据条件查询(小区滞纳金配置表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	List<GroupBuildingCalculateLatefeeRule> getGroupBuildingCalculateLatefeeRuleByCondition(Map<String, Object> paramMap);
	/**
	 * 根据条件查询(小区滞纳金配置表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	List<GroupBuildingCalculateLatefeeRule> getGroupBuildingCalculateLatefeeRuleByConditionDim(Map<String, Object> paramMap);
	/**
	 * 按条件分页查询(小区滞纳金配置表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	List<GroupBuildingCalculateLatefeeRule> getGroupBuildingCalculateLatefeeRuleByCondition(Map<String, Object> paramMap, PageModel pageModel);
	/**
	 * 按条件分页查询(小区滞纳金配置表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	List<GroupBuildingCalculateLatefeeRule> getGroupBuildingCalculateLatefeeRuleByConditionDim(Map<String, Object> paramMap, PageModel pageModel);
	/**
	 * 根据序列号查询(小区滞纳金配置表)信息
	 * @param id
	 * @return
	 */
	GroupBuildingCalculateLatefeeRule getGroupBuildingCalculateLatefeeRuleBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(小区滞纳金配置表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	int getGroupBuildingCalculateLatefeeRuleCount(Map<String, Object> paramMap);
	/**
	 * 根据条件查询满足条件的(小区滞纳金配置表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	int getGroupBuildingCalculateLatefeeRuleCountDim(Map<String, Object> paramMap);
	/**
	 * 往(小区滞纳金配置表)新增一条记录
	 * @param groupBuildingCalculateLatefeeRule
	 * @return
	 */
	int insertGroupBuildingCalculateLatefeeRule(GroupBuildingCalculateLatefeeRule groupBuildingCalculateLatefeeRule);
	/**
	 * 批量新增(小区滞纳金配置表)
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
	 * 根据序列号批量删除(小区滞纳金配置表)信息_逻辑删除
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
