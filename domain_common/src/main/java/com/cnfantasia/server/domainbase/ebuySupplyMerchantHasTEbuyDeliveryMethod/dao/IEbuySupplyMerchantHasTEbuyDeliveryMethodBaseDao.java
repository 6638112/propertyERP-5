package com.cnfantasia.server.domainbase.ebuySupplyMerchantHasTEbuyDeliveryMethod.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuySupplyMerchantHasTEbuyDeliveryMethod.entity.EbuySupplyMerchantHasTEbuyDeliveryMethod;

/**
 * 描述:(供应商支持的配送方式) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IEbuySupplyMerchantHasTEbuyDeliveryMethodBaseDao {
	/**
	 * 根据条件查询(供应商支持的配送方式)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<EbuySupplyMerchantHasTEbuyDeliveryMethod> selectEbuySupplyMerchantHasTEbuyDeliveryMethodByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(供应商支持的配送方式)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<EbuySupplyMerchantHasTEbuyDeliveryMethod> selectEbuySupplyMerchantHasTEbuyDeliveryMethodByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(供应商支持的配送方式)信息
	 * @param id
	 * @return
	 */
	public EbuySupplyMerchantHasTEbuyDeliveryMethod selectEbuySupplyMerchantHasTEbuyDeliveryMethodBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(供应商支持的配送方式)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectEbuySupplyMerchantHasTEbuyDeliveryMethodCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(供应商支持的配送方式)新增一条记录
	 * @param ebuySupplyMerchantHasTEbuyDeliveryMethod
	 * @return
	 */
	public int insertEbuySupplyMerchantHasTEbuyDeliveryMethod(EbuySupplyMerchantHasTEbuyDeliveryMethod ebuySupplyMerchantHasTEbuyDeliveryMethod);
	
	/**
	 * 批量新增(供应商支持的配送方式)信息
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
	 * 根据Id 批量删除(供应商支持的配送方式)信息_逻辑删除
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
