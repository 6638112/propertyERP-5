package com.cnfantasia.server.domainbase.taskLogger.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.taskLogger.entity.TaskLogger;

/**
 * 描述:(job记录表) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ITaskLoggerBaseDao {
	/**
	 * 根据条件查询(job记录表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<TaskLogger> selectTaskLoggerByCondition(Map<String, Object> paramMap, boolean isDim);
	/**
	 * 按条件分页查询(job记录表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<TaskLogger> selectTaskLoggerByCondition(Map<String, Object> paramMap, PageModel pageModel, boolean isDim);
	/**
	 * 根据序列号查询(job记录表)信息
	 * @param id
	 * @return
	 */
	public TaskLogger selectTaskLoggerBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(job记录表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectTaskLoggerCount(Map<String, Object> paramMap, boolean isDim);
	/**
	 * 往(job记录表)新增一条记录
	 * @param taskLogger
	 * @return
	 */
	public int insertTaskLogger(TaskLogger taskLogger);
	
	/**
	 * 批量新增(job记录表)信息
	 * @param taskLoggerList
	 * @return
	 */
	public int insertTaskLoggerBatch(List<TaskLogger> taskLoggerList);
	
	/**
	 * 更新(job记录表)信息
	 * @param taskLogger
	 * @return
	 */
	public int updateTaskLogger(TaskLogger taskLogger);
	
	/**
	 * 批量更新(job记录表)信息
	 * @param taskLoggerList
	 * @return
	 */
	public int updateTaskLoggerBatch(List<TaskLogger> taskLoggerList);
	
	/**
	 * 根据序列号删除(job记录表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	/* 
	public int deleteTaskLoggerLogic(java.math.BigInteger id);
	 */
	/**
	 * 根据Id 批量删除(job记录表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	/* 
	public int deleteTaskLoggerLogicBatch(List<java.math.BigInteger> idList);
	 */
	
//	/**
//	 * 根据序列号删除(job记录表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteTaskLogger(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(job记录表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteTaskLoggerBatch(List<java.math.BigInteger> idList);
	
	
}
