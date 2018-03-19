package com.cnfantasia.server.domainbase.operationConstantData.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.operationConstantData.dao.IOperationConstantDataBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.operationConstantData.entity.OperationConstantData;

/**
 * 描述:(运维相关的数据) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class OperationConstantDataBaseService implements IOperationConstantDataBaseService{
	
	private IOperationConstantDataBaseDao operationConstantDataBaseDao;
	public void setOperationConstantDataBaseDao(IOperationConstantDataBaseDao operationConstantDataBaseDao) {
		this.operationConstantDataBaseDao = operationConstantDataBaseDao;
	}
	/**
	 * 根据条件查询(运维相关的数据)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<OperationConstantData> getOperationConstantDataByCondition(Map<String,Object> paramMap){
		return operationConstantDataBaseDao.selectOperationConstantDataByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(运维相关的数据)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<OperationConstantData> getOperationConstantDataByConditionDim(Map<String,Object> paramMap){
		return operationConstantDataBaseDao.selectOperationConstantDataByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(运维相关的数据)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<OperationConstantData> getOperationConstantDataByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return operationConstantDataBaseDao.selectOperationConstantDataByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(运维相关的数据)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<OperationConstantData> getOperationConstantDataByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return operationConstantDataBaseDao.selectOperationConstantDataByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(运维相关的数据)信息
	 * @param id
	 * @return
	 */
	@Override
	public OperationConstantData getOperationConstantDataBySeqId(java.math.BigInteger id){
		return operationConstantDataBaseDao.selectOperationConstantDataBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(运维相关的数据)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getOperationConstantDataCount(Map<String,Object> paramMap){
		return operationConstantDataBaseDao.selectOperationConstantDataCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(运维相关的数据)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getOperationConstantDataCountDim(Map<String,Object> paramMap){
		return operationConstantDataBaseDao.selectOperationConstantDataCount(paramMap,true);
	}
	/**
	 * 往(运维相关的数据)新增一条记录
	 * @param operationConstantData
	 * @return
	 */
	@Override
	public int insertOperationConstantData(OperationConstantData operationConstantData){
		return operationConstantDataBaseDao.insertOperationConstantData(operationConstantData);
	}
	/**
	 * 批量新增(运维相关的数据)
	 * @param operationConstantDataList
	 * @return
	 */
	@Override
	public int insertOperationConstantDataBatch(List<OperationConstantData> operationConstantDataList){
		return operationConstantDataBaseDao.insertOperationConstantDataBatch(operationConstantDataList);
	}
	/**
	 * 更新(运维相关的数据)信息
	 * @param operationConstantData
	 * @return
	 */
	@Override
	public int updateOperationConstantData(OperationConstantData operationConstantData){
		return operationConstantDataBaseDao.updateOperationConstantData(operationConstantData);
	}
	/**
	 * 批量更新(运维相关的数据)信息
	 * @param operationConstantDataList
	 * @return
	 */
	@Override
	public int updateOperationConstantDataBatch(List<OperationConstantData> operationConstantDataList){
		return operationConstantDataBaseDao.updateOperationConstantDataBatch(operationConstantDataList);
	}
	/**
	 * 根据序列号删除(运维相关的数据)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteOperationConstantDataLogic(java.math.BigInteger id){
		return operationConstantDataBaseDao.deleteOperationConstantDataLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(运维相关的数据)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteOperationConstantDataLogicBatch(List<java.math.BigInteger> idList){
		return operationConstantDataBaseDao.deleteOperationConstantDataLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(运维相关的数据)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteOperationConstantData(java.math.BigInteger id){
//		return operationConstantDataBaseDao.deleteOperationConstantData(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(运维相关的数据)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteOperationConstantDataBatch(List<java.math.BigInteger> idList){
//		return operationConstantDataBaseDao.deleteOperationConstantDataBatch(idList);
//	}
	
}
