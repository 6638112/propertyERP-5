package com.cnfantasia.server.domainbase.groupBuildingBillCycleConfig.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.groupBuildingBillCycleConfig.entity.GroupBuildingBillCycleConfig;

/**
 * 描述:(收费时间管理配置（自动生成账期和账单的配置）) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IGroupBuildingBillCycleConfigBaseService {
	
	/**
	 * 根据条件查询(收费时间管理配置（自动生成账期和账单的配置）)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	List<GroupBuildingBillCycleConfig> getGroupBuildingBillCycleConfigByCondition(Map<String, Object> paramMap);
	/**
	 * 根据条件查询(收费时间管理配置（自动生成账期和账单的配置）)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	List<GroupBuildingBillCycleConfig> getGroupBuildingBillCycleConfigByConditionDim(Map<String, Object> paramMap);
	/**
	 * 按条件分页查询(收费时间管理配置（自动生成账期和账单的配置）)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	List<GroupBuildingBillCycleConfig> getGroupBuildingBillCycleConfigByCondition(Map<String, Object> paramMap, PageModel pageModel);
	/**
	 * 按条件分页查询(收费时间管理配置（自动生成账期和账单的配置）)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	List<GroupBuildingBillCycleConfig> getGroupBuildingBillCycleConfigByConditionDim(Map<String, Object> paramMap, PageModel pageModel);
	/**
	 * 根据序列号查询(收费时间管理配置（自动生成账期和账单的配置）)信息
	 * @param id
	 * @return
	 */
	GroupBuildingBillCycleConfig getGroupBuildingBillCycleConfigBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(收费时间管理配置（自动生成账期和账单的配置）)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	int getGroupBuildingBillCycleConfigCount(Map<String, Object> paramMap);
	/**
	 * 根据条件查询满足条件的(收费时间管理配置（自动生成账期和账单的配置）)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	int getGroupBuildingBillCycleConfigCountDim(Map<String, Object> paramMap);
	/**
	 * 往(收费时间管理配置（自动生成账期和账单的配置）)新增一条记录
	 * @param groupBuildingBillCycleConfig
	 * @return
	 */
	int insertGroupBuildingBillCycleConfig(GroupBuildingBillCycleConfig groupBuildingBillCycleConfig);
	/**
	 * 批量新增(收费时间管理配置（自动生成账期和账单的配置）)
	 * @param groupBuildingBillCycleConfigList
	 * @return
	 */
	int insertGroupBuildingBillCycleConfigBatch(List<GroupBuildingBillCycleConfig> groupBuildingBillCycleConfigList);
	/**
	 * 更新(收费时间管理配置（自动生成账期和账单的配置）)信息
	 * @param groupBuildingBillCycleConfig
	 * @return
	 */
	int updateGroupBuildingBillCycleConfig(GroupBuildingBillCycleConfig groupBuildingBillCycleConfig);
	/**
	 * 批量更新(收费时间管理配置（自动生成账期和账单的配置）)信息
	 * @param groupBuildingBillCycleConfigList
	 * @return
	 */
	int updateGroupBuildingBillCycleConfigBatch(List<GroupBuildingBillCycleConfig> groupBuildingBillCycleConfigList);
	/**
	 * 根据序列号删除(收费时间管理配置（自动生成账期和账单的配置）)信息_逻辑删除
	 * @param id
	 * @return
	 */

	int deleteGroupBuildingBillCycleConfigLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(收费时间管理配置（自动生成账期和账单的配置）)信息_逻辑删除
	 * @param idList
	 * @return
	 */

	int deleteGroupBuildingBillCycleConfigLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(收费时间管理配置（自动生成账期和账单的配置）)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteGroupBuildingBillCycleConfig(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(收费时间管理配置（自动生成账期和账单的配置）)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteGroupBuildingBillCycleConfigBatch(List<java.math.BigInteger> idList);
	
}
