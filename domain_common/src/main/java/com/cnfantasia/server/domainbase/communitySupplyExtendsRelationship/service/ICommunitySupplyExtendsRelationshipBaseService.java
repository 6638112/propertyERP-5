package com.cnfantasia.server.domainbase.communitySupplyExtendsRelationship.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.communitySupplyExtendsRelationship.entity.CommunitySupplyExtendsRelationship;

/**
 * 描述:(社区商家拓展对应信息) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ICommunitySupplyExtendsRelationshipBaseService {
	
	/**
	 * 根据条件查询(社区商家拓展对应信息)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<CommunitySupplyExtendsRelationship> getCommunitySupplyExtendsRelationshipByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(社区商家拓展对应信息)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<CommunitySupplyExtendsRelationship> getCommunitySupplyExtendsRelationshipByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(社区商家拓展对应信息)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<CommunitySupplyExtendsRelationship> getCommunitySupplyExtendsRelationshipByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(社区商家拓展对应信息)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<CommunitySupplyExtendsRelationship> getCommunitySupplyExtendsRelationshipByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(社区商家拓展对应信息)信息
	 * @param id
	 * @return
	 */
	public CommunitySupplyExtendsRelationship getCommunitySupplyExtendsRelationshipBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(社区商家拓展对应信息)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getCommunitySupplyExtendsRelationshipCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(社区商家拓展对应信息)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getCommunitySupplyExtendsRelationshipCountDim(Map<String,Object> paramMap);
	/**
	 * 往(社区商家拓展对应信息)新增一条记录
	 * @param communitySupplyExtendsRelationship
	 * @return
	 */
	public int insertCommunitySupplyExtendsRelationship(CommunitySupplyExtendsRelationship communitySupplyExtendsRelationship);
	/**
	 * 批量新增(社区商家拓展对应信息)
	 * @param communitySupplyExtendsRelationshipList
	 * @return
	 */
	public int insertCommunitySupplyExtendsRelationshipBatch(List<CommunitySupplyExtendsRelationship> communitySupplyExtendsRelationshipList);
	/**
	 * 更新(社区商家拓展对应信息)信息
	 * @param communitySupplyExtendsRelationship
	 * @return
	 */
	public int updateCommunitySupplyExtendsRelationship(CommunitySupplyExtendsRelationship communitySupplyExtendsRelationship);
	/**
	 * 批量更新(社区商家拓展对应信息)信息
	 * @param communitySupplyExtendsRelationshipList
	 * @return
	 */
	public int updateCommunitySupplyExtendsRelationshipBatch(List<CommunitySupplyExtendsRelationship> communitySupplyExtendsRelationshipList);
	/**
	 * 根据序列号删除(社区商家拓展对应信息)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteCommunitySupplyExtendsRelationshipLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(社区商家拓展对应信息)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteCommunitySupplyExtendsRelationshipLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(社区商家拓展对应信息)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteCommunitySupplyExtendsRelationship(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(社区商家拓展对应信息)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteCommunitySupplyExtendsRelationshipBatch(List<java.math.BigInteger> idList);
	
}
