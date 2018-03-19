package com.cnfantasia.server.domainbase.communityExchangeWanted.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.communityExchangeWanted.entity.CommunityExchangeWanted;

/**
 * 描述:(换一换之跪求换) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ICommunityExchangeWantedBaseService {
	
	/**
	 * 根据条件查询(换一换之跪求换)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<CommunityExchangeWanted> getCommunityExchangeWantedByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(换一换之跪求换)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<CommunityExchangeWanted> getCommunityExchangeWantedByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(换一换之跪求换)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<CommunityExchangeWanted> getCommunityExchangeWantedByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(换一换之跪求换)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<CommunityExchangeWanted> getCommunityExchangeWantedByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(换一换之跪求换)信息
	 * @param id
	 * @return
	 */
	public CommunityExchangeWanted getCommunityExchangeWantedBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(换一换之跪求换)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getCommunityExchangeWantedCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(换一换之跪求换)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getCommunityExchangeWantedCountDim(Map<String,Object> paramMap);
	/**
	 * 往(换一换之跪求换)新增一条记录
	 * @param communityExchangeWanted
	 * @return
	 */
	public int insertCommunityExchangeWanted(CommunityExchangeWanted communityExchangeWanted);
	/**
	 * 批量新增(换一换之跪求换)
	 * @param communityExchangeWantedList
	 * @return
	 */
	public int insertCommunityExchangeWantedBatch(List<CommunityExchangeWanted> communityExchangeWantedList);
	/**
	 * 更新(换一换之跪求换)信息
	 * @param communityExchangeWanted
	 * @return
	 */
	public int updateCommunityExchangeWanted(CommunityExchangeWanted communityExchangeWanted);
	/**
	 * 批量更新(换一换之跪求换)信息
	 * @param communityExchangeWantedList
	 * @return
	 */
	public int updateCommunityExchangeWantedBatch(List<CommunityExchangeWanted> communityExchangeWantedList);
	/**
	 * 根据序列号删除(换一换之跪求换)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteCommunityExchangeWantedLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(换一换之跪求换)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteCommunityExchangeWantedLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(换一换之跪求换)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteCommunityExchangeWanted(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(换一换之跪求换)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteCommunityExchangeWantedBatch(List<java.math.BigInteger> idList);
	
}
