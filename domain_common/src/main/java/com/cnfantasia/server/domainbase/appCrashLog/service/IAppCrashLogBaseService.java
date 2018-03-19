package com.cnfantasia.server.domainbase.appCrashLog.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.appCrashLog.entity.AppCrashLog;

/**
 * 描述:(app崩溃日志) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IAppCrashLogBaseService {
	
	/**
	 * 根据条件查询(app崩溃日志)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<AppCrashLog> getAppCrashLogByCondition(Map<String, Object> paramMap);
	/**
	 * 根据条件查询(app崩溃日志)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<AppCrashLog> getAppCrashLogByConditionDim(Map<String, Object> paramMap);
	/**
	 * 按条件分页查询(app崩溃日志)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<AppCrashLog> getAppCrashLogByCondition(Map<String, Object> paramMap, PageModel pageModel);
	/**
	 * 按条件分页查询(app崩溃日志)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<AppCrashLog> getAppCrashLogByConditionDim(Map<String, Object> paramMap, PageModel pageModel);
	/**
	 * 根据序列号查询(app崩溃日志)信息
	 * @param id
	 * @return
	 */
	public AppCrashLog getAppCrashLogBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(app崩溃日志)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getAppCrashLogCount(Map<String, Object> paramMap);
	/**
	 * 根据条件查询满足条件的(app崩溃日志)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getAppCrashLogCountDim(Map<String, Object> paramMap);
	/**
	 * 往(app崩溃日志)新增一条记录
	 * @param appCrashLog
	 * @return
	 */
	public int insertAppCrashLog(AppCrashLog appCrashLog);
	/**
	 * 批量新增(app崩溃日志)
	 * @param appCrashLogList
	 * @return
	 */
	public int insertAppCrashLogBatch(List<AppCrashLog> appCrashLogList);
	/**
	 * 更新(app崩溃日志)信息
	 * @param appCrashLog
	 * @return
	 */
	public int updateAppCrashLog(AppCrashLog appCrashLog);
	/**
	 * 批量更新(app崩溃日志)信息
	 * @param appCrashLogList
	 * @return
	 */
	public int updateAppCrashLogBatch(List<AppCrashLog> appCrashLogList);
	/**
	 * 根据序列号删除(app崩溃日志)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteAppCrashLogLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(app崩溃日志)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteAppCrashLogLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(app崩溃日志)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteAppCrashLog(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(app崩溃日志)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteAppCrashLogBatch(List<java.math.BigInteger> idList);
	
}
