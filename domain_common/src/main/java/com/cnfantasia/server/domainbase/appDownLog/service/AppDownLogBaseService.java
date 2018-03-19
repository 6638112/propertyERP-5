package com.cnfantasia.server.domainbase.appDownLog.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.appDownLog.dao.IAppDownLogBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.appDownLog.entity.AppDownLog;

/**
 * 描述:(应用下载日志) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class AppDownLogBaseService implements IAppDownLogBaseService{
	
	private IAppDownLogBaseDao appDownLogBaseDao;
	public void setAppDownLogBaseDao(IAppDownLogBaseDao appDownLogBaseDao) {
		this.appDownLogBaseDao = appDownLogBaseDao;
	}
	/**
	 * 根据条件查询(应用下载日志)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<AppDownLog> getAppDownLogByCondition(Map<String,Object> paramMap){
		return appDownLogBaseDao.selectAppDownLogByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(应用下载日志)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<AppDownLog> getAppDownLogByConditionDim(Map<String,Object> paramMap){
		return appDownLogBaseDao.selectAppDownLogByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(应用下载日志)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<AppDownLog> getAppDownLogByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return appDownLogBaseDao.selectAppDownLogByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(应用下载日志)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<AppDownLog> getAppDownLogByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return appDownLogBaseDao.selectAppDownLogByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(应用下载日志)信息
	 * @param id
	 * @return
	 */
	@Override
	public AppDownLog getAppDownLogBySeqId(java.math.BigInteger id){
		return appDownLogBaseDao.selectAppDownLogBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(应用下载日志)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getAppDownLogCount(Map<String,Object> paramMap){
		return appDownLogBaseDao.selectAppDownLogCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(应用下载日志)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getAppDownLogCountDim(Map<String,Object> paramMap){
		return appDownLogBaseDao.selectAppDownLogCount(paramMap,true);
	}
	/**
	 * 往(应用下载日志)新增一条记录
	 * @param appDownLog
	 * @return
	 */
	@Override
	public int insertAppDownLog(AppDownLog appDownLog){
		return 0;//appDownLogBaseDao.insertAppDownLog(appDownLog);
	}
	/**
	 * 批量新增(应用下载日志)
	 * @param appDownLogList
	 * @return
	 */
	@Override
	public int insertAppDownLogBatch(List<AppDownLog> appDownLogList){
		return appDownLogBaseDao.insertAppDownLogBatch(appDownLogList);
	}
	/**
	 * 更新(应用下载日志)信息
	 * @param appDownLog
	 * @return
	 */
	@Override
	public int updateAppDownLog(AppDownLog appDownLog){
		return appDownLogBaseDao.updateAppDownLog(appDownLog);
	}
	/**
	 * 批量更新(应用下载日志)信息
	 * @param appDownLogList
	 * @return
	 */
	@Override
	public int updateAppDownLogBatch(List<AppDownLog> appDownLogList){
		return appDownLogBaseDao.updateAppDownLogBatch(appDownLogList);
	}
	/**
	 * 根据序列号删除(应用下载日志)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteAppDownLogLogic(java.math.BigInteger id){
		return appDownLogBaseDao.deleteAppDownLogLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(应用下载日志)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteAppDownLogLogicBatch(List<java.math.BigInteger> idList){
		return appDownLogBaseDao.deleteAppDownLogLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(应用下载日志)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteAppDownLog(java.math.BigInteger id){
//		return appDownLogBaseDao.deleteAppDownLog(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(应用下载日志)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteAppDownLogBatch(List<java.math.BigInteger> idList){
//		return appDownLogBaseDao.deleteAppDownLogBatch(idList);
//	}
	
}
