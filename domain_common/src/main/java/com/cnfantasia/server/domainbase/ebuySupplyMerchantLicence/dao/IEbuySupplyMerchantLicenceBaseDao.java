package com.cnfantasia.server.domainbase.ebuySupplyMerchantLicence.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuySupplyMerchantLicence.entity.EbuySupplyMerchantLicence;

/**
 * 描述:(供应商营业执照图片) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IEbuySupplyMerchantLicenceBaseDao {
	/**
	 * 根据条件查询(供应商营业执照图片)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<EbuySupplyMerchantLicence> selectEbuySupplyMerchantLicenceByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(供应商营业执照图片)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<EbuySupplyMerchantLicence> selectEbuySupplyMerchantLicenceByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(供应商营业执照图片)信息
	 * @param id
	 * @return
	 */
	public EbuySupplyMerchantLicence selectEbuySupplyMerchantLicenceBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(供应商营业执照图片)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectEbuySupplyMerchantLicenceCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(供应商营业执照图片)新增一条记录
	 * @param ebuySupplyMerchantLicence
	 * @return
	 */
	public int insertEbuySupplyMerchantLicence(EbuySupplyMerchantLicence ebuySupplyMerchantLicence);
	
	/**
	 * 批量新增(供应商营业执照图片)信息
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
	 * 根据Id 批量删除(供应商营业执照图片)信息_逻辑删除
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
