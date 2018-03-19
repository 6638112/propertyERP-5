package com.cnfantasia.server.domainbase.groupBuildingHasTCommunitySupply.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.groupBuildingHasTCommunitySupply.entity.GroupBuildingHasTCommunitySupply;

/**
 * 描述:(小区商家关系表) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IGroupBuildingHasTCommunitySupplyBaseDao {
	/**
	 * 根据条件查询(小区商家关系表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<GroupBuildingHasTCommunitySupply> selectGroupBuildingHasTCommunitySupplyByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(小区商家关系表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<GroupBuildingHasTCommunitySupply> selectGroupBuildingHasTCommunitySupplyByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(小区商家关系表)信息
	 * @param id
	 * @return
	 */
	public GroupBuildingHasTCommunitySupply selectGroupBuildingHasTCommunitySupplyBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(小区商家关系表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectGroupBuildingHasTCommunitySupplyCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(小区商家关系表)新增一条记录
	 * @param groupBuildingHasTCommunitySupply
	 * @return
	 */
	public int insertGroupBuildingHasTCommunitySupply(GroupBuildingHasTCommunitySupply groupBuildingHasTCommunitySupply);
	
	/**
	 * 批量新增(小区商家关系表)信息
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
	 * 根据Id 批量删除(小区商家关系表)信息_逻辑删除
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
