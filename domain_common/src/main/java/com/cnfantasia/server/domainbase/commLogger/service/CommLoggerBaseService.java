package com.cnfantasia.server.domainbase.commLogger.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.commLogger.dao.ICommLoggerBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.commLogger.entity.CommLogger;

/**
 * 描述:(公共日志记录表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class CommLoggerBaseService implements ICommLoggerBaseService{
	
	private ICommLoggerBaseDao commLoggerBaseDao;
	public void setCommLoggerBaseDao(ICommLoggerBaseDao commLoggerBaseDao) {
		this.commLoggerBaseDao = commLoggerBaseDao;
	}
	/**
	 * 根据条件查询(公共日志记录表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<CommLogger> getCommLoggerByCondition(Map<String,Object> paramMap){
		return commLoggerBaseDao.selectCommLoggerByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(公共日志记录表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<CommLogger> getCommLoggerByConditionDim(Map<String,Object> paramMap){
		return commLoggerBaseDao.selectCommLoggerByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(公共日志记录表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<CommLogger> getCommLoggerByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return commLoggerBaseDao.selectCommLoggerByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(公共日志记录表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<CommLogger> getCommLoggerByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return commLoggerBaseDao.selectCommLoggerByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(公共日志记录表)信息
	 * @param id
	 * @return
	 */
	@Override
	public CommLogger getCommLoggerBySeqId(java.math.BigInteger id){
		return commLoggerBaseDao.selectCommLoggerBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(公共日志记录表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getCommLoggerCount(Map<String,Object> paramMap){
		return commLoggerBaseDao.selectCommLoggerCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(公共日志记录表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getCommLoggerCountDim(Map<String,Object> paramMap){
		return commLoggerBaseDao.selectCommLoggerCount(paramMap,true);
	}
	/**
	 * 往(公共日志记录表)新增一条记录
	 * @param commLogger
	 * @return
	 */
	@Override
	public int insertCommLogger(CommLogger commLogger){
		return commLoggerBaseDao.insertCommLogger(commLogger);
	}
	/**
	 * 批量新增(公共日志记录表)
	 * @param commLoggerList
	 * @return
	 */
	@Override
	public int insertCommLoggerBatch(List<CommLogger> commLoggerList){
		return commLoggerBaseDao.insertCommLoggerBatch(commLoggerList);
	}
	/**
	 * 更新(公共日志记录表)信息
	 * @param commLogger
	 * @return
	 */
	@Override
	public int updateCommLogger(CommLogger commLogger){
		return commLoggerBaseDao.updateCommLogger(commLogger);
	}
	/**
	 * 批量更新(公共日志记录表)信息
	 * @param commLoggerList
	 * @return
	 */
	@Override
	public int updateCommLoggerBatch(List<CommLogger> commLoggerList){
		return commLoggerBaseDao.updateCommLoggerBatch(commLoggerList);
	}
	/**
	 * 根据序列号删除(公共日志记录表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteCommLoggerLogic(java.math.BigInteger id){
		return commLoggerBaseDao.deleteCommLoggerLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(公共日志记录表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteCommLoggerLogicBatch(List<java.math.BigInteger> idList){
		return commLoggerBaseDao.deleteCommLoggerLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(公共日志记录表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteCommLogger(java.math.BigInteger id){
//		return commLoggerBaseDao.deleteCommLogger(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(公共日志记录表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteCommLoggerBatch(List<java.math.BigInteger> idList){
//		return commLoggerBaseDao.deleteCommLoggerBatch(idList);
//	}
	
}
