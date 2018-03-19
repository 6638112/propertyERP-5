package com.cnfantasia.server.domainbase.ebuyFightOrder.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.ebuyFightOrder.entity.EbuyFightOrder;

/**
 * 描述:(拼购详情订单) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IEbuyFightOrderBaseService {
	
	/**
	 * 根据条件查询(拼购详情订单)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<EbuyFightOrder> getEbuyFightOrderByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(拼购详情订单)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<EbuyFightOrder> getEbuyFightOrderByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(拼购详情订单)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<EbuyFightOrder> getEbuyFightOrderByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(拼购详情订单)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<EbuyFightOrder> getEbuyFightOrderByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(拼购详情订单)信息
	 * @param id
	 * @return
	 */
	public EbuyFightOrder getEbuyFightOrderBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(拼购详情订单)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getEbuyFightOrderCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(拼购详情订单)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getEbuyFightOrderCountDim(Map<String,Object> paramMap);
	/**
	 * 往(拼购详情订单)新增一条记录
	 * @param ebuyFightOrder
	 * @return
	 */
	public int insertEbuyFightOrder(EbuyFightOrder ebuyFightOrder);
	/**
	 * 批量新增(拼购详情订单)
	 * @param ebuyFightOrderList
	 * @return
	 */
	public int insertEbuyFightOrderBatch(List<EbuyFightOrder> ebuyFightOrderList);
	/**
	 * 更新(拼购详情订单)信息
	 * @param ebuyFightOrder
	 * @return
	 */
	public int updateEbuyFightOrder(EbuyFightOrder ebuyFightOrder);
	/**
	 * 批量更新(拼购详情订单)信息
	 * @param ebuyFightOrderList
	 * @return
	 */
	public int updateEbuyFightOrderBatch(List<EbuyFightOrder> ebuyFightOrderList);
	/**
	 * 根据序列号删除(拼购详情订单)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteEbuyFightOrderLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(拼购详情订单)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteEbuyFightOrderLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(拼购详情订单)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteEbuyFightOrder(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(拼购详情订单)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteEbuyFightOrderBatch(List<java.math.BigInteger> idList);
	
}
