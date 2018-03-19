package com.cnfantasia.server.domainbase.commLogger.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.commLogger.entity.CommLogger;

/**
 * 描述:(公共日志记录表) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ICommLoggerBaseDao {
	/**
	 * 根据条件查询(公共日志记录表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<CommLogger> selectCommLoggerByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(公共日志记录表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<CommLogger> selectCommLoggerByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(公共日志记录表)信息
	 * @param id
	 * @return
	 */
	public CommLogger selectCommLoggerBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(公共日志记录表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectCommLoggerCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(公共日志记录表)新增一条记录
	 * @param commLogger
	 * @return
	 */
	public int insertCommLogger(CommLogger commLogger);
	
	/**
	 * 批量新增(公共日志记录表)信息
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
	 * 根据Id 批量删除(公共日志记录表)信息_逻辑删除
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
