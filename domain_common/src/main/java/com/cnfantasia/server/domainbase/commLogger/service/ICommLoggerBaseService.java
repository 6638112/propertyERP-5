package com.cnfantasia.server.domainbase.commLogger.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.commLogger.entity.CommLogger;

/**
 * 描述:(公共日志记录表) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ICommLoggerBaseService {
	
	/**
	 * 根据条件查询(公共日志记录表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<CommLogger> getCommLoggerByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(公共日志记录表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<CommLogger> getCommLoggerByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(公共日志记录表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<CommLogger> getCommLoggerByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(公共日志记录表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<CommLogger> getCommLoggerByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(公共日志记录表)信息
	 * @param id
	 * @return
	 */
	public CommLogger getCommLoggerBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(公共日志记录表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getCommLoggerCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(公共日志记录表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getCommLoggerCountDim(Map<String,Object> paramMap);
	/**
	 * 往(公共日志记录表)新增一条记录
	 * @param commLogger
	 * @return
	 */
	public int insertCommLogger(CommLogger commLogger);
	/**
	 * 批量新增(公共日志记录表)
	 * @param commLoggerList
	 * @return
	 */
	public int insertCommLoggerBatch(List<CommLogger> commLoggerList);
	/**
	 * 更新(公共日志记录表)信息
	 * @param commLogger
	 * @return
	 */
	public int updateCommLogger(CommLogger commLogger);
	/**
	 * 批量更新(公共日志记录表)信息
	 * @param commLoggerList
	 * @return
	 */
	public int updateCommLoggerBatch(List<CommLogger> commLoggerList);
	/**
	 * 根据序列号删除(公共日志记录表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteCommLoggerLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(公共日志记录表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteCommLoggerLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(公共日志记录表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteCommLogger(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(公共日志记录表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteCommLoggerBatch(List<java.math.BigInteger> idList);
	
}
