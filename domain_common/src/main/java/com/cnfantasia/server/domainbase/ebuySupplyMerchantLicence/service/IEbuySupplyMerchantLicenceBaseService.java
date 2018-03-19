package com.cnfantasia.server.domainbase.ebuySupplyMerchantLicence.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.ebuySupplyMerchantLicence.entity.EbuySupplyMerchantLicence;

/**
 * 描述:(供应商营业执照图片) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IEbuySupplyMerchantLicenceBaseService {
	
	/**
	 * 根据条件查询(供应商营业执照图片)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<EbuySupplyMerchantLicence> getEbuySupplyMerchantLicenceByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(供应商营业执照图片)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<EbuySupplyMerchantLicence> getEbuySupplyMerchantLicenceByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(供应商营业执照图片)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<EbuySupplyMerchantLicence> getEbuySupplyMerchantLicenceByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(供应商营业执照图片)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<EbuySupplyMerchantLicence> getEbuySupplyMerchantLicenceByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(供应商营业执照图片)信息
	 * @param id
	 * @return
	 */
	public EbuySupplyMerchantLicence getEbuySupplyMerchantLicenceBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(供应商营业执照图片)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getEbuySupplyMerchantLicenceCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(供应商营业执照图片)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getEbuySupplyMerchantLicenceCountDim(Map<String,Object> paramMap);
	/**
	 * 往(供应商营业执照图片)新增一条记录
	 * @param ebuySupplyMerchantLicence
	 * @return
	 */
	public int insertEbuySupplyMerchantLicence(EbuySupplyMerchantLicence ebuySupplyMerchantLicence);
	/**
	 * 批量新增(供应商营业执照图片)
	 * @param ebuySupplyMerchantLicenceList
	 * @return
	 */
	public int insertEbuySupplyMerchantLicenceBatch(List<EbuySupplyMerchantLicence> ebuySupplyMerchantLicenceList);
	/**
	 * 更新(供应商营业执照图片)信息
	 * @param ebuySupplyMerchantLicence
	 * @return
	 */
	public int updateEbuySupplyMerchantLicence(EbuySupplyMerchantLicence ebuySupplyMerchantLicence);
	/**
	 * 批量更新(供应商营业执照图片)信息
	 * @param ebuySupplyMerchantLicenceList
	 * @return
	 */
	public int updateEbuySupplyMerchantLicenceBatch(List<EbuySupplyMerchantLicence> ebuySupplyMerchantLicenceList);
	/**
	 * 根据序列号删除(供应商营业执照图片)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteEbuySupplyMerchantLicenceLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(供应商营业执照图片)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteEbuySupplyMerchantLicenceLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(供应商营业执照图片)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteEbuySupplyMerchantLicence(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(供应商营业执照图片)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteEbuySupplyMerchantLicenceBatch(List<java.math.BigInteger> idList);
	
}
