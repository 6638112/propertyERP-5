package com.cnfantasia.server.domainbase.openDoorLog.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.openDoorLog.dao.IOpenDoorLogBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.openDoorLog.entity.OpenDoorLog;

/**
 * 描述:(车牌表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class OpenDoorLogBaseService implements IOpenDoorLogBaseService{
	
	private IOpenDoorLogBaseDao openDoorLogBaseDao;
	public void setOpenDoorLogBaseDao(IOpenDoorLogBaseDao openDoorLogBaseDao) {
		this.openDoorLogBaseDao = openDoorLogBaseDao;
	}
	/**
	 * 根据条件查询(车牌表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<OpenDoorLog> getOpenDoorLogByCondition(Map<String,Object> paramMap){
		return openDoorLogBaseDao.selectOpenDoorLogByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(车牌表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<OpenDoorLog> getOpenDoorLogByConditionDim(Map<String,Object> paramMap){
		return openDoorLogBaseDao.selectOpenDoorLogByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(车牌表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<OpenDoorLog> getOpenDoorLogByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return openDoorLogBaseDao.selectOpenDoorLogByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(车牌表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<OpenDoorLog> getOpenDoorLogByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return openDoorLogBaseDao.selectOpenDoorLogByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(车牌表)信息
	 * @param id
	 * @return
	 */
	@Override
	public OpenDoorLog getOpenDoorLogBySeqId(java.math.BigInteger id){
		return openDoorLogBaseDao.selectOpenDoorLogBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(车牌表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getOpenDoorLogCount(Map<String,Object> paramMap){
		return openDoorLogBaseDao.selectOpenDoorLogCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(车牌表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getOpenDoorLogCountDim(Map<String,Object> paramMap){
		return openDoorLogBaseDao.selectOpenDoorLogCount(paramMap,true);
	}
	/**
	 * 往(车牌表)新增一条记录
	 * @param openDoorLog
	 * @return
	 */
	@Override
	public int insertOpenDoorLog(OpenDoorLog openDoorLog){
		return openDoorLogBaseDao.insertOpenDoorLog(openDoorLog);
	}
	/**
	 * 批量新增(车牌表)
	 * @param openDoorLogList
	 * @return
	 */
	@Override
	public int insertOpenDoorLogBatch(List<OpenDoorLog> openDoorLogList){
		return openDoorLogBaseDao.insertOpenDoorLogBatch(openDoorLogList);
	}
	/**
	 * 更新(车牌表)信息
	 * @param openDoorLog
	 * @return
	 */
	@Override
	public int updateOpenDoorLog(OpenDoorLog openDoorLog){
		return openDoorLogBaseDao.updateOpenDoorLog(openDoorLog);
	}
	/**
	 * 批量更新(车牌表)信息
	 * @param openDoorLogList
	 * @return
	 */
	@Override
	public int updateOpenDoorLogBatch(List<OpenDoorLog> openDoorLogList){
		return openDoorLogBaseDao.updateOpenDoorLogBatch(openDoorLogList);
	}
	/**
	 * 根据序列号删除(车牌表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteOpenDoorLogLogic(java.math.BigInteger id){
		return openDoorLogBaseDao.deleteOpenDoorLogLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(车牌表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteOpenDoorLogLogicBatch(List<java.math.BigInteger> idList){
		return openDoorLogBaseDao.deleteOpenDoorLogLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(车牌表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteOpenDoorLog(java.math.BigInteger id){
//		return openDoorLogBaseDao.deleteOpenDoorLog(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(车牌表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteOpenDoorLogBatch(List<java.math.BigInteger> idList){
//		return openDoorLogBaseDao.deleteOpenDoorLogBatch(idList);
//	}
	
}
