package com.cnfantasia.server.domainbase.propertyRepairTypeBase.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.propertyRepairTypeBase.dao.IPropertyRepairTypeBaseBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.propertyRepairTypeBase.entity.PropertyRepairTypeBase;

/**
 * 描述:(物业报修类型(解放区预定义的)) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class PropertyRepairTypeBaseBaseService implements IPropertyRepairTypeBaseBaseService{
	
	private IPropertyRepairTypeBaseBaseDao propertyRepairTypeBaseBaseDao;
	public void setPropertyRepairTypeBaseBaseDao(IPropertyRepairTypeBaseBaseDao propertyRepairTypeBaseBaseDao) {
		this.propertyRepairTypeBaseBaseDao = propertyRepairTypeBaseBaseDao;
	}
	/**
	 * 根据条件查询(物业报修类型(解放区预定义的))信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<PropertyRepairTypeBase> getPropertyRepairTypeBaseByCondition(Map<String,Object> paramMap){
		return propertyRepairTypeBaseBaseDao.selectPropertyRepairTypeBaseByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(物业报修类型(解放区预定义的))信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<PropertyRepairTypeBase> getPropertyRepairTypeBaseByConditionDim(Map<String,Object> paramMap){
		return propertyRepairTypeBaseBaseDao.selectPropertyRepairTypeBaseByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(物业报修类型(解放区预定义的))信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<PropertyRepairTypeBase> getPropertyRepairTypeBaseByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return propertyRepairTypeBaseBaseDao.selectPropertyRepairTypeBaseByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(物业报修类型(解放区预定义的))信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<PropertyRepairTypeBase> getPropertyRepairTypeBaseByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return propertyRepairTypeBaseBaseDao.selectPropertyRepairTypeBaseByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(物业报修类型(解放区预定义的))信息
	 * @param id
	 * @return
	 */
	@Override
	public PropertyRepairTypeBase getPropertyRepairTypeBaseBySeqId(java.math.BigInteger id){
		return propertyRepairTypeBaseBaseDao.selectPropertyRepairTypeBaseBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(物业报修类型(解放区预定义的))记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getPropertyRepairTypeBaseCount(Map<String,Object> paramMap){
		return propertyRepairTypeBaseBaseDao.selectPropertyRepairTypeBaseCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(物业报修类型(解放区预定义的))记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getPropertyRepairTypeBaseCountDim(Map<String,Object> paramMap){
		return propertyRepairTypeBaseBaseDao.selectPropertyRepairTypeBaseCount(paramMap,true);
	}
	/**
	 * 往(物业报修类型(解放区预定义的))新增一条记录
	 * @param propertyRepairTypeBase
	 * @return
	 */
	@Override
	public int insertPropertyRepairTypeBase(PropertyRepairTypeBase propertyRepairTypeBase){
		return propertyRepairTypeBaseBaseDao.insertPropertyRepairTypeBase(propertyRepairTypeBase);
	}
	/**
	 * 批量新增(物业报修类型(解放区预定义的))
	 * @param propertyRepairTypeBaseList
	 * @return
	 */
	@Override
	public int insertPropertyRepairTypeBaseBatch(List<PropertyRepairTypeBase> propertyRepairTypeBaseList){
		return propertyRepairTypeBaseBaseDao.insertPropertyRepairTypeBaseBatch(propertyRepairTypeBaseList);
	}
	/**
	 * 更新(物业报修类型(解放区预定义的))信息
	 * @param propertyRepairTypeBase
	 * @return
	 */
	@Override
	public int updatePropertyRepairTypeBase(PropertyRepairTypeBase propertyRepairTypeBase){
		return propertyRepairTypeBaseBaseDao.updatePropertyRepairTypeBase(propertyRepairTypeBase);
	}
	/**
	 * 批量更新(物业报修类型(解放区预定义的))信息
	 * @param propertyRepairTypeBaseList
	 * @return
	 */
	@Override
	public int updatePropertyRepairTypeBaseBatch(List<PropertyRepairTypeBase> propertyRepairTypeBaseList){
		return propertyRepairTypeBaseBaseDao.updatePropertyRepairTypeBaseBatch(propertyRepairTypeBaseList);
	}
	/**
	 * 根据序列号删除(物业报修类型(解放区预定义的))信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deletePropertyRepairTypeBaseLogic(java.math.BigInteger id){
		return propertyRepairTypeBaseBaseDao.deletePropertyRepairTypeBaseLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(物业报修类型(解放区预定义的))信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deletePropertyRepairTypeBaseLogicBatch(List<java.math.BigInteger> idList){
		return propertyRepairTypeBaseBaseDao.deletePropertyRepairTypeBaseLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(物业报修类型(解放区预定义的))信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deletePropertyRepairTypeBase(java.math.BigInteger id){
//		return propertyRepairTypeBaseBaseDao.deletePropertyRepairTypeBase(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(物业报修类型(解放区预定义的))信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deletePropertyRepairTypeBaseBatch(List<java.math.BigInteger> idList){
//		return propertyRepairTypeBaseBaseDao.deletePropertyRepairTypeBaseBatch(idList);
//	}
	
}
