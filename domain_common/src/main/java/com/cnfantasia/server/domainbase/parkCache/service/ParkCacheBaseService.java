package com.cnfantasia.server.domainbase.parkCache.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.parkCache.dao.IParkCacheBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.parkCache.entity.ParkCache;

/**
 * 描述:(缓存的socket数据) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class ParkCacheBaseService implements IParkCacheBaseService{
	
	private IParkCacheBaseDao parkCacheBaseDao;
	public void setParkCacheBaseDao(IParkCacheBaseDao parkCacheBaseDao) {
		this.parkCacheBaseDao = parkCacheBaseDao;
	}
	/**
	 * 根据条件查询(缓存的socket数据)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<ParkCache> getParkCacheByCondition(Map<String,Object> paramMap){
		return parkCacheBaseDao.selectParkCacheByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(缓存的socket数据)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<ParkCache> getParkCacheByConditionDim(Map<String,Object> paramMap){
		return parkCacheBaseDao.selectParkCacheByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(缓存的socket数据)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<ParkCache> getParkCacheByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return parkCacheBaseDao.selectParkCacheByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(缓存的socket数据)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<ParkCache> getParkCacheByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return parkCacheBaseDao.selectParkCacheByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(缓存的socket数据)信息
	 * @param id
	 * @return
	 */
	@Override
	public ParkCache getParkCacheBySeqId(java.math.BigInteger id){
		return parkCacheBaseDao.selectParkCacheBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(缓存的socket数据)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getParkCacheCount(Map<String,Object> paramMap){
		return parkCacheBaseDao.selectParkCacheCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(缓存的socket数据)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getParkCacheCountDim(Map<String,Object> paramMap){
		return parkCacheBaseDao.selectParkCacheCount(paramMap,true);
	}
	/**
	 * 往(缓存的socket数据)新增一条记录
	 * @param parkCache
	 * @return
	 */
	@Override
	public int insertParkCache(ParkCache parkCache){
		return parkCacheBaseDao.insertParkCache(parkCache);
	}
	/**
	 * 批量新增(缓存的socket数据)
	 * @param parkCacheList
	 * @return
	 */
	@Override
	public int insertParkCacheBatch(List<ParkCache> parkCacheList){
		return parkCacheBaseDao.insertParkCacheBatch(parkCacheList);
	}
	/**
	 * 更新(缓存的socket数据)信息
	 * @param parkCache
	 * @return
	 */
	@Override
	public int updateParkCache(ParkCache parkCache){
		return parkCacheBaseDao.updateParkCache(parkCache);
	}
	/**
	 * 批量更新(缓存的socket数据)信息
	 * @param parkCacheList
	 * @return
	 */
	@Override
	public int updateParkCacheBatch(List<ParkCache> parkCacheList){
		return parkCacheBaseDao.updateParkCacheBatch(parkCacheList);
	}
	/**
	 * 根据序列号删除(缓存的socket数据)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteParkCacheLogic(java.math.BigInteger id){
		return parkCacheBaseDao.deleteParkCacheLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(缓存的socket数据)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteParkCacheLogicBatch(List<java.math.BigInteger> idList){
		return parkCacheBaseDao.deleteParkCacheLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(缓存的socket数据)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteParkCache(java.math.BigInteger id){
//		return parkCacheBaseDao.deleteParkCache(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(缓存的socket数据)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteParkCacheBatch(List<java.math.BigInteger> idList){
//		return parkCacheBaseDao.deleteParkCacheBatch(idList);
//	}
	
}
