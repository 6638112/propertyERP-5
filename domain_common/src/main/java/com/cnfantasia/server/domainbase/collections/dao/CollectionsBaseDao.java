package com.cnfantasia.server.domainbase.collections.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.collections.entity.Collections;

/**
 * 描述:(收藏) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class CollectionsBaseDao extends AbstractBaseDao implements ICollectionsBaseDao{
	/**
	 * 根据条件查询(收藏)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<Collections> selectCollectionsByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("collectionsBase.select_collections",paramMap);
	}
	/**
	 * 按条件分页查询(收藏)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<Collections> selectCollectionsByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectCollectionsCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<Collections> resMap= sqlSession.selectList("collectionsBase.select_collections_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(收藏)信息
	 * @param id
	 * @return
	 */
	@Override
	public Collections selectCollectionsBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("collectionsBase.select_collections_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(收藏)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectCollectionsCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("collectionsBase.select_collections_count", paramMap);
	}
	/**
	 * 往(收藏)新增一条记录
	 * @param collections
	 * @return
	 */
	@Override
	public int insertCollections(Collections collections){
		return sqlSession.insert("collectionsBase.insert_collections",collections);
	}
	/**
	 * 批量新增(收藏)信息
	 * @param collectionsList
	 * @return
	 */
	@Override
	public int insertCollectionsBatch(List<Collections> collectionsList) {
		return sqlSession.insert("collectionsBase.insert_collections_Batch",collectionsList);
	}
	
	/**
	 * 更新(收藏)信息
	 * @param collections
	 * @return
	 */
	@Override
	public int updateCollections(Collections collections){
		return sqlSession.update("collectionsBase.update_collections", collections);
	}
	/**
	 * 批量更新(收藏)信息
	 * @param collectionsList
	 * @return
	 */
	@Override
	public int updateCollectionsBatch(List<Collections> collectionsList) {
		return sqlSession.update("collectionsBase.update_collections_Batch", collectionsList);
	}
	
	/**
	 * 根据序列号删除(收藏)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteCollectionsLogic(java.math.BigInteger id){
		Collections collections = new Collections();
		collections.setId(id);
		collections.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("collectionsBase.delete_collections_Logic",collections);
	}
	
	/**
	 * 根据Id 批量删除(收藏)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteCollectionsLogicBatch(List<java.math.BigInteger> idList) {
		List<Collections> delList = new java.util.ArrayList<Collections>();
		for(java.math.BigInteger id:idList){
			Collections collections = new Collections();
			collections.setId(id);
			collections.setSys0DelState(RecordState_DELETED);
			delList.add(collections);
		}
		return sqlSession.update("collectionsBase.delete_collections_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(收藏)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteCollections(java.math.BigInteger id){
//		return sqlSession.delete("collectionsBase.delete_collections", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(收藏)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteCollectionsBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("collectionsBase.delete_collections_Batch", idList);
//	}
	
	
}
