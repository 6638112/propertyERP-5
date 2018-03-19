package com.cnfantasia.server.domainbase.groupBuildingBillCycleConfig.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.groupBuildingBillCycleConfig.entity.GroupBuildingBillCycleConfig;

/**
 * 描述:(收费时间管理配置（自动生成账期和账单的配置）) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IGroupBuildingBillCycleConfigBaseDao {
	/**
	 * 根据条件查询(收费时间管理配置（自动生成账期和账单的配置）)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	List<GroupBuildingBillCycleConfig> selectGroupBuildingBillCycleConfigByCondition(Map<String, Object> paramMap, boolean isDim);
	/**
	 * 按条件分页查询(收费时间管理配置（自动生成账期和账单的配置）)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	List<GroupBuildingBillCycleConfig> selectGroupBuildingBillCycleConfigByCondition(Map<String, Object> paramMap, PageModel pageModel, boolean isDim);
	/**
	 * 根据序列号查询(收费时间管理配置（自动生成账期和账单的配置）)信息
	 * @param id
	 * @return
	 */
	GroupBuildingBillCycleConfig selectGroupBuildingBillCycleConfigBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(收费时间管理配置（自动生成账期和账单的配置）)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	int selectGroupBuildingBillCycleConfigCount(Map<String, Object> paramMap, boolean isDim);
	/**
	 * 往(收费时间管理配置（自动生成账期和账单的配置）)新增一条记录
	 * @param groupBuildingBillCycleConfig
	 * @return
	 */
	int insertGroupBuildingBillCycleConfig(GroupBuildingBillCycleConfig groupBuildingBillCycleConfig);
	
	/**
	 * 批量新增(收费时间管理配置（自动生成账期和账单的配置）)信息
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
	 * 根据Id 批量删除(收费时间管理配置（自动生成账期和账单的配置）)信息_逻辑删除
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
