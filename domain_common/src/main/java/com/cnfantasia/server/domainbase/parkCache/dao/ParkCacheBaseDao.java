package com.cnfantasia.server.domainbase.parkCache.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.parkCache.entity.ParkCache;

/**
 * 描述:(缓存的socket数据) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class ParkCacheBaseDao extends AbstractBaseDao implements IParkCacheBaseDao{
	/**
	 * 根据条件查询(缓存的socket数据)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<ParkCache> selectParkCacheByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("parkCacheBase.select_parkCache",paramMap);
	}
	/**
	 * 按条件分页查询(缓存的socket数据)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<ParkCache> selectParkCacheByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectParkCacheCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<ParkCache> resMap= sqlSession.selectList("parkCacheBase.select_parkCache_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(缓存的socket数据)信息
	 * @param id
	 * @return
	 */
	@Override
	public ParkCache selectParkCacheBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("parkCacheBase.select_parkCache_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(缓存的socket数据)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectParkCacheCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("parkCacheBase.select_parkCache_count", paramMap);
	}
	/**
	 * 往(缓存的socket数据)新增一条记录
	 * @param parkCache
	 * @return
	 */
	@Override
	public int insertParkCache(ParkCache parkCache){
		return sqlSession.insert("parkCacheBase.insert_parkCache",parkCache);
	}
	/**
	 * 批量新增(缓存的socket数据)信息
	 * @param parkCacheList
	 * @return
	 */
	@Override
	public int insertParkCacheBatch(List<ParkCache> parkCacheList) {
		return sqlSession.insert("parkCacheBase.insert_parkCache_Batch",parkCacheList);
	}
	
	/**
	 * 更新(缓存的socket数据)信息
	 * @param parkCache
	 * @return
	 */
	@Override
	public int updateParkCache(ParkCache parkCache){
		return sqlSession.update("parkCacheBase.update_parkCache", parkCache);
	}
	/**
	 * 批量更新(缓存的socket数据)信息
	 * @param parkCacheList
	 * @return
	 */
	@Override
	public int updateParkCacheBatch(List<ParkCache> parkCacheList) {
		return sqlSession.update("parkCacheBase.update_parkCache_Batch", parkCacheList);
	}
	
	/**
	 * 根据序列号删除(缓存的socket数据)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteParkCacheLogic(java.math.BigInteger id){
		ParkCache parkCache = new ParkCache();
		parkCache.setId(id);
		parkCache.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("parkCacheBase.delete_parkCache_Logic",parkCache);
	}
	
	/**
	 * 根据Id 批量删除(缓存的socket数据)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteParkCacheLogicBatch(List<java.math.BigInteger> idList) {
		List<ParkCache> delList = new java.util.ArrayList<ParkCache>();
		for(java.math.BigInteger id:idList){
			ParkCache parkCache = new ParkCache();
			parkCache.setId(id);
			parkCache.setSys0DelState(RecordState_DELETED);
			delList.add(parkCache);
		}
		return sqlSession.update("parkCacheBase.delete_parkCache_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(缓存的socket数据)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteParkCache(java.math.BigInteger id){
//		return sqlSession.delete("parkCacheBase.delete_parkCache", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(缓存的socket数据)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteParkCacheBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("parkCacheBase.delete_parkCache_Batch", idList);
//	}
	
	
}
