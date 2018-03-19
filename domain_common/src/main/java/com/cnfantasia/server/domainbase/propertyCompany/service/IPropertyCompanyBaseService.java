package com.cnfantasia.server.domainbase.propertyCompany.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.propertyCompany.entity.PropertyCompany;

/**
 * 描述:(物业公司) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IPropertyCompanyBaseService {
	
	/**
	 * 根据条件查询(物业公司)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<PropertyCompany> getPropertyCompanyByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(物业公司)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<PropertyCompany> getPropertyCompanyByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(物业公司)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<PropertyCompany> getPropertyCompanyByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(物业公司)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<PropertyCompany> getPropertyCompanyByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(物业公司)信息
	 * @param id
	 * @return
	 */
	public PropertyCompany getPropertyCompanyBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(物业公司)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getPropertyCompanyCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(物业公司)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getPropertyCompanyCountDim(Map<String,Object> paramMap);
	/**
	 * 往(物业公司)新增一条记录
	 * @param propertyCompany
	 * @return
	 */
	public int insertPropertyCompany(PropertyCompany propertyCompany);
	/**
	 * 批量新增(物业公司)
	 * @param propertyCompanyList
	 * @return
	 */
	public int insertPropertyCompanyBatch(List<PropertyCompany> propertyCompanyList);
	/**
	 * 更新(物业公司)信息
	 * @param propertyCompany
	 * @return
	 */
	public int updatePropertyCompany(PropertyCompany propertyCompany);
	/**
	 * 批量更新(物业公司)信息
	 * @param propertyCompanyList
	 * @return
	 */
	public int updatePropertyCompanyBatch(List<PropertyCompany> propertyCompanyList);
	/**
	 * 根据序列号删除(物业公司)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deletePropertyCompanyLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(物业公司)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deletePropertyCompanyLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(物业公司)信息
//	 * @param id
//	 * @return
//	 */
//	public int deletePropertyCompany(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(物业公司)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deletePropertyCompanyBatch(List<java.math.BigInteger> idList);
	
}
