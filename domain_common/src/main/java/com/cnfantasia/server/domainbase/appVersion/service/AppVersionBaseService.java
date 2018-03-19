package com.cnfantasia.server.domainbase.appVersion.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.appVersion.dao.IAppVersionBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.appVersion.entity.AppVersion;

/**
 * 描述:(应用版本信息) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class AppVersionBaseService implements IAppVersionBaseService{
	
	private IAppVersionBaseDao appVersionBaseDao;
	public void setAppVersionBaseDao(IAppVersionBaseDao appVersionBaseDao) {
		this.appVersionBaseDao = appVersionBaseDao;
	}
	/**
	 * 根据条件查询(应用版本信息)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<AppVersion> getAppVersionByCondition(Map<String,Object> paramMap){
		return appVersionBaseDao.selectAppVersionByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(应用版本信息)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<AppVersion> getAppVersionByConditionDim(Map<String,Object> paramMap){
		return appVersionBaseDao.selectAppVersionByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(应用版本信息)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<AppVersion> getAppVersionByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return appVersionBaseDao.selectAppVersionByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(应用版本信息)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<AppVersion> getAppVersionByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return appVersionBaseDao.selectAppVersionByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(应用版本信息)信息
	 * @param id
	 * @return
	 */
	@Override
	public AppVersion getAppVersionBySeqId(java.math.BigInteger id){
		return appVersionBaseDao.selectAppVersionBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(应用版本信息)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getAppVersionCount(Map<String,Object> paramMap){
		return appVersionBaseDao.selectAppVersionCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(应用版本信息)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getAppVersionCountDim(Map<String,Object> paramMap){
		return appVersionBaseDao.selectAppVersionCount(paramMap,true);
	}
	/**
	 * 往(应用版本信息)新增一条记录
	 * @param appVersion
	 * @return
	 */
	@Override
	public int insertAppVersion(AppVersion appVersion){
		return appVersionBaseDao.insertAppVersion(appVersion);
	}
	/**
	 * 批量新增(应用版本信息)
	 * @param appVersionList
	 * @return
	 */
	@Override
	public int insertAppVersionBatch(List<AppVersion> appVersionList){
		return appVersionBaseDao.insertAppVersionBatch(appVersionList);
	}
	/**
	 * 更新(应用版本信息)信息
	 * @param appVersion
	 * @return
	 */
	@Override
	public int updateAppVersion(AppVersion appVersion){
		return appVersionBaseDao.updateAppVersion(appVersion);
	}
	/**
	 * 批量更新(应用版本信息)信息
	 * @param appVersionList
	 * @return
	 */
	@Override
	public int updateAppVersionBatch(List<AppVersion> appVersionList){
		return appVersionBaseDao.updateAppVersionBatch(appVersionList);
	}
	/**
	 * 根据序列号删除(应用版本信息)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteAppVersionLogic(java.math.BigInteger id){
		return appVersionBaseDao.deleteAppVersionLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(应用版本信息)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteAppVersionLogicBatch(List<java.math.BigInteger> idList){
		return appVersionBaseDao.deleteAppVersionLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(应用版本信息)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteAppVersion(java.math.BigInteger id){
//		return appVersionBaseDao.deleteAppVersion(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(应用版本信息)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteAppVersionBatch(List<java.math.BigInteger> idList){
//		return appVersionBaseDao.deleteAppVersionBatch(idList);
//	}
	
}
