package com.cnfantasia.server.domainbase.communityExchangeRelation.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.communityExchangeRelation.entity.CommunityExchangeRelation;

/**
 * 描述:(换物关系表) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ICommunityExchangeRelationBaseDao {
	/**
	 * 根据条件查询(换物关系表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<CommunityExchangeRelation> selectCommunityExchangeRelationByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(换物关系表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<CommunityExchangeRelation> selectCommunityExchangeRelationByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(换物关系表)信息
	 * @param id
	 * @return
	 */
	public CommunityExchangeRelation selectCommunityExchangeRelationBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(换物关系表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectCommunityExchangeRelationCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(换物关系表)新增一条记录
	 * @param communityExchangeRelation
	 * @return
	 */
	public int insertCommunityExchangeRelation(CommunityExchangeRelation communityExchangeRelation);
	
	/**
	 * 批量新增(换物关系表)信息
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
	 * 根据Id 批量删除(换物关系表)信息_逻辑删除
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
