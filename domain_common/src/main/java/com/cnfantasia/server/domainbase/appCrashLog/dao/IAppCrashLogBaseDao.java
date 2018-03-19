package com.cnfantasia.server.domainbase.appCrashLog.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.appCrashLog.entity.AppCrashLog;

/**
 * 描述:(app崩溃日志) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IAppCrashLogBaseDao {
	/**
	 * 根据条件查询(app崩溃日志)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<AppCrashLog> selectAppCrashLogByCondition(Map<String, Object> paramMap, boolean isDim);
	/**
	 * 按条件分页查询(app崩溃日志)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<AppCrashLog> selectAppCrashLogByCondition(Map<String, Object> paramMap, PageModel pageModel, boolean isDim);
	/**
	 * 根据序列号查询(app崩溃日志)信息
	 * @param id
	 * @return
	 */
	public AppCrashLog selectAppCrashLogBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(app崩溃日志)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectAppCrashLogCount(Map<String, Object> paramMap, boolean isDim);
	/**
	 * 往(app崩溃日志)新增一条记录
	 * @param appCrashLog
	 * @return
	 */
	public int insertAppCrashLog(AppCrashLog appCrashLog);
	
	/**
	 * 批量新增(app崩溃日志)信息
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
	 * 根据Id 批量删除(app崩溃日志)信息_逻辑删除
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
