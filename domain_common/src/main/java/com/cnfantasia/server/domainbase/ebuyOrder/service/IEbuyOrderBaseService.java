package com.cnfantasia.server.domainbase.ebuyOrder.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.ebuyOrder.entity.EbuyOrder;

/**
 * 描述:(订单表) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IEbuyOrderBaseService {
	
	/**
	 * 根据条件查询(订单表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<EbuyOrder> getEbuyOrderByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(订单表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<EbuyOrder> getEbuyOrderByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(订单表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<EbuyOrder> getEbuyOrderByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(订单表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<EbuyOrder> getEbuyOrderByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(订单表)信息
	 * @param id
	 * @return
	 */
	public EbuyOrder getEbuyOrderBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(订单表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getEbuyOrderCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(订单表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getEbuyOrderCountDim(Map<String,Object> paramMap);
	/**
	 * 往(订单表)新增一条记录
	 * @param ebuyOrder
	 * @return
	 */
	public int insertEbuyOrder(EbuyOrder ebuyOrder);
	/**
	 * 批量新增(订单表)
	 * @param ebuyOrderList
	 * @return
	 */
	public int insertEbuyOrderBatch(List<EbuyOrder> ebuyOrderList);
	/**
	 * 更新(订单表)信息
	 * @param ebuyOrder
	 * @return
	 */
	public int updateEbuyOrder(EbuyOrder ebuyOrder);
	/**
	 * 批量更新(订单表)信息
	 * @param ebuyOrderList
	 * @return
	 */
	public int updateEbuyOrderBatch(List<EbuyOrder> ebuyOrderList);
	/**
	 * 根据序列号删除(订单表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteEbuyOrderLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(订单表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteEbuyOrderLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(订单表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteEbuyOrder(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(订单表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteEbuyOrderBatch(List<java.math.BigInteger> idList);
	
}
