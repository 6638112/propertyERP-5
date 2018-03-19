package com.cnfantasia.server.domainbase.collections.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.collections.entity.Collections;

/**
 * 描述:(收藏) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ICollectionsBaseDao {
	/**
	 * 根据条件查询(收藏)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<Collections> selectCollectionsByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(收藏)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<Collections> selectCollectionsByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(收藏)信息
	 * @param id
	 * @return
	 */
	public Collections selectCollectionsBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(收藏)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectCollectionsCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(收藏)新增一条记录
	 * @param collections
	 * @return
	 */
	public int insertCollections(Collections collections);
	
	/**
	 * 批量新增(收藏)信息
	 * @param collectionsList
	 * @return
	 */
	public int insertCollectionsBatch(List<Collections> collectionsList);
	
	/**
	 * 更新(收藏)信息
	 * @param collections
	 * @return
	 */
	public int updateCollections(Collections collections);
	
	/**
	 * 批量更新(收藏)信息
	 * @param collectionsList
	 * @return
	 */
	public int updateCollectionsBatch(List<Collections> collectionsList);
	
	/**
	 * 根据序列号删除(收藏)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteCollectionsLogic(java.math.BigInteger id);
	
	/**
	 * 根据Id 批量删除(收藏)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteCollectionsLogicBatch(List<java.math.BigInteger> idList);
	
	
//	/**
//	 * 根据序列号删除(收藏)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteCollections(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(收藏)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteCollectionsBatch(List<java.math.BigInteger> idList);
	
	
}
