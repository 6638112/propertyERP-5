package com.cnfantasia.server.domainbase.groupBuildingHasTCommunitySupplyTmp.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.groupBuildingHasTCommunitySupplyTmp.entity.GroupBuildingHasTCommunitySupplyTmp;

/**
 * 描述:(小区临时商家关系表) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IGroupBuildingHasTCommunitySupplyTmpBaseService {
	
	/**
	 * 根据条件查询(小区临时商家关系表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<GroupBuildingHasTCommunitySupplyTmp> getGroupBuildingHasTCommunitySupplyTmpByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(小区临时商家关系表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<GroupBuildingHasTCommunitySupplyTmp> getGroupBuildingHasTCommunitySupplyTmpByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(小区临时商家关系表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<GroupBuildingHasTCommunitySupplyTmp> getGroupBuildingHasTCommunitySupplyTmpByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(小区临时商家关系表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<GroupBuildingHasTCommunitySupplyTmp> getGroupBuildingHasTCommunitySupplyTmpByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(小区临时商家关系表)信息
	 * @param id
	 * @return
	 */
	public GroupBuildingHasTCommunitySupplyTmp getGroupBuildingHasTCommunitySupplyTmpBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(小区临时商家关系表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getGroupBuildingHasTCommunitySupplyTmpCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(小区临时商家关系表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getGroupBuildingHasTCommunitySupplyTmpCountDim(Map<String,Object> paramMap);
	/**
	 * 往(小区临时商家关系表)新增一条记录
	 * @param groupBuildingHasTCommunitySupplyTmp
	 * @return
	 */
	public int insertGroupBuildingHasTCommunitySupplyTmp(GroupBuildingHasTCommunitySupplyTmp groupBuildingHasTCommunitySupplyTmp);
	/**
	 * 批量新增(小区临时商家关系表)
	 * @param groupBuildingHasTCommunitySupplyTmpList
	 * @return
	 */
	public int insertGroupBuildingHasTCommunitySupplyTmpBatch(List<GroupBuildingHasTCommunitySupplyTmp> groupBuildingHasTCommunitySupplyTmpList);
	/**
	 * 更新(小区临时商家关系表)信息
	 * @param groupBuildingHasTCommunitySupplyTmp
	 * @return
	 */
	public int updateGroupBuildingHasTCommunitySupplyTmp(GroupBuildingHasTCommunitySupplyTmp groupBuildingHasTCommunitySupplyTmp);
	/**
	 * 批量更新(小区临时商家关系表)信息
	 * @param groupBuildingHasTCommunitySupplyTmpList
	 * @return
	 */
	public int updateGroupBuildingHasTCommunitySupplyTmpBatch(List<GroupBuildingHasTCommunitySupplyTmp> groupBuildingHasTCommunitySupplyTmpList);
	/**
	 * 根据序列号删除(小区临时商家关系表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteGroupBuildingHasTCommunitySupplyTmpLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(小区临时商家关系表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteGroupBuildingHasTCommunitySupplyTmpLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(小区临时商家关系表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteGroupBuildingHasTCommunitySupplyTmp(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(小区临时商家关系表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteGroupBuildingHasTCommunitySupplyTmpBatch(List<java.math.BigInteger> idList);
	
}
