package com.cnfantasia.server.domainbase.parkCache.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.parkCache.entity.ParkCache;

/**
 * 描述:(缓存的socket数据) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IParkCacheBaseService {
	
	/**
	 * 根据条件查询(缓存的socket数据)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<ParkCache> getParkCacheByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(缓存的socket数据)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<ParkCache> getParkCacheByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(缓存的socket数据)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<ParkCache> getParkCacheByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(缓存的socket数据)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<ParkCache> getParkCacheByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(缓存的socket数据)信息
	 * @param id
	 * @return
	 */
	public ParkCache getParkCacheBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(缓存的socket数据)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getParkCacheCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(缓存的socket数据)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getParkCacheCountDim(Map<String,Object> paramMap);
	/**
	 * 往(缓存的socket数据)新增一条记录
	 * @param parkCache
	 * @return
	 */
	public int insertParkCache(ParkCache parkCache);
	/**
	 * 批量新增(缓存的socket数据)
	 * @param parkCacheList
	 * @return
	 */
	public int insertParkCacheBatch(List<ParkCache> parkCacheList);
	/**
	 * 更新(缓存的socket数据)信息
	 * @param parkCache
	 * @return
	 */
	public int updateParkCache(ParkCache parkCache);
	/**
	 * 批量更新(缓存的socket数据)信息
	 * @param parkCacheList
	 * @return
	 */
	public int updateParkCacheBatch(List<ParkCache> parkCacheList);
	/**
	 * 根据序列号删除(缓存的socket数据)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteParkCacheLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(缓存的socket数据)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteParkCacheLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(缓存的socket数据)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteParkCache(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(缓存的socket数据)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteParkCacheBatch(List<java.math.BigInteger> idList);
	
}
