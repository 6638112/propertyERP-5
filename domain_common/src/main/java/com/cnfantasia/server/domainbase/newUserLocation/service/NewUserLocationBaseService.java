package com.cnfantasia.server.domainbase.newUserLocation.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.newUserLocation.dao.INewUserLocationBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.newUserLocation.entity.NewUserLocation;

/**
 * 描述:(新用户第一次打开app定位结果信息) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class NewUserLocationBaseService implements INewUserLocationBaseService{
	
	private INewUserLocationBaseDao newUserLocationBaseDao;
	public void setNewUserLocationBaseDao(INewUserLocationBaseDao newUserLocationBaseDao) {
		this.newUserLocationBaseDao = newUserLocationBaseDao;
	}
	/**
	 * 根据条件查询(新用户第一次打开app定位结果信息)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<NewUserLocation> getNewUserLocationByCondition(Map<String,Object> paramMap){
		return newUserLocationBaseDao.selectNewUserLocationByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(新用户第一次打开app定位结果信息)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<NewUserLocation> getNewUserLocationByConditionDim(Map<String,Object> paramMap){
		return newUserLocationBaseDao.selectNewUserLocationByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(新用户第一次打开app定位结果信息)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<NewUserLocation> getNewUserLocationByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return newUserLocationBaseDao.selectNewUserLocationByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(新用户第一次打开app定位结果信息)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<NewUserLocation> getNewUserLocationByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return newUserLocationBaseDao.selectNewUserLocationByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(新用户第一次打开app定位结果信息)信息
	 * @param id
	 * @return
	 */
	@Override
	public NewUserLocation getNewUserLocationBySeqId(java.math.BigInteger id){
		return newUserLocationBaseDao.selectNewUserLocationBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(新用户第一次打开app定位结果信息)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getNewUserLocationCount(Map<String,Object> paramMap){
		return newUserLocationBaseDao.selectNewUserLocationCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(新用户第一次打开app定位结果信息)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getNewUserLocationCountDim(Map<String,Object> paramMap){
		return newUserLocationBaseDao.selectNewUserLocationCount(paramMap,true);
	}
	/**
	 * 往(新用户第一次打开app定位结果信息)新增一条记录
	 * @param newUserLocation
	 * @return
	 */
	@Override
	public int insertNewUserLocation(NewUserLocation newUserLocation){
		return newUserLocationBaseDao.insertNewUserLocation(newUserLocation);
	}
	/**
	 * 批量新增(新用户第一次打开app定位结果信息)
	 * @param newUserLocationList
	 * @return
	 */
	@Override
	public int insertNewUserLocationBatch(List<NewUserLocation> newUserLocationList){
		return newUserLocationBaseDao.insertNewUserLocationBatch(newUserLocationList);
	}
	/**
	 * 更新(新用户第一次打开app定位结果信息)信息
	 * @param newUserLocation
	 * @return
	 */
	@Override
	public int updateNewUserLocation(NewUserLocation newUserLocation){
		return newUserLocationBaseDao.updateNewUserLocation(newUserLocation);
	}
	/**
	 * 批量更新(新用户第一次打开app定位结果信息)信息
	 * @param newUserLocationList
	 * @return
	 */
	@Override
	public int updateNewUserLocationBatch(List<NewUserLocation> newUserLocationList){
		return newUserLocationBaseDao.updateNewUserLocationBatch(newUserLocationList);
	}
	/**
	 * 根据序列号删除(新用户第一次打开app定位结果信息)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteNewUserLocationLogic(java.math.BigInteger id){
		return newUserLocationBaseDao.deleteNewUserLocationLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(新用户第一次打开app定位结果信息)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteNewUserLocationLogicBatch(List<java.math.BigInteger> idList){
		return newUserLocationBaseDao.deleteNewUserLocationLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(新用户第一次打开app定位结果信息)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteNewUserLocation(java.math.BigInteger id){
//		return newUserLocationBaseDao.deleteNewUserLocation(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(新用户第一次打开app定位结果信息)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteNewUserLocationBatch(List<java.math.BigInteger> idList){
//		return newUserLocationBaseDao.deleteNewUserLocationBatch(idList);
//	}
	
}
