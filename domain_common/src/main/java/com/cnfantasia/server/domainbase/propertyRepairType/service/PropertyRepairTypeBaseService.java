package com.cnfantasia.server.domainbase.propertyRepairType.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.propertyRepairType.dao.IPropertyRepairTypeBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.propertyRepairType.entity.PropertyRepairType;

/**
 * 描述:(物业报修类型) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class PropertyRepairTypeBaseService implements IPropertyRepairTypeBaseService{
	
	private IPropertyRepairTypeBaseDao propertyRepairTypeBaseDao;
	public void setPropertyRepairTypeBaseDao(IPropertyRepairTypeBaseDao propertyRepairTypeBaseDao) {
		this.propertyRepairTypeBaseDao = propertyRepairTypeBaseDao;
	}
	/**
	 * 根据条件查询(物业报修类型)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<PropertyRepairType> getPropertyRepairTypeByCondition(Map<String,Object> paramMap){
		return propertyRepairTypeBaseDao.selectPropertyRepairTypeByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(物业报修类型)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<PropertyRepairType> getPropertyRepairTypeByConditionDim(Map<String,Object> paramMap){
		return propertyRepairTypeBaseDao.selectPropertyRepairTypeByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(物业报修类型)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<PropertyRepairType> getPropertyRepairTypeByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return propertyRepairTypeBaseDao.selectPropertyRepairTypeByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(物业报修类型)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<PropertyRepairType> getPropertyRepairTypeByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return propertyRepairTypeBaseDao.selectPropertyRepairTypeByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(物业报修类型)信息
	 * @param id
	 * @return
	 */
	@Override
	public PropertyRepairType getPropertyRepairTypeBySeqId(java.math.BigInteger id){
		return propertyRepairTypeBaseDao.selectPropertyRepairTypeBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(物业报修类型)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getPropertyRepairTypeCount(Map<String,Object> paramMap){
		return propertyRepairTypeBaseDao.selectPropertyRepairTypeCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(物业报修类型)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getPropertyRepairTypeCountDim(Map<String,Object> paramMap){
		return propertyRepairTypeBaseDao.selectPropertyRepairTypeCount(paramMap,true);
	}
	/**
	 * 往(物业报修类型)新增一条记录
	 * @param propertyRepairType
	 * @return
	 */
	@Override
	public int insertPropertyRepairType(PropertyRepairType propertyRepairType){
		return propertyRepairTypeBaseDao.insertPropertyRepairType(propertyRepairType);
	}
	/**
	 * 批量新增(物业报修类型)
	 * @param propertyRepairTypeList
	 * @return
	 */
	@Override
	public int insertPropertyRepairTypeBatch(List<PropertyRepairType> propertyRepairTypeList){
		return propertyRepairTypeBaseDao.insertPropertyRepairTypeBatch(propertyRepairTypeList);
	}
	/**
	 * 更新(物业报修类型)信息
	 * @param propertyRepairType
	 * @return
	 */
	@Override
	public int updatePropertyRepairType(PropertyRepairType propertyRepairType){
		return propertyRepairTypeBaseDao.updatePropertyRepairType(propertyRepairType);
	}
	/**
	 * 批量更新(物业报修类型)信息
	 * @param propertyRepairTypeList
	 * @return
	 */
	@Override
	public int updatePropertyRepairTypeBatch(List<PropertyRepairType> propertyRepairTypeList){
		return propertyRepairTypeBaseDao.updatePropertyRepairTypeBatch(propertyRepairTypeList);
	}
	/**
	 * 根据序列号删除(物业报修类型)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deletePropertyRepairTypeLogic(java.math.BigInteger id){
		return propertyRepairTypeBaseDao.deletePropertyRepairTypeLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(物业报修类型)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deletePropertyRepairTypeLogicBatch(List<java.math.BigInteger> idList){
		return propertyRepairTypeBaseDao.deletePropertyRepairTypeLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(物业报修类型)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deletePropertyRepairType(java.math.BigInteger id){
//		return propertyRepairTypeBaseDao.deletePropertyRepairType(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(物业报修类型)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deletePropertyRepairTypeBatch(List<java.math.BigInteger> idList){
//		return propertyRepairTypeBaseDao.deletePropertyRepairTypeBatch(idList);
//	}
	
}
