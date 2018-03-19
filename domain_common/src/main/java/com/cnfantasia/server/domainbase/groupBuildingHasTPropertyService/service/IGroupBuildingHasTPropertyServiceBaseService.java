package com.cnfantasia.server.domainbase.groupBuildingHasTPropertyService.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.groupBuildingHasTPropertyService.entity.GroupBuildingHasTPropertyService;

/**
 * 描述:(小区服务关系表) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IGroupBuildingHasTPropertyServiceBaseService {
	
	/**
	 * 根据条件查询(小区服务关系表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<GroupBuildingHasTPropertyService> getGroupBuildingHasTPropertyServiceByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(小区服务关系表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<GroupBuildingHasTPropertyService> getGroupBuildingHasTPropertyServiceByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(小区服务关系表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<GroupBuildingHasTPropertyService> getGroupBuildingHasTPropertyServiceByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(小区服务关系表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<GroupBuildingHasTPropertyService> getGroupBuildingHasTPropertyServiceByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(小区服务关系表)信息
	 * @param id
	 * @return
	 */
	public GroupBuildingHasTPropertyService getGroupBuildingHasTPropertyServiceBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(小区服务关系表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getGroupBuildingHasTPropertyServiceCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(小区服务关系表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getGroupBuildingHasTPropertyServiceCountDim(Map<String,Object> paramMap);
	/**
	 * 往(小区服务关系表)新增一条记录
	 * @param groupBuildingHasTPropertyService
	 * @return
	 */
	public int insertGroupBuildingHasTPropertyService(GroupBuildingHasTPropertyService groupBuildingHasTPropertyService);
	/**
	 * 批量新增(小区服务关系表)
	 * @param groupBuildingHasTPropertyServiceList
	 * @return
	 */
	public int insertGroupBuildingHasTPropertyServiceBatch(List<GroupBuildingHasTPropertyService> groupBuildingHasTPropertyServiceList);
	/**
	 * 更新(小区服务关系表)信息
	 * @param groupBuildingHasTPropertyService
	 * @return
	 */
	public int updateGroupBuildingHasTPropertyService(GroupBuildingHasTPropertyService groupBuildingHasTPropertyService);
	/**
	 * 批量更新(小区服务关系表)信息
	 * @param groupBuildingHasTPropertyServiceList
	 * @return
	 */
	public int updateGroupBuildingHasTPropertyServiceBatch(List<GroupBuildingHasTPropertyService> groupBuildingHasTPropertyServiceList);
	/**
	 * 根据序列号删除(小区服务关系表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteGroupBuildingHasTPropertyServiceLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(小区服务关系表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteGroupBuildingHasTPropertyServiceLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(小区服务关系表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteGroupBuildingHasTPropertyService(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(小区服务关系表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteGroupBuildingHasTPropertyServiceBatch(List<java.math.BigInteger> idList);
	
}
