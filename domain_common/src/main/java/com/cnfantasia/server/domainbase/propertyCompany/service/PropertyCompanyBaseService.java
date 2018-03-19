package com.cnfantasia.server.domainbase.propertyCompany.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.propertyCompany.dao.IPropertyCompanyBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.propertyCompany.entity.PropertyCompany;

/**
 * 描述:(物业公司) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class PropertyCompanyBaseService implements IPropertyCompanyBaseService{
	
	private IPropertyCompanyBaseDao propertyCompanyBaseDao;
	public void setPropertyCompanyBaseDao(IPropertyCompanyBaseDao propertyCompanyBaseDao) {
		this.propertyCompanyBaseDao = propertyCompanyBaseDao;
	}
	/**
	 * 根据条件查询(物业公司)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<PropertyCompany> getPropertyCompanyByCondition(Map<String,Object> paramMap){
		return propertyCompanyBaseDao.selectPropertyCompanyByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(物业公司)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<PropertyCompany> getPropertyCompanyByConditionDim(Map<String,Object> paramMap){
		return propertyCompanyBaseDao.selectPropertyCompanyByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(物业公司)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<PropertyCompany> getPropertyCompanyByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return propertyCompanyBaseDao.selectPropertyCompanyByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(物业公司)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<PropertyCompany> getPropertyCompanyByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return propertyCompanyBaseDao.selectPropertyCompanyByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(物业公司)信息
	 * @param id
	 * @return
	 */
	@Override
	public PropertyCompany getPropertyCompanyBySeqId(java.math.BigInteger id){
		return propertyCompanyBaseDao.selectPropertyCompanyBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(物业公司)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getPropertyCompanyCount(Map<String,Object> paramMap){
		return propertyCompanyBaseDao.selectPropertyCompanyCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(物业公司)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getPropertyCompanyCountDim(Map<String,Object> paramMap){
		return propertyCompanyBaseDao.selectPropertyCompanyCount(paramMap,true);
	}
	/**
	 * 往(物业公司)新增一条记录
	 * @param propertyCompany
	 * @return
	 */
	@Override
	public int insertPropertyCompany(PropertyCompany propertyCompany){
		return propertyCompanyBaseDao.insertPropertyCompany(propertyCompany);
	}
	/**
	 * 批量新增(物业公司)
	 * @param propertyCompanyList
	 * @return
	 */
	@Override
	public int insertPropertyCompanyBatch(List<PropertyCompany> propertyCompanyList){
		return propertyCompanyBaseDao.insertPropertyCompanyBatch(propertyCompanyList);
	}
	/**
	 * 更新(物业公司)信息
	 * @param propertyCompany
	 * @return
	 */
	@Override
	public int updatePropertyCompany(PropertyCompany propertyCompany){
		return propertyCompanyBaseDao.updatePropertyCompany(propertyCompany);
	}
	/**
	 * 批量更新(物业公司)信息
	 * @param propertyCompanyList
	 * @return
	 */
	@Override
	public int updatePropertyCompanyBatch(List<PropertyCompany> propertyCompanyList){
		return propertyCompanyBaseDao.updatePropertyCompanyBatch(propertyCompanyList);
	}
	/**
	 * 根据序列号删除(物业公司)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deletePropertyCompanyLogic(java.math.BigInteger id){
		return propertyCompanyBaseDao.deletePropertyCompanyLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(物业公司)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deletePropertyCompanyLogicBatch(List<java.math.BigInteger> idList){
		return propertyCompanyBaseDao.deletePropertyCompanyLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(物业公司)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deletePropertyCompany(java.math.BigInteger id){
//		return propertyCompanyBaseDao.deletePropertyCompany(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(物业公司)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deletePropertyCompanyBatch(List<java.math.BigInteger> idList){
//		return propertyCompanyBaseDao.deletePropertyCompanyBatch(idList);
//	}
	
}
