package com.cnfantasia.server.domainbase.groupBuildingHasTCommunitySupplyTmp.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.groupBuildingHasTCommunitySupplyTmp.entity.GroupBuildingHasTCommunitySupplyTmp;

/**
 * 描述:(小区临时商家关系表) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IGroupBuildingHasTCommunitySupplyTmpBaseDao {
	/**
	 * 根据条件查询(小区临时商家关系表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<GroupBuildingHasTCommunitySupplyTmp> selectGroupBuildingHasTCommunitySupplyTmpByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(小区临时商家关系表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<GroupBuildingHasTCommunitySupplyTmp> selectGroupBuildingHasTCommunitySupplyTmpByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(小区临时商家关系表)信息
	 * @param id
	 * @return
	 */
	public GroupBuildingHasTCommunitySupplyTmp selectGroupBuildingHasTCommunitySupplyTmpBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(小区临时商家关系表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectGroupBuildingHasTCommunitySupplyTmpCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(小区临时商家关系表)新增一条记录
	 * @param groupBuildingHasTCommunitySupplyTmp
	 * @return
	 */
	public int insertGroupBuildingHasTCommunitySupplyTmp(GroupBuildingHasTCommunitySupplyTmp groupBuildingHasTCommunitySupplyTmp);
	
	/**
	 * 批量新增(小区临时商家关系表)信息
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
	 * 根据Id 批量删除(小区临时商家关系表)信息_逻辑删除
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
