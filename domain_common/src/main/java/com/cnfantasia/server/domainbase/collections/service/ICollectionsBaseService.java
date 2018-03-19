package com.cnfantasia.server.domainbase.collections.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.collections.entity.Collections;

/**
 * 描述:(收藏) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ICollectionsBaseService {
	
	/**
	 * 根据条件查询(收藏)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<Collections> getCollectionsByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(收藏)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<Collections> getCollectionsByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(收藏)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<Collections> getCollectionsByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(收藏)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<Collections> getCollectionsByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(收藏)信息
	 * @param id
	 * @return
	 */
	public Collections getCollectionsBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(收藏)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getCollectionsCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(收藏)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getCollectionsCountDim(Map<String,Object> paramMap);
	/**
	 * 往(收藏)新增一条记录
	 * @param collections
	 * @return
	 */
	public int insertCollections(Collections collections);
	/**
	 * 批量新增(收藏)
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
	 * 根据序列号批量删除(收藏)信息_逻辑删除
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
