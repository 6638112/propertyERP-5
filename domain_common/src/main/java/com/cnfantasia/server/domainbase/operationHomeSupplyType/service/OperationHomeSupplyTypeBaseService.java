package com.cnfantasia.server.domainbase.operationHomeSupplyType.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.operationHomeSupplyType.dao.IOperationHomeSupplyTypeBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.operationHomeSupplyType.entity.OperationHomeSupplyType;

/**
 * 描述:(首页商家类别运营表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class OperationHomeSupplyTypeBaseService implements IOperationHomeSupplyTypeBaseService{
	
	private IOperationHomeSupplyTypeBaseDao operationHomeSupplyTypeBaseDao;
	public void setOperationHomeSupplyTypeBaseDao(IOperationHomeSupplyTypeBaseDao operationHomeSupplyTypeBaseDao) {
		this.operationHomeSupplyTypeBaseDao = operationHomeSupplyTypeBaseDao;
	}
	/**
	 * 根据条件查询(首页商家类别运营表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<OperationHomeSupplyType> getOperationHomeSupplyTypeByCondition(Map<String,Object> paramMap){
		return operationHomeSupplyTypeBaseDao.selectOperationHomeSupplyTypeByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(首页商家类别运营表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<OperationHomeSupplyType> getOperationHomeSupplyTypeByConditionDim(Map<String,Object> paramMap){
		return operationHomeSupplyTypeBaseDao.selectOperationHomeSupplyTypeByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(首页商家类别运营表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<OperationHomeSupplyType> getOperationHomeSupplyTypeByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return operationHomeSupplyTypeBaseDao.selectOperationHomeSupplyTypeByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(首页商家类别运营表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<OperationHomeSupplyType> getOperationHomeSupplyTypeByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return operationHomeSupplyTypeBaseDao.selectOperationHomeSupplyTypeByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(首页商家类别运营表)信息
	 * @param id
	 * @return
	 */
	@Override
	public OperationHomeSupplyType getOperationHomeSupplyTypeBySeqId(java.math.BigInteger id){
		return operationHomeSupplyTypeBaseDao.selectOperationHomeSupplyTypeBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(首页商家类别运营表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getOperationHomeSupplyTypeCount(Map<String,Object> paramMap){
		return operationHomeSupplyTypeBaseDao.selectOperationHomeSupplyTypeCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(首页商家类别运营表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getOperationHomeSupplyTypeCountDim(Map<String,Object> paramMap){
		return operationHomeSupplyTypeBaseDao.selectOperationHomeSupplyTypeCount(paramMap,true);
	}
	/**
	 * 往(首页商家类别运营表)新增一条记录
	 * @param operationHomeSupplyType
	 * @return
	 */
	@Override
	public int insertOperationHomeSupplyType(OperationHomeSupplyType operationHomeSupplyType){
		return operationHomeSupplyTypeBaseDao.insertOperationHomeSupplyType(operationHomeSupplyType);
	}
	/**
	 * 批量新增(首页商家类别运营表)
	 * @param operationHomeSupplyTypeList
	 * @return
	 */
	@Override
	public int insertOperationHomeSupplyTypeBatch(List<OperationHomeSupplyType> operationHomeSupplyTypeList){
		return operationHomeSupplyTypeBaseDao.insertOperationHomeSupplyTypeBatch(operationHomeSupplyTypeList);
	}
	/**
	 * 更新(首页商家类别运营表)信息
	 * @param operationHomeSupplyType
	 * @return
	 */
	@Override
	public int updateOperationHomeSupplyType(OperationHomeSupplyType operationHomeSupplyType){
		return operationHomeSupplyTypeBaseDao.updateOperationHomeSupplyType(operationHomeSupplyType);
	}
	/**
	 * 批量更新(首页商家类别运营表)信息
	 * @param operationHomeSupplyTypeList
	 * @return
	 */
	@Override
	public int updateOperationHomeSupplyTypeBatch(List<OperationHomeSupplyType> operationHomeSupplyTypeList){
		return operationHomeSupplyTypeBaseDao.updateOperationHomeSupplyTypeBatch(operationHomeSupplyTypeList);
	}
	/**
	 * 根据序列号删除(首页商家类别运营表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteOperationHomeSupplyTypeLogic(java.math.BigInteger id){
		return operationHomeSupplyTypeBaseDao.deleteOperationHomeSupplyTypeLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(首页商家类别运营表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteOperationHomeSupplyTypeLogicBatch(List<java.math.BigInteger> idList){
		return operationHomeSupplyTypeBaseDao.deleteOperationHomeSupplyTypeLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(首页商家类别运营表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteOperationHomeSupplyType(java.math.BigInteger id){
//		return operationHomeSupplyTypeBaseDao.deleteOperationHomeSupplyType(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(首页商家类别运营表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteOperationHomeSupplyTypeBatch(List<java.math.BigInteger> idList){
//		return operationHomeSupplyTypeBaseDao.deleteOperationHomeSupplyTypeBatch(idList);
//	}
	
}
