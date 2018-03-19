package com.cnfantasia.server.domainbase.ebuyOrderDeliveryType.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.ebuyOrderDeliveryType.entity.EbuyOrderDeliveryType;

/**
 * 描述:(订单的配送设置) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IEbuyOrderDeliveryTypeBaseService {
	
	/**
	 * 根据条件查询(订单的配送设置)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<EbuyOrderDeliveryType> getEbuyOrderDeliveryTypeByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(订单的配送设置)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<EbuyOrderDeliveryType> getEbuyOrderDeliveryTypeByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(订单的配送设置)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<EbuyOrderDeliveryType> getEbuyOrderDeliveryTypeByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(订单的配送设置)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<EbuyOrderDeliveryType> getEbuyOrderDeliveryTypeByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(订单的配送设置)信息
	 * @param id
	 * @return
	 */
	public EbuyOrderDeliveryType getEbuyOrderDeliveryTypeBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(订单的配送设置)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getEbuyOrderDeliveryTypeCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(订单的配送设置)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getEbuyOrderDeliveryTypeCountDim(Map<String,Object> paramMap);
	/**
	 * 往(订单的配送设置)新增一条记录
	 * @param ebuyOrderDeliveryType
	 * @return
	 */
	public int insertEbuyOrderDeliveryType(EbuyOrderDeliveryType ebuyOrderDeliveryType);
	/**
	 * 批量新增(订单的配送设置)
	 * @param ebuyOrderDeliveryTypeList
	 * @return
	 */
	public int insertEbuyOrderDeliveryTypeBatch(List<EbuyOrderDeliveryType> ebuyOrderDeliveryTypeList);
	/**
	 * 更新(订单的配送设置)信息
	 * @param ebuyOrderDeliveryType
	 * @return
	 */
	public int updateEbuyOrderDeliveryType(EbuyOrderDeliveryType ebuyOrderDeliveryType);
	/**
	 * 批量更新(订单的配送设置)信息
	 * @param ebuyOrderDeliveryTypeList
	 * @return
	 */
	public int updateEbuyOrderDeliveryTypeBatch(List<EbuyOrderDeliveryType> ebuyOrderDeliveryTypeList);
	/**
	 * 根据序列号删除(订单的配送设置)信息_逻辑删除
	 * @param id
	 * @return
	 */
	/* 
	public int deleteEbuyOrderDeliveryTypeLogic(java.math.BigInteger id);
	 */
	/**
	 * 根据序列号批量删除(订单的配送设置)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	/* 
	public int deleteEbuyOrderDeliveryTypeLogicBatch(List<java.math.BigInteger> idList);
	 */
//	/**
//	 * 根据序列号删除(订单的配送设置)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteEbuyOrderDeliveryType(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(订单的配送设置)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteEbuyOrderDeliveryTypeBatch(List<java.math.BigInteger> idList);
	
}
