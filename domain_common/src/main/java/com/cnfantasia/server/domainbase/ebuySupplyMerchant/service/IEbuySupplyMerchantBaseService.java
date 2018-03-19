package com.cnfantasia.server.domainbase.ebuySupplyMerchant.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.ebuySupplyMerchant.entity.EbuySupplyMerchant;

/**
 * 描述:(供应商) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IEbuySupplyMerchantBaseService {
	
	/**
	 * 根据条件查询(供应商)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<EbuySupplyMerchant> getEbuySupplyMerchantByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(供应商)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<EbuySupplyMerchant> getEbuySupplyMerchantByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(供应商)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<EbuySupplyMerchant> getEbuySupplyMerchantByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(供应商)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<EbuySupplyMerchant> getEbuySupplyMerchantByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(供应商)信息
	 * @param id
	 * @return
	 */
	public EbuySupplyMerchant getEbuySupplyMerchantBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(供应商)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getEbuySupplyMerchantCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(供应商)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getEbuySupplyMerchantCountDim(Map<String,Object> paramMap);
	/**
	 * 往(供应商)新增一条记录
	 * @param ebuySupplyMerchant
	 * @return
	 */
	public int insertEbuySupplyMerchant(EbuySupplyMerchant ebuySupplyMerchant);
	/**
	 * 批量新增(供应商)
	 * @param ebuySupplyMerchantList
	 * @return
	 */
	public int insertEbuySupplyMerchantBatch(List<EbuySupplyMerchant> ebuySupplyMerchantList);
	/**
	 * 更新(供应商)信息
	 * @param ebuySupplyMerchant
	 * @return
	 */
	public int updateEbuySupplyMerchant(EbuySupplyMerchant ebuySupplyMerchant);
	/**
	 * 批量更新(供应商)信息
	 * @param ebuySupplyMerchantList
	 * @return
	 */
	public int updateEbuySupplyMerchantBatch(List<EbuySupplyMerchant> ebuySupplyMerchantList);
	/**
	 * 根据序列号删除(供应商)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteEbuySupplyMerchantLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(供应商)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteEbuySupplyMerchantLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(供应商)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteEbuySupplyMerchant(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(供应商)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteEbuySupplyMerchantBatch(List<java.math.BigInteger> idList);
	
}
