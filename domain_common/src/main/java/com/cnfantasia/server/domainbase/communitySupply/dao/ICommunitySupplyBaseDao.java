package com.cnfantasia.server.domainbase.communitySupply.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.communitySupply.entity.CommunitySupply;

/**
 * 描述:(社区商家信息) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ICommunitySupplyBaseDao {
	/**
	 * 根据条件查询(社区商家信息)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<CommunitySupply> selectCommunitySupplyByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(社区商家信息)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<CommunitySupply> selectCommunitySupplyByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(社区商家信息)信息
	 * @param id
	 * @return
	 */
	public CommunitySupply selectCommunitySupplyBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(社区商家信息)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectCommunitySupplyCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(社区商家信息)新增一条记录
	 * @param communitySupply
	 * @return
	 */
	public int insertCommunitySupply(CommunitySupply communitySupply);
	
	/**
	 * 批量新增(社区商家信息)信息
	 * @param communitySupplyList
	 * @return
	 */
	public int insertCommunitySupplyBatch(List<CommunitySupply> communitySupplyList);
	
	/**
	 * 更新(社区商家信息)信息
	 * @param communitySupply
	 * @return
	 */
	public int updateCommunitySupply(CommunitySupply communitySupply);
	
	/**
	 * 批量更新(社区商家信息)信息
	 * @param communitySupplyList
	 * @return
	 */
	public int updateCommunitySupplyBatch(List<CommunitySupply> communitySupplyList);
	
	/**
	 * 根据序列号删除(社区商家信息)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteCommunitySupplyLogic(java.math.BigInteger id);
	
	/**
	 * 根据Id 批量删除(社区商家信息)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteCommunitySupplyLogicBatch(List<java.math.BigInteger> idList);
	
	
//	/**
//	 * 根据序列号删除(社区商家信息)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteCommunitySupply(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(社区商家信息)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteCommunitySupplyBatch(List<java.math.BigInteger> idList);
	
	
}
