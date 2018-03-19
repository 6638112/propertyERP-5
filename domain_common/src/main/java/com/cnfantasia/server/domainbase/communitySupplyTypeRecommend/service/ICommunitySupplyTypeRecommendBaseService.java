package com.cnfantasia.server.domainbase.communitySupplyTypeRecommend.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.communitySupplyTypeRecommend.entity.CommunitySupplyTypeRecommend;

/**
 * 描述:(推荐的商家类别) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ICommunitySupplyTypeRecommendBaseService {
	
	/**
	 * 根据条件查询(推荐的商家类别)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<CommunitySupplyTypeRecommend> getCommunitySupplyTypeRecommendByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(推荐的商家类别)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<CommunitySupplyTypeRecommend> getCommunitySupplyTypeRecommendByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(推荐的商家类别)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<CommunitySupplyTypeRecommend> getCommunitySupplyTypeRecommendByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(推荐的商家类别)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<CommunitySupplyTypeRecommend> getCommunitySupplyTypeRecommendByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(推荐的商家类别)信息
	 * @param id
	 * @return
	 */
	public CommunitySupplyTypeRecommend getCommunitySupplyTypeRecommendBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(推荐的商家类别)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getCommunitySupplyTypeRecommendCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(推荐的商家类别)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getCommunitySupplyTypeRecommendCountDim(Map<String,Object> paramMap);
	/**
	 * 往(推荐的商家类别)新增一条记录
	 * @param communitySupplyTypeRecommend
	 * @return
	 */
	public int insertCommunitySupplyTypeRecommend(CommunitySupplyTypeRecommend communitySupplyTypeRecommend);
	/**
	 * 批量新增(推荐的商家类别)
	 * @param communitySupplyTypeRecommendList
	 * @return
	 */
	public int insertCommunitySupplyTypeRecommendBatch(List<CommunitySupplyTypeRecommend> communitySupplyTypeRecommendList);
	/**
	 * 更新(推荐的商家类别)信息
	 * @param communitySupplyTypeRecommend
	 * @return
	 */
	public int updateCommunitySupplyTypeRecommend(CommunitySupplyTypeRecommend communitySupplyTypeRecommend);
	/**
	 * 批量更新(推荐的商家类别)信息
	 * @param communitySupplyTypeRecommendList
	 * @return
	 */
	public int updateCommunitySupplyTypeRecommendBatch(List<CommunitySupplyTypeRecommend> communitySupplyTypeRecommendList);
	/**
	 * 根据序列号删除(推荐的商家类别)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteCommunitySupplyTypeRecommendLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(推荐的商家类别)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteCommunitySupplyTypeRecommendLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(推荐的商家类别)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteCommunitySupplyTypeRecommend(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(推荐的商家类别)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteCommunitySupplyTypeRecommendBatch(List<java.math.BigInteger> idList);
	
}
