package com.cnfantasia.server.domainbase.appDownLog.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.appDownLog.entity.AppDownLog;

/**
 * 描述:(应用下载日志) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IAppDownLogBaseDao {
	/**
	 * 根据条件查询(应用下载日志)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<AppDownLog> selectAppDownLogByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(应用下载日志)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<AppDownLog> selectAppDownLogByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(应用下载日志)信息
	 * @param id
	 * @return
	 */
	public AppDownLog selectAppDownLogBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(应用下载日志)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectAppDownLogCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(应用下载日志)新增一条记录
	 * @param appDownLog
	 * @return
	 */
	public int insertAppDownLog(AppDownLog appDownLog);
	
	/**
	 * 批量新增(应用下载日志)信息
	 * @param appDownLogList
	 * @return
	 */
	public int insertAppDownLogBatch(List<AppDownLog> appDownLogList);
	
	/**
	 * 更新(应用下载日志)信息
	 * @param appDownLog
	 * @return
	 */
	public int updateAppDownLog(AppDownLog appDownLog);
	
	/**
	 * 批量更新(应用下载日志)信息
	 * @param appDownLogList
	 * @return
	 */
	public int updateAppDownLogBatch(List<AppDownLog> appDownLogList);
	
	/**
	 * 根据序列号删除(应用下载日志)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteAppDownLogLogic(java.math.BigInteger id);
	
	/**
	 * 根据Id 批量删除(应用下载日志)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteAppDownLogLogicBatch(List<java.math.BigInteger> idList);
	
	
//	/**
//	 * 根据序列号删除(应用下载日志)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteAppDownLog(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(应用下载日志)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteAppDownLogBatch(List<java.math.BigInteger> idList);
	
	
}
