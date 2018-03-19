package com.cnfantasia.server.domainbase.ebuyDeliveryOrder.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.ebuyDeliveryOrder.entity.EbuyDeliveryOrder;

/**
 * 描述:(送货单) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IEbuyDeliveryOrderBaseService {
	
	/**
	 * 根据条件查询(送货单)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<EbuyDeliveryOrder> getEbuyDeliveryOrderByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(送货单)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<EbuyDeliveryOrder> getEbuyDeliveryOrderByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(送货单)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<EbuyDeliveryOrder> getEbuyDeliveryOrderByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(送货单)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<EbuyDeliveryOrder> getEbuyDeliveryOrderByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(送货单)信息
	 * @param id
	 * @return
	 */
	public EbuyDeliveryOrder getEbuyDeliveryOrderBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(送货单)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getEbuyDeliveryOrderCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(送货单)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getEbuyDeliveryOrderCountDim(Map<String,Object> paramMap);
	/**
	 * 往(送货单)新增一条记录
	 * @param ebuyDeliveryOrder
	 * @return
	 */
	public int insertEbuyDeliveryOrder(EbuyDeliveryOrder ebuyDeliveryOrder);
	/**
	 * 批量新增(送货单)
	 * @param ebuyDeliveryOrderList
	 * @return
	 */
	public int insertEbuyDeliveryOrderBatch(List<EbuyDeliveryOrder> ebuyDeliveryOrderList);
	/**
	 * 更新(送货单)信息
	 * @param ebuyDeliveryOrder
	 * @return
	 */
	public int updateEbuyDeliveryOrder(EbuyDeliveryOrder ebuyDeliveryOrder);
	/**
	 * 批量更新(送货单)信息
	 * @param ebuyDeliveryOrderList
	 * @return
	 */
	public int updateEbuyDeliveryOrderBatch(List<EbuyDeliveryOrder> ebuyDeliveryOrderList);
	/**
	 * 根据序列号删除(送货单)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteEbuyDeliveryOrderLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(送货单)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteEbuyDeliveryOrderLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(送货单)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteEbuyDeliveryOrder(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(送货单)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteEbuyDeliveryOrderBatch(List<java.math.BigInteger> idList);
	
}
