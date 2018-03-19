package com.cnfantasia.server.domainbase.appCrashLog.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.appCrashLog.dao.IAppCrashLogBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.appCrashLog.entity.AppCrashLog;

/**
 * 描述:(app崩溃日志) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class AppCrashLogBaseService implements IAppCrashLogBaseService{
	
	private IAppCrashLogBaseDao appCrashLogBaseDao;
	public void setAppCrashLogBaseDao(IAppCrashLogBaseDao appCrashLogBaseDao) {
		this.appCrashLogBaseDao = appCrashLogBaseDao;
	}
	/**
	 * 根据条件查询(app崩溃日志)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<AppCrashLog> getAppCrashLogByCondition(Map<String,Object> paramMap){
		return appCrashLogBaseDao.selectAppCrashLogByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(app崩溃日志)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<AppCrashLog> getAppCrashLogByConditionDim(Map<String,Object> paramMap){
		return appCrashLogBaseDao.selectAppCrashLogByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(app崩溃日志)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<AppCrashLog> getAppCrashLogByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return appCrashLogBaseDao.selectAppCrashLogByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(app崩溃日志)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<AppCrashLog> getAppCrashLogByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return appCrashLogBaseDao.selectAppCrashLogByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(app崩溃日志)信息
	 * @param id
	 * @return
	 */
	@Override
	public AppCrashLog getAppCrashLogBySeqId(java.math.BigInteger id){
		return appCrashLogBaseDao.selectAppCrashLogBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(app崩溃日志)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getAppCrashLogCount(Map<String,Object> paramMap){
		return appCrashLogBaseDao.selectAppCrashLogCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(app崩溃日志)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getAppCrashLogCountDim(Map<String,Object> paramMap){
		return appCrashLogBaseDao.selectAppCrashLogCount(paramMap,true);
	}
	/**
	 * 往(app崩溃日志)新增一条记录
	 * @param appCrashLog
	 * @return
	 */
	@Override
	public int insertAppCrashLog(AppCrashLog appCrashLog){
		return appCrashLogBaseDao.insertAppCrashLog(appCrashLog);
	}
	/**
	 * 批量新增(app崩溃日志)
	 * @param appCrashLogList
	 * @return
	 */
	@Override
	public int insertAppCrashLogBatch(List<AppCrashLog> appCrashLogList){
		return appCrashLogBaseDao.insertAppCrashLogBatch(appCrashLogList);
	}
	/**
	 * 更新(app崩溃日志)信息
	 * @param appCrashLog
	 * @return
	 */
	@Override
	public int updateAppCrashLog(AppCrashLog appCrashLog){
		return appCrashLogBaseDao.updateAppCrashLog(appCrashLog);
	}
	/**
	 * 批量更新(app崩溃日志)信息
	 * @param appCrashLogList
	 * @return
	 */
	@Override
	public int updateAppCrashLogBatch(List<AppCrashLog> appCrashLogList){
		return appCrashLogBaseDao.updateAppCrashLogBatch(appCrashLogList);
	}
	/**
	 * 根据序列号删除(app崩溃日志)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteAppCrashLogLogic(java.math.BigInteger id){
		return appCrashLogBaseDao.deleteAppCrashLogLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(app崩溃日志)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteAppCrashLogLogicBatch(List<java.math.BigInteger> idList){
		return appCrashLogBaseDao.deleteAppCrashLogLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(app崩溃日志)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteAppCrashLog(java.math.BigInteger id){
//		return appCrashLogBaseDao.deleteAppCrashLog(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(app崩溃日志)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteAppCrashLogBatch(List<java.math.BigInteger> idList){
//		return appCrashLogBaseDao.deleteAppCrashLogBatch(idList);
//	}
	
}
