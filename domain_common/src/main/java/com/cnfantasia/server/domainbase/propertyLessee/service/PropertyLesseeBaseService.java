package com.cnfantasia.server.domainbase.propertyLessee.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.propertyLessee.dao.IPropertyLesseeBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.propertyLessee.entity.PropertyLessee;

/**
 * 描述:(租户信息表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class PropertyLesseeBaseService implements IPropertyLesseeBaseService{
	
	private IPropertyLesseeBaseDao propertyLesseeBaseDao;
	public void setPropertyLesseeBaseDao(IPropertyLesseeBaseDao propertyLesseeBaseDao) {
		this.propertyLesseeBaseDao = propertyLesseeBaseDao;
	}
	/**
	 * 根据条件查询(租户信息表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<PropertyLessee> getPropertyLesseeByCondition(Map<String,Object> paramMap){
		return propertyLesseeBaseDao.selectPropertyLesseeByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(租户信息表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<PropertyLessee> getPropertyLesseeByConditionDim(Map<String,Object> paramMap){
		return propertyLesseeBaseDao.selectPropertyLesseeByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(租户信息表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<PropertyLessee> getPropertyLesseeByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return propertyLesseeBaseDao.selectPropertyLesseeByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(租户信息表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<PropertyLessee> getPropertyLesseeByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return propertyLesseeBaseDao.selectPropertyLesseeByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(租户信息表)信息
	 * @param id
	 * @return
	 */
	@Override
	public PropertyLessee getPropertyLesseeBySeqId(java.math.BigInteger id){
		return propertyLesseeBaseDao.selectPropertyLesseeBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(租户信息表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getPropertyLesseeCount(Map<String,Object> paramMap){
		return propertyLesseeBaseDao.selectPropertyLesseeCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(租户信息表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getPropertyLesseeCountDim(Map<String,Object> paramMap){
		return propertyLesseeBaseDao.selectPropertyLesseeCount(paramMap,true);
	}
	/**
	 * 往(租户信息表)新增一条记录
	 * @param propertyLessee
	 * @return
	 */
	@Override
	public int insertPropertyLessee(PropertyLessee propertyLessee){
		return propertyLesseeBaseDao.insertPropertyLessee(propertyLessee);
	}
	/**
	 * 批量新增(租户信息表)
	 * @param propertyLesseeList
	 * @return
	 */
	@Override
	public int insertPropertyLesseeBatch(List<PropertyLessee> propertyLesseeList){
		return propertyLesseeBaseDao.insertPropertyLesseeBatch(propertyLesseeList);
	}
	/**
	 * 更新(租户信息表)信息
	 * @param propertyLessee
	 * @return
	 */
	@Override
	public int updatePropertyLessee(PropertyLessee propertyLessee){
		return propertyLesseeBaseDao.updatePropertyLessee(propertyLessee);
	}
	/**
	 * 批量更新(租户信息表)信息
	 * @param propertyLesseeList
	 * @return
	 */
	@Override
	public int updatePropertyLesseeBatch(List<PropertyLessee> propertyLesseeList){
		return propertyLesseeBaseDao.updatePropertyLesseeBatch(propertyLesseeList);
	}
	/**
	 * 根据序列号删除(租户信息表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deletePropertyLesseeLogic(java.math.BigInteger id){
		return propertyLesseeBaseDao.deletePropertyLesseeLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(租户信息表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deletePropertyLesseeLogicBatch(List<java.math.BigInteger> idList){
		return propertyLesseeBaseDao.deletePropertyLesseeLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(租户信息表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deletePropertyLessee(java.math.BigInteger id){
//		return propertyLesseeBaseDao.deletePropertyLessee(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(租户信息表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deletePropertyLesseeBatch(List<java.math.BigInteger> idList){
//		return propertyLesseeBaseDao.deletePropertyLesseeBatch(idList);
//	}
	
}
