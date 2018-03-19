package com.cnfantasia.server.domainbase.encyptLog.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.encyptLog.dao.IEncyptLogBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.encyptLog.entity.EncyptLog;

/**
 * 描述:(加密日志记录) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class EncyptLogBaseService implements IEncyptLogBaseService{
	
	private IEncyptLogBaseDao encyptLogBaseDao;
	public void setEncyptLogBaseDao(IEncyptLogBaseDao encyptLogBaseDao) {
		this.encyptLogBaseDao = encyptLogBaseDao;
	}
	/**
	 * 根据条件查询(加密日志记录)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<EncyptLog> getEncyptLogByCondition(Map<String,Object> paramMap){
		return encyptLogBaseDao.selectEncyptLogByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(加密日志记录)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<EncyptLog> getEncyptLogByConditionDim(Map<String,Object> paramMap){
		return encyptLogBaseDao.selectEncyptLogByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(加密日志记录)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<EncyptLog> getEncyptLogByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return encyptLogBaseDao.selectEncyptLogByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(加密日志记录)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<EncyptLog> getEncyptLogByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return encyptLogBaseDao.selectEncyptLogByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(加密日志记录)信息
	 * @param id
	 * @return
	 */
	@Override
	public EncyptLog getEncyptLogBySeqId(java.math.BigInteger id){
		return encyptLogBaseDao.selectEncyptLogBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(加密日志记录)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getEncyptLogCount(Map<String,Object> paramMap){
		return encyptLogBaseDao.selectEncyptLogCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(加密日志记录)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getEncyptLogCountDim(Map<String,Object> paramMap){
		return encyptLogBaseDao.selectEncyptLogCount(paramMap,true);
	}
	/**
	 * 往(加密日志记录)新增一条记录
	 * @param encyptLog
	 * @return
	 */
	@Override
	public int insertEncyptLog(EncyptLog encyptLog){
		return encyptLogBaseDao.insertEncyptLog(encyptLog);
	}
	/**
	 * 批量新增(加密日志记录)
	 * @param encyptLogList
	 * @return
	 */
	@Override
	public int insertEncyptLogBatch(List<EncyptLog> encyptLogList){
		return encyptLogBaseDao.insertEncyptLogBatch(encyptLogList);
	}
	/**
	 * 更新(加密日志记录)信息
	 * @param encyptLog
	 * @return
	 */
	@Override
	public int updateEncyptLog(EncyptLog encyptLog){
		return encyptLogBaseDao.updateEncyptLog(encyptLog);
	}
	/**
	 * 批量更新(加密日志记录)信息
	 * @param encyptLogList
	 * @return
	 */
	@Override
	public int updateEncyptLogBatch(List<EncyptLog> encyptLogList){
		return encyptLogBaseDao.updateEncyptLogBatch(encyptLogList);
	}
	/**
	 * 根据序列号删除(加密日志记录)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteEncyptLogLogic(java.math.BigInteger id){
		return encyptLogBaseDao.deleteEncyptLogLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(加密日志记录)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteEncyptLogLogicBatch(List<java.math.BigInteger> idList){
		return encyptLogBaseDao.deleteEncyptLogLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(加密日志记录)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteEncyptLog(java.math.BigInteger id){
//		return encyptLogBaseDao.deleteEncyptLog(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(加密日志记录)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteEncyptLogBatch(List<java.math.BigInteger> idList){
//		return encyptLogBaseDao.deleteEncyptLogBatch(idList);
//	}
	
}
