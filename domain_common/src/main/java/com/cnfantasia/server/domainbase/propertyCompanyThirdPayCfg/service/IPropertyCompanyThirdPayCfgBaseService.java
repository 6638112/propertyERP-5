package com.cnfantasia.server.domainbase.propertyCompanyThirdPayCfg.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.propertyCompanyThirdPayCfg.entity.PropertyCompanyThirdPayCfg;

/**
 * 描述:(物业公司/管理处/自有平台的支付宝信息) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IPropertyCompanyThirdPayCfgBaseService {
	
	/**
	 * 根据条件查询(物业公司/管理处/自有平台的支付宝信息)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	List<PropertyCompanyThirdPayCfg> getPropertyCompanyThirdPayCfgByCondition(Map<String, Object> paramMap);
	/**
	 * 根据条件查询(物业公司/管理处/自有平台的支付宝信息)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	List<PropertyCompanyThirdPayCfg> getPropertyCompanyThirdPayCfgByConditionDim(Map<String, Object> paramMap);
	/**
	 * 按条件分页查询(物业公司/管理处/自有平台的支付宝信息)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	List<PropertyCompanyThirdPayCfg> getPropertyCompanyThirdPayCfgByCondition(Map<String, Object> paramMap, PageModel pageModel);
	/**
	 * 按条件分页查询(物业公司/管理处/自有平台的支付宝信息)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	List<PropertyCompanyThirdPayCfg> getPropertyCompanyThirdPayCfgByConditionDim(Map<String, Object> paramMap, PageModel pageModel);
	/**
	 * 根据序列号查询(物业公司/管理处/自有平台的支付宝信息)信息
	 * @param id
	 * @return
	 */
	PropertyCompanyThirdPayCfg getPropertyCompanyThirdPayCfgBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(物业公司/管理处/自有平台的支付宝信息)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	int getPropertyCompanyThirdPayCfgCount(Map<String, Object> paramMap);
	/**
	 * 根据条件查询满足条件的(物业公司/管理处/自有平台的支付宝信息)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	int getPropertyCompanyThirdPayCfgCountDim(Map<String, Object> paramMap);
	/**
	 * 往(物业公司/管理处/自有平台的支付宝信息)新增一条记录
	 * @param propertyCompanyThirdPayCfg
	 * @return
	 */
	int insertPropertyCompanyThirdPayCfg(PropertyCompanyThirdPayCfg propertyCompanyThirdPayCfg);
	/**
	 * 批量新增(物业公司/管理处/自有平台的支付宝信息)
	 * @param propertyCompanyThirdPayCfgList
	 * @return
	 */
	int insertPropertyCompanyThirdPayCfgBatch(List<PropertyCompanyThirdPayCfg> propertyCompanyThirdPayCfgList);
	/**
	 * 更新(物业公司/管理处/自有平台的支付宝信息)信息
	 * @param propertyCompanyThirdPayCfg
	 * @return
	 */
	int updatePropertyCompanyThirdPayCfg(PropertyCompanyThirdPayCfg propertyCompanyThirdPayCfg);
	/**
	 * 批量更新(物业公司/管理处/自有平台的支付宝信息)信息
	 * @param propertyCompanyThirdPayCfgList
	 * @return
	 */
	int updatePropertyCompanyThirdPayCfgBatch(List<PropertyCompanyThirdPayCfg> propertyCompanyThirdPayCfgList);
	/**
	 * 根据序列号删除(物业公司/管理处/自有平台的支付宝信息)信息_逻辑删除
	 * @param id
	 * @return
	 */

	int deletePropertyCompanyThirdPayCfgLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(物业公司/管理处/自有平台的支付宝信息)信息_逻辑删除
	 * @param idList
	 * @return
	 */

	int deletePropertyCompanyThirdPayCfgLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(物业公司/管理处/自有平台的支付宝信息)信息
//	 * @param id
//	 * @return
//	 */
//	public int deletePropertyCompanyThirdPayCfg(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(物业公司/管理处/自有平台的支付宝信息)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deletePropertyCompanyThirdPayCfgBatch(List<java.math.BigInteger> idList);
	
}
