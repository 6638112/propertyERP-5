package com.cnfantasia.server.domainbase.taskLogger.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.taskLogger.dao.ITaskLoggerBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.taskLogger.entity.TaskLogger;

/**
 * 描述:(job记录表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class TaskLoggerBaseService implements ITaskLoggerBaseService{
	
	private ITaskLoggerBaseDao taskLoggerBaseDao;
	public void setTaskLoggerBaseDao(ITaskLoggerBaseDao taskLoggerBaseDao) {
		this.taskLoggerBaseDao = taskLoggerBaseDao;
	}
	/**
	 * 根据条件查询(job记录表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<TaskLogger> getTaskLoggerByCondition(Map<String,Object> paramMap){
		return taskLoggerBaseDao.selectTaskLoggerByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(job记录表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<TaskLogger> getTaskLoggerByConditionDim(Map<String,Object> paramMap){
		return taskLoggerBaseDao.selectTaskLoggerByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(job记录表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<TaskLogger> getTaskLoggerByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return taskLoggerBaseDao.selectTaskLoggerByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(job记录表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<TaskLogger> getTaskLoggerByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return taskLoggerBaseDao.selectTaskLoggerByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(job记录表)信息
	 * @param id
	 * @return
	 */
	@Override
	public TaskLogger getTaskLoggerBySeqId(java.math.BigInteger id){
		return taskLoggerBaseDao.selectTaskLoggerBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(job记录表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getTaskLoggerCount(Map<String,Object> paramMap){
		return taskLoggerBaseDao.selectTaskLoggerCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(job记录表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getTaskLoggerCountDim(Map<String,Object> paramMap){
		return taskLoggerBaseDao.selectTaskLoggerCount(paramMap,true);
	}
	/**
	 * 往(job记录表)新增一条记录
	 * @param taskLogger
	 * @return
	 */
	@Override
	public int insertTaskLogger(TaskLogger taskLogger){
		return taskLoggerBaseDao.insertTaskLogger(taskLogger);
	}
	/**
	 * 批量新增(job记录表)
	 * @param taskLoggerList
	 * @return
	 */
	@Override
	public int insertTaskLoggerBatch(List<TaskLogger> taskLoggerList){
		return taskLoggerBaseDao.insertTaskLoggerBatch(taskLoggerList);
	}
	/**
	 * 更新(job记录表)信息
	 * @param taskLogger
	 * @return
	 */
	@Override
	public int updateTaskLogger(TaskLogger taskLogger){
		return taskLoggerBaseDao.updateTaskLogger(taskLogger);
	}
	/**
	 * 批量更新(job记录表)信息
	 * @param taskLoggerList
	 * @return
	 */
	@Override
	public int updateTaskLoggerBatch(List<TaskLogger> taskLoggerList){
		return taskLoggerBaseDao.updateTaskLoggerBatch(taskLoggerList);
	}
	/**
	 * 根据序列号删除(job记录表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	/* 
	@Override
	public int deleteTaskLoggerLogic(java.math.BigInteger id){
		return taskLoggerBaseDao.deleteTaskLoggerLogic(id);
	}
	 */
	/**
	 * 根据序列号批量删除(job记录表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	/* 
	@Override
	public int deleteTaskLoggerLogicBatch(List<java.math.BigInteger> idList){
		return taskLoggerBaseDao.deleteTaskLoggerLogicBatch(idList);
	}
	 */
//	/**
//	 * 根据序列号删除(job记录表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteTaskLogger(java.math.BigInteger id){
//		return taskLoggerBaseDao.deleteTaskLogger(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(job记录表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteTaskLoggerBatch(List<java.math.BigInteger> idList){
//		return taskLoggerBaseDao.deleteTaskLoggerBatch(idList);
//	}
	
}
