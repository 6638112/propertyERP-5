package com.cnfantasia.server.domainbase.communityExchangeRelation.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.communityExchangeRelation.entity.CommunityExchangeRelation;

/**
 * 描述:(换物关系表) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ICommunityExchangeRelationBaseService {
	
	/**
	 * 根据条件查询(换物关系表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<CommunityExchangeRelation> getCommunityExchangeRelationByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(换物关系表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<CommunityExchangeRelation> getCommunityExchangeRelationByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(换物关系表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<CommunityExchangeRelation> getCommunityExchangeRelationByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(换物关系表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<CommunityExchangeRelation> getCommunityExchangeRelationByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(换物关系表)信息
	 * @param id
	 * @return
	 */
	public CommunityExchangeRelation getCommunityExchangeRelationBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(换物关系表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getCommunityExchangeRelationCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(换物关系表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getCommunityExchangeRelationCountDim(Map<String,Object> paramMap);
	/**
	 * 往(换物关系表)新增一条记录
	 * @param communityExchangeRelation
	 * @return
	 */
	public int insertCommunityExchangeRelation(CommunityExchangeRelation communityExchangeRelation);
	/**
	 * 批量新增(换物关系表)
	 * @param communityExchangeRelationList
	 * @return
	 */
	public int insertCommunityExchangeRelationBatch(List<CommunityExchangeRelation> communityExchangeRelationList);
	/**
	 * 更新(换物关系表)信息
	 * @param communityExchangeRelation
	 * @return
	 */
	public int updateCommunityExchangeRelation(CommunityExchangeRelation communityExchangeRelation);
	/**
	 * 批量更新(换物关系表)信息
	 * @param communityExchangeRelationList
	 * @return
	 */
	public int updateCommunityExchangeRelationBatch(List<CommunityExchangeRelation> communityExchangeRelationList);
	/**
	 * 根据序列号删除(换物关系表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteCommunityExchangeRelationLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(换物关系表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteCommunityExchangeRelationLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(换物关系表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteCommunityExchangeRelation(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(换物关系表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteCommunityExchangeRelationBatch(List<java.math.BigInteger> idList);
	
}
