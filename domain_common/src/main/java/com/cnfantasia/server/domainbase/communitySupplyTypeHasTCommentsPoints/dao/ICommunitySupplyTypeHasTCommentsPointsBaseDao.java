package com.cnfantasia.server.domainbase.communitySupplyTypeHasTCommentsPoints.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.communitySupplyTypeHasTCommentsPoints.entity.CommunitySupplyTypeHasTCommentsPoints;

/**
 * 描述:(商家类别支持哪些评分) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ICommunitySupplyTypeHasTCommentsPointsBaseDao {
	/**
	 * 根据条件查询(商家类别支持哪些评分)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<CommunitySupplyTypeHasTCommentsPoints> selectCommunitySupplyTypeHasTCommentsPointsByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(商家类别支持哪些评分)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<CommunitySupplyTypeHasTCommentsPoints> selectCommunitySupplyTypeHasTCommentsPointsByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(商家类别支持哪些评分)信息
	 * @param id
	 * @return
	 */
	public CommunitySupplyTypeHasTCommentsPoints selectCommunitySupplyTypeHasTCommentsPointsBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(商家类别支持哪些评分)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectCommunitySupplyTypeHasTCommentsPointsCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(商家类别支持哪些评分)新增一条记录
	 * @param communitySupplyTypeHasTCommentsPoints
	 * @return
	 */
	public int insertCommunitySupplyTypeHasTCommentsPoints(CommunitySupplyTypeHasTCommentsPoints communitySupplyTypeHasTCommentsPoints);
	
	/**
	 * 批量新增(商家类别支持哪些评分)信息
	 * @param communitySupplyTypeHasTCommentsPointsList
	 * @return
	 */
	public int insertCommunitySupplyTypeHasTCommentsPointsBatch(List<CommunitySupplyTypeHasTCommentsPoints> communitySupplyTypeHasTCommentsPointsList);
	
	/**
	 * 更新(商家类别支持哪些评分)信息
	 * @param communitySupplyTypeHasTCommentsPoints
	 * @return
	 */
	public int updateCommunitySupplyTypeHasTCommentsPoints(CommunitySupplyTypeHasTCommentsPoints communitySupplyTypeHasTCommentsPoints);
	
	/**
	 * 批量更新(商家类别支持哪些评分)信息
	 * @param communitySupplyTypeHasTCommentsPointsList
	 * @return
	 */
	public int updateCommunitySupplyTypeHasTCommentsPointsBatch(List<CommunitySupplyTypeHasTCommentsPoints> communitySupplyTypeHasTCommentsPointsList);
	
	/**
	 * 根据序列号删除(商家类别支持哪些评分)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteCommunitySupplyTypeHasTCommentsPointsLogic(java.math.BigInteger id);
	
	/**
	 * 根据Id 批量删除(商家类别支持哪些评分)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteCommunitySupplyTypeHasTCommentsPointsLogicBatch(List<java.math.BigInteger> idList);
	
	
//	/**
//	 * 根据序列号删除(商家类别支持哪些评分)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteCommunitySupplyTypeHasTCommentsPoints(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(商家类别支持哪些评分)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteCommunitySupplyTypeHasTCommentsPointsBatch(List<java.math.BigInteger> idList);
	
	
}
