package com.cnfantasia.server.domainbase.propertyCompany.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.propertyCompany.entity.PropertyCompany;

/**
 * 描述:(物业公司) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IPropertyCompanyBaseDao {
	/**
	 * 根据条件查询(物业公司)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<PropertyCompany> selectPropertyCompanyByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(物业公司)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<PropertyCompany> selectPropertyCompanyByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(物业公司)信息
	 * @param id
	 * @return
	 */
	public PropertyCompany selectPropertyCompanyBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(物业公司)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectPropertyCompanyCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(物业公司)新增一条记录
	 * @param propertyCompany
	 * @return
	 */
	public int insertPropertyCompany(PropertyCompany propertyCompany);
	
	/**
	 * 批量新增(物业公司)信息
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
	 * 根据Id 批量删除(物业公司)信息_逻辑删除
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
