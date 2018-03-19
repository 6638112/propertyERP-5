package com.cnfantasia.server.domainbase.ebuyRefundOrder.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.ebuyRefundOrder.entity.EbuyRefundOrder;

/**
 * 描述:(退货订单) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IEbuyRefundOrderBaseService {
	
	/**
	 * 根据条件查询(退货订单)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<EbuyRefundOrder> getEbuyRefundOrderByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(退货订单)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<EbuyRefundOrder> getEbuyRefundOrderByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(退货订单)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<EbuyRefundOrder> getEbuyRefundOrderByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(退货订单)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<EbuyRefundOrder> getEbuyRefundOrderByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(退货订单)信息
	 * @param id
	 * @return
	 */
	public EbuyRefundOrder getEbuyRefundOrderBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(退货订单)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getEbuyRefundOrderCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(退货订单)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getEbuyRefundOrderCountDim(Map<String,Object> paramMap);
	/**
	 * 往(退货订单)新增一条记录
	 * @param ebuyRefundOrder
	 * @return
	 */
	public int insertEbuyRefundOrder(EbuyRefundOrder ebuyRefundOrder);
	/**
	 * 批量新增(退货订单)
	 * @param ebuyRefundOrderList
	 * @return
	 */
	public int insertEbuyRefundOrderBatch(List<EbuyRefundOrder> ebuyRefundOrderList);
	/**
	 * 更新(退货订单)信息
	 * @param ebuyRefundOrder
	 * @return
	 */
	public int updateEbuyRefundOrder(EbuyRefundOrder ebuyRefundOrder);
	/**
	 * 批量更新(退货订单)信息
	 * @param ebuyRefundOrderList
	 * @return
	 */
	public int updateEbuyRefundOrderBatch(List<EbuyRefundOrder> ebuyRefundOrderList);
	/**
	 * 根据序列号删除(退货订单)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteEbuyRefundOrderLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(退货订单)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteEbuyRefundOrderLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(退货订单)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteEbuyRefundOrder(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(退货订单)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteEbuyRefundOrderBatch(List<java.math.BigInteger> idList);
	
}
