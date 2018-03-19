package com.cnfantasia.server.domainbase.propertyProprietor.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.propertyProprietor.dao.IPropertyProprietorBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.propertyProprietor.entity.PropertyProprietor;

/**
 * 描述:(业主信息表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class PropertyProprietorBaseService implements IPropertyProprietorBaseService{
	
	private IPropertyProprietorBaseDao propertyProprietorBaseDao;
	public void setPropertyProprietorBaseDao(IPropertyProprietorBaseDao propertyProprietorBaseDao) {
		this.propertyProprietorBaseDao = propertyProprietorBaseDao;
	}
	/**
	 * 根据条件查询(业主信息表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<PropertyProprietor> getPropertyProprietorByCondition(Map<String,Object> paramMap){
		return propertyProprietorBaseDao.selectPropertyProprietorByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(业主信息表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<PropertyProprietor> getPropertyProprietorByConditionDim(Map<String,Object> paramMap){
		return propertyProprietorBaseDao.selectPropertyProprietorByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(业主信息表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<PropertyProprietor> getPropertyProprietorByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return propertyProprietorBaseDao.selectPropertyProprietorByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(业主信息表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<PropertyProprietor> getPropertyProprietorByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return propertyProprietorBaseDao.selectPropertyProprietorByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(业主信息表)信息
	 * @param id
	 * @return
	 */
	@Override
	public PropertyProprietor getPropertyProprietorBySeqId(java.math.BigInteger id){
		return propertyProprietorBaseDao.selectPropertyProprietorBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(业主信息表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getPropertyProprietorCount(Map<String,Object> paramMap){
		return propertyProprietorBaseDao.selectPropertyProprietorCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(业主信息表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getPropertyProprietorCountDim(Map<String,Object> paramMap){
		return propertyProprietorBaseDao.selectPropertyProprietorCount(paramMap,true);
	}
	/**
	 * 往(业主信息表)新增一条记录
	 * @param propertyProprietor
	 * @return
	 */
	@Override
	public int insertPropertyProprietor(PropertyProprietor propertyProprietor){
		return propertyProprietorBaseDao.insertPropertyProprietor(propertyProprietor);
	}
	/**
	 * 批量新增(业主信息表)
	 * @param propertyProprietorList
	 * @return
	 */
	@Override
	public int insertPropertyProprietorBatch(List<PropertyProprietor> propertyProprietorList){
		return propertyProprietorBaseDao.insertPropertyProprietorBatch(propertyProprietorList);
	}
	/**
	 * 更新(业主信息表)信息
	 * @param propertyProprietor
	 * @return
	 */
	@Override
	public int updatePropertyProprietor(PropertyProprietor propertyProprietor){
		return propertyProprietorBaseDao.updatePropertyProprietor(propertyProprietor);
	}
	/**
	 * 批量更新(业主信息表)信息
	 * @param propertyProprietorList
	 * @return
	 */
	@Override
	public int updatePropertyProprietorBatch(List<PropertyProprietor> propertyProprietorList){
		return propertyProprietorBaseDao.updatePropertyProprietorBatch(propertyProprietorList);
	}
	/**
	 * 根据序列号删除(业主信息表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deletePropertyProprietorLogic(java.math.BigInteger id){
		return propertyProprietorBaseDao.deletePropertyProprietorLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(业主信息表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deletePropertyProprietorLogicBatch(List<java.math.BigInteger> idList){
		return propertyProprietorBaseDao.deletePropertyProprietorLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(业主信息表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deletePropertyProprietor(java.math.BigInteger id){
//		return propertyProprietorBaseDao.deletePropertyProprietor(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(业主信息表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deletePropertyProprietorBatch(List<java.math.BigInteger> idList){
//		return propertyProprietorBaseDao.deletePropertyProprietorBatch(idList);
//	}
	
}
