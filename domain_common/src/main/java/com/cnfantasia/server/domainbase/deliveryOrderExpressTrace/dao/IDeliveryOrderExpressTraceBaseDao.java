package com.cnfantasia.server.domainbase.deliveryOrderExpressTrace.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.deliveryOrderExpressTrace.entity.DeliveryOrderExpressTrace;

/**
 * 描述:(供应商快递记录表) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IDeliveryOrderExpressTraceBaseDao {
	/**
	 * 根据条件查询(供应商快递记录表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<DeliveryOrderExpressTrace> selectDeliveryOrderExpressTraceByCondition(Map<String, Object> paramMap, boolean isDim);
	/**
	 * 按条件分页查询(供应商快递记录表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<DeliveryOrderExpressTrace> selectDeliveryOrderExpressTraceByCondition(Map<String, Object> paramMap, PageModel pageModel, boolean isDim);
	/**
	 * 根据序列号查询(供应商快递记录表)信息
	 * @param id
	 * @return
	 */
	public DeliveryOrderExpressTrace selectDeliveryOrderExpressTraceBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(供应商快递记录表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectDeliveryOrderExpressTraceCount(Map<String, Object> paramMap, boolean isDim);
	/**
	 * 往(供应商快递记录表)新增一条记录
	 * @param deliveryOrderExpressTrace
	 * @return
	 */
	public int insertDeliveryOrderExpressTrace(DeliveryOrderExpressTrace deliveryOrderExpressTrace);
	
	/**
	 * 批量新增(供应商快递记录表)信息
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
	 * 根据Id 批量删除(供应商快递记录表)信息_逻辑删除
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
