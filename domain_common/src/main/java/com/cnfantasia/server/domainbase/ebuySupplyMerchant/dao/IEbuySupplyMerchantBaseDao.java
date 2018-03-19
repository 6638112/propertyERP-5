package com.cnfantasia.server.domainbase.ebuySupplyMerchant.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuySupplyMerchant.entity.EbuySupplyMerchant;

/**
 * 描述:(供应商) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IEbuySupplyMerchantBaseDao {
	/**
	 * 根据条件查询(供应商)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<EbuySupplyMerchant> selectEbuySupplyMerchantByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(供应商)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<EbuySupplyMerchant> selectEbuySupplyMerchantByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(供应商)信息
	 * @param id
	 * @return
	 */
	public EbuySupplyMerchant selectEbuySupplyMerchantBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(供应商)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectEbuySupplyMerchantCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(供应商)新增一条记录
	 * @param ebuySupplyMerchant
	 * @return
	 */
	public int insertEbuySupplyMerchant(EbuySupplyMerchant ebuySupplyMerchant);
	
	/**
	 * 批量新增(供应商)信息
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
	 * 根据Id 批量删除(供应商)信息_逻辑删除
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
