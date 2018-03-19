package com.cnfantasia.server.domainbase.ebuySupplyMerchantHasTEbuyDeliveryMethod.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.ebuySupplyMerchantHasTEbuyDeliveryMethod.entity.EbuySupplyMerchantHasTEbuyDeliveryMethod;

/**
 * 描述:(供应商支持的配送方式) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IEbuySupplyMerchantHasTEbuyDeliveryMethodBaseService {
	
	/**
	 * 根据条件查询(供应商支持的配送方式)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<EbuySupplyMerchantHasTEbuyDeliveryMethod> getEbuySupplyMerchantHasTEbuyDeliveryMethodByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(供应商支持的配送方式)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<EbuySupplyMerchantHasTEbuyDeliveryMethod> getEbuySupplyMerchantHasTEbuyDeliveryMethodByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(供应商支持的配送方式)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<EbuySupplyMerchantHasTEbuyDeliveryMethod> getEbuySupplyMerchantHasTEbuyDeliveryMethodByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(供应商支持的配送方式)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<EbuySupplyMerchantHasTEbuyDeliveryMethod> getEbuySupplyMerchantHasTEbuyDeliveryMethodByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(供应商支持的配送方式)信息
	 * @param id
	 * @return
	 */
	public EbuySupplyMerchantHasTEbuyDeliveryMethod getEbuySupplyMerchantHasTEbuyDeliveryMethodBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(供应商支持的配送方式)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getEbuySupplyMerchantHasTEbuyDeliveryMethodCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(供应商支持的配送方式)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getEbuySupplyMerchantHasTEbuyDeliveryMethodCountDim(Map<String,Object> paramMap);
	/**
	 * 往(供应商支持的配送方式)新增一条记录
	 * @param ebuySupplyMerchantHasTEbuyDeliveryMethod
	 * @return
	 */
	public int insertEbuySupplyMerchantHasTEbuyDeliveryMethod(EbuySupplyMerchantHasTEbuyDeliveryMethod ebuySupplyMerchantHasTEbuyDeliveryMethod);
	/**
	 * 批量新增(供应商支持的配送方式)
	 * @param ebuySupplyMerchantHasTEbuyDeliveryMethodList
	 * @return
	 */
	public int insertEbuySupplyMerchantHasTEbuyDeliveryMethodBatch(List<EbuySupplyMerchantHasTEbuyDeliveryMethod> ebuySupplyMerchantHasTEbuyDeliveryMethodList);
	/**
	 * 更新(供应商支持的配送方式)信息
	 * @param ebuySupplyMerchantHasTEbuyDeliveryMethod
	 * @return
	 */
	public int updateEbuySupplyMerchantHasTEbuyDeliveryMethod(EbuySupplyMerchantHasTEbuyDeliveryMethod ebuySupplyMerchantHasTEbuyDeliveryMethod);
	/**
	 * 批量更新(供应商支持的配送方式)信息
	 * @param ebuySupplyMerchantHasTEbuyDeliveryMethodList
	 * @return
	 */
	public int updateEbuySupplyMerchantHasTEbuyDeliveryMethodBatch(List<EbuySupplyMerchantHasTEbuyDeliveryMethod> ebuySupplyMerchantHasTEbuyDeliveryMethodList);
	/**
	 * 根据序列号删除(供应商支持的配送方式)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteEbuySupplyMerchantHasTEbuyDeliveryMethodLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(供应商支持的配送方式)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteEbuySupplyMerchantHasTEbuyDeliveryMethodLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(供应商支持的配送方式)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteEbuySupplyMerchantHasTEbuyDeliveryMethod(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(供应商支持的配送方式)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteEbuySupplyMerchantHasTEbuyDeliveryMethodBatch(List<java.math.BigInteger> idList);
	
}
