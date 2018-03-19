package com.cnfantasia.server.domainbase.collections.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.collections.dao.ICollectionsBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.collections.entity.Collections;

/**
 * 描述:(收藏) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class CollectionsBaseService implements ICollectionsBaseService{
	
	private ICollectionsBaseDao collectionsBaseDao;
	public void setCollectionsBaseDao(ICollectionsBaseDao collectionsBaseDao) {
		this.collectionsBaseDao = collectionsBaseDao;
	}
	/**
	 * 根据条件查询(收藏)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<Collections> getCollectionsByCondition(Map<String,Object> paramMap){
		return collectionsBaseDao.selectCollectionsByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(收藏)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<Collections> getCollectionsByConditionDim(Map<String,Object> paramMap){
		return collectionsBaseDao.selectCollectionsByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(收藏)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<Collections> getCollectionsByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return collectionsBaseDao.selectCollectionsByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(收藏)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<Collections> getCollectionsByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return collectionsBaseDao.selectCollectionsByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(收藏)信息
	 * @param id
	 * @return
	 */
	@Override
	public Collections getCollectionsBySeqId(java.math.BigInteger id){
		return collectionsBaseDao.selectCollectionsBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(收藏)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getCollectionsCount(Map<String,Object> paramMap){
		return collectionsBaseDao.selectCollectionsCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(收藏)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getCollectionsCountDim(Map<String,Object> paramMap){
		return collectionsBaseDao.selectCollectionsCount(paramMap,true);
	}
	/**
	 * 往(收藏)新增一条记录
	 * @param collections
	 * @return
	 */
	@Override
	public int insertCollections(Collections collections){
		return collectionsBaseDao.insertCollections(collections);
	}
	/**
	 * 批量新增(收藏)
	 * @param collectionsList
	 * @return
	 */
	@Override
	public int insertCollectionsBatch(List<Collections> collectionsList){
		return collectionsBaseDao.insertCollectionsBatch(collectionsList);
	}
	/**
	 * 更新(收藏)信息
	 * @param collections
	 * @return
	 */
	@Override
	public int updateCollections(Collections collections){
		return collectionsBaseDao.updateCollections(collections);
	}
	/**
	 * 批量更新(收藏)信息
	 * @param collectionsList
	 * @return
	 */
	@Override
	public int updateCollectionsBatch(List<Collections> collectionsList){
		return collectionsBaseDao.updateCollectionsBatch(collectionsList);
	}
	/**
	 * 根据序列号删除(收藏)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteCollectionsLogic(java.math.BigInteger id){
		return collectionsBaseDao.deleteCollectionsLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(收藏)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteCollectionsLogicBatch(List<java.math.BigInteger> idList){
		return collectionsBaseDao.deleteCollectionsLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(收藏)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteCollections(java.math.BigInteger id){
//		return collectionsBaseDao.deleteCollections(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(收藏)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteCollectionsBatch(List<java.math.BigInteger> idList){
//		return collectionsBaseDao.deleteCollectionsBatch(idList);
//	}
	
}
