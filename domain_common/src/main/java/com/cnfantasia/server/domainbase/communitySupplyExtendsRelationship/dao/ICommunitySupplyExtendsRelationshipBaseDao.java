package com.cnfantasia.server.domainbase.communitySupplyExtendsRelationship.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.communitySupplyExtendsRelationship.entity.CommunitySupplyExtendsRelationship;

/**
 * 描述:(社区商家拓展对应信息) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ICommunitySupplyExtendsRelationshipBaseDao {
	/**
	 * 根据条件查询(社区商家拓展对应信息)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<CommunitySupplyExtendsRelationship> selectCommunitySupplyExtendsRelationshipByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(社区商家拓展对应信息)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<CommunitySupplyExtendsRelationship> selectCommunitySupplyExtendsRelationshipByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(社区商家拓展对应信息)信息
	 * @param id
	 * @return
	 */
	public CommunitySupplyExtendsRelationship selectCommunitySupplyExtendsRelationshipBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(社区商家拓展对应信息)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectCommunitySupplyExtendsRelationshipCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(社区商家拓展对应信息)新增一条记录
	 * @param communitySupplyExtendsRelationship
	 * @return
	 */
	public int insertCommunitySupplyExtendsRelationship(CommunitySupplyExtendsRelationship communitySupplyExtendsRelationship);
	
	/**
	 * 批量新增(社区商家拓展对应信息)信息
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
	 * 根据Id 批量删除(社区商家拓展对应信息)信息_逻辑删除
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
