package com.cnfantasia.server.domainbase.supplyMerchantDeliveryFeeSettlement.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.supplyMerchantDeliveryFeeSettlement.dao.ISupplyMerchantDeliveryFeeSettlementBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.supplyMerchantDeliveryFeeSettlement.entity.SupplyMerchantDeliveryFeeSettlement;

/**
 * 描述:(与供应商结算邮费规则) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class SupplyMerchantDeliveryFeeSettlementBaseService implements ISupplyMerchantDeliveryFeeSettlementBaseService{
	
	private ISupplyMerchantDeliveryFeeSettlementBaseDao supplyMerchantDeliveryFeeSettlementBaseDao;
	public void setSupplyMerchantDeliveryFeeSettlementBaseDao(ISupplyMerchantDeliveryFeeSettlementBaseDao supplyMerchantDeliveryFeeSettlementBaseDao) {
		this.supplyMerchantDeliveryFeeSettlementBaseDao = supplyMerchantDeliveryFeeSettlementBaseDao;
	}
	/**
	 * 根据条件查询(与供应商结算邮费规则)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<SupplyMerchantDeliveryFeeSettlement> getSupplyMerchantDeliveryFeeSettlementByCondition(Map<String,Object> paramMap){
		return supplyMerchantDeliveryFeeSettlementBaseDao.selectSupplyMerchantDeliveryFeeSettlementByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(与供应商结算邮费规则)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<SupplyMerchantDeliveryFeeSettlement> getSupplyMerchantDeliveryFeeSettlementByConditionDim(Map<String,Object> paramMap){
		return supplyMerchantDeliveryFeeSettlementBaseDao.selectSupplyMerchantDeliveryFeeSettlementByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(与供应商结算邮费规则)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<SupplyMerchantDeliveryFeeSettlement> getSupplyMerchantDeliveryFeeSettlementByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return supplyMerchantDeliveryFeeSettlementBaseDao.selectSupplyMerchantDeliveryFeeSettlementByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(与供应商结算邮费规则)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<SupplyMerchantDeliveryFeeSettlement> getSupplyMerchantDeliveryFeeSettlementByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return supplyMerchantDeliveryFeeSettlementBaseDao.selectSupplyMerchantDeliveryFeeSettlementByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(与供应商结算邮费规则)信息
	 * @param id
	 * @return
	 */
	@Override
	public SupplyMerchantDeliveryFeeSettlement getSupplyMerchantDeliveryFeeSettlementBySeqId(java.math.BigInteger id){
		return supplyMerchantDeliveryFeeSettlementBaseDao.selectSupplyMerchantDeliveryFeeSettlementBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(与供应商结算邮费规则)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getSupplyMerchantDeliveryFeeSettlementCount(Map<String,Object> paramMap){
		return supplyMerchantDeliveryFeeSettlementBaseDao.selectSupplyMerchantDeliveryFeeSettlementCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(与供应商结算邮费规则)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getSupplyMerchantDeliveryFeeSettlementCountDim(Map<String,Object> paramMap){
		return supplyMerchantDeliveryFeeSettlementBaseDao.selectSupplyMerchantDeliveryFeeSettlementCount(paramMap,true);
	}
	/**
	 * 往(与供应商结算邮费规则)新增一条记录
	 * @param supplyMerchantDeliveryFeeSettlement
	 * @return
	 */
	@Override
	public int insertSupplyMerchantDeliveryFeeSettlement(SupplyMerchantDeliveryFeeSettlement supplyMerchantDeliveryFeeSettlement){
		return supplyMerchantDeliveryFeeSettlementBaseDao.insertSupplyMerchantDeliveryFeeSettlement(supplyMerchantDeliveryFeeSettlement);
	}
	/**
	 * 批量新增(与供应商结算邮费规则)
	 * @param supplyMerchantDeliveryFeeSettlementList
	 * @return
	 */
	@Override
	public int insertSupplyMerchantDeliveryFeeSettlementBatch(List<SupplyMerchantDeliveryFeeSettlement> supplyMerchantDeliveryFeeSettlementList){
		return supplyMerchantDeliveryFeeSettlementBaseDao.insertSupplyMerchantDeliveryFeeSettlementBatch(supplyMerchantDeliveryFeeSettlementList);
	}
	/**
	 * 更新(与供应商结算邮费规则)信息
	 * @param supplyMerchantDeliveryFeeSettlement
	 * @return
	 */
	@Override
	public int updateSupplyMerchantDeliveryFeeSettlement(SupplyMerchantDeliveryFeeSettlement supplyMerchantDeliveryFeeSettlement){
		return supplyMerchantDeliveryFeeSettlementBaseDao.updateSupplyMerchantDeliveryFeeSettlement(supplyMerchantDeliveryFeeSettlement);
	}
	/**
	 * 批量更新(与供应商结算邮费规则)信息
	 * @param supplyMerchantDeliveryFeeSettlementList
	 * @return
	 */
	@Override
	public int updateSupplyMerchantDeliveryFeeSettlementBatch(List<SupplyMerchantDeliveryFeeSettlement> supplyMerchantDeliveryFeeSettlementList){
		return supplyMerchantDeliveryFeeSettlementBaseDao.updateSupplyMerchantDeliveryFeeSettlementBatch(supplyMerchantDeliveryFeeSettlementList);
	}
	/**
	 * 根据序列号删除(与供应商结算邮费规则)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteSupplyMerchantDeliveryFeeSettlementLogic(java.math.BigInteger id){
		return supplyMerchantDeliveryFeeSettlementBaseDao.deleteSupplyMerchantDeliveryFeeSettlementLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(与供应商结算邮费规则)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteSupplyMerchantDeliveryFeeSettlementLogicBatch(List<java.math.BigInteger> idList){
		return supplyMerchantDeliveryFeeSettlementBaseDao.deleteSupplyMerchantDeliveryFeeSettlementLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(与供应商结算邮费规则)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteSupplyMerchantDeliveryFeeSettlement(java.math.BigInteger id){
//		return supplyMerchantDeliveryFeeSettlementBaseDao.deleteSupplyMerchantDeliveryFeeSettlement(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(与供应商结算邮费规则)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteSupplyMerchantDeliveryFeeSettlementBatch(List<java.math.BigInteger> idList){
//		return supplyMerchantDeliveryFeeSettlementBaseDao.deleteSupplyMerchantDeliveryFeeSettlementBatch(idList);
//	}
	
}
