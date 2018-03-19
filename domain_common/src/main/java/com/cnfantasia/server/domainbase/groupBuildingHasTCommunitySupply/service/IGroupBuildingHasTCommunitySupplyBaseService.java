package com.cnfantasia.server.domainbase.groupBuildingHasTCommunitySupply.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.groupBuildingHasTCommunitySupply.entity.GroupBuildingHasTCommunitySupply;

/**
 * 描述:(小区商家关系表) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IGroupBuildingHasTCommunitySupplyBaseService {
	
	/**
	 * 根据条件查询(小区商家关系表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<GroupBuildingHasTCommunitySupply> getGroupBuildingHasTCommunitySupplyByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(小区商家关系表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<GroupBuildingHasTCommunitySupply> getGroupBuildingHasTCommunitySupplyByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(小区商家关系表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<GroupBuildingHasTCommunitySupply> getGroupBuildingHasTCommunitySupplyByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(小区商家关系表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<GroupBuildingHasTCommunitySupply> getGroupBuildingHasTCommunitySupplyByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(小区商家关系表)信息
	 * @param id
	 * @return
	 */
	public GroupBuildingHasTCommunitySupply getGroupBuildingHasTCommunitySupplyBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(小区商家关系表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getGroupBuildingHasTCommunitySupplyCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(小区商家关系表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getGroupBuildingHasTCommunitySupplyCountDim(Map<String,Object> paramMap);
	/**
	 * 往(小区商家关系表)新增一条记录
	 * @param groupBuildingHasTCommunitySupply
	 * @return
	 */
	public int insertGroupBuildingHasTCommunitySupply(GroupBuildingHasTCommunitySupply groupBuildingHasTCommunitySupply);
	/**
	 * 批量新增(小区商家关系表)
	 * @param groupBuildingHasTCommunitySupplyList
	 * @return
	 */
	public int insertGroupBuildingHasTCommunitySupplyBatch(List<GroupBuildingHasTCommunitySupply> groupBuildingHasTCommunitySupplyList);
	/**
	 * 更新(小区商家关系表)信息
	 * @param groupBuildingHasTCommunitySupply
	 * @return
	 */
	public int updateGroupBuildingHasTCommunitySupply(GroupBuildingHasTCommunitySupply groupBuildingHasTCommunitySupply);
	/**
	 * 批量更新(小区商家关系表)信息
	 * @param groupBuildingHasTCommunitySupplyList
	 * @return
	 */
	public int updateGroupBuildingHasTCommunitySupplyBatch(List<GroupBuildingHasTCommunitySupply> groupBuildingHasTCommunitySupplyList);
	/**
	 * 根据序列号删除(小区商家关系表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteGroupBuildingHasTCommunitySupplyLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(小区商家关系表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteGroupBuildingHasTCommunitySupplyLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(小区商家关系表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteGroupBuildingHasTCommunitySupply(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(小区商家关系表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteGroupBuildingHasTCommunitySupplyBatch(List<java.math.BigInteger> idList);
	
}
