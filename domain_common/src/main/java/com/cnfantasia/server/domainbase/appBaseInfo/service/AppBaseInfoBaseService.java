package com.cnfantasia.server.domainbase.appBaseInfo.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.appBaseInfo.dao.IAppBaseInfoBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.appBaseInfo.entity.AppBaseInfo;

/**
 * 描述:(应用信息) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class AppBaseInfoBaseService implements IAppBaseInfoBaseService{
	
	private IAppBaseInfoBaseDao appBaseInfoBaseDao;
	public void setAppBaseInfoBaseDao(IAppBaseInfoBaseDao appBaseInfoBaseDao) {
		this.appBaseInfoBaseDao = appBaseInfoBaseDao;
	}
	/**
	 * 根据条件查询(应用信息)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<AppBaseInfo> getAppBaseInfoByCondition(Map<String,Object> paramMap){
		return appBaseInfoBaseDao.selectAppBaseInfoByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(应用信息)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<AppBaseInfo> getAppBaseInfoByConditionDim(Map<String,Object> paramMap){
		return appBaseInfoBaseDao.selectAppBaseInfoByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(应用信息)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<AppBaseInfo> getAppBaseInfoByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return appBaseInfoBaseDao.selectAppBaseInfoByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(应用信息)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<AppBaseInfo> getAppBaseInfoByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return appBaseInfoBaseDao.selectAppBaseInfoByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(应用信息)信息
	 * @param id
	 * @return
	 */
	@Override
	public AppBaseInfo getAppBaseInfoBySeqId(java.math.BigInteger id){
		return appBaseInfoBaseDao.selectAppBaseInfoBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(应用信息)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getAppBaseInfoCount(Map<String,Object> paramMap){
		return appBaseInfoBaseDao.selectAppBaseInfoCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(应用信息)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getAppBaseInfoCountDim(Map<String,Object> paramMap){
		return appBaseInfoBaseDao.selectAppBaseInfoCount(paramMap,true);
	}
	/**
	 * 往(应用信息)新增一条记录
	 * @param appBaseInfo
	 * @return
	 */
	@Override
	public int insertAppBaseInfo(AppBaseInfo appBaseInfo){
		return appBaseInfoBaseDao.insertAppBaseInfo(appBaseInfo);
	}
	/**
	 * 批量新增(应用信息)
	 * @param appBaseInfoList
	 * @return
	 */
	@Override
	public int insertAppBaseInfoBatch(List<AppBaseInfo> appBaseInfoList){
		return appBaseInfoBaseDao.insertAppBaseInfoBatch(appBaseInfoList);
	}
	/**
	 * 更新(应用信息)信息
	 * @param appBaseInfo
	 * @return
	 */
	@Override
	public int updateAppBaseInfo(AppBaseInfo appBaseInfo){
		return appBaseInfoBaseDao.updateAppBaseInfo(appBaseInfo);
	}
	/**
	 * 批量更新(应用信息)信息
	 * @param appBaseInfoList
	 * @return
	 */
	@Override
	public int updateAppBaseInfoBatch(List<AppBaseInfo> appBaseInfoList){
		return appBaseInfoBaseDao.updateAppBaseInfoBatch(appBaseInfoList);
	}
	/**
	 * 根据序列号删除(应用信息)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteAppBaseInfoLogic(java.math.BigInteger id){
		return appBaseInfoBaseDao.deleteAppBaseInfoLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(应用信息)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteAppBaseInfoLogicBatch(List<java.math.BigInteger> idList){
		return appBaseInfoBaseDao.deleteAppBaseInfoLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(应用信息)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteAppBaseInfo(java.math.BigInteger id){
//		return appBaseInfoBaseDao.deleteAppBaseInfo(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(应用信息)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteAppBaseInfoBatch(List<java.math.BigInteger> idList){
//		return appBaseInfoBaseDao.deleteAppBaseInfoBatch(idList);
//	}
	
}
