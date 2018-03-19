package com.cnfantasia.server.domainbase.operationSaHasEbSupply.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.operationSaHasEbSupply.dao.IOperationSaHasEbSupplyBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.operationSaHasEbSupply.entity.OperationSaHasEbSupply;

/**
 * 描述:() 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class OperationSaHasEbSupplyBaseService implements IOperationSaHasEbSupplyBaseService{
	
	private IOperationSaHasEbSupplyBaseDao operationSaHasEbSupplyBaseDao;
	public void setOperationSaHasEbSupplyBaseDao(IOperationSaHasEbSupplyBaseDao operationSaHasEbSupplyBaseDao) {
		this.operationSaHasEbSupplyBaseDao = operationSaHasEbSupplyBaseDao;
	}
	/**
	 * 根据条件查询()信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<OperationSaHasEbSupply> getOperationSaHasEbSupplyByCondition(Map<String,Object> paramMap){
		return operationSaHasEbSupplyBaseDao.selectOperationSaHasEbSupplyByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询()信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<OperationSaHasEbSupply> getOperationSaHasEbSupplyByConditionDim(Map<String,Object> paramMap){
		return operationSaHasEbSupplyBaseDao.selectOperationSaHasEbSupplyByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询()信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<OperationSaHasEbSupply> getOperationSaHasEbSupplyByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return operationSaHasEbSupplyBaseDao.selectOperationSaHasEbSupplyByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询()信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<OperationSaHasEbSupply> getOperationSaHasEbSupplyByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return operationSaHasEbSupplyBaseDao.selectOperationSaHasEbSupplyByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询()信息
	 * @param id
	 * @return
	 */
	@Override
	public OperationSaHasEbSupply getOperationSaHasEbSupplyBySeqId(java.math.BigInteger id){
		return operationSaHasEbSupplyBaseDao.selectOperationSaHasEbSupplyBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的()记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getOperationSaHasEbSupplyCount(Map<String,Object> paramMap){
		return operationSaHasEbSupplyBaseDao.selectOperationSaHasEbSupplyCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的()记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getOperationSaHasEbSupplyCountDim(Map<String,Object> paramMap){
		return operationSaHasEbSupplyBaseDao.selectOperationSaHasEbSupplyCount(paramMap,true);
	}
	/**
	 * 往()新增一条记录
	 * @param operationSaHasEbSupply
	 * @return
	 */
	@Override
	public int insertOperationSaHasEbSupply(OperationSaHasEbSupply operationSaHasEbSupply){
		return operationSaHasEbSupplyBaseDao.insertOperationSaHasEbSupply(operationSaHasEbSupply);
	}
	/**
	 * 批量新增()
	 * @param operationSaHasEbSupplyList
	 * @return
	 */
	@Override
	public int insertOperationSaHasEbSupplyBatch(List<OperationSaHasEbSupply> operationSaHasEbSupplyList){
		return operationSaHasEbSupplyBaseDao.insertOperationSaHasEbSupplyBatch(operationSaHasEbSupplyList);
	}
	/**
	 * 更新()信息
	 * @param operationSaHasEbSupply
	 * @return
	 */
	@Override
	public int updateOperationSaHasEbSupply(OperationSaHasEbSupply operationSaHasEbSupply){
		return operationSaHasEbSupplyBaseDao.updateOperationSaHasEbSupply(operationSaHasEbSupply);
	}
	/**
	 * 批量更新()信息
	 * @param operationSaHasEbSupplyList
	 * @return
	 */
	@Override
	public int updateOperationSaHasEbSupplyBatch(List<OperationSaHasEbSupply> operationSaHasEbSupplyList){
		return operationSaHasEbSupplyBaseDao.updateOperationSaHasEbSupplyBatch(operationSaHasEbSupplyList);
	}
	/**
	 * 根据序列号删除()信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteOperationSaHasEbSupplyLogic(java.math.BigInteger id){
		return operationSaHasEbSupplyBaseDao.deleteOperationSaHasEbSupplyLogic(id);
	}
	
	/**
	 * 根据序列号批量删除()信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteOperationSaHasEbSupplyLogicBatch(List<java.math.BigInteger> idList){
		return operationSaHasEbSupplyBaseDao.deleteOperationSaHasEbSupplyLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除()信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteOperationSaHasEbSupply(java.math.BigInteger id){
//		return operationSaHasEbSupplyBaseDao.deleteOperationSaHasEbSupply(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除()信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteOperationSaHasEbSupplyBatch(List<java.math.BigInteger> idList){
//		return operationSaHasEbSupplyBaseDao.deleteOperationSaHasEbSupplyBatch(idList);
//	}
	
}
