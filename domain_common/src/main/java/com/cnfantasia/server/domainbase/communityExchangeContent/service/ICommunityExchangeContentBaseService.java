package com.cnfantasia.server.domainbase.communityExchangeContent.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.communityExchangeContent.entity.CommunityExchangeContent;

/**
 * 描述:(换物信息表) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ICommunityExchangeContentBaseService {
	
	/**
	 * 根据条件查询(换物信息表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<CommunityExchangeContent> getCommunityExchangeContentByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(换物信息表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<CommunityExchangeContent> getCommunityExchangeContentByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(换物信息表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<CommunityExchangeContent> getCommunityExchangeContentByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(换物信息表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<CommunityExchangeContent> getCommunityExchangeContentByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(换物信息表)信息
	 * @param id
	 * @return
	 */
	public CommunityExchangeContent getCommunityExchangeContentBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(换物信息表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getCommunityExchangeContentCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(换物信息表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getCommunityExchangeContentCountDim(Map<String,Object> paramMap);
	/**
	 * 往(换物信息表)新增一条记录
	 * @param communityExchangeContent
	 * @return
	 */
	public int insertCommunityExchangeContent(CommunityExchangeContent communityExchangeContent);
	/**
	 * 批量新增(换物信息表)
	 * @param communityExchangeContentList
	 * @return
	 */
	public int insertCommunityExchangeContentBatch(List<CommunityExchangeContent> communityExchangeContentList);
	/**
	 * 更新(换物信息表)信息
	 * @param communityExchangeContent
	 * @return
	 */
	public int updateCommunityExchangeContent(CommunityExchangeContent communityExchangeContent);
	/**
	 * 批量更新(换物信息表)信息
	 * @param communityExchangeContentList
	 * @return
	 */
	public int updateCommunityExchangeContentBatch(List<CommunityExchangeContent> communityExchangeContentList);
	/**
	 * 根据序列号删除(换物信息表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteCommunityExchangeContentLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(换物信息表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteCommunityExchangeContentLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(换物信息表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteCommunityExchangeContent(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(换物信息表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteCommunityExchangeContentBatch(List<java.math.BigInteger> idList);
	
}
