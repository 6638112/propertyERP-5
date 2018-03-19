package com.cnfantasia.server.domainbase.supplyMerchantDeliveryFeeSettlement.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.supplyMerchantDeliveryFeeSettlement.entity.SupplyMerchantDeliveryFeeSettlement;

/**
 * 描述:(与供应商结算邮费规则) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ISupplyMerchantDeliveryFeeSettlementBaseService {
	
	/**
	 * 根据条件查询(与供应商结算邮费规则)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<SupplyMerchantDeliveryFeeSettlement> getSupplyMerchantDeliveryFeeSettlementByCondition(Map<String, Object> paramMap);
	/**
	 * 根据条件查询(与供应商结算邮费规则)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<SupplyMerchantDeliveryFeeSettlement> getSupplyMerchantDeliveryFeeSettlementByConditionDim(Map<String, Object> paramMap);
	/**
	 * 按条件分页查询(与供应商结算邮费规则)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<SupplyMerchantDeliveryFeeSettlement> getSupplyMerchantDeliveryFeeSettlementByCondition(Map<String, Object> paramMap, PageModel pageModel);
	/**
	 * 按条件分页查询(与供应商结算邮费规则)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<SupplyMerchantDeliveryFeeSettlement> getSupplyMerchantDeliveryFeeSettlementByConditionDim(Map<String, Object> paramMap, PageModel pageModel);
	/**
	 * 根据序列号查询(与供应商结算邮费规则)信息
	 * @param id
	 * @return
	 */
	public SupplyMerchantDeliveryFeeSettlement getSupplyMerchantDeliveryFeeSettlementBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(与供应商结算邮费规则)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getSupplyMerchantDeliveryFeeSettlementCount(Map<String, Object> paramMap);
	/**
	 * 根据条件查询满足条件的(与供应商结算邮费规则)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getSupplyMerchantDeliveryFeeSettlementCountDim(Map<String, Object> paramMap);
	/**
	 * 往(与供应商结算邮费规则)新增一条记录
	 * @param supplyMerchantDeliveryFeeSettlement
	 * @return
	 */
	public int insertSupplyMerchantDeliveryFeeSettlement(SupplyMerchantDeliveryFeeSettlement supplyMerchantDeliveryFeeSettlement);
	/**
	 * 批量新增(与供应商结算邮费规则)
	 * @param supplyMerchantDeliveryFeeSettlementList
	 * @return
	 */
	public int insertSupplyMerchantDeliveryFeeSettlementBatch(List<SupplyMerchantDeliveryFeeSettlement> supplyMerchantDeliveryFeeSettlementList);
	/**
	 * 更新(与供应商结算邮费规则)信息
	 * @param supplyMerchantDeliveryFeeSettlement
	 * @return
	 */
	public int updateSupplyMerchantDeliveryFeeSettlement(SupplyMerchantDeliveryFeeSettlement supplyMerchantDeliveryFeeSettlement);
	/**
	 * 批量更新(与供应商结算邮费规则)信息
	 * @param supplyMerchantDeliveryFeeSettlementList
	 * @return
	 */
	public int updateSupplyMerchantDeliveryFeeSettlementBatch(List<SupplyMerchantDeliveryFeeSettlement> supplyMerchantDeliveryFeeSettlementList);
	/**
	 * 根据序列号删除(与供应商结算邮费规则)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteSupplyMerchantDeliveryFeeSettlementLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(与供应商结算邮费规则)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteSupplyMerchantDeliveryFeeSettlementLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(与供应商结算邮费规则)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteSupplyMerchantDeliveryFeeSettlement(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(与供应商结算邮费规则)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteSupplyMerchantDeliveryFeeSettlementBatch(List<java.math.BigInteger> idList);
	
}
