package com.cnfantasia.server.domainbase.communitySupplyTypeRecommend.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.communitySupplyTypeRecommend.entity.CommunitySupplyTypeRecommend;

/**
 * 描述:(推荐的商家类别) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ICommunitySupplyTypeRecommendBaseDao {
	/**
	 * 根据条件查询(推荐的商家类别)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<CommunitySupplyTypeRecommend> selectCommunitySupplyTypeRecommendByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(推荐的商家类别)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<CommunitySupplyTypeRecommend> selectCommunitySupplyTypeRecommendByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(推荐的商家类别)信息
	 * @param id
	 * @return
	 */
	public CommunitySupplyTypeRecommend selectCommunitySupplyTypeRecommendBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(推荐的商家类别)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectCommunitySupplyTypeRecommendCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(推荐的商家类别)新增一条记录
	 * @param communitySupplyTypeRecommend
	 * @return
	 */
	public int insertCommunitySupplyTypeRecommend(CommunitySupplyTypeRecommend communitySupplyTypeRecommend);
	
	/**
	 * 批量新增(推荐的商家类别)信息
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
	 * 根据Id 批量删除(推荐的商家类别)信息_逻辑删除
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
