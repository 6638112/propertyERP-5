package com.cnfantasia.server.domainbase.operationServiceArea.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.operationServiceArea.dao.IOperationServiceAreaBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.operationServiceArea.entity.OperationServiceArea;

/**
 * 描述:(服务范围的运营数据表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class OperationServiceAreaBaseService implements IOperationServiceAreaBaseService{
	
	private IOperationServiceAreaBaseDao operationServiceAreaBaseDao;
	public void setOperationServiceAreaBaseDao(IOperationServiceAreaBaseDao operationServiceAreaBaseDao) {
		this.operationServiceAreaBaseDao = operationServiceAreaBaseDao;
	}
	/**
	 * 根据条件查询(服务范围的运营数据表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<OperationServiceArea> getOperationServiceAreaByCondition(Map<String,Object> paramMap){
		return operationServiceAreaBaseDao.selectOperationServiceAreaByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(服务范围的运营数据表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<OperationServiceArea> getOperationServiceAreaByConditionDim(Map<String,Object> paramMap){
		return operationServiceAreaBaseDao.selectOperationServiceAreaByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(服务范围的运营数据表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<OperationServiceArea> getOperationServiceAreaByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return operationServiceAreaBaseDao.selectOperationServiceAreaByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(服务范围的运营数据表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<OperationServiceArea> getOperationServiceAreaByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return operationServiceAreaBaseDao.selectOperationServiceAreaByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(服务范围的运营数据表)信息
	 * @param id
	 * @return
	 */
	@Override
	public OperationServiceArea getOperationServiceAreaBySeqId(java.math.BigInteger id){
		return operationServiceAreaBaseDao.selectOperationServiceAreaBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(服务范围的运营数据表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getOperationServiceAreaCount(Map<String,Object> paramMap){
		return operationServiceAreaBaseDao.selectOperationServiceAreaCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(服务范围的运营数据表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getOperationServiceAreaCountDim(Map<String,Object> paramMap){
		return operationServiceAreaBaseDao.selectOperationServiceAreaCount(paramMap,true);
	}
	/**
	 * 往(服务范围的运营数据表)新增一条记录
	 * @param operationServiceArea
	 * @return
	 */
	@Override
	public int insertOperationServiceArea(OperationServiceArea operationServiceArea){
		return operationServiceAreaBaseDao.insertOperationServiceArea(operationServiceArea);
	}
	/**
	 * 批量新增(服务范围的运营数据表)
	 * @param operationServiceAreaList
	 * @return
	 */
	@Override
	public int insertOperationServiceAreaBatch(List<OperationServiceArea> operationServiceAreaList){
		return operationServiceAreaBaseDao.insertOperationServiceAreaBatch(operationServiceAreaList);
	}
	/**
	 * 更新(服务范围的运营数据表)信息
	 * @param operationServiceArea
	 * @return
	 */
	@Override
	public int updateOperationServiceArea(OperationServiceArea operationServiceArea){
		return operationServiceAreaBaseDao.updateOperationServiceArea(operationServiceArea);
	}
	/**
	 * 批量更新(服务范围的运营数据表)信息
	 * @param operationServiceAreaList
	 * @return
	 */
	@Override
	public int updateOperationServiceAreaBatch(List<OperationServiceArea> operationServiceAreaList){
		return operationServiceAreaBaseDao.updateOperationServiceAreaBatch(operationServiceAreaList);
	}
	/**
	 * 根据序列号删除(服务范围的运营数据表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteOperationServiceAreaLogic(java.math.BigInteger id){
		return operationServiceAreaBaseDao.deleteOperationServiceAreaLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(服务范围的运营数据表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteOperationServiceAreaLogicBatch(List<java.math.BigInteger> idList){
		return operationServiceAreaBaseDao.deleteOperationServiceAreaLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(服务范围的运营数据表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteOperationServiceArea(java.math.BigInteger id){
//		return operationServiceAreaBaseDao.deleteOperationServiceArea(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(服务范围的运营数据表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteOperationServiceAreaBatch(List<java.math.BigInteger> idList){
//		return operationServiceAreaBaseDao.deleteOperationServiceAreaBatch(idList);
//	}
	
}
