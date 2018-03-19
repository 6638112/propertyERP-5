package com.cnfantasia.server.domainbase.deliveryOrderExpressTrace.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.deliveryOrderExpressTrace.entity.DeliveryOrderExpressTrace;

/**
 * 描述:(供应商快递记录表) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IDeliveryOrderExpressTraceBaseService {
	
	/**
	 * 根据条件查询(供应商快递记录表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<DeliveryOrderExpressTrace> getDeliveryOrderExpressTraceByCondition(Map<String, Object> paramMap);
	/**
	 * 根据条件查询(供应商快递记录表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<DeliveryOrderExpressTrace> getDeliveryOrderExpressTraceByConditionDim(Map<String, Object> paramMap);
	/**
	 * 按条件分页查询(供应商快递记录表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<DeliveryOrderExpressTrace> getDeliveryOrderExpressTraceByCondition(Map<String, Object> paramMap, PageModel pageModel);
	/**
	 * 按条件分页查询(供应商快递记录表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<DeliveryOrderExpressTrace> getDeliveryOrderExpressTraceByConditionDim(Map<String, Object> paramMap, PageModel pageModel);
	/**
	 * 根据序列号查询(供应商快递记录表)信息
	 * @param id
	 * @return
	 */
	public DeliveryOrderExpressTrace getDeliveryOrderExpressTraceBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(供应商快递记录表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getDeliveryOrderExpressTraceCount(Map<String, Object> paramMap);
	/**
	 * 根据条件查询满足条件的(供应商快递记录表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getDeliveryOrderExpressTraceCountDim(Map<String, Object> paramMap);
	/**
	 * 往(供应商快递记录表)新增一条记录
	 * @param deliveryOrderExpressTrace
	 * @return
	 */
	public int insertDeliveryOrderExpressTrace(DeliveryOrderExpressTrace deliveryOrderExpressTrace);
	/**
	 * 批量新增(供应商快递记录表)
	 * @param deliveryOrderExpressTraceList
	 * @return
	 */
	public int insertDeliveryOrderExpressTraceBatch(List<DeliveryOrderExpressTrace> deliveryOrderExpressTraceList);
	/**
	 * 更新(供应商快递记录表)信息
	 * @param deliveryOrderExpressTrace
	 * @return
	 */
	public int updateDeliveryOrderExpressTrace(DeliveryOrderExpressTrace deliveryOrderExpressTrace);
	/**
	 * 批量更新(供应商快递记录表)信息
	 * @param deliveryOrderExpressTraceList
	 * @return
	 */
	public int updateDeliveryOrderExpressTraceBatch(List<DeliveryOrderExpressTrace> deliveryOrderExpressTraceList);
	/**
	 * 根据序列号删除(供应商快递记录表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteDeliveryOrderExpressTraceLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(供应商快递记录表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteDeliveryOrderExpressTraceLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(供应商快递记录表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteDeliveryOrderExpressTrace(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(供应商快递记录表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteDeliveryOrderExpressTraceBatch(List<java.math.BigInteger> idList);
	
}
