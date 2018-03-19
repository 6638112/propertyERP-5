package com.cnfantasia.server.domainbase.deliveryOrderExpressTrace.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.deliveryOrderExpressTrace.dao.IDeliveryOrderExpressTraceBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.deliveryOrderExpressTrace.entity.DeliveryOrderExpressTrace;

/**
 * 描述:(供应商快递记录表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class DeliveryOrderExpressTraceBaseService implements IDeliveryOrderExpressTraceBaseService{
	
	private IDeliveryOrderExpressTraceBaseDao deliveryOrderExpressTraceBaseDao;
	public void setDeliveryOrderExpressTraceBaseDao(IDeliveryOrderExpressTraceBaseDao deliveryOrderExpressTraceBaseDao) {
		this.deliveryOrderExpressTraceBaseDao = deliveryOrderExpressTraceBaseDao;
	}
	/**
	 * 根据条件查询(供应商快递记录表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<DeliveryOrderExpressTrace> getDeliveryOrderExpressTraceByCondition(Map<String,Object> paramMap){
		return deliveryOrderExpressTraceBaseDao.selectDeliveryOrderExpressTraceByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(供应商快递记录表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<DeliveryOrderExpressTrace> getDeliveryOrderExpressTraceByConditionDim(Map<String,Object> paramMap){
		return deliveryOrderExpressTraceBaseDao.selectDeliveryOrderExpressTraceByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(供应商快递记录表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<DeliveryOrderExpressTrace> getDeliveryOrderExpressTraceByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return deliveryOrderExpressTraceBaseDao.selectDeliveryOrderExpressTraceByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(供应商快递记录表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<DeliveryOrderExpressTrace> getDeliveryOrderExpressTraceByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return deliveryOrderExpressTraceBaseDao.selectDeliveryOrderExpressTraceByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(供应商快递记录表)信息
	 * @param id
	 * @return
	 */
	@Override
	public DeliveryOrderExpressTrace getDeliveryOrderExpressTraceBySeqId(java.math.BigInteger id){
		return deliveryOrderExpressTraceBaseDao.selectDeliveryOrderExpressTraceBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(供应商快递记录表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getDeliveryOrderExpressTraceCount(Map<String,Object> paramMap){
		return deliveryOrderExpressTraceBaseDao.selectDeliveryOrderExpressTraceCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(供应商快递记录表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getDeliveryOrderExpressTraceCountDim(Map<String,Object> paramMap){
		return deliveryOrderExpressTraceBaseDao.selectDeliveryOrderExpressTraceCount(paramMap,true);
	}
	/**
	 * 往(供应商快递记录表)新增一条记录
	 * @param deliveryOrderExpressTrace
	 * @return
	 */
	@Override
	public int insertDeliveryOrderExpressTrace(DeliveryOrderExpressTrace deliveryOrderExpressTrace){
		return deliveryOrderExpressTraceBaseDao.insertDeliveryOrderExpressTrace(deliveryOrderExpressTrace);
	}
	/**
	 * 批量新增(供应商快递记录表)
	 * @param deliveryOrderExpressTraceList
	 * @return
	 */
	@Override
	public int insertDeliveryOrderExpressTraceBatch(List<DeliveryOrderExpressTrace> deliveryOrderExpressTraceList){
		return deliveryOrderExpressTraceBaseDao.insertDeliveryOrderExpressTraceBatch(deliveryOrderExpressTraceList);
	}
	/**
	 * 更新(供应商快递记录表)信息
	 * @param deliveryOrderExpressTrace
	 * @return
	 */
	@Override
	public int updateDeliveryOrderExpressTrace(DeliveryOrderExpressTrace deliveryOrderExpressTrace){
		return deliveryOrderExpressTraceBaseDao.updateDeliveryOrderExpressTrace(deliveryOrderExpressTrace);
	}
	/**
	 * 批量更新(供应商快递记录表)信息
	 * @param deliveryOrderExpressTraceList
	 * @return
	 */
	@Override
	public int updateDeliveryOrderExpressTraceBatch(List<DeliveryOrderExpressTrace> deliveryOrderExpressTraceList){
		return deliveryOrderExpressTraceBaseDao.updateDeliveryOrderExpressTraceBatch(deliveryOrderExpressTraceList);
	}
	/**
	 * 根据序列号删除(供应商快递记录表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteDeliveryOrderExpressTraceLogic(java.math.BigInteger id){
		return deliveryOrderExpressTraceBaseDao.deleteDeliveryOrderExpressTraceLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(供应商快递记录表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteDeliveryOrderExpressTraceLogicBatch(List<java.math.BigInteger> idList){
		return deliveryOrderExpressTraceBaseDao.deleteDeliveryOrderExpressTraceLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(供应商快递记录表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteDeliveryOrderExpressTrace(java.math.BigInteger id){
//		return deliveryOrderExpressTraceBaseDao.deleteDeliveryOrderExpressTrace(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(供应商快递记录表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteDeliveryOrderExpressTraceBatch(List<java.math.BigInteger> idList){
//		return deliveryOrderExpressTraceBaseDao.deleteDeliveryOrderExpressTraceBatch(idList);
//	}
	
}
